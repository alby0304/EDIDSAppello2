package myTest;

import java.util.NoSuchElementException;
import org.junit.*;
import myAdapter.*;

public class MapAdapterTest{
    @Test
    public void test(){
        MapAdapter map = new MapAdapter();
        map.put(1,"suca");
        Assert.assertEquals(true,map.entrySet().iterator().hasNext());
        Assert.assertEquals("suca",((HMap.Entry)(map.entrySet().iterator().next())).getValue());
        map.entrySet().iterator().remove();
        Assert.assertEquals(0, map.size());
    }
}