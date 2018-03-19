package lambdasinaction.chap11;

import static lambdasinaction.chap11.Util.SHOP_EXECUTOR;
import static lambdasinaction.chap11.Util.SHOPS_1;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinderDiscount {
    
    public List<String> findPricesSequential(String product) {
        return SHOPS_1.stream()
            // Retrieve the non-discounted price from each shop.
            .map(shop -> shop.getPrice(product))
            // Transform the Strings returned by the SHOPS_1 in Quote objects.
            .map(Quote::parse)
            // Contact the Discount service to apply the discount on each Quote.
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesParallel(String product) {
        return SHOPS_1.parallelStream()
            .map(shop -> shop.getPrice(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }
    
    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
            findPricesStream(product)
            .collect(Collectors.<CompletableFuture<String>>toList());
        
//        priceFutures.forEach(f -> f.join());
        
        // Wait for the completion of all asynchronous operations.
        // Wait for all the Futures in the stream to be completed and extract their respective results.
        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }
    
    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return SHOPS_1.stream()
            // Calculate each price asynchronously with a CompletableFuture
            // Asynchronously retrieve the non-discounted price from each shop.
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), SHOP_EXECUTOR))
            // Transform the String returned by a shop into a Quote object when it becomes available.
            .map(future -> future.thenApply(Quote::parse))
            // Compose the resulting Future with another asynchronous task, applying the discount code.
            .map(future -> future.thenApply(Discount::applyDiscount));
            
//            .map(future -> future.thenCompose(
//                quote -> CompletableFuture.supplyAsync(
//                    () -> Discount.applyDiscount(quote), SHOP_EXECUTOR)));
    }
    
    public void printPricesStreamFastestResponse(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream(product)
            .map(future -> future.thenAccept(s -> printFinTime(start, s)))
            .toArray(size -> new CompletableFuture[size]);
//        .toArray(CompletableFuture[]::new);
        
        CompletableFuture.allOf(futures).join();
        
        System.out.println("All SHOPS_1 have now responded in "
            + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }
    
    private void printFinTime(long start, String s) {
        System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)");
    }
    
}
