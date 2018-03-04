package lambdasinaction.chap6;

import static lambdasinaction.chap6.Dish.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CustomCollectors
{
  public static void main( String[] args )
  {
    List<Dish> dishes = menu.stream().collect( ArrayList::new, List::add, List::addAll );
    List<Integer> collect = IntStream.rangeClosed(1, 10_000).boxed().parallel().collect(new ToListCollector<>());
    System.out.println("collect = " + collect);
    System.out.println(collect.size());
  }
}
