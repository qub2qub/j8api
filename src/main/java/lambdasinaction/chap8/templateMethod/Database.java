package lambdasinaction.chap8.templateMethod;

class Database {
  
  static Customer getCustomerWithId(int id) {
    return new Customer();
  }
}