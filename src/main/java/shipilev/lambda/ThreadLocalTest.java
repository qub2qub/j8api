package shipilev.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    @Test
    public void threadLocalLegacy(){
        final AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return counter.incrementAndGet();
            }
        };
        Assert.assertEquals(tlNumber.get(), (Integer)1);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
    }

    @Test
    public void threadLocalLambda() {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = ThreadLocal.withInitial(() -> counter.incrementAndGet());
        Assert.assertEquals(tlNumber.get(), (Integer)1);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
    }

    @Test
    public void threadLocalMRef() {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = ThreadLocal.withInitial(counter::incrementAndGet);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
    }

}
