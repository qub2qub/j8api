package tutorial.Chapter_2_Simple_Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrap to Double
 * Listen to types
 */
public class Ex_5_Solution_Sorted_Stream {
    public static void main(String[] args) {
      List<Double> list = Stream.of(
          new Double(2), new Double(3), 4.2, new Double(13), new Double(-1), new Double(3), 8.8)
          .filter(e -> e > 100)
          //                .forEach(System.out::println)
          .collect(Collectors.toList());

      System.out.println("list = " + list.size());
    }
}
