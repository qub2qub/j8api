package lambdasinaction.chap8.templateMethod;

import java.util.function.Consumer;


public class OnlineBankingLambda {
  
  public static void main(String[] args) {
    new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello! " + c.hashCode()));
  }
  
  public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
    Customer c = Database.getCustomerWithId(id);
    makeCustomerHappy.accept(c);
  }
}
