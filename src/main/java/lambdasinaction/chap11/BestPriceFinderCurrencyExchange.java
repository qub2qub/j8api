package lambdasinaction.chap11;

import static lambdasinaction.chap11.Util.RATE_EXECUTOR;
import static lambdasinaction.chap11.Util.SHOP_EXECUTOR;
import static lambdasinaction.chap11.Util.SHOPS_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lambdasinaction.chap11.ExchangeService.Money;

public class BestPriceFinderCurrencyExchange {
    
    private String createString(Shop2Exchange shop, String product) {
        return shop.getName() + "::" + shop.getPrice(product);
    }
    
    public List<String> findPricesSequential(String product) {
        return SHOPS_2.stream()
            .map(shop -> createString(shop, product))
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesParallel(String product) {
        return SHOPS_2.parallelStream()
            .map(shop -> createString(shop, product))
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = SHOPS_2.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                () -> createString(shop, product), SHOP_EXECUTOR))
            .collect(Collectors.toList());
        
        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService j7executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop2Exchange shop : SHOPS_2) {
            final Future<Double> futureRate = j7executor.submit(new Callable<Double>() {
                public Double call() {
                    return ExchangeService.getRate(Money.EUR, Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = j7executor.submit(new Callable<Double>() {
                @Override
                public String toString() {
                    return "Future--" + shop.getName();
                }
                
                public Double call() {
                    try {
                        double priceInEUR = shop.getPrice(product);
                        return (double) Math.round(priceInEUR * futureRate.get() * 100) / 100;
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(/*shop.getName() +*/ /*priceFuture.toString() +*/ " price is " + priceFuture.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }
    
    //***************************************************************************************************************
    private static final BiFunction<Double, Double, Double> applyExchangeRateBiFunction =
        (price, rate) -> (double) Math.round(price * rate * 100) / 100;
    
    public List<String> findPricesInUSD(String product) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>(SHOPS_2.size());
        for (Shop2Exchange shop : SHOPS_2) {
            CompletableFuture<Double> futurePriceInUSD =
                // Create a first task querying the shop to obtain the price of a product.
                CompletableFuture.supplyAsync(() -> shop.getPrice(product), RATE_EXECUTOR)
                    .thenCombine( // here no need for thenCombineAsync()
                        // Create a second independent task to retrieve the conversion rate between USD and EUR.
                        CompletableFuture.supplyAsync(
                            () -> ExchangeService.getRate(Money.EUR, Money.USD), RATE_EXECUTOR),
                        // apply combine bi-function: Combine the price and exchange rate by multiplying them.
                        applyExchangeRateBiFunction
                    );
            priceFutures.add(futurePriceInUSD);
        }
        // Drawback: The shop is not accessible anymore outside the loop,
        // so the getName() call below has been commented out.
        return priceFutures.stream()
            .map(CompletableFuture::join)
            .map(price -> /*shop.getName() +*/ " price is " + price)
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        for (Shop2Exchange shop : SHOPS_2) {
            // Here, an extra operation has been added so that the shop name is retrieved within the loop.
            // As a result, we now deal with CompletableFuture<String> instances.
            CompletableFuture<String> futurePriceInUSD =
                CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                    .thenCombine(
                        CompletableFuture.supplyAsync(
                            () -> ExchangeService.getRate(Money.EUR, Money.USD)),
                        applyExchangeRateBiFunction
                    ).thenApply(price -> shop.getName() + " price is " + price);
            priceFutures.add(futurePriceInUSD);
        }
        
        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesInUSD3(String product) {
        // Here, the for loop has been replaced by a mapping function...
        Stream<CompletableFuture<String>> priceFuturesStream = SHOPS_2.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                .thenCombine(
                    CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                    applyExchangeRateBiFunction)
                .thenApply(price -> shop.getName() + " price is " + price));
        // However, we should gather the CompletableFutures into a List
        // so that the asynchronous operations are triggered before being "joined."
        List<CompletableFuture<String>> priceFutures = priceFuturesStream.collect(Collectors.toList());
        
        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesInUSD4(String product) {
        return SHOPS_2.stream().map(
                shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), RATE_EXECUTOR)
                .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD), RATE_EXECUTOR), applyExchangeRateBiFunction)
                .thenApply(price -> shop.getName() + " price is " + price))
            .collect(Collectors.toList()) // launch executing
            .stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    
}
