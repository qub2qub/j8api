package lambdasinaction.chap5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
  
  public static void main(String... args) throws Exception {
    
    // Stream.of
    Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
    stream.map(String::toUpperCase).forEach(System.out::print);
    System.out.println("\n-----------------------");
    // Stream.empty
    Stream<String> emptyStream = Stream.empty();
    
    // Arrays.stream
    int[] numbers = {2, 3, 5, 7, 11, 13};
    System.out.println("sum="+Arrays.stream(numbers).sum());
    
    // Stream.iterate
    Stream.iterate(0, n -> n + 2)
      .limit(10)
      .forEach(System.out::print);
    System.out.println("\n-----------------------");
    
    // fibonnaci with iterate
    System.out.println("fibonnaci:");
    Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
      .limit(10)
//      .sorted() //OutOfMemoryError
      .forEach(t -> System.out.print("(" + t[0] + ", " + t[1] + ")"));
    System.out.println("\n-----------------------");
    Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
      .limit(10)
      .map(t -> t[0])
      .forEach(x -> System.out.print(x+", "));
    
    System.out.println("\n-----------------------\nRandom:");
    // random stream of doubles with Stream.generate
    Stream.generate(Math::random)
      .limit(20)
      .forEach(System.out::print);
    System.out.println("\n----------- IntStream.generate() ------------");
    // stream of 1s with Stream.generate
    IntStream.generate(() -> 1)
      .limit(5)
      .forEach(System.out::print);
    System.out.println("\n----------- IntStream.generate(2) ------------");
    IntStream.generate(new IntSupplier() {
      public int getAsInt() {
        return 2;
      }
    }).limit(5)
      .forEach(System.out::print);
  
    System.out.println("\n----------- Fibonnaci Generator ------------");
    IntSupplier fibonnaciGenerator = new IntSupplier() {
      private int previous = 0;
      private int current = 1;
      
      public int getAsInt() {
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return this.previous;
      }
    };
    IntStream.generate(fibonnaciGenerator).limit(10).forEach(System.out::print);
    System.out.println("\n----------- ReadFile ------------");
    
    long uniqueWords = Files.lines(
//      Paths.get("lambdasinaction/chap5/data.txt"),
      Paths.get("D:\\Denis_Work\\github_qub2qub\\j8api\\src\\main\\resources\\lambdasinaction\\chap5\\data.txt"),
      Charset.defaultCharset())
      //use flatMap to produce one flattened stream of words instead of multiple streams of words for each line.
      .flatMap(line -> Arrays.stream(line.split(" ")))
      .distinct()
      .count();
    System.out.println("There are " + uniqueWords + " unique words in data.txt");
/*    try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
      uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
        .distinct()
        .count();
    } catch (IOException e) {
    }*/
    
  }
}
