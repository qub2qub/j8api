package lambdasinaction.chap8.strategy;

public class Validator {
  
  private final ValidationStrategy strategy;
  
  public Validator(ValidationStrategy v) {
    this.strategy = v;
  }
  
  public boolean validate(String s) {
    return strategy.execute(s);
  }
  
  
  public static void main(String[] args) {
    Validator numericValidator = new Validator(new IsNumeric());
    boolean b1 = numericValidator.validate("aaaa");
    Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
    boolean b2 = lowerCaseValidator.validate("bbbb");
    
    Validator numericValidator2 = new Validator((String s) -> s.matches("[a-z]+"));
    boolean b21 = numericValidator.validate("aaaa");
    Validator lowerCaseValidator2 = new Validator((String s) -> s.matches("\\d+"));
    boolean b22 = lowerCaseValidator.validate("bbbb");
    
  }
  
}