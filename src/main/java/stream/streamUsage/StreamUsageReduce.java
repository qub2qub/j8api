package stream.streamUsage;

import java.util.Arrays;
import java.util.List;

/**
 * Usage of Reduce.
 * Aggregation functions on collection
 * reducing to one value.
 *
 * Created by dkorolev on 1/5/2017.
 */
public class StreamUsageReduce {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 2);

        //get sum of numbers or return 0
        System.out.println(integerList.stream().reduce((i1,i2)-> i1+i2).orElse(0));

        //get max or -1
        System.out.println(integerList.stream().reduce((i1,i2)->Integer.max(i1,i2)).orElse(-1));

        //get sum of all od numbers or 0
        System.out.println(integerList.stream()
                .filter(i-> i%2 != 0)
                .reduce((i1,i2)-> i1+i2).orElse(0));
    }
}
