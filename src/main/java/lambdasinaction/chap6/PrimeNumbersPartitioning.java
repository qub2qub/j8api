package lambdasinaction.chap6;

import static java.util.stream.Collectors.partitioningBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimeNumbersPartitioning
{
  
  public static final PrimeNumbersCollector COLLECTOR = new PrimeNumbersCollector();
  
  public static void main( String ... args )
  {
    System.out.println( "Numbers partitioned in prime and non-prime: \n" + partitionPrimes( 100 ) );
    System.out.println( "Numbers partitioned in prime and non-prime: \n" + partitionPrimesWithCustomCollector( 100 ) );
  }
  
  public static Map<Boolean, List<Integer>> partitionPrimes( int n )
  {
    return IntStream.rangeClosed( 2, n ).boxed().collect( partitioningBy( candidate -> isPrime( candidate ) ) );
  }
  
  public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector( int n )
  {
    return IntStream.rangeClosed( 2, n ).boxed().collect(COLLECTOR);
  }

  public static boolean isPrime( int candidate )
  {
    return IntStream.rangeClosed( 2, candidate - 1 )
      .limit( (long)Math.floor( Math.sqrt( (double)candidate ) ) - 1 )
      .noneMatch( i -> candidate % i == 0 );
  }

  public static boolean isPrime( List<Integer> primes, Integer candidate )
  {
    return isPrime(candidate);
  }

  public static Map<Boolean, List<Integer>> partitionPrimesWithInlineCollector(int n)
  {
    return Stream.iterate( 2, i -> i + 1 ).limit( n ).collect( () -> new HashMap<Boolean, List<Integer>>() {
      {
        put( true, new ArrayList<Integer>() );
        put( false, new ArrayList<Integer>() );
      }
    }, ( acc, candidate ) -> {
      acc.get( isPrime( acc.get( true ), candidate ) ).add( candidate );
    }, ( map1, map2 ) -> {
      map1.get( true ).addAll( map2.get( true ) );
      map1.get( false ).addAll( map2.get( false ) );
    } );
  }
}
