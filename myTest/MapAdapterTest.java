package myTest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import static org.junit.Assert.assertTrue;

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
     * @testDescription Instantiates a map, put a key and a value inside of it and
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
    public void testEqualsDifferentValues() {
        
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





    //TEST HSET RITORNATO DA KEYSET
    
    /**
     * Tests the size method of Hset. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a Hset when size() is called on it.
     * 
     * @testDescription Instantiates a set and checks its size.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set has size 10.
     */
    @Test
    public void testSizeKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertEquals(10, keySet.size());
    }

    /**
     * Tests the size method of Hset on empty Set. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a empty Hset when size() is called on it.
     * 
     * @testDescription Instantiates a set and checks its size.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set has size 0.
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
     *                 of a Hset when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a set and checks if it is empty.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set is not empty.
     */
    @Test
    public void testIsEmptyKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.isEmpty());
    }
    
    /**
     * Tests the isEmpty method of Hset on empty Set. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty Hset when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a set and checks if it is empty.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set is empty.
     */
    @Test
    public void testIsEmptyKeySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertTrue(keySet.isEmpty());
    }
    
     /**
     * Tests the containsKey method of Hset on Set. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty Hset when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a set and checks if it is empty.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set is empty.
     */
    @Test
    public void testContainsKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertTrue(keySet.contains(0));
    }
    
     /**
     * Tests the contains method of Hset. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of a Hset when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a set and checks if it is empty.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set is empty.
     */
    @Test
    public void testContainsKeySetonEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.contains(0));
    }
    
     /**
     * Tests the isEmpty method of Hset on empty Set. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty Hset when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a set and checks if it is empty.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set is empty.
     */
    @Test
    public void testContainsNullKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.contains(null);});
    }
    
    /**
     * Tests the isEmpty method of Hset on empty Set. 
     *     
     * @testCaseDesign This test is designed for analyzing the behaviour 
     *                 of an empty Hset when isEmpty() is called on it.
     * 
     * @testDescription Instantiates a set and checks if it is empty.
     * 
     * @preCondition The set is correctly instantiated.
     * @postCondition The set is valid instance.
     * @expectedResults The set is empty.
     */
    @Test
    public void testContainsNullKeySetOnEmptySet() {
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.contains(null);});
    }

    @Test
    public void testContainsDifferentTypeKeySet() {
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.contains(2.1);});
    }

    @Test
    public void testIteratorCreationKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void testIteratorCreationonEmptyKeySet(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        HIterator it = keySet.iterator();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testToArrayKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        int[] vet = {0,1,2};
        Object[] vet2 = keySet.toArray();
        Assert.assertEquals(2,vet2[0]);
        Assert.assertEquals(3,vet2.length);
    }

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

    @Test
    public void testToArrayObjectNullPointerKeySet(){
        MapAdapter map = new MapAdapter();
        map.put("1","A");
        map.put("2","B");
        map.put("3","C");
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{Object[] vet2 = keySet.toArray(null);});
    }

    @Test
    public void testAddKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{keySet.add(1);});
    }

    @Test
    public void testRemoveKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        keySet.remove(0);
        Assert.assertEquals(9,keySet.size());
        Assert.assertFalse(keySet.contains(0));
    }

    @Test
    public void testRemoveDifferentTypeKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.remove(2.1);});
    }

    @Test
    public void testRemoveNullKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.remove(null);});
    }

    @Test
    public void testRemoveOnEmptyKeySet(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.remove(0));
    }

    @Test
    public void testRemoveNotInKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        Assert.assertFalse(keySet.remove(11));
    }

    @Test
    public void testContainsAllKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.containsAll(keySet2));
    }

    @Test
    public void testContainsAllSomeDifferentKeySet(){
        MapAdapter map = helper(0,3);
        map.remove(1);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.containsAll(keySet2));
    }

    @Test
    public void testContainsAllNoOneInKeySet(){
        MapAdapter map = helper(4,7);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.containsAll(keySet2));
    }

    @Test
    public void testContainsAllNullPointerKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.containsAll(null);});
    }

    @Test
    public void testContainsAllClassCastKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.containsAll(keySet2);});
    }

    @Test
    public void testAddAllKeySet(){
        MapAdapter map = helper(0,10);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(myAdapter.UnsupportedOperationException.class,()->{keySet.addAll(keySet2);});
    }

    @Test
    public void testRetainAllKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.retainAll(keySet2));
        Assert.assertEquals(3,keySet.size());
    }

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

    @Test
    public void testRetainAllNoOneInKeySet(){
        MapAdapter map = helper(4,7);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.retainAll(keySet2));
    }

    @Test
    public void testRetainAllNullPointerKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.retainAll(null);});
    }

    @Test
    public void testRetainAllClassCastKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.retainAll(keySet2);});
    }

    @Test
    public void testRemoveAllKeySet(){
        MapAdapter map = helper(0,3);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.removeAll(keySet2));
        Assert.assertEquals(0,keySet.size());
    }

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

    @Test
    public void testRemoveAllNoOneInKeySet(){
        MapAdapter map = helper(4,7);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,3);
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.removeAll(keySet2));
    }

    @Test
    public void testRemoveAllNullPointerKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = helper(0,2);
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(NullPointerException.class,()->{keySet.removeAll(null);});
    }

    @Test
    public void testRemoveAllClassCastKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        MapAdapter map2 = new MapAdapter();
        map2.put("A","A");
        HSet keySet2 = map2.keySet();
        Assert.assertThrows(ClassCastException.class,()->{keySet.removeAll(keySet2);});
    }

    @Test
    public void testClearKeySet(){
        MapAdapter map = helper(0,2);
        HSet keySet = map.keySet();
        keySet.clear();
        Assert.assertEquals(0,keySet.size());
    }
    
    @Test
    public void testClearOnEmptyKeySet(){
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        keySet.clear();
        Assert.assertEquals(0,keySet.size());
    }

    @Test 
    public void testEqualsKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertTrue(keySet.equals(keySet2));
    }

    @Test 
    public void testEqualsLessKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(0);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.equals(keySet2));
    }

    @Test 
    public void testEqualsDifferentKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.put(11,"A");
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertFalse(keySet.equals(keySet2));
    }

    @Test 
    public void testHashCodeKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertEquals(keySet.hashCode(),keySet2.hashCode());
    }

    @Test 
    public void testHashCodeDifferentKeySet() {
        
        MapAdapter map = helper(0,10);
        MapAdapter map2 = helper(0,10);
        map2.remove(0);
        HSet keySet = map.keySet();
        HSet keySet2 = map2.keySet();
        Assert.assertEquals(keySet.hashCode(),keySet2.hashCode());
    }
    

    @Test 
    public void testHashCodeNullKeySet() {
        
        MapAdapter map = new MapAdapter();
        HSet keySet = map.keySet();
        Assert.assertEquals(0,keySet.hashCode());
    }
}
