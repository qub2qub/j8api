package lambdasinaction.chap8.observer;

class LeMonde implements Observer {
    
    @Override
    public void inform(String tweet) {
      if (tweet != null && tweet.contains("vine")) {
        System.out.println("Today cheese, wine and news! " + tweet);
      }
    }
  }