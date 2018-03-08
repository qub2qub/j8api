package lambdasinaction.chap8.strategy;

public class IsNumeric implements ValidationStrategy {
public boolean execute(String s){
return s.matches("\\d+");
}
}