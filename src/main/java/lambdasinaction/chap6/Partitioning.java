package lambdasinaction.chap6;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap6.Dish.menu;

import java.util.Optional;

public class Partitioning {
  
  public static void main(String... args) {
    System.out.println("Dishes partitioned by vegetarian:\n" + menu.stream().collect(
      partitioningBy(Dish::isVegetarian)));
    System.out.println("Vegetarian Dishes:\n" +menu.stream().filter(
      Dish::isVegetarian).collect(toList()));
    
    System.out.println("Vegetarian Dishes by type:\n" + menu.stream().collect(
      partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))));
    
    System.out.println("Most caloric dishes by vegetarian:\n" + menu.stream().collect(
      partitioningBy(Dish::isVegetarian, collectingAndThen(
        maxBy(comparingInt(Dish::getCalories)), Optional::get))));
  
    System.out.println("Vegetarian&Calories more than 500:\n" + menu.stream().collect(
      partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500))));
    System.out.println("Count vegetarian dishes:\n" +menu.stream().collect(
      partitioningBy(Dish::isVegetarian, counting())));
  
    // This won’t compile because partitioningBy() requires a predicate, a function returning a boolean.
    // Dish::isVegetarian - возвращает булеан
    // And the method reference Dish::getType can’t be used as a predicate.
//    menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType)));
  }
}

