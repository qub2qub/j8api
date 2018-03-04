package lambdasinaction.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class ParallelStreamsHarness {
  
  // SINGLETON
  public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();
  
  public static void main(String[] args) {
    for (int i = 0; i < 3; i++) {
      System.out.println("(1) Iterative Sum: __" + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
      System.out.println("(2) Sequential Sum (iterate.limit): __" + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
      System.out.println("(3) Parallel Sum (iterate.limit.parallel): __" + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs");
      System.out.println("(4) Range Sum (rangeClosed): __" + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
      System.out.println("(5) Parallel range Sum (rangeClosed.parallel): __" + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs");
      System.out.println("(6) ForkJoin sum: __" + measurePerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs");
      System.out.println("(7) no SideEffect sum (1thread)(mutate shared state): __" + measurePerf(ParallelStreams::sideEffectSum, 10_000_000L) + " msecs");
      System.out.println("(8) yes SideEffect prallel sum (mutate shared state): __" + measurePerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs");
      System.out.println("************************************************************");
    }
  }
  
  public static <T, R> long measurePerf(Function<T, R> f, T input) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      R result = f.apply(input);
      long duration = (System.nanoTime() - start) / 1_000_000;
//      System.out.print("||" + result);
      if (duration < fastest) {
        fastest = duration;
      }
    }
//    System.out.println();
    return fastest;
  }
}
