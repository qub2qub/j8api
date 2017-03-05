package stream.streamUsage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Usage of ForEach and Peek.
 * Both change elements in stream.
 * The diff is ForEach - terminal, Peek - conveyor.
 *
 * Created by dkorolev on 1/5/2017.
 */
public class StreamUsageForEachAndPeek {
    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("a1", "a2", "a3", "a1");

        //add "_new" with ForEach
        stringList.stream().forEach(s->System.out.print(s.concat("_new")+" "));
        System.out.println();

        //add "_new" with Peek (with String doesn't work!)
        Collection<StringBuilder> list = Arrays.asList(new StringBuilder("a1"), new StringBuilder("a2"), new StringBuilder("a3"));
        List<StringBuilder> aNew = list.stream().peek((o) -> o.append("_new")).collect(Collectors.toList());
        aNew.stream().forEach(System.out::println);

    }
}
