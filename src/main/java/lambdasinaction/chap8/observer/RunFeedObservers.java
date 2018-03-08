package lambdasinaction.chap8.observer;

public class RunFeedObservers {
  
  public static void main(String[] args) {
    Feed f = new Feed();
    f.registerObserver(new NYTimes());
    f.registerObserver(new Guardian());
    f.registerObserver(new LeMonde());
    f.notifyObservers("While drinking her vine : The queen said her favourite book is Java 8 in Action!");
    
    Feed feedLambda = new Feed();
    // how to delete them?
    feedLambda.registerObserver((String tweet) -> {
      if (tweet != null && tweet.contains("money")) {
        System.out.println("Breaking news in NY! " + tweet);
      }
    });
    feedLambda.registerObserver((String tweet) -> {
      if (tweet != null && tweet.contains("queen")) {
        System.out.println("Yet another news in London... " + tweet);
      }
    });
    
    feedLambda.notifyObservers("Money money money, give me money!");
    
  }
}
