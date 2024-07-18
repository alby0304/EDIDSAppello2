package myTest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import myAdapter.*;
import myAdapter.MapAdapter.EntryAdapter;
import java.util.NoSuchElementException;

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
    public void testSizeOnEmptyMap(){
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
     * @testDescription Instantiates a map, put a null key and
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
     * @testDescription Instantiates a map, put a null value and
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
     * Tests the put method on a map when a existing key is passed. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                 map when put() is called on it with a existing key.
     * @testDescription Instantiates a map and checks if the previous value 
     *                  associated with a specified key has been replaced.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is an valid instance.
     * @expectedResults The map has a new value associated with the same key.
     */
    @Test
    public void testPutExistingKey() {
        MapAdapter map = new MapAdapter();
        map.put (2,"A");
        Assert.assertEquals("A", map.get(2));
        Assert.assertEquals("A", map.put(2,"B"));
        Assert.assertEquals("B", map.get(2));
        
    }

    /**
     * Tests the remove method on a map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when remove() is called on it.
     * @testDescription Instantiates a map and
     *                  checks if the value is still associated to the key .
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
     * Tests the remove method with a key that is not in the map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                 map when remove() is called on it with a key that is not present.
     * @testDescription Instantiates a map and checks if the map has the key.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The map has not the specified key.
     */
    @Test
    public void testRemoveKeyNotInMap() {
        
        MapAdapter map = helper(1,10);
        Assert.assertEquals(null, map.remove(20));
    }

    /**
     * Tests the putAll method on a map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when putAll() is called on it.
     * @testDescription Instantiates two maps, calls putAll() method and
     *                  checks if the second map is equals to the first one.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults The second map is equals to the first one.
     */
    @Test 
    public void testPutAll() {
        
        MapAdapter map = helper(1,10);
        MapAdapter map2 = new MapAdapter();
        map2.putAll(map);
        Assert.assertTrue(map2.equals(map));
    }

    /**
     * Tests the put method on a map when a null value is passed.
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when put() is called on it with a null value.
     * @testDescription Instantiates a map, put a key and a null value inside of it and
     *                  checks that the method throws a NullPointerException.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is an invalid instance.
     * @expectedResults The map has an invalid value.
     */
    @Test //(expected = ClassCastException.class)
    public void testPutAllNullPtr() {
        
        MapAdapter map = helper(1, 10);
        Assert.assertThrows(NullPointerException.class,()->{map.putAll(null);});
    }


    /**
     * Tests the putAll() method with a different type ok key. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                 map when putAll() is called on it and is passed a map with a different type of key.
     * @testDescription Instantiates a map and checks if the class of the specified key 
     *                  prevents it from being stored in this map.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a invalid instance.
     * @expectedResults the map cannot contain a different type of key.
     */
    @Test 
    public void testPutAllDifferentTypeKey() {
        
        MapAdapter map = helper(1, 10);
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        Assert.assertThrows(ClassCastException.class,()->{map.putAll(map2);});
    }

    /**
     * Tests the putAll() method passing an empty map. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                 map when putAll() is called on it and is passed an empty map.
     * @testDescription Instantiates a map and checks if map2 is always equals to map.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map is a valid instance.
     * @expectedResults the map is empty.
     */
    @Test 
    public void testPutAllEmptyMap() {
        
        MapAdapter map = helper(1, 10);
        MapAdapter map2 = helper(1,10);
        MapAdapter map3 = new MapAdapter();
        map2.putAll(map3);
        Assert.assertTrue(map2.equals(map));
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
     * Tests the creation of a keySet of a map.
     * 
     * @testCaseDesign This test is designed for analyzing if keySet() method 
     *                 could create a keySet of a map
     * @testDescription Instantiates an empty map, calls the keySet() method and
     *                      checks that the map has a keySet.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map and the keySet are valid intances.
     * @expectedResults The keySet is correctly instantiated.
     */
    @Test
    public void testCreationKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertEquals(10, keySet.size());
        Assert.assertTrue(keySet.contains(9));
    }

    /**
     * Tests the creation of a collecion of values of a map.
     * 
     * @testCaseDesign This test is designed for analyzing if values() method 
     *                 could return a colleciton of values of a map
     * @testDescription Instantiates a map, calls the values() method and
     *                      checks that the collection of values have all the values of the map.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map and the collection are valid intances.
     * @expectedResults The collection of values is correctly instantiated.
     */
    @Test
    public void testCreationValues() {
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertEquals(10, values.size());
        Assert.assertTrue(values.contains("A"));
    }

    /**
     * Tests the creation of a entrySet of a map.
     * 
     * @testCaseDesign This test is designed for analyzing if entrySet() method 
     *                 could create an entrySet of a map
     * @testDescription Instantiates an empty map, calls the entrySet() method and
     *                      checks that the map has a entrySet.
     * @preCondition The map is correctly instantiated.
     * @postCondition The map and the entrySet are valid intances.
     * @expectedResults The entrySet is correctly instantiated.
     */
    @Test
    public void testCreationEntrySet() {
        MapAdapter map = new MapAdapter();
        map.put(0,"A");
        map.put(1,"B");
        HSet entrySet = map.entrySet();
        Assert.assertEquals(2, entrySet.size());
        Assert.assertEquals("A", map.get(0));
        EntryAdapter entry = new EntryAdapter(0,"A");
        Assert.assertTrue(entrySet.contains(entry));
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
     * @expectedResults The two maps are equals
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
     * @testDescription Instantiates two maps, and checks if the maps are equals.
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps are not equals.
     */
    @Test 
    public void testEqualsDifferentValuesMap() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.put(2,4);
        
        Assert.assertFalse(map2.equals(map));
    }

    /**
     * Tests the equals method on two maps with different key. 
     * 
     * @testCaseDesign This test is designed for analyzing the behaviour of a
     *                     map when equals() is called on it using different keys compared to another map.
     * 
     * @testDescription Instantiates a map, and checks if the maps are equals.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps are not equals.
     */
    @Test 
    public void testEqualsDifferentKey() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.put(3,3);
        
        Assert.assertFalse(map2.equals(map));
    }

    /**
     * Tests the equals method on two different maps. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of a 
     *                 map when equals() is called on it compared to another map.
     * 
     * @testDescription Instantiates two maps, and checks if the maps are equals.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps are not equals.
     */
    @Test 
    public void testEqualsDifferentMaps() {
        
        MapAdapter map = new MapAdapter();
        map.put(2, 3);
        MapAdapter map2= new MapAdapter();
        map2.put(3,4);
        
        Assert.assertFalse(map2.equals(map));
    }

    /**
     * Tests the hashCode method on two maps. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of two identical  
     *                 maps when hashCode() is called.
     * 
     * @testDescription Instantiates two maps, and checks if the maps have the same hashCode.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps have the same hashCode.
     */
    @Test 
    public void testhashCodeForEqualMaps() {
        
        MapAdapter map = helper(1,10);
        MapAdapter map2= map;
                
        Assert.assertEquals(map.hashCode(),map2.hashCode());
    }

    /**
     * Tests the hashCode method on two different maps. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of two different  
     *                 maps when hashCode() is called.
     * @testDescription Instantiates two maps, and checks if the maps have a different hashCode.
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps have a different hashCode.
     */
    @Test 
    public void testhashCodeForDifferentMaps() {
        
        MapAdapter map = helper(1,10);
        MapAdapter map2= helper(11,15);
                
        Assert.assertNotEquals(map.hashCode(),map2.hashCode());
    }

    /**
     * Tests the hashCode method on two maps with different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of two  
     *                 maps with different values when hashCode() is called.
     * @testDescription Instantiates two maps, and checks if the maps have a different hashCode.
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps have a different hashCode.
     */
    @Test 
    public void testhashCodeForDifferentValuelMaps() {
        
        MapAdapter map = helper(1,10);
        MapAdapter map2= helper(1,10);
        map2.put(2, "F");  
        Assert.assertNotEquals(map.hashCode(),map2.hashCode());
    }

    /**
     * Tests the hashCode method on two maps, after calling the put() method
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of two identical  
     *                 maps when hashCode() is called after calling put() on one of those.
     * 
     * @testDescription Instantiates two maps, and checks if the maps have a different hashCode.
     * 
     * @preCondition The maps are correctly instantiated.
     * @postCondition The maps are valid instance.
     * @expectedResults The maps have a different hashCode.
     */
    @Test 
    public void testhashCodeForDifferentKeyAndPutMaps() {
        
        MapAdapter map = helper(1,10);
        MapAdapter map2= helper(1,10);
        map2.put(11, "D");  
        Assert.assertNotEquals(map.hashCode(),map2.hashCode());
    }





    //ENTRYADAPTER TEST





    /**
     * Tests the EntryAdapter constructor with null key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of the EntryAdapter constructor with null key
     *                 
     * @testDescription Instantiates an entry with key null.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entry has null key.
     */
    @Test (expected = NullPointerException.class)
    public void testEntryConstructorNullKey() {
        
        EntryAdapter e = new EntryAdapter(null,2);
    }

    /**
     * Tests the EntryAdapter constructor with null value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour of 
     *                 the EntryAdapter constructor with null value
     * 
     * @testDescription Instantiates an entry with value null.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entry has null value.
     */
    @Test (expected = NullPointerException.class)
    public void testEntryConstructorNullValue() {
        
        EntryAdapter e = new EntryAdapter(1,null);
    }

    /**
     * Tests the getKey method of the EntryAdapter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of getKey() on an entry.
     * 
     * @testDescription Instantiates an entry and checks if the key is correct
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entry has a correct key
     */
    @Test 
    public void testEntryGetkey() {
        EntryAdapter e = new EntryAdapter(1,2);

        Assert.assertEquals(1, e.getKey());
    }

    /**
     * Tests the getValue method of the EntryAdapter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of getValue() on an entry.
     * 
     * @testDescription Instantiates an entry and checks if the value is correct.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entry has a correct value.
     */
    @Test 
    public void testEntryGetValue() {
        EntryAdapter e= new EntryAdapter(1,2);

        Assert.assertEquals(2, e.getValue());
    }

    /**
     * Tests the setValue method of the EntryAdapter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of setValue().
     * 
     * @testDescription Instantiates an entry, and checks if the replacement has taken place.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The value of the entry has changed.
     */
    @Test 
    public void testEntrySetValue() {
        
        EntryAdapter e= new EntryAdapter(1,2);
        
        Assert.assertEquals(2, e.setValue(4));
    }

    /**
     * Tests the setValue method of the EntryAdapter using null value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of setValue() passing null value.
     * 
     * @testDescription Instantiates an entry, and checks if the method throws NullPointerException
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entry has an invalid value.
     */
    @Test (expected = NullPointerException.class)
    public void testEntrySetValueNull() {
        
        EntryAdapter e= new EntryAdapter(1,4);
        e.setValue(null);
        
    }

 
    /**
     * Tests the setValue method of the EntryAdapter using different type of value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of setValue() passing a different type of value.
     * 
     * @testDescription Instantiates an entry, and checks if the method throws ClassCastException
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entry has an invalid value.
     */
    @Test (expected = ClassCastException.class)
    public void testEntrySetValueDifferentType() {
        
        EntryAdapter e= new EntryAdapter(1,4);
        e.setValue(2.3);
    }

    /**
     * Tests the equals method of the EntryAdapter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of equals() when the entries are the same.
     *  
     * @testDescription Instantiates two entries and checks if are equals.
     * 
     * @preCondition The entries are correctly instantiated.
     * @postCondition The entries are valid instance.
     * @expectedResults The entries are equals.
     */
    @Test
    public void testEntryEquals() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(1,2);

        Assert.assertTrue(e.equals(e2));
    }

    /**
     * Tests the equals method of EntryAdapter using the same key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of equals() when the entries have the same key.
     * 
     * @testDescription Instantiates two entries and checks if the keys are equals.
     * 
     * @preCondition The entries are correctly instantiated.
     * @postCondition The entries are valid instance.
     * @expectedResults The entries have the same keys.
     */
    @Test
    public void testEntryEqualsWithSameKey() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(1,3);

        Assert.assertTrue(e.getKey().equals(e2.getKey()));
    }

    /**
     * Tests the equals method of EntryAdapter using the same value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of equals() when the entries have the same value.
     * 
     * @testDescription Instantiates two entries and checks if the value are equals.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entries have the same value.
     */
    @Test
    public void testEntryEqualsWithSameValue() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(2,2);

        Assert.assertTrue(e.getValue().equals(e2.getValue()));
    }
    
    /**
     * Tests the equals method of EntryAdapter using different keys. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of equals() when the entries have different keys.
     * 
     * @testDescription IInstantiates two entries and checks if the keys are different.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entries have different keys.
     */
    @Test
    public void testEntryEqualsWithDifferentKey() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(2,3);

        Assert.assertFalse(e.getKey().equals(e2.getKey()));
    }

    /**
     * Tests the equals method of EntryAdapter using different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of equals() when the entries have different values.
     * 
     * @testDescription Instantiates two entries and checks if the values are different
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The entries have different values.
     */
    @Test
    public void testEntryEqualsWithDifferentValue() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(2,3);

        Assert.assertFalse(e.getValue().equals(e2.getValue()));
    }

    /**
     * Tests the equals method of EntryAdapter using different entries. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of equals() using different entries
     * 
     * @testDescription Instantiates two entries and checks if they are equals
     * 
     * @preCondition The entries are correctly instantiated.
     * @postCondition The entries are valid instance.
     * @expectedResults The entries are different.
     */
    @Test
    public void testEntryEqualsWithDifferentEntry() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(2,3);

        Assert.assertFalse(e.equals(e2));
    }
    
    /**
     * Tests the <code>hashCode()</code> method of EntryAdapter comparing 2 identical entries. 
     *     
     * @testCaseDesign This test is designed for analyzing the hashCode 
     *                 of 2 identical entries
     * 
     * @testDescription Instantiates 2 entries and checks if the hashCodes are the same.
     * 
     * @preCondition The entry is correctly instantiated.
     * @postCondition The entry is valid instance.
     * @expectedResults The hashCodes of the 2 entries are the same.
     */
    @Test
    public void testhashCodeForEqualEntry() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(1,2);

        Assert.assertEquals(e.hashCode(),e2.hashCode());
    }

    /**
     * Tests the <code>hashCode()</code> method of EntryAdapter comparing 2 different entries. 
     *     
     * @testCaseDesign This test is designed for analyzing the hashCode 
     *                 of 2 different entries
     * @testDescription Instantiates 2 entries and checks if the hashCodes are different.
     * @preCondition The entries are correctly instantiated.
     * @postCondition The entris are valid instance.
     * @expectedResults The entries have different hashCodes.
     */
    @Test
    public void testhashCodeForDifferentEntry() {
        
        EntryAdapter e = new EntryAdapter(1,2);
        EntryAdapter e2 = new EntryAdapter(7,"A");

        Assert.assertNotEquals(e.hashCode(),e2.hashCode());
    }





    //TODO: TEST HSET RITORNATO DA KEYSET




    
    
    /**
     * Tests the size method of keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when size() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks its size.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet has size 10.
     */
    @Test
    public void testSizeKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertEquals(10, keySet.size());
    }

    /**
     * Tests the size method of Hset on empty keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a empty keySet when size() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks its size.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet has size 0.
     */
    @Test
    public void testSizeKeySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertEquals(0, keySet.size());
    }
    
    /**
     * Tests the isEmpty method of Hset. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if it is empty.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet is not empty.
     */
    @Test
    public void testIsEmptyKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.isEmpty());
    }
    
    /**
     * Tests the isEmpty method of Hset on empty keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty keySet when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if it is empty.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet is empty.
     */
    @Test
    public void testIsEmptyKeySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertTrue(keySet.isEmpty());
    }
    
     /**
     * Tests the contains method of HSet on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when contains() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks it contains the specific key.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet contains the specific key.
     */
    @Test
    public void testContainsKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertTrue(keySet.contains(0));
    }
    
     /**
     * Tests the contains() method of Hset on an empty keySet . 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty keyset when contains() is called on it.
     * 
     * @testDescription Instantiates a keyset and checks if it doesn't contain the specific key.
     * 
     * @preCondition The keyset is correctly instantiated.
     * @postCondition The keyset is valid instance.
     * @expectedResults The keyset doesn't contain the specific key.
     */
    @Test
    public void testContainsKeySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.contains(0));
    }
    
     /**
     * Tests the contains method of Hset on a keySet using null key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using null key when contains() is called on it.
     * 
     * @testDescription Instantiates a keyset and checks if it throws NullPointerException.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet can't contain a null key.
     */
    @Test
    public void testContainsNullKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.contains(null);});
    }
    
    /**
     * Tests the contains() method of Hset on empty keySet using null key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty keySet when contains() is called on it using null key.
     * 
     * @testDescription Instantiates an empty keySet and checks if it can contain a null key .
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet does not contain null key.
     */
    @Test
    public void testContainsNullKeySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.contains(null);});
    }

    /**
     * Tests the contains method of Hset on keySet using a different type of key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using a different type of key when contains() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if it is empty.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet can't contain different type of key.
     */
    @Test
    public void testContainsDifferentTypeKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.contains(2.1);});
    }

    /**
     * Tests the creation of an iterator of a keySet. 
     *     
     * @testCaseDesign This test is designed for checking the creation 
     *                 of an Iterator of a keySet
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if it's valid.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iteration has more elements
     */
    @Test
    public void testIteratorCreationKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertTrue(it.hasNext());
    }
    
    /**
     * Tests the creation of an iterator of an empty keySet. 
     *     
     * @testCaseDesign This test is designed for checking the creation 
     *                 of an Iterator of a keySet that is empty.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if it's invalid.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorCreationonEmptyKeySet(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertFalse(it.hasNext());
    }
    
    /**
     * Tests toArray method of Hset on keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when toArray() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks the array is correctly created.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The array has correct length and value in order.
     */
    @Test
    public void testToArrayKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        Object[] vet2 = keySet.toArray();
        Assert.assertEquals(2,vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of Hset on a keySet passing it an array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when toArray() is called on it passing an array.
     * 
     * @testDescription Instantiates a keySet and checks if the array is correctly created.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The array has correct length and value in order.
     */
    @Test
    public void testToArrayObjectKeySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet keySet = map.keySet();
        String[] vet = new String[3];
        Object[] vet2 = keySet.toArray(vet);
        Assert.assertEquals("3",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }
    
     /**
     * Tests the toArray() method of Hset on a keySet using different type of object. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using a different type of object when toArray() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if the array is correctly created.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The array has correct length and value.
     */
    @Test
    public void testToArrayObjectDifferentTypeKeySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet keySet = map.keySet();
        MapAdapter[] vet = new MapAdapter[3];
        Object[] vet2 = keySet.toArray(vet);
        Assert.assertEquals("3",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of Hset on a keySet passing it a bigger array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when toArray() is called on it passing an array bigger than its size.
     * 
     * @testDescription Instantiates a keySet and checks if the array is correctly created.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The array has correct length, value in order and and the last one is <code>null</code>.
     */
    @Test
    public void testToArrayObjectBiggerSizeKeySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet keySet = map.keySet();
        String[] vet = new String[4];
        Object[] vet2 = keySet.toArray(vet);
        Assert.assertEquals("3",vet2[0]);
        Assert.assertEquals(null,vet2[3]);
        Assert.assertEquals(4,vet2.length);
    }
    
    /**
     * Tests the toArray() method of Hset on a keySet passing it a smaller array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when toArray() is called on it passing an array smaller than its size.
     * 
     * @testDescription Instantiates a keySet and checks if the array is correctly created.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The array has correct length, value in order and and the last one is 3.
     */
    @Test
    public void testToArrayObjectSmallerSizeKeySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet keySet = map.keySet();
        String[] vet = new String[2];
        Object[] vet2 = keySet.toArray(vet);
        Assert.assertEquals("3",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of Hset on a keySet passing it null element. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when toArray() is called on it passing a null element.
     * 
     * @testDescription Instantiates a keySet and checks if the method throws NullPointerException.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The array is not created.
     */
    @Test
    public void testToArrayObjectNullPointerKeySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{Object[] vet2 = keySet.toArray(null);});
    }
    
    /**
     * Tests the add() method of Hset on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when add() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if throws UnsupportedOperationException.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet can't use the opertion. 
     */
    @Test
    public void testAddKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{keySet.add(1);});
    }

    /**
     * Tests the remove() method of Hset on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when remove() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if the key is still in the keySet .
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet has 9 keys.
     */
    @Test
    public void testRemoveKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        keySet.remove(0);
        Assert.assertEquals(9,keySet.size());
        Assert.assertFalse(keySet.contains(0));
    }
    
     /**
     * Tests the remove() method of Hset on a keySet using different type of key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using different type of key when remove() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if the method throws ClassCastException .
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet can't use remove with different type of key.
     */
    @Test
    public void testRemoveDifferentTypeKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.remove(2.1);});
    }

    /**
     * Tests the remove() method of Hset on a keySet passing a null key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when remove() is called on it passing a null key.
     * 
     * @testDescription Instantiates a keySet and checks if a NullPointerException is thrown.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet has 10 keys.
     */
    @Test
    public void testRemoveNullKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.remove(null);});
    }

    /**
     * Tests the remove() method of Hset on an empty keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty keySet when remove() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if the keySet has no key.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet has 0 keys.
     */
    @Test
    public void testRemoveOnEmptyKeySet(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.remove(0));
    }

    /**
     * Tests the remove() method on a keySet passing a key not in the set. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when remove() is called on it passing a key not in the set.
     * 
     * @testDescription Instantiates a keySet and checks if the keySet is the same.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet has 10 keys.
     */
    @Test
    public void testRemoveNotInKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.remove(11));
    }
    
    /**
     * Tests the containsAll() method of Hset on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when containsAll() is called on it.
     * 
     * @testDescription Instantiates two keySet and checks if the two keySets have the same element .
     * 
     * @preCondition The keySet are correctly instantiated.
     * @postCondition The keySet are valid instance.
     * @expectedResults The two keySets have the same element.
     */
    @Test
    public void testContainsAllKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.containsAll(keySet2));
    }

    /**
     * Tests the containsAll() method on a keySet passing a keySet with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when containsAll() is called on it passing a keySet a little difference.
     * 
     * @testDescription Instantiates a keySet and checks if the 2 keySets are different.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The 2 keySets have different elements.
     */
    @Test
    public void testContainsAllSomeDifferentKeySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.containsAll(keySet2));
    }

    /**
     * Tests the containsAll() method on a keySet passing a different keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when containsAll() is called on it passing a different keySet.
     * 
     * @testDescription Instantiates a keySet and checks if the 2 keySets are different.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The 2 keySets are completely different.
     */
    @Test
    public void testContainsAllNoOneInKeySet(){
        MapAdapter map = helper(4,7);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.containsAll(keySet2));
    }

    /**
     * Tests the containsAll() method on a keySet using null key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using null key when containsAll() is called on it.
     * 
     * @testDescription Instantiates two keySets and checks if a NullPointerException is thrown.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults NullPointerException.
     */
    @Test
    public void testContainsAllNullPointerKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.containsAll(null);});
    }

    /**
     * Tests the containsAll() method on a keySet passing a keySet with different type of keys. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when containsAll() is called on it passing a keySet
     *                 with different type of keys.
     * @testDescription Instantiates a keySet and checks if the 2 keySets are different.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults ClassCastException.
     */
    @Test
    public void testContainsAllClassCastKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.containsAll(keySet2);});
    }

    /**
     * Tests the addAll() method on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when addAll() is called on it.
     * @testDescription Instantiates a keySet and checks if addAll() metod is not supported.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults myAdapter.UnsopportedOperationException.
     */
    @Test
    public void testAddAllKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{keySet.addAll(keySet2);});
    }

    /**
     * Tests the retainAll() method on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when retainAll() is called on it passing another keySet.
     * @testDescription Instantiates a keySet and checks if the set is modified.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The keySet is not modified.
     */
    @Test
    public void testRetainAllKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.retainAll(keySet2));
        Assert.assertEquals(3,keySet.size());
    }
    
    /**
     * Tests the retainAll() method on a keySet using keySet with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using keySet with some difference when retainAll() is called on it.
     * 
     * @testDescription Instantiates a keySet and checks if the set is modified.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The keySet is modified.
     */
    @Test
    public void testRetainAllSomeDifferentKeySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        map.put(4,"S");
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.retainAll(keySet2));
        Assert.assertEquals(2,keySet.size());
    }
    
    /**
     * Tests the retainAll() method on a keySet passing a different keyset. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when retainAll() is called on it passing a different keySet.
     * @testDescription Instantiates a keySet and checks if the set is modified.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The keySet is modified.
     */
    @Test
    public void testRetainAllNoOneInKeySet(){
        MapAdapter map = helper(4,7);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.retainAll(keySet2));
        Assert.assertEquals(0,keySet.size());
    }

    /**
     * Tests the retainAll() method on a keySet passing null parameter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when retainAll() is called on it passing a null parameter.
     * @testDescription Instantiates a keySet and checks if NullPointerException is thrown.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is a valid instance.
     * @expectedResults The keySet is not modified.
     */
    @Test
    public void testRetainAllNullPointerKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.retainAll(null);});
    }
    
     /**
     * Tests the retainAll() method on a keySet passing a keySet with defferent type of key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet passing a keySet with defferent type of key when retainAll() is called on it.
     *     
     * @testDescription Instantiates a keySet and checks if the method throws ClassCastException.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults ClassCastException
     */
    @Test
    public void testRetainAllClassCastKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.retainAll(keySet2);});
    }

    /**
     * Tests the removeAll() method on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when removeAll() is called on it passing another keySet.
     * @testDescription Instantiates a keySet and checks if all its element has been removed .
     * 
     * @preCondition The keySet are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The keySet is empty.
     */
    @Test
    public void testRemoveAllKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.removeAll(keySet2));
        Assert.assertEquals(0,keySet.size());
    }
    
    /**
     * Tests the removeAll() method on a keySet using keySet with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using keySet with some difference when removeAll() is called on it.
     * 
     * @testDescription Instantiates two keySet and checks if the modification occurred.
     * 
     * @preCondition The keySets are correctly instantiated.
     * @postCondition The keySets are a valid instance.
     * @expectedResults The keySet has less key.
     */
    @Test
    public void testRemoveAllSomeDifferentKeySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        map.put(4,"S");
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.removeAll(keySet2));
        Assert.assertEquals(1,keySet.size());
    }

    /**
     * Tests the removeAll() method on a keySet passing a different keyset. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when removeAll() is called on it passing a different keySet.
     * @testDescription Instantiates a keySet and checks if the set is modified.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults The keySet is not modified.
     */
    @Test
    public void testRemoveAllNoOneInKeySet(){
        MapAdapter map = helper(4,7);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.removeAll(keySet2));
    }

    /**
     * Tests the removeAll() method on a keySet passing null parameter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when removeAll() is called on it passing a null parameter.
     * @testDescription Instantiates a keySet and checks if NullPointerException is thrown.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is a valid instance.
     * @expectedResults The keySet is not modified.
     */
    @Test
    public void testRemoveAllNullPointerKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.removeAll(null);});
    }
    
    /**
     * Tests the removeAll() method on a keySet passing a keySet with defferent type of key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet passing a keySet with defferent type of key when removeAll() is called on it.
     *     
     * @testDescription Instantiates a keySet and checks if the method throws ClassCastException.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instances.
     * @expectedResults ClassCastException
     */
    @Test
    public void testRemoveAllClassCastKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.removeAll(keySet2);});
    }

    /**
     * Tests the clear() method on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when clear() is called on it.
     *     
     * @testDescription Instantiates a keySet and checks if it has no key.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet is empty.
     */
    @Test
    public void testClearKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        keySet.clear();
        Assert.assertEquals(0,keySet.size());
    }
    
    /**
     * Tests the clear() method on an empty keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty keySet when clear() is called on it.
     *     
     * @testDescription Instantiates a keySet and checks if it's still empty.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet is empty.
     */
    @Test
    public void testClearOnEmptyKeySet(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        keySet.clear();
        Assert.assertEquals(0,keySet.size());
    }

    /**
     * Tests the equals() method on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when equals() is called on it.
     *     
     * @testDescription Instantiates 2 keySets and checks if they are equal.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instance.
     * @expectedResults The 2 keySets are equal.
     */
    @Test 
    public void testEqualsKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.equals(keySet2));
    }

    /**
     * Tests the equalsquals() method on a keySet using less keyset.  
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet using less keyset when equals() is called on it.
     *     
     * @testDescription Instantiates a keySet and checks if it has no key.
     * 
     * @preCondition The keySet is correctly instantiated.
     * @postCondition The keySet is valid instance.
     * @expectedResults The keySet is not empty.
     */
    @Test 
    public void testEqualsLessKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,9);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.equals(keySet2));
    }

    /**
     * Tests the equals() method on a keySet passing a different keyset. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet passing a different keyset when equals() is called on it.
     *     
     * @testDescription Instantiates 2 keySets and checks if they are not equal.
     * 
     * @preCondition The 2 keySets are correctly instantiated.
     * @postCondition The 2 keySets are valid instance.
     * @expectedResults The 2 keySets are not equal.
     */    
    @Test 
    public void testEqualsDifferentKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.put(11,"A");
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.equals(keySet2));
    }
    
    /**
     * Tests the hashCode() method on a keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet when hashcode() is called on it.
     *     
     * @testDescription Instantiates two keySets and checks if have the same hashCode.
     * 
     * @preCondition The keySets are correctly instantiated.
     * @postCondition The keySets are valid instance.
     * @expectedResults The keySets have the same hashCode.
     */
    @Test 
    public void testHashCodeKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertEquals(keySet.hashCode(),keySet2.hashCode());
    }
    
    /**
     * Tests the hashCode() method on a keySet passing different keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet passing different keySet when hashcode() is called on it.
     *     
     * @testDescription Instantiates two keySets and checks if haven't the same hashCode.
     * 
     * @preCondition The keySets are correctly instantiated.
     * @postCondition The keySets are valid instance.
     * @expectedResults The keySets haven't the same hashCode.
     */
    @Test 
    public void testHashCodeDifferentKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(1);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertNotEquals(keySet.hashCode(),keySet2.hashCode());
    }
    
    
    /**
     * Tests the hashCode() method on a keySet passing null keySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a keySet passing null keySet when hashcode() is called on it.
     *     
     * @testDescription Instantiates a keySet and checks if the hashCode is 0.
     * 
     * @preCondition The keySets are correctly instantiated.
     * @postCondition The keySets are valid instance.
     * @expectedResults The keySets have hashCode 0.
     */
    @Test 
    public void testHashCodeNullKeySet() {
        
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertEquals(0,keySet.hashCode());
    }

    
    


    //TODO: TEST HSET RITORNATO DA VALUES
    

    
    
    /**
     * Tests the size method of Values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when size() is called on it.
     * 
     * @testDescription Instantiates a values and checks its size.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values has size 10.
     */
    @Test
    public void testSizeValues() {
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertEquals(10, values.size());
    }

    /**
     * Tests the size method of HCollection on empty values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a empty vlaues when size() is called on it.
     * 
     * @testDescription Instantiates a values and checks its size.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values has size 0.
     */
    @Test
    public void testSizeValuesOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        Assert.assertEquals(0, values.size());
    }
    
    /**
     * Tests the isEmpty method of values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a values and checks if it is empty.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values is not empty.
     */
    @Test
    public void testIsEmptyValues() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.isEmpty());
    }

    /**
     * Tests the isEmpty method of HCollection on empty values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty values when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a values and checks if it is empty.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values is empty.
     */
    @Test
    public void testIsEmptyValuesOnEmptyValues() {
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        Assert.assertTrue(values.isEmpty());
    }

    /**
     * Tests the contains method of HCollection on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when contains() is called on it.
     * 
     * @testDescription Instantiates a values and checks it contains the specific value.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values contains the specific values.
     */
    @Test
    public void testContainsValues() {
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertTrue(values.contains("A"));
    }

    /**
     * Tests the contains() method of HCollection on a values . 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty values when contains() is called on it.
     * 
     * @testDescription Instantiates a values and checks if it doesn't contain the specific value.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values doesn't contain the specific values.
     */
    @Test
    public void testContainsValuesOnEmptyValue() {
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        Assert.assertFalse(values.contains(0));
    }

    /**
     * Tests the contains method of HCollection on a values using null values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using null key when contains() is called on it.
     * 
     * @testDescription Instantiates a values and checks if it throws NullPointerException.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values can't contain a null values.
     */
    @Test
    public void testContainsNullValues() {
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertThrows(NullPointerException.class,()->{values.contains(null);});
    }

    /**
     * Tests the contains() method of HCollection on empty values using null values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty values when contains() is called on it using null values.
     * 
     * @testDescription Instantiates an empty values and checks if it can contain a null values .
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values does not contain null values.
     */
    @Test
    public void testContainsNullValuesOnEmptyValues() {
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        Assert.assertThrows(NullPointerException.class,()->{values.contains(null);});
    }

    /**
     * Tests the contains method of HCollection on values using a different type of values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using a different type of value when contains() is called on it.
     * 
     * @testDescription Instantiates a values and checks if it is empty.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values can't contain different type of values.
     */
    @Test
    public void testContainsDifferentTypeValues() {
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertThrows(ClassCastException.class,()->{values.contains(2.1);});
    }

    /**
     * Tests the creation of an iterator of a values. 
     *     
     * @testCaseDesign This test is designed for checking the creation 
     *                 of an Iterator of a values
     * 
     * @testDescription Instantiates a values and a iterator and checks if it's valid.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iteration has more elements
     */
    @Test
    public void testIteratorCreationValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertTrue(it.hasNext());
    }

    /**
     * Tests the creation of an iterator of an empty values. 
     *     
     * @testCaseDesign This test is designed for checking the creation 
     *                 of an Iterator of a values that is empty.
     * 
     * @testDescription Instantiates a values and a iterator and checks if it's invalid.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorCreationonEmptyValues(){
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Tests toArray method of HCollection on values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when toArray() is called on it.
     * 
     * @testDescription Instantiates a values and checks the array is correctly created.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The array has correct length and value in order.
     */
    @Test
    public void testToArrayValues(){
        MapAdapter map = helper(0,3);
        HCollection values = map.values();
        Object[] vet2 = values.toArray();
        Assert.assertEquals("C",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of HCollection on a values passing it an array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when toArray() is called on it passing an array.
     * 
     * @testDescription Instantiates a values and checks if the array is correctly created.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The array has correct length and value in order.
     */
    @Test
    public void testToArrayObjectValues(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HCollection values = map.values();
        String[] vet = new String[3];
        Object[] vet2 = values.toArray(vet);
        Assert.assertEquals("C",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of HCollection on a values using different type of object. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using a different type of object when toArray() is called on it.
     * 
     * @testDescription Instantiates a values and checks if the array is correctly created.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The array has correct length and value.
     */
    @Test
    public void testToArrayObjectDifferentTypeValues(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HCollection values = map.values();
        MapAdapter[] vet = new MapAdapter[3];
        Object[] vet2 = values.toArray(vet);
        Assert.assertEquals("C",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }
    
    /**
     * Tests the toArray() method of HCollection on a values passing it a bigger array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when toArray() is called on it passing an array bigger than its size.
     * 
     * @testDescription Instantiates a values and checks if the array is correctly created.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The array has correct length, value in order and and the last one is <code>null</code>.
     */
    @Test
    public void testToArrayObjectBiggerSizeValues(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HCollection values = map.values();
        String[] vet = new String[4];
        Object[] vet2 = values.toArray(vet);
        Assert.assertEquals("C",vet2[0]);
        Assert.assertEquals(null,vet2[3]);
        Assert.assertEquals(4,vet2.length);
    }

    /**
     * Tests the toArray() method of HCollection on a values passing it a smaller array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when toArray() is called on it passing an array smaller than its size.
     * 
     * @testDescription Instantiates a values and checks if the array is correctly created.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The array has correct length, value in order and and the last one is 3.
     */
    @Test
    public void testToArrayObjectSmallerSizeValues(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HCollection values = map.values();
        String[] vet = new String[2];
        Object[] vet2 = values.toArray(vet);
        Assert.assertEquals("C",vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of HCollection on a values passing it null element. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when toArray() is called on it passing a null element.
     * 
     * @testDescription Instantiates a values and checks if the method throws NullPointerException.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The array is not created.
     */
    @Test
    public void testToArrayObjectNullPointerValues(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HCollection values = map.values();
        Assert.assertThrows(NullPointerException.class,()->{Object[] vet2 = values.toArray(null);});
    }

    /**
     * Tests the add() method of HCollection on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when add() is called on it.
     * 
     * @testDescription Instantiates a values and checks if throws UnsupportedOperationException.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values can't use the opertion. 
     */
    @Test
    public void testAddValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{values.add("A");});
    }

    /**
     * Tests the remove() method of HCollection on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when remove() is called on it.
     * 
     * @testDescription Instantiates a values and checks if the value is still in the keySet .
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values has 9 values.
     */
    @Test
    public void testRemoveValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        values.remove("A");
        Assert.assertFalse(values.contains("A"));
    }
    
    /**
     * Tests the remove() method of HCollection on a values using different type of value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using different type of key when remove() is called on it.
     * 
     * @testDescription Instantiates a values and checks if the method throws ClassCastException .
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values can't use remove with different type of value.
     */
    @Test
    public void testRemoveDifferentTypeValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertThrows(ClassCastException.class,()->{values.remove(2.1);});
    }

    /**
     * Tests the remove() method of HCollection on a values passing a null value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when remove() is called on it passing a null value.
     * 
     * @testDescription Instantiates a values and checks if a NullPointerException is thrown.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values has 10 values.
     */
    @Test
    public void testRemoveNullValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertThrows(NullPointerException.class,()->{values.remove(null);});
    }
    
    /**
     * Tests the remove() method of HCollection on an empty values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty values when remove() is called on it.
     * 
     * @testDescription Instantiates a values and checks if the values has no value.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values has 0 values.
     */
    @Test
    public void testRemoveOnEmptyValues(){
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        Assert.assertFalse(values.remove("0"));
    }

    /**
     * Tests the remove() method on a values passing a value not in the collection. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when remove() is called on it passing a value not in the set.
     * 
     * @testDescription Instantiates a values and checks if the values is the same.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values has 10 values.
     */
    @Test
    public void testRemoveNotInValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        Assert.assertFalse(values.remove("11"));
    }

    /**
     * Tests the containsAll() method of HCollection on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when containsAll() is called on it.
     * 
     * @testDescription Instantiates two values and checks if the two values have the same element .
     * 
     * @preCondition The values are correctly instantiated.
     * @postCondition The values are valid instance.
     * @expectedResults The two values have the same element.
     */
    @Test
    public void testContainsAllValues(){
        MapAdapter map = helper(0,3);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertTrue(values.containsAll(values2));
    }

    /**
     * Tests the containsAll() method on a values passing a values with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when containsAll() is called on it passing a values a little difference.
     * 
     * @testDescription Instantiates a keySet and checks if the 2 values are different.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The 2 values have different elements.
     */
    @Test
    public void testContainsAllSomeDifferentValues(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertFalse(values.containsAll(values2));
    }

    /**
     * Tests the containsAll() method on a values passing a different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when containsAll() is called on it passing a different values.
     * 
     * @testDescription Instantiates a values and checks if the 2 values are different.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The 2 values are completely different.
     */
    @Test
    public void testContainsAllNoOneInValues(){
        MapAdapter map = helper(4,7);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertFalse(values.containsAll(values2));
    }

    /**
     * Tests the containsAll() method on a values using null value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using null value when containsAll() is called on it.
     * 
     * @testDescription Instantiates two values and checks if a NullPointerException is thrown.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults NullPointerException.
     */
    @Test
    public void testContainsAllNullPointerValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,2);
        HCollection values2 = map2.values();
        Assert.assertThrows(NullPointerException.class,()->{values.containsAll(null);});
    }

    /**
     * Tests the containsAll() method on a values passing a values with different type of keys. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when containsAll() is called on it passing a values
     *                 with different type of value.
     * @testDescription Instantiates a values and checks if the 2 values are different.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults ClassCastException.
     */
    @Test
    public void testContainsAllClassCastValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        MapAdapter map2 = new MapAdapter();
        map2.put("A",1);
        HCollection values2 = map2.values();
        Assert.assertThrows(ClassCastException.class,()->{values.containsAll(values2);});
    }
    
    /**
     * Tests the addAll() method on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when addAll() is called on it.
     * @testDescription Instantiates a values and checks if addAll() metod is not supported.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults myAdapter.UnsopportedOperationException.
     */
    @Test
    public void testAddAllValues(){
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,2);
        HCollection values2 = map2.values();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{values.addAll(values2);});
    }

    /**
     * Tests the retainAll() method on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when retainAll() is called on it passing another values.
     * @testDescription Instantiates a values and checks if the set is modified.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The values is not modified.
     */
    @Test
    public void testRetainAllValues(){
        MapAdapter map = helper(0,3);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertFalse(values.retainAll(values2));
        Assert.assertEquals(3,values.size());
    }
    
    /**
     * Tests the retainAll() method on a values using values with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using values with some difference when retainAll() is called on it.
     * 
     * @testDescription Instantiates a values and checks if the set is modified.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The values is modified.
     */
    @Test
    public void testRetainAllSomeDifferentValues(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        map.put(4,"S");
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertTrue(values.retainAll(values2));
        Assert.assertEquals(2,values.size());
    }
    
    /**
     * Tests the retainAll() method on a values passing a different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when retainAll() is called on it passing a different values.
     * @testDescription Instantiates a values and checks if the set is modified.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The values is modified.
     */
    @Test
    public void testRetainAllNoOneInValues(){
        MapAdapter map = helper(4,7);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertTrue(values.retainAll(values2));
        Assert.assertEquals(0,values.size());
    }

    /**
     * Tests the retainAll() method on a values passing null parameter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when retainAll() is called on it passing a null parameter.
     * @testDescription Instantiates a values and checks if NullPointerException is thrown.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is a valid instance.
     * @expectedResults The values is not modified.
     */
    @Test
    public void testRetainAllNullPointerValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        Assert.assertThrows(NullPointerException.class,()->{values.retainAll(null);});
    }

     /**
     * Tests the retainAll() method on a values passing a values with defferent type of value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values passing a values with defferent type of value when retainAll() is called on it.
     *     
     * @testDescription Instantiates a values and checks if the method throws ClassCastException.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults ClassCastException
     */
    @Test
    public void testRetainAllClassCastValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        MapAdapter map2 = new MapAdapter();
        map2.put("A",1);
        HCollection values2 = map2.values();
        Assert.assertThrows(ClassCastException.class,()->{values.retainAll(values2);});
    }

    /**
     * Tests the removeAll() method on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when removeAll() is called on it passing another value.
     * @testDescription Instantiates a values and checks if all its element has been removed .
     * 
     * @preCondition The values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The values is empty.
     */
    @Test
    public void testRemoveAllValues(){
        MapAdapter map = helper(0,3);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertTrue(values.removeAll(values2));
        Assert.assertEquals(0,values.size());
    }

    /**
     * Tests the removeAll() method on a values with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using values with some difference when removeAll() is called on it.
     * 
     * @testDescription Instantiates two values and checks if the modification occurred.
     * 
     * @preCondition The values are correctly instantiated.
     * @postCondition The values are a valid instance.
     * @expectedResults The values has less key.
     */
    @Test
    public void testRemoveAllSomeDifferentValues(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        map.put(4,"S");
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertTrue(values.removeAll(values2));
        Assert.assertEquals(1,values.size());
    }

    /**
     * Tests the removeAll() method on a values passing a different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when removeAll() is called on it passing a different keySet.
     * @testDescription Instantiates a values and checks if the set is modified.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults The values is not modified.
     */
    @Test
    public void testRemoveAllNoOneInValues(){
        MapAdapter map = helper(4,7);
        HCollection values = map.values();
        MapAdapter map2 = helper(0,3);
        HCollection values2 = map2.values();
        Assert.assertFalse(values.removeAll(values2));
    }

    /**
     * Tests the removeAll() method on a values passing null parameter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when removeAll() is called on it passing a null parameter.
     * @testDescription Instantiates a values and checks if NullPointerException is thrown.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is a valid instance.
     * @expectedResults The values is not modified.
     */
    @Test
    public void testRemoveAllNullPointerValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        Assert.assertThrows(NullPointerException.class,()->{values.removeAll(null);});
    }
    
    /**
     * Tests the removeAll() method on a values passing a values with defferent type of key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values passing a values with defferent type of key when removeAll() is called on it.
     *     
     * @testDescription Instantiates a values and checks if the method throws ClassCastException.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instances.
     * @expectedResults ClassCastException
     */
    @Test
    public void testRemoveAllClassCastValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        MapAdapter map2 = new MapAdapter();
        map2.put("A",1);
        HCollection values2 = map2.values();
        Assert.assertThrows(ClassCastException.class,()->{values.removeAll(values2);});
    }

    /**
     * Tests the clear() method on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when clear() is called on it.
     *     
     * @testDescription Instantiates a values and checks if it has no value.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values is empty.
     */
    @Test
    public void testClearValues(){
        MapAdapter map = helper(0,2);
        HCollection values = map.values();
        values.clear();
        Assert.assertEquals(0,values.size());
    }

    /**
     * Tests the clear() method on an empty values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty values when clear() is called on it.
     *     
     * @testDescription Instantiates a values and checks if it's still empty.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values is empty.
     */
    @Test
    public void testClearOnEmptyValues(){
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        values.clear();
        Assert.assertEquals(0,values.size());
    }

    /**
     * Tests the equals() method on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when equals() is called on it.
     *     
     * @testDescription Instantiates 2 values and checks if they are equal.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instance.
     * @expectedResults The 2 values are equal.
     */
    @Test 
    public void testEqualsValues() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HCollection values = map.values();
        HCollection values2 = map2.values();
        Assert.assertTrue(values.equals(values2));
    }
    
    /**
     * Tests the equalsquals() method on a values using less values.  
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values using less values when equals() is called on it.
     *     
     * @testDescription Instantiates a values and checks if it has no key.
     * 
     * @preCondition The values is correctly instantiated.
     * @postCondition The values is valid instance.
     * @expectedResults The values is not empty.
     */
    @Test 
    public void testEqualsLessValues() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(0);
        HCollection values = map.values();
        HCollection values2 = map2.values();
        Assert.assertFalse(values.equals(values2));
    }

    /**
     * Tests the equals() method on a values passing a different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values passing a different values when equals() is called on it.
     *     
     * @testDescription Instantiates 2 values and checks if they are not equal.
     * 
     * @preCondition The 2 values are correctly instantiated.
     * @postCondition The 2 values are valid instance.
     * @expectedResults The 2 values are not equal.
     */
    @Test 
    public void testEqualsDifferentValues() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(11,15);
        map2.put(11,"A");
        HCollection values = map.values();
        HCollection values2 = map2.values();
        Assert.assertFalse(values.equals(values2));
    }
    
    /**
     * Tests the hashCode() method on a values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values when hashcode() is called on it.
     *     
     * @testDescription Instantiates two values and checks if have the same hashCode.
     * 
     * @preCondition The values are correctly instantiated.
     * @postCondition The values are valid instance.
     * @expectedResults The values have the same hashCode.
     */
    @Test 
    public void testHashCodeValues() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HCollection values = map.values();
        HCollection values2 = map2.values();
        Assert.assertEquals(values.hashCode(),values2.hashCode());
    }
    
    /**
     * Tests the hashCode() method on a values passing different values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values passing different values when hashcode() is called on it.
     *     
     * @testDescription Instantiates two values and checks if haven't the same hashCode.
     * 
     * @preCondition The values are correctly instantiated.
     * @postCondition The values are valid instance.
     * @expectedResults The values haven't the same hashCode.
     */
    @Test 
    public void testHashCodeDifferentValues() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(0);
        HCollection values = map.values();
        HCollection values2 = map2.values();
        Assert.assertNotEquals(values.hashCode(),values2.hashCode());
    }

    /**
     * Tests the hashCode() method on a values passing null values. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a values passing null values when hashcode() is called on it.
     *     
     * @testDescription Instantiates a values and checks if the hashCode is 0.
     * 
     * @preCondition The values are correctly instantiated.
     * @postCondition The values are valid instance.
     * @expectedResults The values have hashCode 0.
     */
    @Test 
    public void testHashCodeNullValues() {
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        Assert.assertEquals(0,values.hashCode());
    }

    //TODO: TEST HSET RITORNATO DA ENTRYSET

        /**
     * Tests the size method of EntrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when size() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks its size.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet has size 10.
     */
    @Test
    public void testSizeEntrySet() {
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertEquals(10, entrySet.size());
    }

    /**
     * Tests the size method of HSet on empty entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a empty vlaues when size() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks its size.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet has size 0.
     */
    @Test
    public void testSizeEntrySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        Assert.assertEquals(0, entrySet.size());
    }
    
    /**
     * Tests the isEmpty method of entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if it is empty.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet is not empty.
     */
    @Test
    public void testIsEmptyEntrySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.isEmpty());
    }

    /**
     * Tests the isEmpty method of HSet on empty entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty entrySet when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if it is empty.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet is empty.
     */
    @Test
    public void testIsEmptyEntrySetOnEmptyEntrySet() {
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        Assert.assertTrue(entrySet.isEmpty());
    }

    /**
     * Tests the contains method of HSet on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when contains() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks it contains the specific value.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet contains the specific entrySet.
     */
    @Test
    public void testContainsEntrySet() {
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertTrue(entrySet.contains(new EntryAdapter(0, "A")));
    }

    /**
     * Tests the contains() method of HSet on a entrySet . 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty entrySet when contains() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if it doesn't contain the specific value.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet doesn't contain the specific entrySet.
     */
    @Test
    public void testContainsEntrySetOnEmptyValue() {
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        Assert.assertFalse(entrySet.contains(new EntryAdapter(0, "A")));
    }

    /**
     * Tests the contains method of HSet on a entrySet using null entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using null key when contains() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if it throws NullPointerException.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet can't contain a null entrySet.
     */
    @Test
    public void testContainsNullEntrySet() {
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{entrySet.contains(null);});
    }

    /**
     * Tests the contains() method of HSet on empty entrySet using null entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty entrySet when contains() is called on it using null entrySet.
     * 
     * @testDescription Instantiates an empty entrySet and checks if it can contain a null entrySet .
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet does not contain null entrySet.
     */
    @Test
    public void testContainsNullEntrySetOnEmptyEntrySet() {
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{entrySet.contains(null);});
    }

    /**
     * Tests the contains method of HSet on entrySet using a different type of entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using a different type of value when contains() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if it is empty.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet can't contain different type of entrySet.
     */
    @Test
    public void testContainsDifferentTypeEntrySet() {
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(ClassCastException.class,()->{entrySet.contains(2.1);});
    }

    /**
     * Tests the creation of an iterator of a entrySet. 
     *     
     * @testCaseDesign This test is designed for checking the creation 
     *                 of an Iterator of a entrySet
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if it's valid.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iteration has more elements
     */
    @Test
    public void testIteratorCreationEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertTrue(it.hasNext());
    }

    /**
     * Tests the creation of an iterator of an empty entrySet. 
     *     
     * @testCaseDesign This test is designed for checking the creation 
     *                 of an Iterator of a entrySet that is empty.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if it's invalid.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorCreationonEmptyEntrySet(){
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Tests toArray method of HSet on entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when toArray() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks the array is correctly created.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The array has correct length and value in order.
     */
    @Test
    public void testToArrayEntrySet(){
        MapAdapter map = helper(0,3);
        HSet entrySet = map.entrySet();
        Object[] vet2 = entrySet.toArray();
        EntryAdapter entry = new EntryAdapter(2, "C");
        Assert.assertTrue(entry.equals(vet2[0]));
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of HSet on a entrySet passing it an array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when toArray() is called on it passing an array.
     * 
     * @testDescription Instantiates a entrySet and checks if the array is correctly created.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The array has correct length and value in order.
     */
    @Test
    public void testToArrayObjectEntrySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet entrySet = map.entrySet();
        String[] vet = new String[3];
        Object[] vet2 = entrySet.toArray(vet);
        Assert.assertEquals(new EntryAdapter("3","C"),vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of HSet on a entrySet using different type of object. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using a different type of object when toArray() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if the array is correctly created.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The array has correct length and value.
     */
    @Test
    public void testToArrayObjectDifferentTypeEntrySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet entrySet = map.entrySet();
        MapAdapter[] vet = new MapAdapter[3];
        Object[] vet2 = entrySet.toArray(vet);
        Assert.assertEquals(new EntryAdapter("3","C"),vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }
    
    /**
     * Tests the toArray() method of HSet on a entrySet passing it a bigger array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when toArray() is called on it passing an array bigger than its size.
     * 
     * @testDescription Instantiates a entrySet and checks if the array is correctly created.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The array has correct length, value in order and and the last one is <code>null</code>.
     */
    @Test
    public void testToArrayObjectBiggerSizeEntrySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet entrySet = map.entrySet();
        String[] vet = new String[4];
        Object[] vet2 = entrySet.toArray(vet);
        Assert.assertEquals(new EntryAdapter("3","C"),vet2[0]);
        Assert.assertEquals(null,vet2[3]);
        Assert.assertEquals(4,vet2.length);
    }

    /**
     * Tests the toArray() method of HSet on a entrySet passing it a smaller array. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when toArray() is called on it passing an array smaller than its size.
     * 
     * @testDescription Instantiates a entrySet and checks if the array is correctly created.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The array has correct length, value in order and and the last one is 3.
     */
    @Test
    public void testToArrayObjectSmallerSizeEntrySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet entrySet = map.entrySet();
        String[] vet = new String[2];
        Object[] vet2 = entrySet.toArray(vet);
        Assert.assertEquals(new EntryAdapter("3","C"),vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

    /**
     * Tests the toArray() method of HSet on a entrySet passing it null element. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when toArray() is called on it passing a null element.
     * 
     * @testDescription Instantiates a entrySet and checks if the method throws NullPointerException.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The array is not created.
     */
    @Test
    public void testToArrayObjectNullPointerEntrySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet entrySet = map.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{Object[] vet2 = entrySet.toArray(null);});
    }

    /**
     * Tests the add() method of HSet on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when add() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if throws UnsupportedOperationException.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet can't use the opertion. 
     */
    @Test
    public void testAddEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{entrySet.add("A");});
    }

    /**
     * Tests the remove() method of HSet on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when remove() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if the value is still in the keySet .
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet has 9 entrySet.
     */
    @Test
    public void testRemoveEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        entrySet.remove(new EntryAdapter(0,"A"));
        Assert.assertFalse(entrySet.contains(new EntryAdapter(0,"A")));
    }
    
    /**
     * Tests the remove() method of HSet on a entrySet using different type of value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using different type of key when remove() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if the method throws ClassCastException .
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet can't use remove with different type of value.
     */
    @Test
    public void testRemoveDifferentTypeEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(ClassCastException.class,()->{entrySet.remove(2.1);});
    }

    /**
     * Tests the remove() method of HSet on a entrySet passing a null value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when remove() is called on it passing a null value.
     * 
     * @testDescription Instantiates a entrySet and checks if a NullPointerException is thrown.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet has 10 entrySet.
     */
    @Test
    public void testRemoveNullEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{entrySet.remove(null);});
    }
    
    /**
     * Tests the remove() method of HSet on an empty entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty entrySet when remove() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if the entrySet has no value.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet has 0 entrySet.
     */
    @Test
    public void testRemoveOnEmptyEntrySet(){
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        Assert.assertFalse(entrySet.remove(new EntryAdapter(0,"A")));
    }

    /**
     * Tests the remove() method on a entrySet passing a value not in the collection. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when remove() is called on it passing a value not in the set.
     * 
     * @testDescription Instantiates a entrySet and checks if the entrySet is the same.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet has 10 entrySet.
     */
    @Test
    public void testRemoveNotInEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        Assert.assertFalse(entrySet.remove(new EntryAdapter(11,"Z")));
    }

    /**
     * Tests the containsAll() method of HSet on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when containsAll() is called on it.
     * 
     * @testDescription Instantiates two entrySet and checks if the two entrySet have the same element .
     * 
     * @preCondition The entrySet are correctly instantiated.
     * @postCondition The entrySet are valid instance.
     * @expectedResults The two entrySet have the same element.
     */
    @Test
    public void testContainsAllEntrySet(){
        MapAdapter map = helper(0,3);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertTrue(entrySet.containsAll(entrySet2));
    }

    /**
     * Tests the containsAll() method on a entrySet passing a entrySet with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when containsAll() is called on it passing a entrySet a little difference.
     * 
     * @testDescription Instantiates a keySet and checks if the 2 entrySet are different.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The 2 entrySet have different elements.
     */
    @Test
    public void testContainsAllSomeDifferentEntrySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertFalse(entrySet.containsAll(entrySet2));
    }

    /**
     * Tests the containsAll() method on a entrySet passing a different entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when containsAll() is called on it passing a different entrySet.
     * 
     * @testDescription Instantiates a entrySet and checks if the 2 entrySet are different.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The 2 entrySet are completely different.
     */
    @Test
    public void testContainsAllNoOneInEntrySet(){
        MapAdapter map = helper(4,7);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertFalse(entrySet.containsAll(entrySet2));
    }

    /**
     * Tests the containsAll() method on a entrySet using null value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using null value when containsAll() is called on it.
     * 
     * @testDescription Instantiates two entrySet and checks if a NullPointerException is thrown.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults NullPointerException.
     */
    @Test
    public void testContainsAllNullPointerEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,2);
        HSet entrySet2 = map2.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{entrySet.containsAll(null);});
    }

    /**
     * Tests the containsAll() method on a entrySet passing a entrySet with different type of keys. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when containsAll() is called on it passing a entrySet
     *                 with different type of value.
     * @testDescription Instantiates a entrySet and checks if the 2 entrySet are different.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults ClassCastException.
     */
    @Test
    public void testContainsAllClassCastEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A",1);
        HSet entrySet2 = map2.entrySet();
        Assert.assertThrows(ClassCastException.class,()->{entrySet.containsAll(entrySet2);});
    }
    
    /**
     * Tests the addAll() method on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when addAll() is called on it.
     * @testDescription Instantiates a entrySet and checks if addAll() metod is not supported.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults myAdapter.UnsopportedOperationException.
     */
    @Test
    public void testAddAllEntrySet(){
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,2);
        HSet entrySet2 = map2.entrySet();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{entrySet.addAll(entrySet2);});
    }

    /**
     * Tests the retainAll() method on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when retainAll() is called on it passing another entrySet.
     * @testDescription Instantiates a entrySet and checks if the set is modified.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The entrySet is not modified.
     */
    @Test
    public void testRetainAllEntrySet(){
        MapAdapter map = helper(0,3);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertFalse(entrySet.retainAll(entrySet2));
        Assert.assertEquals(3,entrySet.size());
    }
    
    /**
     * Tests the retainAll() method on a entrySet using entrySet with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using entrySet with some difference when retainAll() is called on it.
     * 
     * @testDescription Instantiates a entrySet and checks if the set is modified.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The entrySet is modified.
     */
    @Test
    public void testRetainAllSomeDifferentEntrySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        map.put(4,"S");
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertTrue(entrySet.retainAll(entrySet2));
        Assert.assertEquals(2,entrySet.size());
    }
    
    /**
     * Tests the retainAll() method on a entrySet passing a different entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when retainAll() is called on it passing a different entrySet.
     * @testDescription Instantiates a entrySet and checks if the set is modified.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The entrySet is modified.
     */
    @Test
    public void testRetainAllNoOneInEntrySet(){
        MapAdapter map = helper(4,7);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertTrue(entrySet.retainAll(entrySet2));
        Assert.assertEquals(0,entrySet.size());

    }

    /**
     * Tests the retainAll() method on a entrySet passing null parameter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when retainAll() is called on it passing a null parameter.
     * @testDescription Instantiates a entrySet and checks if NullPointerException is thrown.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is a valid instance.
     * @expectedResults The entrySet is not modified.
     */
    @Test
    public void testRetainAllNullPointerEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{entrySet.retainAll(null);});
    }

     /**
     * Tests the retainAll() method on a entrySet passing a entrySet with defferent type of value. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet passing a entrySet with defferent type of value when retainAll() is called on it.
     *     
     * @testDescription Instantiates a entrySet and checks if the method throws ClassCastException.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults ClassCastException
     */
    @Test
    public void testRetainAllClassCastEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A",1);
        HSet entrySet2 = map2.entrySet();
        Assert.assertThrows(ClassCastException.class,()->{entrySet.retainAll(entrySet2);});
    }

    /**
     * Tests the removeAll() method on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when removeAll() is called on it passing another value.
     * @testDescription Instantiates a entrySet and checks if all its element has been removed .
     * 
     * @preCondition The entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The entrySet is empty.
     */
    @Test
    public void testRemoveAllEntrySet(){
        MapAdapter map = helper(0,3);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertTrue(entrySet.removeAll(entrySet2));
        Assert.assertEquals(0,entrySet.size());
    }

    /**
     * Tests the removeAll() method on a entrySet with some difference. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using entrySet with some difference when removeAll() is called on it.
     * 
     * @testDescription Instantiates two entrySet and checks if the modification occurred.
     * 
     * @preCondition The entrySet are correctly instantiated.
     * @postCondition The entrySet are a valid instance.
     * @expectedResults The entrySet has less key.
     */
    @Test
    public void testRemoveAllSomeDifferentEntrySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        map.put(4,"S");
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertTrue(entrySet.removeAll(entrySet2));
        Assert.assertEquals(1,entrySet.size());
    }

    /**
     * Tests the removeAll() method on a entrySet passing a different entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when removeAll() is called on it passing a different keySet.
     * @testDescription Instantiates a entrySet and checks if the set is modified.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults The entrySet is not modified.
     */
    @Test
    public void testRemoveAllNoOneInEntrySet(){
        MapAdapter map = helper(4,7);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = helper(0,3);
        HSet entrySet2 = map2.entrySet();
        Assert.assertFalse(entrySet.removeAll(entrySet2));
    }

    /**
     * Tests the removeAll() method on a entrySet passing null parameter. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when removeAll() is called on it passing a null parameter.
     * @testDescription Instantiates a entrySet and checks if NullPointerException is thrown.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is a valid instance.
     * @expectedResults The entrySet is not modified.
     */
    @Test
    public void testRemoveAllNullPointerEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        Assert.assertThrows(NullPointerException.class,()->{entrySet.removeAll(null);});
    }
    
    /**
     * Tests the removeAll() method on a entrySet passing a entrySet with defferent type of key. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet passing a entrySet with defferent type of key when removeAll() is called on it.
     *     
     * @testDescription Instantiates a entrySet and checks if the method throws ClassCastException.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instances.
     * @expectedResults ClassCastException
     */
    @Test
    public void testRemoveAllClassCastEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A",1);
        HSet entrySet2 = map2.entrySet();
        Assert.assertThrows(ClassCastException.class,()->{entrySet.removeAll(entrySet2);});
    }

    /**
     * Tests the clear() method on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when clear() is called on it.
     *     
     * @testDescription Instantiates a entrySet and checks if it has no value.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet is empty.
     */
    @Test
    public void testClearEntrySet(){
        MapAdapter map = helper(0,2);
        HSet entrySet = map.entrySet();
        entrySet.clear();
        Assert.assertEquals(0,entrySet.size());
    }

    /**
     * Tests the clear() method on an empty entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty entrySet when clear() is called on it.
     *     
     * @testDescription Instantiates a entrySet and checks if it's still empty.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet is empty.
     */
    @Test
    public void testClearOnEmptyEntrySet(){
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        entrySet.clear();
        Assert.assertEquals(0,entrySet.size());
    }

    /**
     * Tests the equals() method on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when equals() is called on it.
     *     
     * @testDescription Instantiates 2 entrySet and checks if they are equal.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instance.
     * @expectedResults The 2 entrySet are equal.
     */
    @Test 
    public void testEqualsEntrySet() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HSet entrySet = map.entrySet();
        HSet entrySet2 = map2.entrySet();
        Assert.assertTrue(entrySet.equals(entrySet2));
    }
    
    /**
     * Tests the equalsquals() method on a entrySet using less entrySet.  
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet using less entrySet when equals() is called on it.
     *     
     * @testDescription Instantiates a entrySet and checks if it has no key.
     * 
     * @preCondition The entrySet is correctly instantiated.
     * @postCondition The entrySet is valid instance.
     * @expectedResults The entrySet is not empty.
     */
    @Test 
    public void testEqualsLessEntrySet() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(0);
        HSet entrySet = map.entrySet();
        HSet entrySet2 = map2.entrySet();
        Assert.assertFalse(entrySet.equals(entrySet2));
    }

    /**
     * Tests the equals() method on a entrySet passing a different entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet passing a different entrySet when equals() is called on it.
     *     
     * @testDescription Instantiates 2 entrySet and checks if they are not equal.
     * 
     * @preCondition The 2 entrySet are correctly instantiated.
     * @postCondition The 2 entrySet are valid instance.
     * @expectedResults The 2 entrySet are not equal.
     */
    @Test 
    public void testEqualsDifferentEntrySet() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(11,15);
        map2.put(11,"A");
        HSet entrySet = map.entrySet();
        HSet entrySet2 = map2.entrySet();
        Assert.assertFalse(entrySet.equals(entrySet2));
    }
    
    /**
     * Tests the hashCode() method on a entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet when hashcode() is called on it.
     *     
     * @testDescription Instantiates two entrySet and checks if have the same hashCode.
     * 
     * @preCondition The entrySet are correctly instantiated.
     * @postCondition The entrySet are valid instance.
     * @expectedResults The entrySet have the same hashCode.
     */
    @Test 
    public void testHashCodeEntrySet() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HSet entrySet = map.entrySet();
        HSet entrySet2 = map2.entrySet();
        Assert.assertEquals(entrySet.hashCode(),entrySet2.hashCode());
    }
    
    /**
     * Tests the hashCode() method on a entrySet passing different entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet passing different entrySet when hashcode() is called on it.
     *     
     * @testDescription Instantiates two entrySet and checks if haven't the same hashCode.
     * 
     * @preCondition The entrySet are correctly instantiated.
     * @postCondition The entrySet are valid instance.
     * @expectedResults The entrySet haven't the same hashCode.
     */
    @Test 
    public void testHashCodeDifferentEntrySet() {
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(0);
        HSet entrySet = map.entrySet();
        HSet entrySet2 = map2.entrySet();
        Assert.assertNotEquals(entrySet.hashCode(),entrySet2.hashCode());
    }

    /**
     * Tests the hashCode() method on a entrySet passing null entrySet. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a entrySet passing null entrySet when hashcode() is called on it.
     *     
     * @testDescription Instantiates a entrySet and checks if the hashCode is 0.
     * 
     * @preCondition The entrySet are correctly instantiated.
     * @postCondition The entrySet are valid instance.
     * @expectedResults The entrySet have hashCode 0.
     */
    @Test 
    public void testHashCodeNullEntrySet() {
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        Assert.assertEquals(0,entrySet.hashCode());
    }





    //TODO: TEST ITERATOR KEYSET





     /**
     * Tests the hasNext method on a keySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() of a keySet using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the iterator is valid.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iteration has elements.
     */
    @Test
    public void testIteratorHasNextKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertTrue(it.hasNext());
    }
    
    /**
     * Tests the hasNext method on an empty keySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() on an empty keySet using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the iterator is invalid.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorHasNextEmptyKeySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertFalse(it.hasNext());
    }
    
    /**
     * Tests the hasNext method on keySet with one key using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() on keySet with one key using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the iterator is invalid.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorHasNextLastElementKeySet() {
        MapAdapter map = helper(0,1);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        it.next();
        Assert.assertFalse(it.hasNext());
    }
    
     /**
     * Tests the next method on a keySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() of a keySet using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the element is correct.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The next element of the iteration is 9.
     */
    @Test
    public void testIteratorNextKeyset(){
        MapAdapter map = helper(0, 10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertEquals(9, it.next());
    }
    
    /**
     * Tests the next method on an empty keySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() of an empty keySet using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the method throws NoSuchElementException
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iterator can't return the next element.
     */
    @Test
    public void testIteratorNextEmptyKeyset(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    /**
     * Tests the next method on keySet with one key, using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() on keySet with one key using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the method throws NoSuchElementException
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The iterator can't return the element.
     */
    @Test
    public void testIteratorNextLastElementKeyset(){
        MapAdapter map = helper(0,1);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        it.next();
        Assert.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    /**
     * Tests the remove method on keySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on keySet using the iterator.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the method throws myAdapter.IllegalStateException
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults It's not possible to remove the element.
     */
    @Test
    public void testIteratorRemoveKeyset(){
        MapAdapter map = helper(0, 10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertThrows(myAdapter.IllegalStateException.class,()->{it.remove();});
    }
    
    /**
     * Tests the remove method on keySet, using the iterator, calling it twice after next(). 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on a keySet using the iterator calling it twice after next().
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the method throws myAdapter.IllegalStateException
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The keyset has 9 keys.
     */
    @Test
    public void testIteratorDoubleRemoveKeyset(){
        MapAdapter map = helper(0, 10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        it.next();
        it.remove();
        Assert.assertThrows(myAdapter.IllegalStateException.class,()->{it.remove();});
        Assert.assertEquals(9,keySet.size());
        Assert.assertEquals(9,map.size());
    }
    
    /**
     * Tests the remove method on keySet using the iterator calling it after next() twice. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on keySet using the iterator calling it after next() twice.
     * 
     * @testDescription Instantiates a keySet and a iterator and checks if the keySet
                        has less keys.
     * 
     * @preCondition The keySet and iterator are correctly instantiated.
     * @postCondition The keySet and iterator are valid instance.
     * @expectedResults The keySet has 8 keys.
     */
    @Test
    public void testIteratorRemoveNextRemoveKeyset(){
        MapAdapter map = helper(0, 10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        it.next();
        it.remove();
        it.next();
        it.remove();
        Assert.assertEquals(8,keySet.size());
        Assert.assertEquals(8,map.size());
        Assert.assertFalse(keySet.contains(9));
        Assert.assertFalse(map.containsKey(9));
        Assert.assertFalse(keySet.contains(8));
        Assert.assertFalse(map.containsKey(8));
    }




    //TODO: TEST ITERATOR VALUES
    /**
     * Tests the hasNext method on a values using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() of a values using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the iterator is valid.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iteration has elements.
     */
    @Test
    public void testIteratorHasNextValues() {
        MapAdapter map = helper(0,10);
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertTrue(it.hasNext());
    }

    /**
     * Tests the hasNext method on an empty values using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() on an empty values using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the iterator is invalid.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorHasNextEmptyValues() {
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Tests the hasNext method on values with one key using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() on values with one key using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the iterator is invalid.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorHasNextLastElementValues() {
        MapAdapter map = helper(0,1);
        HCollection values = map.values();
        HIterator it = values.iterator();
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Tests the next method on a values using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() of a values using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the element is correct.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The next element of the iteration is 9.
     */
    @Test
    public void testIteratorNextValues(){
        MapAdapter map = helper(0, 10);
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertEquals("J", it.next());
    }

    /**
     * Tests the next method on an empty values using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() of an empty values using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the method throws NoSuchElementException
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iterator can't return the next element.
     */
    @Test
    public void testIteratorNextEmptyValues(){
        MapAdapter map = new MapAdapter();
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    /**
     * Tests the next method on values with one key, using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() on values with one key using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the method throws NoSuchElementException
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The iterator can't return the element.
     */
    @Test
    public void testIteratorNextLastElementValues(){
        MapAdapter map = helper(0,1);
        HCollection values = map.values();
        HIterator it = values.iterator();
        it.next();
        Assert.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    /**
     * Tests the remove method on values using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on values using the iterator.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the method throws myAdapter.IllegalStateException
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults It's not possible to remove the element.
     */
    @Test
    public void testIteratorRemoveValues(){
        MapAdapter map = helper(0, 10);
        HCollection values = map.values();
        HIterator it = values.iterator();
        Assert.assertThrows(myAdapter.IllegalStateException.class,()->{it.remove();});
    }

    /**
     * Tests the remove method on values, using the iterator, calling it twice after next(). 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on a values using the iterator calling it twice after next().
     * 
     * @testDescription Instantiates a values and a iterator and checks if the method throws myAdapter.IllegalStateException
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The values has 9 keys.
     */
    @Test
    public void testIteratorDoubleRemoveValues(){
        MapAdapter map = helper(0, 10);
        HCollection values = map.values();
        HIterator it = values.iterator();
        it.next();
        it.remove();
        Assert.assertThrows(myAdapter.IllegalStateException.class,()->{it.remove();});
        Assert.assertEquals(9,values.size());
        Assert.assertEquals(9,map.size());
    }

     /**
     * Tests the remove method on values using the iterator calling it after next() twice. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on values using the iterator calling it after next() twice.
     * 
     * @testDescription Instantiates a values and a iterator and checks if the keySet
                        has less keys.
     * 
     * @preCondition The values and iterator are correctly instantiated.
     * @postCondition The values and iterator are valid instance.
     * @expectedResults The values has 8 keys.
     */
    @Test
    public void testIteratorRemoveNextRemoveValues(){
        MapAdapter map = helper(0, 10);
        HCollection values = map.values();
        HIterator it = values.iterator();
        it.next();
        it.remove();
        it.next();
        it.remove();
        Assert.assertEquals(8,values.size());
        Assert.assertEquals(8,map.size());
        Assert.assertFalse(values.contains("J"));
        Assert.assertFalse(map.containsValue("J"));
        Assert.assertFalse(values.contains("I"));
        Assert.assertFalse(map.containsValue("I"));
    }



    //TEST ITERATOR ENTRYADAPTER
    /**
     * Tests the hasNext method on a entrySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() of a entrySet using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the iterator is valid.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iteration has elements.
     */
    @Test
    public void testIteratorHasNextEntrySet() {
        MapAdapter map = helper(0,10);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertTrue(it.hasNext());
    }

    /**
     * Tests the hasNext method on an empty entrySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() on an empty entrySet using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the iterator is invalid.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorHasNextEmptyEntrySet() {
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Tests the hasNext method on entrySet with one key using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of hasNext() on entrySet with one key using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the iterator is invalid.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iteration has no more elements.
     */
    @Test
    public void testIteratorHasNextLastElementEntrySet() {
        MapAdapter map = helper(0,1);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Tests the next method on a entrySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() of a entrySet using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the element is correct.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The next element of the iteration is 9.
     */
    @Test
    public void testIteratorNextEntrySet(){
        MapAdapter map = helper(0, 10);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertEquals(new EntryAdapter(9,"J"), it.next());
    }

    /**
     * Tests the next method on an empty entrySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() of an empty entrySet using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the method throws NoSuchElementException
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iterator can't return the next element.
     */
    @Test
    public void testIteratorNextEmptyEntrySet(){
        MapAdapter map = new MapAdapter();
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    /**
     * Tests the next method on entrySet with one key, using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of next() on entrySet with one key using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the method throws NoSuchElementException
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The iterator can't return the element.
     */
    @Test
    public void testIteratorNextLastElementEntrySet(){
        MapAdapter map = helper(0,1);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        it.next();
        Assert.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    /**
     * Tests the remove method on entrySet using the iterator. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on entrySet using the iterator.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the method throws myAdapter.IllegalStateException
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults It's not possible to remove the element.
     */
    @Test
    public void testIteratorRemoveEntrySet(){
        MapAdapter map = helper(0, 10);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        Assert.assertThrows(myAdapter.IllegalStateException.class,()->{it.remove();});
    }

    /**
     * Tests the remove method on entrySet, using the iterator, calling it twice after next(). 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on a entrySet using the iterator calling it twice after next().
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the method throws myAdapter.IllegalStateException
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The entrySet has 9 keys.
     */
    @Test
    public void testIteratorDoubleRemoveEntrySet(){
        MapAdapter map = helper(0, 10);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        it.next();
        it.remove();
        Assert.assertThrows(myAdapter.IllegalStateException.class,()->{it.remove();});
        Assert.assertEquals(9,entrySet.size());
        Assert.assertEquals(9,map.size());
    }

         /**
     * Tests the remove method on entrySet using the iterator calling it after next() twice. 
     *     
     * @testCaseDesign This test is designed for checking the behavior
     *                 of remove() on entrySet using the iterator calling it after next() twice.
     * 
     * @testDescription Instantiates a entrySet and a iterator and checks if the keySet
                        has less keys.
     * 
     * @preCondition The entrySet and iterator are correctly instantiated.
     * @postCondition The entrySet and iterator are valid instance.
     * @expectedResults The entrySet has 8 keys.
     */
    @Test
    public void testIteratorRemoveNextRemoveEntrySet(){
        MapAdapter map = helper(0, 10);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        it.next();
        it.remove();
        it.next();
        it.remove();
        Assert.assertEquals(8,entrySet.size());
        Assert.assertEquals(8,map.size());
        Assert.assertFalse(entrySet.contains(new EntryAdapter(9,"J")));
        Assert.assertFalse(map.containsValue("J"));
        Assert.assertFalse(entrySet.contains(new EntryAdapter(8,"I")));
        Assert.assertFalse(map.containsValue("I"));
    }
    

    //SET ENTRY SU ITERATOR 
    @Test
    public void testIteratorSetValuesEntrySet(){
        MapAdapter map = helper(0,1);
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        ((EntryAdapter)(it.next())).setValue("Z");
        Assert.assertEquals("Z",map.get(0));
    }
}



