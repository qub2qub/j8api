package lambdasinaction.chap11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created on 2018-03-17
 */
public class FeatureExample {
    
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(FeatureExample::doSomeLongComputation);
        
        doSomethingElse();
        
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            // the computation threw an exception
        } catch (InterruptedException ie) {
            // the current thread was interrupted while waiting
        } catch (TimeoutException te) {
            // the timeout expired before the Future completion
        }
    }
    
    private static void doSomethingElse() {
    
    }
    
    private static Double doSomeLongComputation() {
        return null;
    }
    
}
