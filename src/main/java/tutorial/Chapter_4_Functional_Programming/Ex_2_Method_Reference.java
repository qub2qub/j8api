package tutorial.Chapter_4_Functional_Programming;

import tutorial.Chapter_3_Migration_from_7_to_8.beans.Department;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class Ex_2_Method_Reference {
  public static void main( String[] args )
  {
    Function<String, Department> constrFun1 = name -> new Department( name );
      // implicit constructor binding
      Function<String, Department> constrFun2 = Department::new;
    Arrays.asList( "Financial", "Mobile" ).stream().map( constrFun2 ).forEach( System.out::println );

    // A few MR examples
    BiFunction<String, CharSequence, Boolean> contains = String::contains;
    System.out.println( contains.apply( "Russia", "Alaska" ) );

    final DoubleUnaryOperator doubleConsumer = Math::abs;
    System.out.println( doubleConsumer.applyAsDouble( -3.33 ) );

    final Function<Department, String> getName = Department::getName;
    System.out.println( getName.apply( constrFun1.apply( "QA Department" ) ) ); // apply(apply(..))

    // Problem: unclear type in the right - you should specify T
    // final BiFunction<Collection<? extends T>, Comparator<? super T>, T> collectionComparatorTBiFunction = Collections::max;
    // Solution
    Function<List<Integer>, Integer> maxElem = Collections::max;
    // Or equivalent
    Function<List<Integer>, Integer> maxElemLambda = ( numbers ) -> Collections.max( numbers );
  }
}
