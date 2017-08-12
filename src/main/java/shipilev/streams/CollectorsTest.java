package shipilev.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectorsTest {

    @Test
    public void test1() {
        List<Integer> result = IntStream.range(0, 1000).boxed().collect(Collectors.toCollection(ArrayList<Integer>::new));
        Assert.assertEquals(1000, result.size());
    }

    @Test
    public void test2() {
        List<Integer> result = IntStream.range(0, 1000).boxed().collect(Collectors.<Integer>toList());
        Assert.assertEquals(1000, result.size());
    }

    @Test
    public void test3() {
        Map<Integer,Integer> map =
                IntStream.range(0, 1000).boxed()
                        .collect(
                                Collectors.toMap(
                                        Function.<Integer>identity(),
                                        (x) -> x % 3
                                )
                        );
        Assert.assertEquals(1000, map.size());
        Assert.assertEquals(Integer.valueOf(0), map.get(111));
    }

    @Test
    public void test4() {
        /*Map<Integer,Integer> map =
                IntStream.range(0, 1000).boxed()
                        .parallel()
                        .collect(
                                Collectors.toConcurrentMap(
                                        (k) -> k % 42,
                                        Function.identity(),
                                        Collectors.lastWinsMerger()
                                )
                        );
        Assert.assertEquals(42, map.size());*/
    }

}
