package lambdasinaction.chap11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created on 2018-03-17
 */
public class FeatureExample {
    
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(FeatureExample::doSomeLongComputation);
//        FutureTask ft; ft.
        doSomethingElse();
        
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
//            future.
        } catch (ExecutionException ee) {
            // the computation threw an exception
            System.out.println("ee = " + ee);
        } catch (InterruptedException ie) {
            // the current thread was interrupted while waiting
            System.out.println("ie = " + ie);
        } catch (TimeoutException te) {
            // the timeout expired before the Future completion
            System.out.println("te = " + te);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            executor.shutdownNow();
        }
    }
    
    private static void doSomethingElse() {
    
    }
    
    private static Double doSomeLongComputation() {
        throw new RuntimeException("my exc");
//        return 1.0;
    }
    
}
