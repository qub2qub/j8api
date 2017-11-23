package tutorial.tempo;

import java.util.Arrays;

/**
 * Created on 2017-11-21
 */
public class VarargPollution<T>
{
  private T[] toArr(T... arr) {
    return arr;
  }
  private T[] arr2(T t1, T t2) {
    return toArr(t1, t2);
  }

  public static void main(String[] args) {
    VarargPollution<Integer> varargPollution = new VarargPollution<>();
    Integer[] integers = varargPollution.toArr(1, 2);
    System.out.println(Arrays.toString(integers));
    Integer[] nums = varargPollution.arr2( 3, 4 );
    System.out.println( Arrays.toString( nums ) );
  }
}
