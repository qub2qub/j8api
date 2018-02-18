package lambdasinaction.chap5;

import static lambdasinaction.chap4.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lambdasinaction.chap4.Dish;

public class Reducing {
  
  public static void main(String... args) {
    
    List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    int sum = numbers.stream().reduce(0, (a, b) -> a + b);
    System.out.println("sum = " + sum);
    
    int sum2 = numbers.stream().reduce(0, Integer::sum);
    System.out.println("sum2 = " + sum2);
    
    int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
    System.out.println("max = " + max);
    
    Optional<Integer> min = numbers.stream().reduce(Integer::min);
//    min.ifPresent(System.out::println);
    System.out.println("min = " + min.orElse(-1));
    
    int calories = menu.stream()
      .map(Dish::getCalories)
      .reduce(0, Integer::sum);
    System.out.println("Number of calories: " + calories);
    
    /*How would you count the number of dishes in a stream using the map and reduce methods?*/
    int count = menu.stream()
      .map(d -> 1)
      .reduce(0, (a, b) -> a + b);
    System.out.println("count = " + count);
    long count2 = menu.stream().count();
    System.out.println("count2 = " + count2);
    System.out.println("count3 = " + menu.size());
  }
}
