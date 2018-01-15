package lambdasinaction.chap3;

import java.util.*;

import static java.util.Comparator.comparing;

public class SortingImprovements {

    public static void main(String...args){
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(80,"green"));
        inventory.add(new Apple(155, "green"));
        inventory.add(new Apple(120, "red"));
        inventory.add(new Apple(333, "yellow"));

        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(30, "green"));
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight()); 
        }});
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        inventory.sort((a1, a2) -> a2.getWeight().compareTo(a1.getWeight()));
        System.out.println(inventory);
        
        inventory.set(1, new Apple(10, "red"));
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);       
    }

    static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
}
