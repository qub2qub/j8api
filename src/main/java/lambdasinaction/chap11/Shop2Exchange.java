package lambdasinaction.chap11;

import static lambdasinaction.chap11.Util.delay;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop2Exchange {
    
    private final String name;
    private final Random random;
    
    public Shop2Exchange(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }
    
    public double getPrice(String product) {
        return calculatePrice(product);
    }
    
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        // Execute the computation asynchronously in a different Thread.
        new Thread(() -> {
            double price = calculatePrice(product);
            // Set the value returned by the long computation on the Future when it becomes available.
            futurePrice.complete(price);
        }).start();
        // Return the Future without waiting for the computation of the result it contains to be completed.
        return futurePrice;
    }
    
    public String getName() {
        return name;
    }
    
}
