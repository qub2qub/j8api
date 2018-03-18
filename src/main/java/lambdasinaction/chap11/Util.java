package lambdasinaction.chap11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Util {
    
    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    
    
    public static final List<Shop2Exchange> SHOPS_2 = Arrays.asList(
        new Shop2Exchange("BestPrice"),
        new Shop2Exchange("LetsSaveBig"),
        new Shop2Exchange("MyFavoriteShop"),
        new Shop2Exchange("BuyItAll"),
        new Shop2Exchange("ShopEasy"),
        new Shop2Exchange("FifthShop"),
        new Shop2Exchange("AAA"),
        new Shop2Exchange("BBB"),
        new Shop2Exchange("CCC"),
        new Shop2Exchange("DDD")
    );
    
    public static final List<Shop1Discount> SHOPS_1 = Arrays.asList(
        new Shop1Discount("1_LetsSaveBig"),
        new Shop1Discount("2_MyFavoriteShop"),
        new Shop1Discount("3_BuyItAll"),
        new Shop1Discount("4_FifthShop"),
        new Shop1Discount("5_AAA"),
        new Shop1Discount("6_BBB"),
        new Shop1Discount("7_CCC"),
        new Shop1Discount("8_DDD"),
        new Shop1Discount("9_BestPrice"),
        new Shop1Discount("10_ShopEasy"));
    
    // Create a thread pool with a number of threads equal to the
    // minimum between 100 and the number of SHOPS_1.
    public static final Executor SHOP_EXECUTOR = Executors.newFixedThreadPool(
        Math.max(SHOPS_1.size(), SHOPS_2.size()), r -> {
            Thread t = new Thread(r);
            // Use daemon threads—they don’t prevent the termination of the program.
            t.setDaemon(true);
            return t;
        });
    
    public static final Executor RATE_EXECUTOR = Executors.newFixedThreadPool(
        SHOPS_2.size()*2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    
    public static void delay() {
        int delay = 1000;
//        int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }
    
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
/*
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
*/
        return CompletableFuture.supplyAsync(() -> futures.stream().
            map(future -> future.join()).
            collect(Collectors.<T>toList()));
    }
    
    public static void execute(String msg, Supplier<List<String>> s) {
        System.out.println(msg.toUpperCase() + " --->");
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(" done in " + duration + " msecs\n__________________");
    }
}
