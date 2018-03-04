package lambdasinaction.chap7;

import java.util.Spliterator;
import java.util.function.Consumer;

class WordSpliterator implements Spliterator<Character> {
    
    private final String string;
    private int currentChar = 0;
    
    public WordSpliterator(String string) {
      this.string = string;
    }
    
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
      // Consume the current character
      action.accept(string.charAt(currentChar++));
      // Return true if there are further characters to be consumed.
      return currentChar < string.length();
    }
    
    @Override
    public Spliterator<Character> trySplit() {
      int currentSize = string.length() - currentChar;
      if (currentSize < 10) {
        // Return null to signal that the String to be parsed is small enough to be processed sequentially.
        return null;
      }
      // Set the candidate’s split position to be half of the String to be parsed.
      for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
        // Advance the split position until the next space.
        if (Character.isWhitespace(string.charAt(splitPos))) {
          // Create a new WordCounter-Spliterator parsing the String from the start to the split position.
          Spliterator<Character> spliterator = new WordSpliterator(string.substring(currentChar, splitPos));
          // Set the start position of this WordCounter-Spliterator to the split position.
          currentChar = splitPos;
          return spliterator;
        }
      }
      return null;
    }
    
    @Override
    public long estimateSize() {
      return string.length() - currentChar;
    }
    
    @Override
    public int characteristics() {
      return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
  }