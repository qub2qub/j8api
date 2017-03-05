package stream.streamUsage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Usage of map, mapToInt, FlatMap, FlatMapToInt.
 *
 * Created by dkorolev on 1/4/2017.
 */
public class StreamUsageMap {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a1", "a2", "a3", "a1");

        List<String> intStringList = Arrays.asList("1,2,0", "4,5");


        //add "_1" to each element of stringList
        System.out.println(stringList.stream()
                .map(s->s.concat("_1")).collect(Collectors.toList()));

        //in stringList remove 1 symbol and return array of int[]
        int[] ints = stringList.stream().map(s -> s.substring(1))
                .mapToInt(Integer::parseInt).toArray();
        int[] ints1 = stringList.stream().mapToInt(s -> Integer.parseInt(s.substring(1))).toArray();
        for (int anInt : ints1) {
            System.out.print(anInt+" ");
        }
        System.out.println();

        //from intStringList get all elements with "," separator
                                                    //divide each elements into couple in new list
        System.out.println(intStringList.stream().flatMap(i -> Arrays.asList(i.split(",")).stream())
                .collect(Collectors.toList()));


        //from intStringList get sum of all numbers after "," separator
        System.out.println(intStringList.stream()
                .flatMapToInt(i -> Arrays.asList(i.split(",")).stream().mapToInt(s -> Integer.parseInt(s)))
                .sum());

    }
}
