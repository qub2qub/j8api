package lambdasinaction.chap5;

import static java.util.stream.Collectors.joining;
import static lambdasinaction.chap4.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lambdasinaction.chap4.Dish;

public class NumericStreams {
  
  public static void main(String... args) {
    
    List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    Arrays.stream(numbers.toArray()).sorted().forEach(System.out::print);
    System.out.println("\n-----------------------");
    
    int calories = menu.stream()
      .mapToInt(Dish::getCalories)
      .sum();
    System.out.println("Number of calories:" + calories);
    
    // max and OptionalInt
    OptionalInt maxCalories = menu.stream()
      .mapToInt(Dish::getCalories)
      .max();
//    maxCalories.getAsInt()
    System.out.println("maxCalories="+maxCalories.orElse(-1));
  
    OptionalInt max = IntStream.empty().max();
    System.out.println("max = " + max); // OptionalInt.empty
    System.out.println("max = " + max.orElse(-1));
    
    // numeric ranges
    IntStream evenNumbers = IntStream.rangeClosed(1, 100)
      .filter(n -> n % 4 == 0)
      .skip(4)
      .limit(5);
    System.out.println(
      evenNumbers.boxed().map(Object::toString).collect(joining(","))
    );
//    evenNumbers.boxed().forEach(n -> System.out.print(n + ","));
    //stream has already been operated upon or closed:
//    System.out.println("\nevenNumbers="+evenNumbers.count());
  
    /*Stream<int[]> pythagoreanTriples =
      IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
        //  test whether the square root of (a^2 + b^2) is an integer number
          .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
          .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
    pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));*/

      IntStream.rangeClosed(1, 100).boxed()
        .flatMap( a -> IntStream.rangeClosed(a, 100)
              .mapToObj(b -> new double[] {a, b, Math.sqrt(a * a + b * b)})
              .filter(sides -> sides[2] % 1 == 0))
        .limit(5)
        .forEach(arr -> System.out.println(Arrays.toString(arr)));
    
    /*What’s the flatMap about? First, you create a numeric range from 1 to 100 to generate values for a.
    For each given value of a you’re creating a stream of triples. !!!Mapping a value of a to a stream of triples
    would result in a stream of streams!!! The flatMap method does the mapping and also flattens
    all the generated streams of triples into a single stream. As a result you produce a stream of triples.
    Note also that you change the range of b to be a to 100. There’s no need to start the range at the value 1
    because this would create duplicate triples (for example, (3, 4, 5) and (4, 3, 5)).*/
  
  }
  
  public static boolean isPerfectSquare(int n) {
    return Math.sqrt(n) % 1 == 0;
  }
  
}
