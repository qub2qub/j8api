package lambdasinaction.chap8.chainOfResponsibilit;

class HeaderTextProcessing
    extends ProcessingObject<String> {
    
    public String handleWork(String text) {
      return "From Raoul, Mario and Alan: " + text;
    }
  }