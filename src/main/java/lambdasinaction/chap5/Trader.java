package lambdasinaction.chap5;

public class Trader {
  
  private String name;
  private String city;
  
  public Trader(String name, String city) {
    this.name = name;
    this.city = city;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getCity() {
    return this.city;
  }
  
  public void setCity(String newCity) {
    this.city = newCity;
  }
  
  public String toString() {
    return this.name + "__" + this.city;
  }
}