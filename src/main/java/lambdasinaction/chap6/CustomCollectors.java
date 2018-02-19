package lambdasinaction.chap6;

import static lambdasinaction.chap6.Dish.menu;

import java.util.ArrayList;
import java.util.List;

public class CustomCollectors
{
  public static void main( String[] args )
  {
    List<Dish> dishes = menu.stream().collect( ArrayList::new, List::add, List::addAll );
  }
}
