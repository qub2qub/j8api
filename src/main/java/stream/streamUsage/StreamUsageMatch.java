package stream.streamUsage;

import java.util.Arrays;
import java.util.List;

/**
 * examples with boolean anyMatch, allMatch and noneMatch
 *
 * Created by dkorolev on 1/4/2017.
 */
public class StreamUsageMatch {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Tom", "Bob", "Mike", "Tom");

        //check if at least 1 element "Tom"
        System.out.println(stringList.stream().anyMatch("Tom"::equals));

        //check "Greg"
        System.out.println(stringList.stream().anyMatch("Greg"::equals));

        //symbol "o" contains in all elements?
        System.out.println(stringList.stream().allMatch(s->s.contains("o")));

        //no elements "Greg"
        System.out.println(stringList.stream().noneMatch("Greg"::equals));
    }
}
