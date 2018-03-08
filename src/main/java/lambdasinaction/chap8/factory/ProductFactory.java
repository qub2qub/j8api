package lambdasinaction.chap8.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class ProductFactory {
  
  private static final Map<String, Supplier<Product>> CREATOR = new HashMap<>();
  static {
    CREATOR.put("loan", Loan::new);
    CREATOR.put("stock", Stock::new);
    CREATOR.put("bond", Bond::new);
  }
  
    public static Product createProduct(String name) {
      switch (name) {
        case "loan":
          return new Loan();
        case "stock":
          return new Stock();
        case "bond":
          return new Bond();
        default:
          throw new RuntimeException("No such product " + name);
      }
    }
    
    public static Product createProductLambda(String name) {
      Supplier<Product> p = CREATOR.get(name);
      if (p != null) {
        return p.get();
      }
      throw new RuntimeException("No such product " + name);
    }
  }