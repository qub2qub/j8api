package lambdasinaction.chap6;

import java.util.function.*;

public class CollectorHarness {

    public static void main(String[] args) {
        
        execute("partitionPrimes:", PrimeNumbersPartitioning::partitionPrimes);
        
        execute("partitionPrimesWithInlineCollector:", PrimeNumbersPartitioning::partitionPrimesWithInlineCollector);
        
        execute("partitionPrimesWithCustomCollector:", PrimeNumbersPartitioning::partitionPrimesWithCustomCollector);
    }

    private static long execute(String str, Consumer<Integer> primePartitioner) {
        System.out.println(str);
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
            System.out.print(".");
        }
        System.out.println(
          "Fastest execution done in " + fastest + " msecs");
        return fastest;
    }
}
