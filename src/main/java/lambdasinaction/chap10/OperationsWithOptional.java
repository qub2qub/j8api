package lambdasinaction.chap10;

import java.util.*;

public class OperationsWithOptional {

    public static void main(String... args) {
        System.out.println(max(Optional.of(3), Optional.of(5)));
        System.out.println(max(Optional.empty(), Optional.of(5)));

        Optional<Integer> opt1 = Optional.of(5);
//        Optional<Integer> opt2 = opt1.or(() -> Optional.of(4));
//        System.out.println(Optional.of(5).or(() -> Optional.of(4)));
    }

    public static final Optional<Integer> max(Optional<Integer> int1, Optional<Integer> int2) {
        return int1.flatMap(a -> int2.map(b -> Math.max(a, b)));
    }
}
