package stream.streamUsage;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream usage for distinct.
 */
public class StreamUsageDistinct {

    public static void main(String[] args) {

        List<String> ordered = Arrays.asList( "Bob", "Mike", "Tom", "Tom");
        Collection<String> unordered = new HashSet<>(ordered);

        //return collection with no duplicate from unordered stream
        System.out.println(ordered.stream().distinct().collect(Collectors.toList()));

        //the same from ordered stream
        System.out.println(unordered.stream().unordered().distinct().collect(Collectors.toList()));

    }
}
