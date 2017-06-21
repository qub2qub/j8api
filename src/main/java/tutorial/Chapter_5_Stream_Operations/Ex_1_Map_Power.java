package tutorial.Chapter_5_Stream_Operations;

import java.util.Arrays;
import java.util.List;

/**
 *  29-May-17.
 */
public class Ex_1_Map_Power {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, -1, 2, -2, 3, -3);

        // Step 1: Transformation
        ints.stream().map(e -> Math.abs(e)).forEach(System.out::println);

        // Step 2: Method reference usage
        ints.stream().map(Math::abs).forEach(System.out::println);

        // Step 3: Specialized map
        System.out.println(ints.stream().mapToInt(Math::abs).max().getAsInt());

        // Step 4: More interesting features
        System.out.println(ints.stream().mapToDouble(Math::cos).summaryStatistics());

    }
}
