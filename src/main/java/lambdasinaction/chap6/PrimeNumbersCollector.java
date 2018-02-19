package lambdasinaction.chap6;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector
  implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>>
{

  private <A> List<A> takeWhile( List<A> list, Predicate<A> p )
  {
    int i = 0;
    for( A item : list ) {
      if ( !p.test( item ) ) {
        return list.subList( 0, i );
      }
      i++;
    }
    return list;
  }

  private boolean isPrime( List<Integer> primes, Integer candidate )
  {
    double candidateRoot = Math.sqrt( (double)candidate );
    // return true;
    return takeWhile( primes, i -> i <= candidateRoot ).stream().noneMatch( i -> candidate % i == 0 );
    // return primes.stream().takeWhile(i -> i <= candidateRoot).noneMatch(i -> candidate % i == 0);
  }

  @Override
  public Supplier<Map<Boolean, List<Integer>>> supplier()
  {
    return () -> new HashMap<Boolean, List<Integer>>() {
      {
        put( true, new ArrayList<Integer>() );
        put( false, new ArrayList<Integer>() );
      }
    };
  }

  @Override
  public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator()
  {
    return ( Map<Boolean, List<Integer>> acc, Integer candidate ) -> {
      acc.get( isPrime( acc.get( true ), candidate ) ).add( candidate );
    };
  }

  @Override
  public BinaryOperator<Map<Boolean, List<Integer>>> combiner()
  {
    return ( Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2 ) -> {
      map1.get( true ).addAll( map2.get( true ) );
      map1.get( false ).addAll( map2.get( false ) );
      return map1;
    };
  }

  @Override
  public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher()
  {
    return i -> i; // Function.identity();
  }

  @Override
  public Set<Characteristics> characteristics()
  {
    // not CONCURRENT because it relies on the fact that prime numbers are discovered in sequence
    return Collections.unmodifiableSet( EnumSet.of( IDENTITY_FINISH ) );
  }
}