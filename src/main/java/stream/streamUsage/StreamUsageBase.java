package stream.streamUsage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream usage for:
 * filter, findFirst, findAny, skip, limit and count.
 *
 * Created by dkorolev on 1/4/2017.
 */
public class StreamUsageBase {
    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("Tom", "Bob", "Mike", "Tom");
        List<Integer> integerList = Arrays.asList(1, 3, 4, 5, 2, 1, 4);

        //counting "Tom"
        System.out.println(stringList.stream().filter(s->s.equals("Tom")).count());

        //return 1st element or 0
        System.out.println(stringList.stream().findFirst().orElse("0"));

        //return last element
        System.out.println(stringList.stream().skip(stringList.size()-1).findFirst().orElse("Empty"));

        //return "Mike" or throw exception
        System.out.println(stringList.stream().filter("Mike"::equals).findFirst().get());

        //return 3rd element
        System.out.println(stringList.stream().skip(2).findFirst().get());

        //return 2 elements starting from 2nd
        System.out.println(stringList.stream().skip(1).limit(2).collect(Collectors.toList()));

        //return all elements according to template "o"
        System.out.println(stringList.stream().filter(s->s.contains("o")).collect(Collectors.toList()));
    }
}
