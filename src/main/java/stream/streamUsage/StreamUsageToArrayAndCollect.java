package stream.streamUsage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Usage of toArray and Collect.
 * And it's aggregate methods.
 *
 * Created by dkorolev on 1/5/2017.
 */
public class StreamUsageToArrayAndCollect {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);
        List<String> stringList = Arrays.asList("a1", "b2", "c3", "a1");

        //sum of odd numbers
        System.out.println(integerList.stream().filter(o->o%2 !=0)
                .collect(Collectors.summingInt(o->o)));
        System.out.println(integerList.stream()
                .collect(Collectors.summingInt(i-> i%2 !=0 ? i : 0)));

        //deduct -1 from each elements and get average
        System.out.println(integerList.stream()
                .map(i->i-1).collect(Collectors.averagingInt(i->i)));
        System.out.println(integerList.stream()
                .collect(Collectors.averagingInt(i->i-1)));

        //add 3 to each element and get statictics
        System.out.println(integerList.stream()
                .collect(Collectors.summarizingInt(i->i+3)));

        //divide numbers to odd or even
        System.out.println(integerList.stream()
                .collect(Collectors.partitioningBy(i->i%2!=0)));



        //list without duplicates
        System.out.println(stringList.stream()
                .distinct().collect(Collectors.toList()));

        //array with upper case without duplicates
        String[] strings = stringList.stream()
                .distinct().map(String::toUpperCase).toArray(String[]::new);
        for (String string : strings) {
            System.out.print(string+" ");
        }
        System.out.println();

        //unite all elements in one string with delimeter ":" and add <b>..</b>
        System.out.println(stringList.stream()
                .collect(Collectors.joining(":", "<b>", "</b>")));

        //transfer to map where 1symbol - key, 2nd - value
        Map<String, String> collect = stringList.stream().distinct()
                .collect(Collectors.toMap(p -> p.substring(0, 1), s -> s.substring(1)));
        for (Map.Entry<String, String> stringEntry : collect.entrySet()) {
            System.out.print("Key: " + stringEntry.getKey() + ", Value: " + stringEntry.getValue() + "\n");
        }

        //transfer to map, grouped by 1st symbol
        Map<String, List<String>> listMap = stringList.stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1)));
        for (Map.Entry<String, List<String>> stringListEntry : listMap.entrySet()) {
            System.out.print("Key: " + stringListEntry.getKey() + ", Value: " + stringListEntry.getValue() + "\n");
        }

        //transfer to map, grouped by 1st symbol and unite second via ":"
        Map<String, String> stringMap = stringList.stream()
                .collect(Collectors.groupingBy(s -> s.substring(0, 1),
                        Collectors.mapping(p -> p.substring(1),
                                Collectors.joining(":"))));
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            System.out.print("Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n");
        }


    }
}
