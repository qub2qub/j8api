package lambdasinaction.chap5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PuttingIntoPractice {
  
  public static void main(String... args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    
    List<Transaction> transactions = Arrays.asList(
      new Transaction(brian, 2011, 300),
      new Transaction(raoul, 2012, 1000),
      new Transaction(raoul, 2011, 400),
      new Transaction(mario, 2012, 710),
      new Transaction(mario, 2013, 700),
      new Transaction(alan, 2012, 950)
    );
    
    // Query 1: Find all transactions from year 2012 and sort them by value (small to high).
    List<Transaction> tr2011 = transactions.stream()
      .filter(transaction -> transaction.getYear() == 2012)
      .sorted(comparing(Transaction::getValue))
      .collect(toList());
    System.out.println("1) transactions from year 2011:");
    transactions.forEach(System.out::println);
    
    // Query 2: What are all the unique cities where the traders work?
    Set<String> cities =
      transactions.stream()
        .map(transaction -> transaction.getTrader().getCity())
        .collect(toSet());
//        .distinct()
//        .collect(toList());
    System.out.println("2) unique cities = " + cities);
    
    // Query 3: Find all traders from Cambridge and sort them by name.
    List<Trader> traders =
      transactions.stream()
        .map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .distinct()
        .sorted(comparing(Trader::getName))
        .collect(toList());
    System.out.println("3) traders from Cambridge = " + traders);
    
    // Query 4: Return a string of all traders’ names sorted alphabetically.
    String traderStr =
      transactions.stream()
        .map(transaction -> transaction.getTrader().getName())
        .distinct()
        .collect(joining(", "));
//        .sorted()
//        .reduce("START:", (n1, n2) -> n1 + " > " +n2);
    System.out.println("4) all traders’ names sorted = " + traderStr);
    
    // Query 5: Are there any trader based in Milan?
    boolean milanBased = transactions.stream()
        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan") );
    System.out.println("5) any trader based in Milan = " + milanBased);
    
    // Print all transactions’ values from the traders living in Cambridge
    transactions.stream()
      .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
      .map(Transaction::getValue)
      .forEach(System.out::println);
  
    // Query 6: What's the highest value in all the transactions?
    int highestValue = transactions.stream()
        .map(Transaction::getValue)
        .reduce(0, Integer::max);
    System.out.println("6) highest value in all the transactions = " + highestValue);
    
    // Find the transaction with the smallest value
    Optional<Transaction> smallestTransaction =
      transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
    Optional<Transaction> smallestTransaction2 =
      transactions.stream().min(comparing(Transaction::getValue));
  
    // Query 7: Update all transactions so that the traders from Milan are set to Cambridge.
    transactions.stream()
      .map(Transaction::getTrader)
      .filter(trader -> trader.getCity().equals("Milan"))
      .forEach(trader -> trader.setCity("Cambridge"));
    System.out.println("7) all transactions:");
    transactions.forEach(System.out::println);
  }
}