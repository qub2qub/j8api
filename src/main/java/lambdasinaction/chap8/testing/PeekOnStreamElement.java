package lambdasinaction.chap8.testing;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;


public class PeekOnStreamElement {
    
    public static void main(String[] args) {
        
        List<Integer> result = Stream.of(2, 3, 4, 5)
            .peek(x -> System.out.println("1 taking from stream: " + x))
            .map(x -> x + 17)
            .peek(x -> System.out.println("2 after map: " + x))
            .filter(x -> x % 2 == 0)
            .peek(x -> System.out.println("3 after filter: " + x))
            .limit(3)
            .peek(x -> System.out.println("4 after limit: " + x))
            .collect(toList());
    }
}
