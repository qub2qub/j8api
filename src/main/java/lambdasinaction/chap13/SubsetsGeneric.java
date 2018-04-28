package lambdasinaction.chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created on 2018-04-01
 */
public class SubsetsGeneric<T> {
  
  public static void main(String[] args) {
    SubsetsGeneric<String> stringSubsets = new SubsetsGeneric<>();
    SubsetsGeneric<Integer> intSubsets = new SubsetsGeneric<>();
    stringSubsets.subsets(Arrays.asList("A", "B", "C")).forEach(System.out::println);
    intSubsets.subsets(Arrays.asList(1, 7, 4)).forEach(System.out::println);
  }
  
  private List<List<T>> subsets(List<T> l) {
    if (l.isEmpty()) {
      List<List<T>> ans = new ArrayList<>();
      ans.add(Collections.emptyList());
      return ans;
    }
    T first = l.get(0);
    List<T> rest = l.subList(1, l.size());
    List<List<T>> subans = subsets(rest);
    List<List<T>> subans2 = insertAll(first, subans);
    return concat(subans, subans2);
  }
  
  private List<List<T>> insertAll(T first, List<List<T>> lists) {
    List<List<T>> result = new ArrayList<>();
    for (List<T> l : lists) {
      // Copy the list to allow you to add to it.
      // You wouldn’t copy the lower-level structure even if it were mutable (Integers are not).
      // for mutable content --> (otherwise you’d have to clone each element too)
      List<T> copyList = new ArrayList<>();
      copyList.add(first);
      copyList.addAll(l);
      result.add(copyList);
    }
    return result;
  }
  
  /**
   *  The second version of concat is a pure function.
   *  It may be using mutation (adding elements to the list r) internally,
   *  but it returns a result based on its arguments and modifies neither of them.
   */
  private List<List<T>> concat(List<List<T>> a, List<List<T>> b) {
    List<List<T>> r = new ArrayList<>(a);
    r.addAll(b);
    return r;
  }
}
