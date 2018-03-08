package lambdasinaction.chap8.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class RunFactory {
  
  public static void main(String[] args) {
    Product p1 = ProductFactory.createProduct("loan");
    
//    Supplier<Product> loanSupplier = Loan::new;
//    Product p2 = loanSupplier.get();
    
    Product p3 = ProductFactory.createProductLambda("loan");
    
  }
  
}
