package lambdasinaction.chap3;

import java.util.*;

public class Lambdas {
	public static void main(String ...args){
		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple(80,"green"));
		inventory.add(new Apple(155, "green"));
		inventory.add(new Apple(120, "red"));
		inventory.add(new Apple(99, "yellow"));

		// Simple example
		Runnable r = () -> System.out.println("Hello!");
		r.run();
		System.out.println(filter(inventory, (Apple a) -> "green".equals(a.getColor())));
		Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		inventory.sort(c);
		System.out.println(inventory);
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}
	
	interface ApplePredicate{
		public boolean test(Apple a);
	}
}