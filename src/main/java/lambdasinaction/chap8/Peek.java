package lambdasinaction.chap8;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;


public class Peek {
  
  public static void main(String[] args) {
  
    int b = 10;
    /*Runnable r1 = () -> {
      int a = 2;
      System.out.println(a);
    };*/
    Runnable r2 = new Runnable(){
      int a = 3;
      public void run(){
        int a = 2;
        System.out.println(a + this.a+b);
      }
    };
    
    new Thread(r2).start();
    
    List<Integer> result = Stream.of(2, 3, 4, 5)
      .peek(x -> System.out.println("taking from stream: " + x)).map(x -> x + 17)
      .peek(x -> System.out.println("after map: " + x)).filter(x -> x % 2 == 0)
      .peek(x -> System.out.println("after filter: " + x)).limit(3)
      .peek(x -> System.out.println("after limit: " + x)).collect(toList());
  }
}
