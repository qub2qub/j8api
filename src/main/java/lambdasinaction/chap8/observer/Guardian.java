package lambdasinaction.chap8.observer;

class Guardian implements Observer {
    
    @Override
    public void inform(String tweet) {
      if (tweet != null && tweet.contains("queen")) {
        System.out.println("Yet another news in London... " + tweet);
      }
    }
  }