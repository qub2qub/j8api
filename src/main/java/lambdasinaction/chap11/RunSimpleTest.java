package lambdasinaction.chap11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RunSimpleTest {
    
    public static void main(String[] args) {
        Shop2Exchange shop = new Shop2Exchange("BestShop");
        runSync(shop);
        System.out.println("______________________");
        runAsync(shop);
    }
    
    private static void runSync(Shop2Exchange shop) {
        long start = System.nanoTime();
        double price = shop.getPrice("product");
        System.out.printf("Price is %.2f%n", price);
        doSomethingElse();
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("SYNC: Price returned after " + retrievalTime + " msecs");
    }
    
    private static void runAsync(Shop2Exchange shop) {
        long start = System.nanoTime();
        // Query the shop to retrieve the price of a product.
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // Do some more tasks, like querying other shops
        doSomethingElse();
        // while the price of the product is being calculated
        try {
            // Read the price from the Future or block until it won’t be available.
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("AAASYNC: Price returned after " + retrievalTime + " msecs");
    }
    
    private static void doSomethingElse() {
        System.out.println("Doing something else...");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
