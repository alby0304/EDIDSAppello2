package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import myAdapter.IllegalStateException;
import myAdapter.UnsupportedOperationException;


/**
 * This class is an implementation of the HMap interface this implementation does not support
 * null value in key and value.
 * <br>
 * HMap is a copy of the Map interface of Java Standars Edition CLDC 1.1.
 * MapAdapter uses as adaptee the class Hashtable of Java Micro Edition CLDC 1.1.
 * <br>
 * MapAdapter is built as part of the exam of the course "Elementi di
 * Ingegneria del Software" 2023/24 at Padua University.
 * <br>
 * MapAdapter has three collection views, which allow a map's contents to be viewed 
 * as a set of keys, collection of values, or set of key-value mappings. 
 * The order of a map is defined as the order in which the iterators on the map's collection views return their elements. 
 * 
 */ 
public class MapAdapter implements HMap{
    /**
     * The backing Hashtable of the Map
     */
    private Hashtable hash;
    /**
     * A void (no arguments) constructor which creates an empty map
     */
    public MapAdapter(){
        hash = new Hashtable();
    }

    /**
     * A constructor with a single argument of type Map,
     * which creates a new map with the same key-value mappings as its argument.
     * 
     * <br>
     * The constructor allows the user to copy any map, producing an equivalent map of the desired class
     * @param t map from which elements are copied
     * @throws NullPointerException if the passed map is a null pointer 
     */
    public MapAdapter(HMap t){
        if(t == null){
            throw new NullPointerException();
        }
        hash = new Hashtable();
        putAll(t);
    }

    /**
     * Returns the number of elements in this collection. If this collection
     * contains more than <code>Integer.MAX_VALUE</code> elements, returns
     * <code>Integer.MAX_VALUE</code>.
     * 
     * @return the number of elements in this collection
     */
    public int size(){
        int size = hash.size();
        if(size>Integer.MAX_VALUE) {
            size = Integer.MAX_VALUE;
        }
        return size;
    }

    /**
     * Returns <code>true</code> if this collection contains no elements.
     *
     * @return <code>true</code> if this collection contains no elements
     */
    public boolean isEmpty(){
        return hash.isEmpty();
    }

    /**
     * Returns <code>true</code> if this map contains a mapping for the specified key.
     * More formally, check if the key is null and return it using the <code>containsKey(Object key)</code> method in Hashtable.
     *
     * @param key key whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains the specified key.
     * @throws ClassCastException  if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is <code>null</code> (this map does not not permit null value).
     */
    public boolean containsKey(Object key){
        excKey(key);
        return hash.containsKey(key);
    }

    /**
     * Returns <code>true</code> if this map maps one or more keys to the specified value.
     * More formally, check if the value is null and return it using the <code>containsValue(Object value)</code> method in Hashtable.
     * This operation will probably require time linear in the map size for most implementations of the Map interface.
     *
     * @param value value whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains the specified key.
     * @throws ClassCastException   if the value is of an inappropriate type for this map.
     * @throws NullPointerException if the value is <code>null</code> (this map does not not permit null value).
     */
    public boolean containsValue(Object value){
        excValue(value);
        return hash.containsValue(value);
    }

