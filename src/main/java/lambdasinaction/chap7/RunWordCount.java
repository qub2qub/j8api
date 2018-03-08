package lambdasinaction.chap7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RunWordCount {
  
  // the first sentence of Dante’s Inferno
  public static final String SENTENCE =
    "Nel mezzo del cammin di nostranostranostranostranostra       vita mi  ritrovai in una  selva oscura che la  dritta via era   smarrita";
  
  public static void main(String[] args) {
    System.out.println("availableProcessors=" + Runtime.getRuntime().availableProcessors());
    System.out.println("1 Found " + countWordsIteratively(SENTENCE) + " words");
    System.out.println("2 Found " + countWordsCalc(
      IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt).parallel()
    )      + " words");
    System.out.println("3 Found " + countWordsStr(SENTENCE) + " words");
  }
  
  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;
    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else {
        if (lastSpace) {
          //Increase the word counter when the last character is a space and the currently traversed one isn’t.
          counter++;
          lastSpace = false;
        }
      }
    }
    return counter;
  }
  
  public static int countWordsStr(String s) {
//    Stream<Character> stream = IntStream.range(0, s.length())
//      .mapToObj(SENTENCE::charAt).parallel();
    Stream<Character> stream = StreamSupport.stream(new WordSpliterator(s), true);
    
    return countWordsCalc(stream);
  }
  
  private static int countWordsCalc(Stream<Character> stream) {
    WordCounter wordCounter = stream/*.parallel()*/.reduce(
      new WordCounter(0, true),
      WordCounter::accumulate,
      WordCounter::combine);
    return wordCounter.getCount();
  }
  
  
}
