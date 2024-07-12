package myAdapter;
/*
 * An object that maps keys to values.
 * A map cannot contain duplicate keys; each key can map to at most one value.
 * <br>
 * <br>
 * This interface takes the place of the <code>Dictionary</code> class,
 *  which was a totally abstract class rather than an interface.
 * <br>
 * <br>
 * The <code>Map</code> interface provides three <i>collection views</i>,
 *  which allow a map's contents to be viewed as a set of keys, collection of values, or set of key-value mappings.
 * The <i>order</i> of a map is defined as the order in which the iterators on the map's collection views return their elements.
 *  Some map implementations, like the <code>TreeMap</code> class, make specific guarantees as to their order; others, like the <code>HashMap</code> class, do not.
 * <br>
 * <br>
 * 
 * Note: great care must be exercised if mutable objects are used as map 
 * keys.  The behavior of a map is not specified if the value of an object is 
 * changed in a manner that affects equals comparisons while the object is a
 * key in the map.  A special case of this prohibition is that it is not 
 * permissible for a map to contain itself as a key.  While it is permissible 
 * for a map to contain itself as a value, extreme caution is advised: the 
 * equals and hashCode methods are no longer well defined on a such a map.
 * <br>
 * <br>
 * All general-purpose map implementation classes should provide two 
 * "standard" constructors: a void (no arguments) constructor which creates an 
 * empty map, and a constructor with a single argument of type <code>Map</code>, 
 * which creates a new map with the same key-value mappings as its argument. 
 * In effect, the latter constructor allows the user to copy any map, 
 * producing an equivalent map of the desired class.  There is no way to 
 * enforce this recommendation (as interfaces cannot contain constructors) but 
 * all of the general-purpose map implementations in the SDK comply.
 * <br>
 * <br>
 * The "destructive" methods contained in this interface, that is, the 
 * methods that modify the map on which they operate, are specified to throw 
 * <code>UnsupportedOperationException</code> if this map does not support the 
 * operation.  If this is the case, these methods may, but are not required 
 * to, throw an <code>UnsupportedOperationException</code> if the invocation would 
 * have no effect on the map.  For example, invoking the {@link #putAll(HMap)}
 * method on an unmodifiable map may, but is not required to, throw the 
 * exception if the map whose mappings are to be "superimposed" is empty.
 * <br>
 * <br>
 * <Some map implementations have restrictions on the keys and values they 
 * may contain.  For example, some implementations prohibit null keys and 
 * values, and some have restrictions on the types of their keys.  Attempting 
 * to insert an ineligible key or value throws an unchecked exception, 
 * typically <code>NullPointerException</code> or <code>ClassCastException</code>. 
 * Attempting to query the presence of an ineligible key or value may throw an 
 * exception, or it may simply return false; some implementations will exhibit 
 * the former behavior and some will exhibit the latter.  More generally, 
 * attempting an operation on an ineligible key or value whose completion 
 * would not result in the insertion of an ineligible element into the map may 
 * throw an exception or it may succeed, at the option of the implementation. 
 * Such exceptions are marked as "optional" in the specification for this interface.
 * <br>
 *<br>
 *
 * This class is a copy of the <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Map.html">Map</a> interface of <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Java Standard Edition 1.4.2</a>.
 * 
 * @see <a href=
 *      "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Map.html">Map</a>
 * @see <a href=
 *      "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Java
 *      Standard Edition 1.4.2</a>
 */
public interface HMap{
    /**
     * Returns the number of elements in this collection. If this collection
     * contains more than <code>Integer.MAX_VALUE</code> elements, returns
     * <code>Integer.MAX_VALUE</code>.
     * 
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns <code>true</code> if this collection contains no elements.
     *
     * @return <code>true</code> if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns <code>true</code> if this map contains a mapping for the specified key.
     * More formally, check if the key is null and return it using the <code>containsKey(Object key)</code> method in Hashtable.
     *
     * @param key key whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains the specified key.
     * @throws ClassCastException  if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is null and this map does not not permit null value.
     */
    boolean containsKey(Object key);

    /**
     * Returns <code>true</code> if this map maps one or more keys to the specified value.
     * More formally, check if the value is null and return it using the <code>containsValue(Object value)</code> method in Hashtable.
     * This operation will probably require time linear in the map size for most implementations of the Map interface.
     *
     * @param value value whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains the specified key.
     * @throws ClassCastException   if the value is of an inappropriate type for this map.
     * @throws NullPointerException if the value is null and this map does not not permit null value.
     */
    boolean containsValue(Object value);

