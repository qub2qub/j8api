package lambdasinaction.chap8.chainOfResponsibilit;

class SpellCheckerProcessing
    extends ProcessingObject<String> {
    
    public String handleWork(String text) {
      return text.replaceAll("labda", "lambda");
    }
  }