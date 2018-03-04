package lambdasinaction.chap7;

import static lambdasinaction.chap7.ParallelStreamsHarness.FORK_JOIN_POOL;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
  
  //The size of the array under which this task is no longer split into subtasks.
//  private static final long THRESHOLD = 10_000;
//  private static final AtomicInteger FORKS_COUNT = new AtomicInteger(0);
  private long threshold = 10_000;
  private static final int CPUs = Runtime.getRuntime().availableProcessors();
  
  private final long[] numbers;
  //The initial and final positions of the portion of the array processed by this subtask.
  private final int start;
  private final int end;
  
  //Public constructor used to create the main task.
  public ForkJoinSumCalculator(long[] numbers) {
    this(numbers, 0, numbers.length, (numbers.length / CPUs)/2);
  }
  //Private constructor used to recursively create subtasks of the main task.
  private ForkJoinSumCalculator(long[] numbers, int start, int end, long threshold) {
//    System.out.println(FORKS_COUNT.incrementAndGet() + "_" + threshold);
    this.numbers = numbers;
    this.start = start;
    this.end = end;
    this.threshold = threshold;
  }
  
  @Override
  protected Long compute() {
    //The size of the portion of the array summed by this task.
    int length = end - start;
    if (length <= threshold) {
      return computeSequentially();
    }
    ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2, threshold);
    // Asynchronously execute the newly created subtask using another thread of the ForkJoinPool.
    leftTask.fork();
    ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end, threshold);
    //Execute this second subtask synchronously, potentially allowing further recursive splits.
    Long rightResult = rightTask.compute();
    //Read the result of the first subtask or wait for it if it isn’t ready.
    Long leftResult = leftTask.join();
    //The result of this task is the combination of the results of the two subtasks.
    return leftResult + rightResult;
  }
  
  private long computeSequentially() {
    long sum = 0;
    for (int i = start; i < end; i++) {
      sum += numbers[i];
    }
    return sum;
  }
  
  public static long forkJoinSum(long n) {
    long[] numbers = LongStream.rangeClosed(1, n).toArray();
    ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
//    return 1;
    return FORK_JOIN_POOL.invoke(task);
  }
  
  public long getVal() {
    return compute();
  }
  
  public static void main(String[] args) throws InterruptedException {
    /*long[] numbers = LongStream.rangeClosed(1, 10_000).toArray();
    ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
//    ForkJoinTask<Long> fork = task.fork();
    System.out.println("task = " + task.getVal());
//    System.out.println("fork = " + fork);*/
  
    System.out.println(ForkJoinSumCalculator.forkJoinSum(10_000_000));
    System.out.println(FORK_JOIN_POOL.toString());
    Thread.sleep(5000);
    System.out.println(FORK_JOIN_POOL.toString());
    Thread.sleep(5000);
    System.out.println(FORK_JOIN_POOL.toString());
    Thread.sleep(5000);
    System.out.println(FORK_JOIN_POOL.toString());
  }
  
}