     /**
     * Returns the value to which this map maps the specified key.
     * Returns <code>null</code> if the map contains no mapping for this key.
     * 
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or <code>null</code> if the map contains no mapping for this key.
     * @throws ClassCastException  if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is null and this map does not not permit null value.
     */
    Object get(Object key);

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old value is replaced by the specified value.
     * (A map <code>m</code> is said to contain a mapping for a key <code>k</code> if and only if {@link #containsKey(Object)} would return true.))
     * 
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key,
     *          or <code>null</code> if there was no mapping for key.
     * @throws ClassCastException    if the class of the specified key or value prevents it from being stored in this map.
     * @throws NullPointerException  if the specified key or value is null.
     */
    Object put(Object key,Object value);
    
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
     * @throws NullPointerException if the key is null and this map does not not permit null value.
     */
    Object remove(Object key);

    /**
     * Copies all of the mappings from the specified map to this map.
     * The effect of this call is equivalent to that of calling {@link #put(Object key,Object value)} on this map once for each mapping from key to value in the specified map.
     * The behavior of this operation is unspecified if the specified map is modified while the operation is in progress.
     * 
     * @param t Mappings to be stored in this map.
     * @throws ClassCastException    if the class of the specified key or value prevents it from being stored in this map.
     * @throws NullPointerException this map does not permit <code>null</code>s keys or values, and the specified key or value is null.
     */
    void putAll(HMap t);

    /**
     * Removes all mappings from this map (optional operation).
     */
    void clear();

     /**
     * Returns a set view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined.
     * The set supports element removal, which removes the corresponding mapping from the map, via the <code>Iterator.remove, Set.remove, removeAll retainAll, and clear operations</code>.
     * It does not support the add or addAll operations.
     *
     * @return a set view of the keys contained in this map.
     */
    HSet keySet();

    /**
     * Returns a collection view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa.
     * If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined.
     * The collection supports element removal, which removes the corresponding mapping from the map, via the <code>Iterator.remove, Set.remove, removeAll retainAll, and clear operations</code>.
     * It does not support the add or addAll operations.
     *
     * @return a collection view of the keys contained in this map.
     */
    HCollection values();

    /**
     * Returns a set view of the mappings contained in this map.
     * Each element in the returned set is a <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Map.Entry</a>.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined.
     * The set supports element removal, which removes the corresponding mapping from the map, via the <code>Iterator.remove, Set.remove, removeAll, retainAll and clear operations</code>.
     * It does not support the add or addAll operations.
     *
     * @return a set view of the mapping contained in this map.
     */
    HSet entrySet();

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
    boolean equals(Object o);

    /**
     * Returns the hash code value for this map.
     * The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view.
     * This ensures that <code>t1.equals(t2)</code> implies that <code>t1.hashCode()==t2.hashCode()</code> for any two maps <code>t1</code> and <code>t2</code>, as required by the general contract of Object.hashCode.
     *
     * @return the hash code value for this map
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();

    /**
     * A map entry (key-value pair).
     * The <code>Map.entrySet</code> method returns a collection-view of the map, whose elements are of this class.
     * The only way to obtain a reference to a map entry is from the iterator of this collection-view.
     * These <code>Map.entrySet</code> objects are valid only for the duration of the iteration; more formally,
     * the behavior of a map entry is undefined if the backing map has been modified after the entry was returned by the iterator,
     * except through the iterator's own <code>remove</code> operation, or through the <code>setValue</code> operation on a map entry returned by the iterator.
     */
    public static interface Entry{
        /**
         * Returns the key corresponding to this entry.
         * 
         * @return the key corresponding to this entry.
         */
        Object getKey();

        /**
         * Returns the value corresponding to this entry. If the mapping has been removed from the backing map (by the iterator's <code>remove</code> operation), the results of this call are undefined.
         * 
         * @return he value corresponding to this entry.
         */
        Object getValue();

        /**
         * Replaces the value corresponding to this entry with the specified value.
         * The behavior of this call is undefined if the mapping has already been removed from the map (by the iterator's <code>remove</code> operation).
         * 
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws ClassCastException if the class of the specified value prevents it from being stored in the backing map.
         * @throws NullPointerException the backing map does not permit null values, and the specified value is null.
         */
        Object setValue(Object value);

        /**
         * Compares the specified object with this entry for equality. Returns <code>true</code> if the given object is also a map entry and the two entries represent the same mapping. More formally, two entries e1 and e2 represent the same mapping if <code>
                        (e1.getKey()==null ?
                        e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &&
                        (e1.getValue()==null ?
                         e2.getValue()==null : e1.getValue().equals(e2.getValue())) </code>

         * <br>
         * @param o object to be compared for equality with this map entry.
         * @return <code>true</code> if the specified object is equal to this map entry.                
         * @see Object#equals(Object)
         *
         */
        boolean equals(Object o);
        
        /**
         * Returns the hash code value for this map entry. The hash code of a map entry <code>e</code> is defined to be:
            <code>(e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
            (e.getValue()==null ? 0 : e.getValue().hashCode())</code>
            <br>
            <br>
            This ensures that <code>e1.equals(e2</code> implies that <code>e1.hashCode()==e2.hashCode()</code> for any two Entries e1 and e2, as required by the general contract of Object.hashCode.
        *
        *@return the hash code value for this map entry.
        *@see Object#hashCode()
         */
        int hashCode();

    }
}