package myTest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.*;
import myAdapter.*;
import myAdapter.MapAdapter.EntryAdapter;

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

    

    /**
     * Tests the get method on a map.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when get() is called on it.
     * @testDescription Instantiates a map, put a key and a value inside of it and
     *                  checks that the key is associated to a value.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has a value associated to a key.
     */
    @Test
    public void testGet() {
        
        MapAdapter map = new MapAdapter();
        map.put(2,3);
        
        Assert.assertEquals(3, map.get(2));
    }

    /**
     * Tests the get method on a map when a null key is passed.
     *
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when get() is called on it when a null key is passed.
     * @testDescription Instantiates a map, and checks that the method throws a NullPointerException
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map doesn't have a value associated to a <code>null</code> key.
     */
    @Test (expected = NullPointerException.class)
    public void testGetNullKey() {
        
        MapAdapter map = helper(1, 10);
        map.get(null);
        
    }

    /**
     * Tests the get method on a different type key. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when get() is called on it using a different type of key.
     * @testDescription Instantiates a map, put a key and a value inside of it,
     *                  checks if it throws ClassCastException 
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has not a value associated to the different key.
     */
    @Test (expected = ClassCastException.class)
    public void testGetDifferentTypeKey() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        double a = 2.1;
        map.get(a);
    }

    /**
     * Tests the get method on an empty map.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     empty map when get() is called on it .
     * @testDescription Instantiates a map and checks if the map is empty .
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has no value.
     */
    @Test
    public void testGetOnEmptyMap() {
        
        MapAdapter map = new MapAdapter();
        Assert.assertEquals(null,map.get(1));
    }

    /**
     * Tests the put method on a map.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it.
     * @testDescription Instantiates a map, put a key and a value inside of it,
     *                  checks if the key is present and returns the correspondig value.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has a value associated to a key.
     */
    @Test
    public void testPut() {
        
        MapAdapter map = new MapAdapter();
        map.put(2,3);

        Assert.assertTrue(map.containsKey(2));
        Assert.assertEquals(3, map.get(2));
    }

    /**
     * Tests the put method on a map using a different type of key. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it using a different type of key.
     * 
     * @testDescription Instantiates a map, put a different type of key and a value inside of it,
     *                  checks if it throws ClassCastException.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has not a valid key.
     */
    @Test (expected = ClassCastException.class)
    public void testPutKeyDifferentTypeMap() {
        
        MapAdapter map = helper(1, 10);
        double a = 2.1;
        map.put(a,3);

    }

    /**
     * Tests the put method on a map using a different type of value.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it using a different type of value.
     * 
     * @testDescription Instantiates a map, put a key and a different type of value inside it,
     *                  checks if throws ClassCastException.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has not a valid value associated to a key.
     */
    @Test (expected = ClassCastException.class)
    public void testPutDifferentTypeValue() {
        
        MapAdapter map = helper(1, 10);
        double a = 2.1;
        map.put(1,a);

    }

    /**
     * Tests the put method on a map when it has a null key.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it with a null key
     * 
     * @testDescription Instantiates a map, put a null key and a value inside of it and
     *                  checks that the method throws a NullPointerException
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is an invalid instance.
     * @expectedResults The map has an invalid key.
     */
    @Test (expected = NullPointerException.class)
    public void testPutNullKey() {
        
        MapAdapter map = new MapAdapter();
        map.put(null,3);
    }

    /**
     * Tests the put method on a map when a null value is passed. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it with a null value.
     * 
     * @testDescription Instantiates a map, put a key and a null value inside of it and
     *                  checks that the method throws a NullPointerException.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is an invalid instance.
     * @expectedResults The map has an invalid value.
     */
    @Test (expected = NullPointerException.class)
    public void testPutNullValue() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, null);
        
    }

    /**
     * Tests the remove method on a map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when remove() is called on it.
     * 
     * @testDescription Instantiates a map, put a key and a value inside of it and
     *                  checks if the value is still associated to the key .
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is an invalid instance.
     * @expectedResults The map has no key.
     */
    @Test 
    public void testRemove() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);

        Assert.assertEquals(3, map.remove(2));   
    }

    /**
     * Tests the remove method on a map using a different type of key. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when remove() is called on it with a different type of key.
     * 
     * @testDescription Instantiates a map and checks if the method throws  ClassCastException.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The method throws ClassCastException.
     */
    @Test (expected = ClassCastException.class)
    public void testRemoveDifferentTypeKey() {
        
        MapAdapter map = helper(1, 10);
        double a = 2.1;
        map.remove(a);
    }

    /**
     * Tests the remove method on a map when a null key is passed.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when remove() is called on it with a null key.
     * 
     * @testDescription Instantiates a map, checks if the method throws a NullPointerException.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has an invalid key.
     */
    @Test (expected = NullPointerException.class)
    public void testRemoveNullKey() {
        
        MapAdapter map = helper(1, 10);
        map.remove(null);
    }

    /**
     * Tests the remove method on a empty map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     empty map when remove() is called on it.
     * 
     * @testDescription Instantiates a map and checks if the map is already empty.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has no value.
     */
    @Test
    public void testRemoveOnEmptyMap() {
        
        MapAdapter map = new MapAdapter();
        
        Assert.assertEquals(null, map.remove(1));
    }

    /**
     * Tests the putAll method on a map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when putAll() is called on it.
     * 
     * @testDescription Instantiates two maps, calls putAll() method and
     *                  checks if the second map is equals to the first one.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The second map is equals to the first one.
     */
    @Test 
    public void testPutAll() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= map;
        map2.putAll(map);
        
        Assert.assertEquals(map, map2);
    }

    /**
     * Tests the put method on a map when a null value is passed. //TODO: far vedere a tess
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it with a null value.
     * 
     * @testDescription Instantiates a map, put a key and a null value inside of it and
     *                  checks that the method throws a NullPointerException.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is an invalid instance.
     * @expectedResults The map has an invalid value.
     */
    @Test //(expected = ClassCastException.class)
    public void testPutAllNullKey() {
        
        MapAdapter map = helper(1, 10);
        Assert.assertThrows(ClassCastException.class,()->{map.put(2.1, 3);});
        MapAdapter map2= map;
        //map2.putAll(map);
        Assert.assertThrows(ClassCastException.class,()->{map2.putAll(map);});
    }

    
    /**
     * Tests the equals method on a map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when equals() is called on it.
     * 
     * @testDescription Instantiates a map, put a key and a value inside of it and
     *                  checks if the equals() method works correctly.
     * 
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The two map are equals
     */
    @Test 
    public void testEquals() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.putAll(map);
        
        Assert.assertTrue(map2.equals(map));
    }

    /**
     * Tests the equals method on two maps with different values . 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                 map when equals() is called on it using different values compared to another map.
     * 
     * @testDescription Instantiates two maps, and checks if the map are equals.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The map are valid instance.
     * @expectedResults The maps are not equals.
     */
    @Test 
    public void testEqualsDifferentValues() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.put(2,4);
        
        Assert.assertFalse(map2.entrySet().equals(map.entrySet()));
    }

    /**
     * Tests the equals method on two maps with different key. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when equals() is called on it using different keys compared to another map.
     * 
     * @testDescription Instantiates a map, and checks if the map are equals.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The map are not equals.
     */
    @Test 
    public void testEqualsDifferentKey() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.put(3,3);
        
        Assert.assertFalse(map2.entrySet().equals(map.entrySet()));
    }

    /**
     * Tests the equals method on two different maps. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of a 
     *                 map when equals() is called on it compared to another map.
     * 
     * @testDescription Instantiates two maps, and checks if the map are equals.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The map are valid instance.
     * @expectedResults The maps are not equals.
     */
    @Test 
    public void testEqualsDifferentMaps() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.put(3,4);
        
        Assert.assertFalse(map2.entrySet().equals(map.entrySet()));
    }
}
