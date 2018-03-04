package lambdasinaction.chap7;

class WordCounter {
    
    private final int count;
    private final boolean lastSpace;
    
    public WordCounter(int count, boolean lastSpace) {
      this.count = count;
      this.lastSpace = lastSpace;
    }
  
  /**
   * The accumulate method traverses the Characters one by one as done by the iterative algorithm.
   */
  public WordCounter accumulate(Character c) {
      if (Character.isWhitespace(c)) {
        return lastSpace ? this : new WordCounter(count, true);
      } else {
        return lastSpace ? new WordCounter(
          //Increase the word counter when the last character is a space and the currently traversed one isn’t.
          count + 1,false) : this;
      }
    }
  
  /**
   * Combine two WordCounters by summing their counters.
   */
  public WordCounter combine(WordCounter other) {
    
      return new WordCounter(count + other.count,
        // Use only the sum of the counters so you don’t care about lastSpace.
        other.lastSpace);
    }
    
    public int getCount() {
      return count;
    }
  }