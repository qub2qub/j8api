package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import static lambdasinaction.chap4.Dish.menu;

public class StreamBasic {
  
  public static void main(String... args) {
    int i = 0;
    int j = 0;
    System.out.println(i++);
    System.out.println(++j);
    
    // Java 7
    getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
    System.out.println("----------");
    // Java 8
    getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    System.out.println("----------");
    threeHighCaloricDishNames();
    
  }
  
  private static long getFibonacciSum(long n) {
    long a = 0, b = 1, c = 0, sum = 0;
    
    while (c < n) {
      sum += b;
//      c = a + b;
      a = b;
//      b = c;
      b = a + b;
    }
    sum += c;
    return sum;
  }
  
  private static void threeHighCaloricDishNames() {
    List<String> threeHighCaloricDishNames =
      menu.stream()
        .filter(d -> d.getCalories() > 300)
//        .sorted((d1,d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
        .sorted(Comparator.comparingInt(Dish::getCalories))
        .map(Dish::getName)
        .limit(3)
        .collect(toList());
    System.out.println(threeHighCaloricDishNames);
  }
  
  public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
    List<Dish> lowCaloricDishes = new ArrayList<>();
    for (Dish d : dishes) {
      if (d.getCalories() < 400) {
        lowCaloricDishes.add(d);
      }
    }
    List<String> lowCaloricDishesName = new ArrayList<>();
    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
      public int compare(Dish d1, Dish d2) {
        return Integer.compare(d1.getCalories(), d2.getCalories());
      }
    });
    for (Dish d : lowCaloricDishes) {
      lowCaloricDishesName.add(d.getName());
    }
    return lowCaloricDishesName;
  }
  
  public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
    return dishes.stream()
      .filter(d -> d.getCalories() < 400)
      .sorted(comparing(Dish::getCalories))
      .map(Dish::getName)
      .collect(toList());
  }
}
