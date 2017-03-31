package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Denis_Maliarevich on 2017-03-31
 */
public class MyCollectors {

  public static void main(String[] args) throws Exception {

//    testConcat();
    testCollectGroupBy();


  }

  private static void testCollectGroupBy() throws FileNotFoundException {
  /*
  Computing a word-count histogram with Streams
   */
    final String filePath = "d:\\Qub2Qub\\github\\j8api\\src\\main\\java\\test\\text.txt";
    Pattern whitespace = Pattern.compile("\\s+");
    BufferedReader reader = null;
    reader = new BufferedReader(new FileReader(filePath));
    Stream<String> stringStreamFlatMap = reader.lines()
//              .flatMap(s -> whitespace.splitAsStream(s));
        .flatMap(whitespace::splitAsStream);

    List<String> fileLines = stringStreamFlatMap.collect(Collectors.toList());
    System.out.println("fileLines = " + fileLines.get(10));

//    Map<String, Integer> wordFrequencies =
//        stringStreamFlatMap // fileLines.stream()
//            .collect(
//                Collectors.groupingBy(String::toLowerCase), // compile error
//                Collectors.counting()
//            );

      /*Map<String, Integer> wordFrequencies =
          reader.lines()
              .flatMap(whitespace::splitAsStream)
              .collect(
                  groupingBy(String::toLowerCase),
                  Collectors.counting()
              );*/
  }

  private static void testConcat() {
    String[] strings2 = {"a", "b", "AA", "c", "DD", "AA", "BB", "a", "CCC", "b"};
    List<String> strings = Arrays.asList(strings2);

    String concat1 = Stream.of(strings2).collect(Collectors.joining());
    System.out.println("concat1 = " + concat1);

    String concat2 = strings.stream().collect(Collectors.joining());
    System.out.println("concat2 = " + concat2);

    StringBuilder concat3 = strings.stream()
        .collect(StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append);
    System.out.println("concat3 = " + concat3);

    StringBuilder concat4 = strings.stream()
        .collect(() -> new StringBuilder(),
            (sb, s) -> sb.append(s),
            (sb, sb2) -> sb.append(sb2));
    System.out.println("concat4 = " + concat4);

    Set<String> uniqueStrings1 = strings.stream()
        .collect(HashSet::new,
            HashSet::add,
            HashSet::addAll);
    System.out.println("uniqueStrings1 = " + uniqueStrings1);

    Set<String> uniqueStrings2 = strings.stream().collect(Collectors.toSet());
    System.out.println("uniqueStrings2 = " + uniqueStrings2);
  }
}