    /**
     * Returns the value to which this map maps the specified key.
     * Returns <code>null</code> if the map contains no mapping for this key.
     * <br>
     * <br>
     * More formally, if this map contains a mapping from a key <code>k</code>
     * to a value <code>v</code> such that <code>(key==null ? k==null : key.equals(k))</code>, then this method returns <code>v</code>; otherwise it returns null.
     * 
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or <code>null</code> if the map contains no mapping for this key.
     * @throws ClassCastException  if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is <code>null</code> (this map does not not permit null value).
     */
    public Object get(Object key){
        excKey(key);
        return hash.get(key);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old value is replaced by the specified value.
     * (A map <code>m</code> is said to contain a mapping for a key <code>k</code> if and only if {@link #containsKey(Object)} would return true).
     * 
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key,
     *          or <code>null</code> if there was no mapping for key.
     * @throws ClassCastException    if the class of the specified key or value prevents it from being stored in this map.
     * @throws NullPointerException  if the specified key or value is <code>null</code>.
     */
    public Object put(Object key,Object value){
        excKey(key);
        excValue(value);
        return hash.put(key,value);
    }

    /**
     * Removes the mapping for this key from this map if it is present.
     * More formally, if this map contains a mapping from key k to value v such that <code>(key==null ? k==null : key.equals(k))</code>,
     * that mapping is removed. (The map can contain at most one such mapping.)
     * <br>
     * <br>
     * Returns the value to which the map previously associated the key,
     *  or <code>null</code> if the map contained no mapping for this key.
     * 
     * @param key key whose associated value is to be removed.
     * @return previous value associated with specified key, or <code>null</code> if there was no mapping for key.
     * @throws ClassCastException   if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is <code>null</code> (this map does not not permit null value).
     */
    public Object remove(Object key){
        excKey(key);
        return hash.remove(key);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * The effect of this call is equivalent to that of calling {@link #put(Object key,Object value)} on this map once for each mapping from key to value in the specified map.
     * The behavior of this operation is unspecified if the specified map is modified while the operation is in progress.
     * 
     * @param t Mappings to be stored in this map.
     * @throws ClassCastException    if the class of the specified key or value prevents it from being stored in this map.
     * @throws NullPointerException this map does not permit <code>null</code> keys or values, and the specified key or value is null.
     */
    public void putAll(HMap t){
        HIterator it = t.entrySet().iterator();
        while(it.hasNext())
        {
            EntryAdapter entry = (EntryAdapter)it.next();
            put(entry.getKey(),entry.getValue());
        }
    }

    /**
     * Removes all mappings from this map (optional operation).
     */
    public void clear(){
        hash.clear();
    }

    /**
     * Returns a set view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined.
     * The set supports element removal, which removes the corresponding mapping from the map, via the <code>Iterator.remove, Set.remove, removeAll retainAll, and clear operations</code>.
     * It does not support the <code>add or addAll</code> operations.
     *
     * @return a set view of the keys contained in this map.
     */
    public HSet keySet(){
        return new SetAdapter(0);
    }

    /**
     * Returns a collection view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa.
     * If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined.
     * The collection supports element removal, which removes the corresponding mapping from the map, via the <code>Iterator.remove, Set.remove, removeAll retainAll, and clear operations</code>.
     * It does not support the <code>add or addAll</code> operations.
     *
     * @return a collection view of the keys contained in this map.
     */
    public HCollection values(){
        return new SetAdapter(1);
    }

     /**
     * Returns a set view of the mappings contained in this map.
     * Each element in the returned set is a <a href= "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Map.Entry</a>.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined.
     * The set supports element removal, which removes the corresponding mapping from the map, via the <code>Iterator.remove, Set.remove, removeAll, retainAll and clear operations</code>.
     * It does not support the <code>add or addAll</code> operations.
     *
     * @return a set view of the mapping contained in this map.
     */
    public HSet entrySet(){
        return new SetAdapter(2);
    }

    /**
     * Compares the specified object with this map for equality.
     * Returns true if the given object is also a map and the two Maps represent the same mappings.
     * More formally, two maps t1 and t2 represent the same mappings if <code>t1.entrySet().equals(t2.entrySet())</code>.
     * This ensures that the equals method works properly across different implementations of the Map interface.
     *
     * @param o Object to be compared for equality with this map.
     * @return <code>true</code> if the specified object is equal to this
     *         collection
     * 
     * @see Object#equals(Object)
     */
    public boolean equals(Object o){
        if (!(o instanceof HMap)){
            return false;
        }

        HMap map = (HMap) o;
        if (size() != map.size()){
            return false;
        }
        else if(entrySet().equals(map.entrySet())){
            return true;
        }
        return false;
    }
    
    /**
     * Returns the hash code value for this map.
     * The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view.
     * This ensures that <code>t1.equals(t2)</code> implies that <code>t1.hashCode()==t2.hashCode()</code> for any two maps <code>t1</code> and <code>t2</code>, as required by the general contract of Object.hashCode.
     *
     * @return the hash code value for this map
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    public int hashCode(){
        int hash = 0;
        HIterator it = entrySet().iterator();
        while(it.hasNext()){
            hash += it.next().hashCode();
        }
        return hash;
    }

    /**
     * Auxiliary method that tests the keys to ensure they are not null and are all of the same type.
     *  More formally, it checks if the map is not empty that the instance of the key is the same as the key of the first element.
     * @param o key to verify 
     */
    private void excKey(Object o){
        if (o == null) 
        {
            throw new NullPointerException();
        }
        if (!hash.isEmpty() && !hash.keys().nextElement().getClass().isInstance(o)) 
        {
            throw new ClassCastException();
        }
    }

    /**
     * Auxiliary method that tests the values to ensure they are not null and are all of the same type.
     *  More formally, it checks if the map is not empty that the instance of the value is the same as the value of the first element.
     * @param o value to verify 
     */
    private void excValue(Object o) {
        if (o == null) 
        {
            throw new NullPointerException();
        }
        if (!hash.isEmpty() && !hash.elements().nextElement().getClass().isInstance(o)) 
        {
            throw new ClassCastException();
        }
    }

    /**
     * This class implements the HMap.Entry interface through the use of keys and values ​​to manage the map itself.
     * This cannot contain duplicate keys which can only refer to one value. 
     * <br>
     * Two standard constructors are provided:
     * <ul>
     * <li> an empty constructor (no arguments) 
     * <li> a constructor that initializes variables (key, value)  
     * </ul>
     * <br>
     * Some implementations prohibit inserting null keys and values ​​if unsuitable parameters are used.
     * NullPointerException and ClassCastException exceptions are thrown.
     * <br>
     * The only way to obtain a reference to a map entry is from the iterator of this collection-view.
     * <code>These Map.Entry</code> objects are valid only for the duration of the iteration
     * 
     * 
     */    
    public static class EntryAdapter implements HMap.Entry{
        /**
         * Key element of the key-value pair that constitutes the Entry.
         * All keys within the Map are of the same type and cannot be null.
         * Each key is unique and cannot have duplicates, making it the preferred element for use as the Map favors efficient searching and the use of methods on the keys.
         */
        private Object key;
        /**
         * Value element of the key-value pair that constitutes the Entry
         *  All values within the Map are of the same type and cannot be null.
         * Values can have duplicates, which is not recommended for use as Maps favor efficient searching and the use of methods on keys.
         */
        private Object value;
        /**
         * Reference to the hashtable it relies on, in case it is an Entry derived from the return of the next() method of iterator() of the entrySet().
         */
        private Hashtable hash;
        /**
         * Empty public constructor
         */
        public EntryAdapter(){
        }
        /**
         * Public constructor that allows initializing the object's variables.
         * @param key object to assign to the key element 
         * @param value object to assign to the value element
         * @throws NullPointerException if the key or the value is null
         */
        public EntryAdapter(Object key, Object value){
            if(key == null || value == null){
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
        }

        /**
         * Private constructor that allows initializing the object's variables.
         * @param hash the hashtable it relies on
         * @param key object to assign to the key element 
         * @param value object to assign to the value element
         * @throws NullPointerException if the key or the value is null
         */
        private EntryAdapter(Hashtable hash, Object key, Object value){
            if(key == null || value == null){
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        /**
         * Returns the key corresponding to this entry.
         * 
         * @return the key corresponding to this entry.
         */
        public Object getKey(){
            return key;
        }

        /**
         * Returns the value corresponding to this entry. If the mapping has been removed from the backing map (by the iterator's <code>remove</code> operation), the results of this call are undefined.
         * 
         * @return he value corresponding to this entry.
         */
        public Object getValue(){
            return value;
        }
        
        /**
         * Replaces the value corresponding to this entry with the specified value.
         * The behavior of this call is undefined if the mapping has already been removed from the map (by the iterator's <code>remove</code> operation).
         * <br>
         * If applied to an Entry returned by entrySet, it also modifies the original map; otherwise, it only modifies its value.
         * 
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws ClassCastException if the class of the specified value prevents it from being stored in the backing map.
         * @throws NullPointerException the backing map does not permit null values, and the specified value is null.
         */
        public Object setValue(Object value){
            if(value == null){
                throw new NullPointerException();
            }
            Object o = this.value;
            
             if(!value.getClass().isInstance(o)){
                throw new ClassCastException();
             }
             this.value = value;
             if(hash != null){
                hash.remove(key);
                hash.put(key,value);

             }
             return o;
        }

        /**
         * Compares the specified object with this entry for equality. Returns <code>true</code> if the given object is also a map entry and the two entries represent the same mapping. More formally, two entries e1 and e2 represent the same mapping if <code>
                        (e1.getKey()==null ?
                        e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
                        (e1.getValue()==null ?
                         e2.getValue()==null : e1.getValue().equals(e2.getValue())) </code>

         * <br>
         * @param o object to be compared for equality with this map entry.
         * @return <code>true</code> if the specified object is equal to this map entry.                
         * @see Object#equals(Object)
         *
         */
        public boolean equals(Object o){
            if(o.getClass().isInstance(new EntryAdapter())){
                EntryAdapter entry = (EntryAdapter)o;
                if(key.equals(entry.getKey())&&value.equals(entry.getValue())){
                    return true;
                }
            }
            return false;
        }
        
        /**
         * Returns the hash code value for this map entry. The hash code of a map entry <code>e</code> is defined to be:
            <code>(e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
            (e.getValue()==null ? 0 : e.getValue().hashCode())</code>
            <br>
            <br>
            This ensures that <code>e1.equals(e2</code> implies that <code>e1.hashCode()==e2.hashCode()</code> for any two Entries e1 and e2, as required by the general contract of Object.hashCode.
         *
         * @return the hash code value for this map entry.
         * @see Object#hashCode()
         */
        public int hashCode(){
            int hash = 0;
            if(key == null){
                hash +=0;
            }
            else{
                hash += key.hashCode();
            }
            if(value == null){
                hash +=0;
            }
            else{
                hash += value.hashCode();
            }
            return hash;
        }



    }

    /**
     * Set of the class MapAdapter. It implements the HSet interface and HCollection (because HSet extends HCollection).
     * It implements all methods except for <code>add()</code> and <code>addAll()</code> 
     * which are not supported and throw exceptions.
     * <br>
     */
    private class SetAdapter implements HSet{

        /**
         * The attribute 'type' indicates the behavior of the returned set.
         * <ul>
         * <li>With type = 0, the set behaves as defined by <code>keySet</code> (a set containing the keys).</li>
         * <li>With type = 1, it behaves as defined by <code>values</code>  (a collection containing the values).</li>
         * <li>With type = 2, it behaves as defined by <code>entrySet</code>  (a set containing 'Map.Entry' pairs of key-value).</li>
         * </ul>
         * <br>
         * In all three cases, removals are permitted using also an iterator, but additions are not.
         */
        private int type; 
        
        /**
         * Private constructor as it should only be possible to create Set/Collection through the aforementioned methods.
         * Respects the constraints of the previously described type.
         * @param type indicate type of the structure
         */
        private SetAdapter(int type){
            this.type = type;
        }

        /**
         *  Returns the number of elements in this set (its cardinality). If this set contains more than <code>Integer.MAX_VALUE</code> elements, returns <code>Integer.MAX_VALUE</code>.
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#size()">size</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * @return the number of elements in this set (its cardinality). 
         */
        public int size(){
            return hash.size();
        }

        /**
         *  Returns <code>true</code> if this set contains no elements.
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#isEmpty()">isEmpty</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * @return  <code>true</code> if this set contains no elements.
         */
        public boolean isEmpty(){
            return  hash.isEmpty();
        }

        /**
         * Returns true if this set contains the specified element.
         * More formally, returns <code>true</code> if and only if this set contains an element <code>e</code> such that <code>(o==null ? e==null : o.equals(e))</code>
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#contains(Object)">iterator</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * 
         * @return an <code>HIterator</code> over the elements in this collection
         * @throws ClassCastException   if the value or key is of an inappropriate type for this map.
         * @throws NullPointerException if the value or key is <code>null</code> (this map does not not permit null value).
         */
        public boolean contains(Object o){
            if(type == 0){
                return containsKey(o);
            }
            else if(type == 1){
                return containsValue(o);
            }
            else{
                if(o == null){
                    throw new NullPointerException();
                }
                else if(!o.getClass().isInstance(new EntryAdapter())){
                    throw new ClassCastException();
                }
                EntryAdapter enter = (EntryAdapter) o;
                if(containsKey(enter.getKey())){
                    if(enter.getValue().equals(get(enter.getKey()))){
                        return true;
                    }
                }
                return false;

            }
        }

        /**
         * Returns an iterator over the elements in this collection. There are no
         * guarantees concerning the order in which the elements are returned
         * (unless this collection is an instance of some class that provides a
         * guarantee).
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#iterator()">iterator</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * 
         * @return an <code>HIterator</code> over the elements in this collection
         */
        public HIterator iterator(){
            return new IteratorAdapter(type);
        }

        /**
         * Returns an array containing all of the elements in this set. Obeys the general contract of the Collection.toArray method.
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#toArray()">toArray</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * @return an array containing all of the elements in this set
         */
        public Object[] toArray() {
            return  toArray(new Object[size()]);
        }

        /**
         * Returns an array containing all of the elements in this set;the runtime type of the returned array is that of the specified array.
         * Obeys the general contract of the <code>Collection.toArray(Object[])</code> method.
         *<br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#toArray(java.lang.Object[])">toArray</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * 
        * @param a the array into which the elements of this collection are to be
        *          stored, if it is big enough; otherwise, a new array of the same
        *          runtime type is allocated for this purpose.
        * @return an array containing the elements of this collection
        * @throws NullPointerException if the specified array is <code>null</code>.
        */
        public Object[] toArray(Object[] a) {
            if (a == null){
                throw new NullPointerException();
            }
    
            if (a.length <= size()){
                a = new Object[size()];
            }
            else{
                a = new Object[a.length];
            }
            int i = 0;
            HIterator it = iterator();
            while(it.hasNext()){
                a[i] = it.next();
                i++;
            }
    
            if (a.length > size()){
                for(int j=i;i<size();i++){
                    a[j] = null;
                }
            }
    
            return a;
        }

        /**
        *  Add operation is not supported for this implementation.
        * @param o the array into which the elements of this collection are to be
        *          stored, if it is big enough; otherwise, a new array of the same
        *          runtime type is allocated for this purpose.
        * @throws UnsupportedOperationException add is not supported by this
     *                                       collection.
        */
        public boolean add(Object o){
            throw new UnsupportedOperationException();
        }

        /**
        * Removes the specified element from this set if it is present (optional operation). More formally,
        * removes an element <code>e</code> such that <code>(o==null ?  e==null :
        * o.equals(e))</code>, if the set contains such an element.
        * Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call).
        * (The set will not contain the specified element once the call returns).
        * <br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#remove()">remove</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * @param o element to be removed from this collection, if present.
        * @return <code>true</code> if this collection changed as a result of the
        *         call
        * 
        * @throws ClassCastException            if the type of the specified element
        *                                       is incompatible with this collection
        *                                       (optional).
        * @throws NullPointerException          if the specified element is null and
        *                                       this
        *                                       collection does not support null
        *                                       elements (optional).
        */
        public boolean remove(Object o){
            boolean rtn = false;
            if(type == 0){
                excKey(o);
                if(hash.remove(o) != null){
                    rtn = true;
                }
            }
            else if(type == 1){
                excValue(o);
                Enumeration en = hash.keys();
                while(en.hasMoreElements()){
                    Object key = en.nextElement();
                    if(o.equals(hash.get(key))){
                        hash.remove(key);
                        rtn = true;
                    }
                }
            }
            else {
                if(o == null){
                    throw new NullPointerException();
                }
                else if(!o.getClass().isInstance(new EntryAdapter())){
                    throw new ClassCastException();
                }
                EntryAdapter enter = (EntryAdapter) o;
                if(hash.remove(enter.getKey())!=null){
                    rtn = true;
                }
            }
            return rtn;
        }

        /**
        * Returns <code>true</code> if this set contains all of the elements in the specified collection.
        * If the specified collection is also a set, this method returns <code>true</code> if it is a <i>subset</i> of this set.
        *<br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#containsAll()">containsAll</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * @param c collection to be checked for containment in this collection.
        * @return <code>true</code> if this collection contains all of the elements
        *         in the specified collection
        * @throws ClassCastException   if the types of one or more elements
        *                              in the specified collection are incompatible
        *                              with this collection (optional).
        * @throws NullPointerException if the specified collection contains one
        *                              or more null elements and this collection does
        *                              not support null elements (optional).
        * @throws NullPointerException if the specified collection is
        *                              <code>null</code>.
        * @see #contains(Object)
        */
        public boolean containsAll(HCollection c){
            if(c==null){
                throw new NullPointerException();
            }
            HIterator it = c.iterator();
            while(it.hasNext()){
                if(contains(it.next())==false)
                {
                    return false;
                }
            }
            return true;
        }

        /**
         * Add All operation is not supported.
         * @param c elements to be inserted into this collection.
         * @return <code>true</code> if this collection changed as a result of the
         *         call
         * 
         * @throws UnsupportedOperationException if this collection does not
         *                                       support the <code>addAll</code> method.
         * @see #add(Object)
         */
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
            //Not supported (ne metodo values,ne entrySet,ne keySet supportano questo metodo)
        }

        /**
         * Retains only the elements in this collection that are contained in the
         * specified collection. In other words, removes from
         * this set all of its elements that are not contained in the specified collection.
         * If the specified collection is also a set, this operation effectively modifies this set so that its value is the <i>intersection</i> of the two sets.
         *  <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#retainAll()">retainAll</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * @param c elements to be retained in this collection.
         * @return <code>true</code> if this collection changed as a result of the
         *         call
         * @throws ClassCastException            if the types of one or more elements
         *                                       in this collection are incompatible
         *                                       with the specified collection
         *                                       (optional).
         * @throws NullPointerException          if this collection contains one or more
         *                                       null elements and the specified
         *                                       collection does not support null
         *                                       elements (optional).
         * @throws NullPointerException          if the specified collection is
         *                                       <code>null</code>.
         * @see #remove(Object)
         */
        public boolean retainAll(HCollection c){
            if(c==null){
                throw new NullPointerException();
            }
            HIterator it = iterator();
            boolean rtn = false;
            while (it.hasNext()) {
                Object o = it.next();
                if (!c.contains(o)) {
                    remove(o);
                    rtn = true;
                }
            }
            return rtn;
        }

        /**
         * 
         * Removes all this collection's elements that are also contained in the
         * specified collection.
         * If the specified collection is also a set,
         * this operation effectively modifies this set so that its value is the <i>asymmetric set difference</i> of the two sets.
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#removeAll()">removeAll</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * @param c elements to be removed from this collection.
         * @return <code>true</code> if this collection changed as a result of the
         *         call
         * @throws ClassCastException            if the types of one or more elements
         *                                       in this collection are incompatible
         *                                       with the specified collection
         *                                       (optional).
         * @throws NullPointerException          if this collection contains one or more
         *                                       null elements and the specified
         *                                       collection does not support null
         *                                       elements (optional).
         * @throws NullPointerException          if the specified collection is
         *                                       <code>null</code>.
         * @see #remove(Object)
         */
        public boolean removeAll(HCollection c){
            if(c==null){
                throw new NullPointerException();
            }
            HIterator it = iterator();
            boolean rtn = false;
            while (it.hasNext()) {
                Object o = it.next();
                if (c.contains(o)) {
                    remove(o);
                    rtn = true;
                }
            }
            return rtn;
        }

        /**
         * Removes all of the elements from this set.
         * This set will be empty after this method returns.
         *<br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#clear()">clear</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        */
        public void clear()
        {
            hash.clear();
        }

        /**
         * Compares the specified object with this collection for equality.
         * Returns <code>true</code> if the specified object is also a set, the two sets have the same size, and every member of the specified set is contained in this set (or equivalently, every member of this set is contained in the specified set).
         * This definition ensures that the equals method works properly across different implementations of the set interface.
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#equals()">equals</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         *
         * @param o Object to be compared for equality with this collection.
         * @return <code>true</code> if the specified object is equal to this
         *         collection
         * 
         * @see Object#hashCode()
         */
        public boolean equals(Object o){
            
            if(type == 1){
                if(!(o instanceof HCollection)){
                    return false;
                }
            }
            else{
                if(!(o instanceof HSet)){
                    return false;
                }
            }
            SetAdapter c = (SetAdapter) o;
            HIterator it = iterator();
            HIterator it2 = c.iterator();
            while(it.hasNext()&&it2.hasNext()){
                Object o1 = it.next();
                Object o2 = it2.next();
                if((o1 == null)||(o2==null)||(o1.equals(o2)==false)){
                    return false;
                }
            }
            if(it.hasNext()!=it2.hasNext()){
                return false;
            }
            return true;
        }

        /**
         * Returns the hash code value for this set. 
         * The hash code of a set is defined to be the sum of the hash codes of the elements in the set,
         * where the hashcode of a null element is defined to be zero. 
         * This ensures that <code>s1.equals(s2)</code> implies that <code>s1.hashCode()==s2.hashCode()</code> for any two sets <code>s1</code> and <code>s2</code>,
         * as required by the general contract of the <code>Object.hashCode</code> method
         *<br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#hashCode()">hashCode</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * @return the hash code value for this collection
        * 
        * @see Object#hashCode()
        * @see Object#equals(Object)
        */
        public int hashCode(){
            int hash = 0;
            HIterator it = iterator();
            while(it.hasNext()){
                hash += it.next().hashCode();
            }
            return hash; 
        }

        /**
         * Iterator of the Set. It implements the HIterator interface.
         * The iterator is used to scan and put the set in order.
         * <br>
         * The type of Iterator depends on the Set and can have 3 different behaviors:
         * <ul>
         * <li>if type = 0, it behaves like an iterator over the <code>keys</code></li>
         * <li>if type = 1, it behaves like an iterator over the <code>value</code></li>
         * <li>if type = 2, it behaves like an iterator over the <code>Map.Entry</code></li>
         * </ul>
         */
        private class IteratorAdapter implements HIterator{
            /**
             * Variable that indicates whether the state of the iterator is still valid.
             * An iterator becomes invalid if the <code>remove()</code>  method is called twice consecutively without calling <code>next()</code>  before the second call.
             */
            private boolean state = false;
            /**
             * The attribute follows the specifications provided in SetAdapter.
             * In particular:
             * <ul>
             * <li>if type = 0, it behaves like an iterator over the <code>keys</code></li>
             * <li>if type = 1, it behaves like an iterator over the <code>value</code></li>
             * <li>if type = 2, it behaves like an iterator over the <code>Map.Entry</code></li>
             * </ul>
             */
            private int type;
            /**
             * The backing Enumeration of the iterator.
             * It can contain elements of different types:
             * <ul>
             * <li>type = 0 contains the <code>keys</code> of the map</li>
             * <li>type = 1 contains the <code>value</code> of the map</li>
             * <li>type = 2, although it is an iterator of <code>Map.Entry</code>, does not contain this class, but contains the keys since navigation and search are facilitated with the keys.</li>
             * </ul>
             * <br>
             * However, it will still return objects of type Map.Entry."
             */
            private Enumeration en;
            /**
             * Object containing the element returned by the next method:
             * <ul>
             * <li>type = 0 contains object of the <code>keys type</code>
             * <li>type = 1 contains object of the <code>value type</code>
             * <li>type = 2 contains object of the <code>Map.Entry type</code></li>
             * </ul>
            */
            private Object next;
            
            /**
             * Private constructor because it is not possible to instantiate an object of this type from the outside;
             * the object is only returned by the <code>iterator()</code> method of the <code>SetAdapter</code> class.
             * <br>
             * It instantiates the Enumeration with the corresponding enumeration of keys/values, respecting the type behavior described above.
             * @param type indicate the type of the structure.
             */
            private IteratorAdapter(int type){
                this.type = type;
                if(type == 0){
                    en = hash.keys();
                }
                if(type == 1){
                    en = hash.elements();
                }
                if(type == 2){
                    en = hash.keys();
                }


            }
            
            /**
             * Returns <code>true</code> if the iteration has more elements. (In other
             * words, returns <code>true</code> if <code>next</code> would return an element
             * rather than throwing an exception.)
             *
             * @return <code>true</code> if the iterator has more elements.
             */
            public boolean hasNext(){
                return en.hasMoreElements();
            }
    
            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration.
             * @throws NoSuchElementException iteration has no more elements.
             */
            public  Object next(){
                state = true;
                if(!hasNext())
                {
                    throw new NoSuchElementException();
                }
                next = en.nextElement();
                if(type == 2 ){
                    return new EntryAdapter(hash,next,get(next));
                }
                return next;
            }

            /**
             * 
             * Removes from the underlying collection the last element returned by the
             * iterator (optional operation). This method can be called only once per
             * call to <code>next</code>. The behavior of an iterator is unspecified if
             * the underlying collection is modified while the iteration is in
             * progress in any way other than by calling this method.
             * 
             * @throws IllegalStateException         if the <code>next</code> method has not
             *                                       yet been called, or the
             *                                       <code>remove</code> method has already
             *                                       been called after the last call to the
             *                                       <code>next</code> method.
             */
            public void remove(){
                if(state != false){
                    state = false;
                    if(type == 1){
                        Enumeration en2 = hash.keys();
                        while(en2.hasMoreElements()){
                            Object key = en2.nextElement();
                                if(next == get(key)){
                                    hash.remove(key);
                                }
                        }
                        
                    }
                    else{
                        hash.remove(next);

                    }
                }
                else{
                    throw new IllegalStateException();
                }
            }
    
    
        }
    }
    
}

