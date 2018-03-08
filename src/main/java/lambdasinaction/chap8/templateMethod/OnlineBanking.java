package lambdasinaction.chap8.templateMethod;


abstract class OnlineBanking {
  
  public void processCustomer(int id) {
    Customer c = Database.getCustomerWithId(id);
    makeCustomerHappy(c);
  }
  
  abstract void makeCustomerHappy(Customer c);
}
