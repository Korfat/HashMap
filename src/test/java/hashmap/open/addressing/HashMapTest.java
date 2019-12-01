package hashmap.open.addressing;

import org.junit.Assert;
import org.junit.Test;

public class HashMapTest {
    @Test
    public void getByNonExistedKey() {
        HashMap hashMap = new HashMap();
        Assert.assertEquals(-1, hashMap.get(1));
    }

    @Test
    public void putAndGetOk() {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 313546);
        hashMap.put(2, 51234);
        hashMap.put(3, 11234567558565674L);

        Assert.assertEquals(3, hashMap.size());
        Assert.assertEquals(313546, hashMap.get(1));
        Assert.assertEquals(51234, hashMap.get(2));
        Assert.assertEquals(11234567558565674L, hashMap.get(3));
    }

    @Test
    public void putAndGetWithCollision() {
        HashMap hashMap = new HashMap();
        hashMap.put(1, 313546);
        hashMap.put(2, 51234);
        hashMap.put(17, 11234567558565674L);
        hashMap.put(33, 56755812312L);

        Assert.assertEquals(4, hashMap.size());
        Assert.assertEquals(313546, hashMap.get(1));
        Assert.assertEquals(51234, hashMap.get(2));
        Assert.assertEquals(11234567558565674L, hashMap.get(17));
        Assert.assertEquals(56755812312L, hashMap.get(33));
    }

    @Test
    public void putAndGetTheOverriddenValueByKey() {
        HashMap hashMap = new HashMap();
        hashMap.put(3, 2342);
        Assert.assertEquals(1, hashMap.size());
        Assert.assertEquals(2342, hashMap.get(3));

        hashMap.put(3, 11234567558565674L);
        Assert.assertEquals(1, hashMap.size());
        Assert.assertEquals(11234567558565674L, hashMap.get(3));
    }

    @Test
    public void checkTheHashMapIncrease() {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 1000; i++) {
            hashMap.put(i, i * 100);
        }
        Assert.assertEquals(1000, hashMap.size());
        Assert.assertEquals(99900, hashMap.get(999));
    }

    @Test
    public void getSizeOfEmptyHashMap() {
        HashMap hashMap = new HashMap();
        Assert.assertEquals(0, hashMap.size());
    }

    @Test
    public void getSizeWithCollision() {
        HashMap hashMap = new HashMap();
        hashMap.put(4, 3);
        hashMap.put(131, 5);
        Assert.assertEquals(2, hashMap.size());
    }
}
