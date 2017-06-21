package tutorial.Chapter_4_Functional_Programming;


import tutorial.Chapter_3_Migration_from_7_to_8.beans.Department;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Ex_1_FunctionalInterfaces {
    public static void main(String[] args) {
        Predicate<Integer> isPrime = number -> isEven(number);
        Predicate<Integer> isPrime2 = Ex_1_FunctionalInterfaces::isEven;

        Consumer<? super Integer> msgConsumer = msg -> System.out.println("Arg=" + msg);

        Function<String, Department> strangeConstructor = name -> new Department(name);

        Function<Integer, Integer> addOne = a -> a + 1;

        System.out.println(addOne.apply(1));

        Supplier<? extends Boolean> random = () -> ThreadLocalRandom.current().nextBoolean();

        System.out.println(random.get());

        Arrays.asList(1,2,3,4,5).stream().map(addOne).filter(isPrime).forEach(msgConsumer);


    }

    private static boolean isEven(Integer number) {
        if(number % 2 == 0) {
            return  true;
        }
        return false;
    }
}
