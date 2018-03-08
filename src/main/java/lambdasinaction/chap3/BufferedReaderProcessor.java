package lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderProcessor {
  // lets you pass different lambdas to work with a BufferedReader object
  String process(BufferedReader b) throws IOException;
}