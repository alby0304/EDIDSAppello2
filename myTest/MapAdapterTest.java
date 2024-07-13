package myTest;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import myAdapter.*;

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
 * @testSuiteDesign For every (public) method present in the tested classes,
 *                      all the possible use cases were considered and all the
 *                      relevant edge cases were tested (at least these were the
 *                      intentions). For example the method get could
 *                      be called on an empty map, and if called on a full map
 *                      than it should work and return key and should not work 
 *                      for negative or biggest size() indexes. 
 * @see myAdapter.MapAdapter
 * @see myAdapter.MapAdapter.EntryAdapter
 * @see myAdapter.MapAdapter.SetAdapter
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
     * @testCaseDesign This test is designed for analyzing the creation of a new
     *                     empty map
     * @testDescription Instantiates a map and verifies that it is empty and
     *                      that its size is zero.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is empty and has size zero.
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
     * @testCaseDesign This test is designed for analyzing the creation of a
     *                     map filled with numbers keys and letters value via the helper method
     *                     helper().
     * @testDescription Creates a maps via the helper(0, 10) method
     *                      and verifies that it is not empty, its size equals 10,
     *                      and the first and the last element are correct.
     * @postCondition The map is instantiated and filled with numbers.
     * @expectedResults The mapp has the right size, is not empty and contains
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
     * @testCaseDesign This test is designed for analyzing the creation of a
     *                     map filled with another map.
     * @testDescription Creates a maps via the helper(0, 10) method
     *                      and initialies a new map passing the newly created map as a parameter,
     *                      then testing the new map that it is not empty, its size equals 10,
     *                      and the first and the last element are correct.
     * @postCondition The map is instantiated and filled with numbers.
     * @expectedResults The mapp has the right size, is not empty and contains
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
     * Test throwing the exception <code>NullPointerException</code>
     * 
     * @testCaseDesign the test is designed to verify that an exception is thrown 
     *                 following the creation of a map to which a null pointer is passed
     * @testDescription Instantiates a map by passing it a null pointer
     * @postCondition The map is a invalid instance.
     * @expectedResults Throwing NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testNewMapWithNullPointerExceptionConstructor() 
    {
        MapAdapter map = new MapAdapter(null);
    }

     /**
     * Tests the size method of a map.
     * 
     * @testCaseDesign This test is designed for analyzing the size() method of a map with
     *                     some element in it.
     * @testDescription Instantiates a map, fill it with the helper(0,10) method and
     *                      that its size is ten.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has size ten.
     */
    @Test
    public void testSizeMapOnFilledMap(){
        MapAdapter map = helper(0,10);
        Assert.assertEquals(10, map.size());

    }

     /**
     * Tests the size method of an empty map.
     * 
     * @testCaseDesign This test is designed for analyzing the size() method of a new
     *                     empty map
     * @testDescription Instantiates a map and verifies that it is empty and
     *                      that its size is zero.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is has size zero.
     */
    @Test
    public void testSizeEmptyMap(){
        MapAdapter map = new MapAdapter();

        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());

    }


    /**
     * Tests the isEmpty() method on a filled map.
     * 
     * @testCaseDesign This test is designed for analyzing the isEmpty() method of a 
     *                 filled map.                      
     * @testDescription Instantiates a map and verifies that it is not empty and
     *                  the method returns <code>false</code>.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is not empty.
     */
    @Test
    public void testIsEmptyOnFilledMap(){
        MapAdapter map = helper(0,10);
        Assert.assertFalse(map.isEmpty());
    }

    /**
     * Tests the isEmpty() method on an empty map.
     * 
     * @testCaseDesign This test is designed for analyzing the isEmpty() method on an 
     *                 empty map.                      
     * @testDescription Instantiates a map and verifies that it is empty and
     *                  the method returns <code>true</code>.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is empty.
     */
    @Test
    public void testIsEmpyOnEmptyMap(){
        MapAdapter map = new MapAdapter();
        Assert.assertTrue(map.isEmpty());
    }

    /**
     * Tests the containsKey method on a map.
     * 
     * @testCaseDesign This test is designed for analyzing the containsKey() method 
     *                     of a new map.
     * @testDescription Instantiates a map and checks that the map is not empty and 
     *                      has the key inside of it.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is not empty and contains the right key.
     */
    @Test
    public void testContainsKey() {
        MapAdapter map = helper(0,10);
        Assert.assertTrue(map.containsKey(3));
        Assert.assertEquals(10, map.size());
    }
    
    /**
     * Tests the containsKey method on an empty map.
     * 
     * @testCaseDesign This test is designed for analyzing the containsKey() method 
     *                 of an empty map.
     * @testDescription Instantiates a map and checks that the map is empty (it doesn't have any key) 
     *                  and has size 0.     
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is empty and does not contain any keys.
     */
    @Test
    public void testContainsKeyOnEmptyMap() {
        MapAdapter map = new MapAdapter();
        Assert.assertFalse(map.containsKey(3));
        Assert.assertEquals(0, map.size());
    }

    /**
     * Tests the containsKey() method on a map passing it a null key.
     * 
     * @testCaseDesign This test is designed for analyzing the containsKey() method 
     *                 of a new map passing it a null value and checking the throwing of an exception.
     * @testDescription Instantiates a map and checks that the method throws a NullPointerException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map doesn't have the null key inside of it.
     */
    @Test (expected = NullPointerException.class)
    public void testContainsKeyNull() {
        MapAdapter map = helper(0,10);
        map.containsKey(null);
    }

    /**
     * Tests the containsKey() method on an empty map passing it a null key.
     * 
     * @testCaseDesign This test is designed for analyzing the containsKey() method 
     *                 on an empty map passing it a null value and checking the throwing of an exception.
     * @testDescription Instantiates an empty map and checks that the method throws a NullPointerException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map is empty and doesn't have the null key inside of it.
     */
    @Test (expected = NullPointerException.class)
    public void testContainsKeyNullOnEmptyMap() {
        MapAdapter map = new MapAdapter();
        map.containsKey(null);
    }
    
    /**
     * Tests the containsKey() method on a map passing it a <code>double</code> key.
     * 
     * @testCaseDesign This test is designed for analyzing the containsKey() method 
     *                 of a new map passing it a <code>double</code> value of a key
     *                 and checking the throwing of an exception.
     * @testDescription Instantiates a map and checks that the method throws a ClassCastException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map doesn't have the <code>double</code> key inside of it.
     */
    @Test (expected = ClassCastException.class)
    public void testContainsKeyDifferentTypeMap() {
        MapAdapter map = helper(0,10);
        double a = 2.1;
        map.containsKey(a);
    }

    /**
     * Tests the containsValue() method on a map.
     * 
     * @testCaseDesign This test is designed for analyzing the containsValue() method 
     *                 of a new map.
     * @testDescription Instantiates a map and checks that the map contains the right value.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has size 10 and has a value inside of it.
     */
    @Test
    public void testContainsValue() {
        MapAdapter map = helper(0,10);
        Assert.assertTrue(map.containsValue("B"));
        Assert.assertEquals(10, map.size());
    }
    
    /**
     * Tests the containsValue() method on an empty map.
     * 
     * @testCaseDesign This test is designed for analyzing the containsValue() method 
     *                 on an empty map.
     * @testDescription Instantiates a map and checks that the map does not contain 
     *                  any value.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has size 0 and does not have any value.
     */
    @Test
    public void testContainsValueOnEmptyMap() {
        MapAdapter map = new MapAdapter();
        Assert.assertFalse(map.containsValue("B"));
        Assert.assertEquals(0, map.size());
    }

    /**
     * Tests the containsValue() method on a map passing it a <code>null</code> value.
     * 
     * @testCaseDesign This test is designed for analyzing the containsValue() method 
     *                 of a new map passing it a <code>null</code> value 
     *                 and checking the throwing of an exception.
     * @testDescription Instantiates a map and checks that the method throws a NullPointerException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map doesn't have the <code>null</code> value inside of it.
     */
    @Test (expected = NullPointerException.class)
    public void testContainsValueNull() {
        MapAdapter map = helper(0,10);
        map.containsValue(null);
    }

    /**
     * Tests the containsValue() method on an empty map passing it a <code>null</code> value.
     * 
     * @testCaseDesign This test is designed for analyzing the containsValue() method 
     *                 of an empty map passing it a <code>null</code> value 
     *                 and checking the throwing of an exception.
     * @testDescription Instantiates a map and checks that the method throws a NullPointerException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map doesn't have the <code>null</code> value inside of it and it has size 0.
     */
    @Test
    public void testContainsValueNullOnEmptyMap() {
        MapAdapter map = new MapAdapter();
        Assert.assertThrows(NullPointerException.class,()-> {map.containsValue(null);});
        Assert.assertEquals(0, map.size());
    }

    /**
     * Tests the containsValue() method on a map passing it a <code>double</code> value.
     * 
     * @testCaseDesign This test is designed for analyzing the containsValue() method 
     *                 of a new map passing it a <code>value</code> value 
     *                 and checking the throwing of an exception.
     * @testDescription Instantiates a map and checks that the method throws a ClassCastException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map doesn't have the <code>double</code> value inside of it.
     */
    @Test (expected = ClassCastException.class)
    public void testContainsValueDifferentTypeMap() {
        MapAdapter map = helper(0,10);
        double a = 2.1;
        map.containsValue(a);
    }
    
    /**
     * Tests the clear method on an empty map.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of an
     *                     empty map when clear() is called on it.
     * @testDescription Instantiates an empty map, calls the clear() method and
     *                      checks that the  map is empty and has size zero.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is still empty.
     * @expectedResults The map is still empty and has size zero.
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
     * @testCaseDesign This test is designed for analyzing the behaviour of an
     *                     empty map when clear() is called on it.
     * @testDescription Instantiates an empty map, calls the clear() method and
     *                      checks that the  map is empty and has size zero.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is still empty.
     * @expectedResults The map is still empty and has size zero.
     */
    @Test
    public void testClearNonEmptyMap() {
        MapAdapter map = helper(0,10);

        map.clear();

        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
    }


}
