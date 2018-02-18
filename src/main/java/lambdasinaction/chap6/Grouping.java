package lambdasinaction.chap6;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static lambdasinaction.chap6.Dish.menu;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lambdasinaction.chap6.Dish.Type;

public class Grouping {
  
  enum CaloricLevel {DIET, NORMAL, FAT};
  
  public static void main(String... args) {
    System.out.println("Dishes grouped by type: "
      + menu.stream().collect(groupingBy(Dish::getType)));
    System.out.println("Dish names grouped by type: "
      + menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList()))));
    System.out.println("Caloric dishes grouped by type: "
      + menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType)));
    
    System.out.println("-------------------------------------");
    System.out.println("groupingBy 1: Dishes grouped by caloric level:\n"
      + menu.stream().collect(groupingBy(Grouping::checkCaloricLevel)));
    
    Map<Type, Map<CaloricLevel, List<Dish>>> twoGroups =
      menu.stream().collect(groupingBy(Dish::getType, groupingBy(Grouping::checkCaloricLevel)));
    System.out.println("groupingBy 2: Dishes grouped by type and caloric level:\n" + twoGroups);
    System.out.println("groupingBy 3:" + menu.stream().collect(groupingBy(Dish::getType, groupingBy(Grouping::checkCaloricLevel, counting()))));
    System.out.println("-------------------------------------");
    System.out.println("Count dishes in groups: " + menu.stream().collect(groupingBy(Dish::getType, counting())));
    System.out.println("-------------------------------------");
  
//    System.out.println("1 Most caloric dishes by type: " + menu.stream().collect(
//          groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories)))));
    
    System.out.println("1 Most caloric dishes by type: " + menu.stream().collect(
      groupingBy(Dish::getType,
        reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)))); // should return dish
  
    System.out.println("2 Most caloric dishes by type: " + menu.stream().collect(
      groupingBy(Dish::getType, collectingAndThen(
        reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
        Optional::get)))); // Transformation function
    
    System.out.println("3 Most caloric dishes by type: " + menu.stream().collect(
      toMap(Dish::getType, Function.identity(), (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    System.out.println("-------------------------------------");
    
    System.out.println("Sum calories by type: "
      + menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories))));
    
    System.out.println("MAPPING Function 1 toList: Caloric levels by type:\n" + menu.stream().collect(
      groupingBy(Dish::getType, mapping( Grouping::checkCaloricLevel, toList()))));
    System.out.println("MAPPING Function 2 toSet: Caloric levels by type:\n" + menu.stream().collect(
      groupingBy(Dish::getType, mapping( Grouping::checkCaloricLevel, toSet()))));
    System.out.println("MAPPING Function 3 toCollection: Caloric levels by type:\n" + menu.stream().collect(
      groupingBy(Dish::getType, mapping( Grouping::checkCaloricLevel, toCollection(HashSet::new)))));
  }
  
  private static CaloricLevel checkCaloricLevel(Dish dish) {
    if (dish.getCalories() <= 400) {
      return CaloricLevel.DIET;
    } else if (dish.getCalories() <= 700) {
      return CaloricLevel.NORMAL;
    } else {
      return CaloricLevel.FAT;
    }
  }
  
}
