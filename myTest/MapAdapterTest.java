package myTest;
import org.junit.*;
import myAdapter.*;

//TODO tets suite case design
/**
 * This class is a test suite designed for testing the MapAdapter class.
 * <br>
 * This test suite is designed using Junit version 4.13.2, the jar files
 * (junit-4.13.2.jar and hamcrest-core-1.3.jar) can be found in the JUnit
 * folder.
 * <br>
 * MapAdapterTest is built as part of the exam of the course "Elementi di
 * Ingegneria del Software" 2023/24 at Padua University.
 * <br>
 * This test suite contains n different tests plus one helper method written
 * to avoid duplicating code. Every method of MaAdapter, EntryAdapter and private class
 * SetAdapter is tested. Some methods could not have a test named after
 * them, but every method is tested. The naming convention chosen is
 * testFeatureBeingTested like.
 * 
 * @doc.testSuiteDesign For every (public) method present in the tested classes,
 *                      all the possible use cases were considered and all the
 *                      relevant edge cases were tested (at least these were the
 *                      intentions). For example the method get could
 *                      be called on an empty map, and if called on a full map
 *                      than it should work and return key and should not work 
 *                      for negative or biggest size() indexes. 
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.EntryAdapter
 * 
 */
public class MapAdapterTest{
    /**
     * Empty constructor.
     */
    public MapAdapterTest(){

    }
    /**
     * Returns a MapAdapter containing element with the keys (Integer) between first
     * (inclusive) and last(exclusive) in order and the value the corresponding alphabet letter/s.
     * This is a helper method, not a
     * test.
     * 
     * @param first the first number to be contained in the list
     * @param last  the first number not to be contained in the list
     * @return a MapAdapter containing all the keys and letters between first and last
     */
    private MapAdapter helper(int first, int last) {
        MapAdapter rtn = new MapAdapter();
        for (int i = first; i < last; i++) {
            String risult = "";
            int num = i+1;
            while(num>0){
                num --;
                risult = risult + (char)('A' + num%26);
                num = num/26;
            }
            rtn.put(i,risult);
        }
        return rtn;
    }
     /**
     * Tests the creation of an empty map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the creation of a new
     *                     empty map
     * @doc.testDescription Instantiates a map and verifies that it is empty and
     *                      that its size is zero.
     * @doc.postCondition The map is a valid instance.
     * @doc.expectedResults The map is empty and has size zero.
     */
    @Test
    public void testNewMap(){
        MapAdapter map = new MapAdapter();

        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());

    }
        
    /**
     * Tests the helper method.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the creation of a
     *                     map filled with numbers keys and letters value via the helper method
     *                     helper().
     * @doc.testDescription Creates a maps via the helper(0, 10) method
     *                      and verifies that it is not empty, its size equals 10,
     *                      and the first and the last element are correct.
     * @doc.postCondition The map is instantiated and filled with numbers.
     * @doc.expectedResults The mapp has the right size, is not empty and contains
     *                      the right elements.
     */
    @Test
    public void testNewMapWithHelper() {
        MapAdapter map = helper(0, 10);

        Assert.assertFalse(map.isEmpty());
        Assert.assertEquals(10, map.size());
        Assert.assertEquals("A", map.get(0));
        Assert.assertEquals("J", map.get(9));
    }
    

    /**
     * Tests the creation by copy of the map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the creation of a
     *                     map filled with another map.
     * @doc.testDescription Creates a maps via the helper(0, 10) method
     *                      and initialies a new map passing the newly created map as a parameter,
     *                      then testing the new map that it is not empty, its size equals 10,
     *                      and the first and the last element are correct.
     * @doc.postCondition The map is instantiated and filled with numbers.
     * @doc.expectedResults The mapp has the right size, is not empty and contains
     *                      the right elements.
     */
    @Test
    public void testNewMapWithConstructor() {
        MapAdapter map2 = helper(0,10);
        MapAdapter map = new MapAdapter(map2);
        Assert.assertFalse(map.isEmpty());
        Assert.assertEquals(10, map.size());
        Assert.assertEquals("A", map.get(0));
        Assert.assertEquals("J", map.get(9));
    }

     /**
     * Tests the size method of a map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the size() method of a map with
     *                     some element in it.
     * @doc.testDescription Instantiates a map, fill it with the helper(0,10) method and
     *                      that its size is ten.
     * @doc.postCondition The map is a valid instance.
     * @doc.expectedResults The map has size ten.
     */
    @Test
    public void testSizeMap(){
        MapAdapter map = helper(0,10);
        Assert.assertEquals(10, map.size());

    }

     /**
     * Tests the size method of an empty map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the size() method of a new
     *                     empty map
     * @doc.testDescription Instantiates a map and verifies that it is empty and
     *                      that its size is zero.
     * @doc.postCondition The map is a valid instance.
     * @doc.expectedResults The map is has size zero.
     */
    @Test
    public void testSizeEmptyMap(){
        MapAdapter map = new MapAdapter();

        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());

    }

    
    /**
     * Tests the clear method on an empty map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the behaviour of an
     *                     empty map when clear() is called on it.
     * @doc.testDescription Instantiates an empty map, calls the clear() method and
     *                      checks that the  map is empty and has size zero.
     * @doc.preCondition The map is correctly instantiated.
     * @doc.postCondition The map is still empty.
     * @doc.expectedResults The map is still empty and has size zero.
     */
    @Test
    public void testClearEmptyMap() {
        MapAdapter map = new MapAdapter();

        map.clear();

        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
    }

    /**
     * Tests the clear method on an empty map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the behaviour of an
     *                     empty map when clear() is called on it.
     * @doc.testDescription Instantiates an empty map, calls the clear() method and
     *                      checks that the  map is empty and has size zero.
     * @doc.preCondition The map is correctly instantiated.
     * @doc.postCondition The map is still empty.
     * @doc.expectedResults The map is still empty and has size zero.
     */
    @Test
    public void testClearNonEmptyMap() {
        MapAdapter map = helper(0,10);

        map.clear();

        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
    }

    /**
     * Tests the containsKey method on a map.
     * 
     * @doc.testCaseDesign This test is designed for analyzing the containsKey() method 
     *                     of a new map
     * @doc.testDescription Instantiates a map and checks that the map is not empty and 
     *                      has the key inside of it.
     * @doc.preCondition The map is correctly instantiated.
     * @doc.postCondition The map is a valid instance.
     * @doc.expectedResults The map is not empty and contains the right key.
     */
    @Test
    public void testContainsKey() {
        MapAdapter map = helper(0,10);

        Assert.assertTrue(map.containsKey(3));
        Assert.assertEquals(10, map.size());
    }

}
