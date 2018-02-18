package lambdasinaction.chap5;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created on 2018-02-10
 */
public class Quiz {
  
  public static void main(String[] args) {
  		/*1. Given a list of numbers, how would you return
  		a list of the square of each number?
  		For example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25]*/
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squares =
      numbers.stream()
        .map(n -> n * n)
        .collect(toList());
    System.out.println("squares = " + squares);
    
    /*2. Given two lists of numbers, how would you return all pairs of numbers?
    For example, given a list [1, 2, 3] and a list [3, 4]
    you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
    For simplicity, you can represent a pair as an array with two elements.*/
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(4, 5);
    /*You could use two maps to iterate on the two lists and generate the pairs.
    But this would return a Stream<Stream<Integer[]>>. What you need to do is flatten
    the generated streams to result in a Stream<Integer[]>. This is what flatMap is for*/
    List<Stream<int[]>> collect = numbers1.stream()
      .map(i -> numbers2.stream().map(j -> new int[]{i, j}))
      .collect(toList());
    System.out.println("collect = " + collect);
    
    List<int[]> collect2 = numbers1.stream()
      .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
      .collect(toList());
    System.out.println("collect2 = " + collect2);
    
    numbers1.stream()
      .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
      .map(Arrays::toString)
      .forEach(System.out::print);
    System.out.println("\n---------------");
    
    /*3. How would you extend the previous example to return only pairs
    whose sum is divisible by 3? For example, (2, 4) and (3, 3) are valid.*/
    numbers1.stream()
      .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
      .map(Arrays::toString)
      .forEach(System.out::print);
    System.out.println("\n---------------");
  }
}
