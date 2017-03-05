package stream.basicOperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base conveyor methods of stream API
 * <p>
 * Created by dkorolev on 1/2/2017.
 */
public class BasicConveyerMethods {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Bob", "Tom", "Mark", "Tom");

        System.out.println(stringList);

        //filter
        System.out.println(
                stringList
                        .stream()
                        .filter("Mark"::equals)
                        .count());

        System.out.println(
                stringList
                        .stream()
                        .filter("Mark"::equals)
                        .collect(Collectors.toList()));

        //skip - skipe N first elements
        System.out.println(
                stringList
                        .stream()
                        .skip(stringList.size() - 3)
                        .findFirst()
                        .orElse("-1"));

        //distinct - remove duplicated
        System.out.println(
                stringList
                        .stream()
                        .distinct()
                        .collect(Collectors.toList()));

        //map - transfer each element of the collection
        System.out.println(
                stringList
                        .stream()
                        .map(s -> s + "_1")
                        .collect(Collectors.toList()));

        //peek - returns the same stream with action to all elements of collection
        stringList
                .stream()
                .map(String::toUpperCase)
                .peek(e -> System.out.print(e + ","))
                .collect(Collectors.toList());

        System.out.println();

        //limit - reduce elements to some number
        System.out.println(
                stringList
                        .stream()
                        .limit(2)
                        .collect(Collectors.toList()));

        //sorted - sort collection
        System.out.println(
                stringList
                        .stream()
                        .sorted()
                        .collect(Collectors.toList()));

        //mapToXXX - transfer element to XXX type
        Stream<String> stringStream = Stream.of("1", "4", "3");

        int[] ints = stringStream
                .mapToInt(s -> Integer.parseInt(s) - 1)
                .toArray();

        for (int anInt : ints) {
            System.out.print(anInt + ", ");
        }
        System.out.println();

        //flatMap - change each elements in collection(as map()) and can split output into couple
        Stream<String> stringStream1 = Stream.of("65", "34", "355");

        System.out.println(stringList
                .stream()
//                .flatMap(s -> Arrays.asList(s.split("o")).stream())
                .flatMap(s -> Arrays.stream(s.split("o")))
                .collect(Collectors.toList()));

        long[] longs = stringStream1
//                .flatMapToLong(s -> Collections.singletonList(Long.parseLong(s)).stream().mapToLong(l -> l))
                .flatMapToLong(s -> Stream.of(Long.parseLong(s)).mapToLong(l -> l))
                .toArray();
        for (long aLong : longs) {
            System.out.print(aLong + ",");
        }
    }
}
