package tutorial.Chapter_4_Functional_Programming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Overview of function composition in Java 8
 */
public class Ex_3_Composition_in_Java {
    public static void main(String[] args) {
        Function<Integer, String> join2str = a -> a + ", ";
        Function<Integer, Integer> addOne = e -> e + 1;
        Function<Integer, Integer> multiplyTen = e -> e * 10;

        List<Integer> fibonacciNumbers = Arrays.asList(1,1,2,3,5,8,13,21);
        // Step 1
        fibonacciNumbers.stream().map(e -> e + 1).map(join2str).forEach(System.out::print);
        System.out.println("\n");
        // Step 2: Extract the function
        fibonacciNumbers.stream().map(addOne).map(join2str).forEach(System.out::print);
        System.out.println("\n************** addOne.andThen(multiplyTen) *************");
        // Step 3: Pipe two functions in direct order

        fibonacciNumbers.stream().map(addOne.andThen(multiplyTen))
            .map(join2str).forEach(System.out::print);
        System.out.println("\n************** addOne.compose(multiplyTen) *************");
        // другой порядок: сначала multiplyTen а потом addOne
        fibonacciNumbers.stream().map(addOne.compose(multiplyTen))
            .forEach(e -> System.out.print(e+", "));
        System.out.println("\n************** addOne.compose(e-> e*10) *************");
        // Step 4: Pipe two functions in opposite order
        fibonacciNumbers.stream().map(addOne.compose(e-> e*10)).forEach(System.out::print);
        System.out.println("\n************** mycompose(addOne, multiplyTen): *************");
        // Step 5: Use custom compose
        fibonacciNumbers.stream().map(mycompose(addOne, multiplyTen)).map(join2str).forEach(System.out::print);
    }

    public static <T> Function<T, T> mycompose(Function<T,T> first, Function<T,T> second) {
        return x -> second.apply(first.apply(x));
    }
}
