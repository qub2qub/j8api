package lambdasinaction.chap8.testing;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018-03-10
 */
public class Logging {
    
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
    
        numbers.stream()
            .map(x -> x + 17)
            .filter(x -> x % 2 == 0)
            .limit(3)
            .forEach(System.out::println);
    }
    
}
