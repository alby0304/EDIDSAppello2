package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import myAdapter.IllegalStateException;
import myAdapter.UnsupportedOperationException;

//TODO:introduzione progetto
public class MapAdapter implements HMap{
    /*
     * The backing Hashtable of the Map
     */
    private Hashtable hash;
    /*
     * A void (no arguments) constructor which creates an empty map
     */
    public MapAdapter(){
        hash = new Hashtable();
    }
    /*
     * A constructor with a single argument of type Map,
     * which creates a new map with the same key-value mappings as its argument.
     * 
     * <br>
     * The constructor allows the user to copy any map, producing an equivalent map of the desired class
     */
    public MapAdapter(HMap t){
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
     * @throws NullPointerException if the key is null and this map does not not permit null value.
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
     * @throws NullPointerException if the value is null and this map does not not permit null value.
     */
    public boolean containsValue(Object value){
        excValue(value);
        return hash.containsValue(value);
    }

    /**
     * Returns the value to which this map maps the specified key.
     * Returns <code>null</code> if the map contains no mapping for this key.
     * 
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or <code>null</code> if the map contains no mapping for this key.
     * @throws ClassCastException  if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is null and this map does not not permit null value.
     */
    public Object get(Object key){
        excKey(key);
        return hash.get(key);
    }

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
    public Object put(Object key,Object value){
        excKey(key);
        excValue(value);
        if(hash.containsKey(key)){
            hash.remove(key);
        }
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
     * @throws NullPointerException if the key is null and this map does not not permit null value.
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
     * @throws NullPointerException this map does not permit <code>null</code>s keys or values, and the specified key or value is null.
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
     * It does not support the add or addAll operations.
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
     * It does not support the add or addAll operations.
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
     * It does not support the add or addAll operations.
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
        if (!(o instanceof HMap))
            return false;

        HMap map = (HMap) o;
        if (size() != map.size())
            return false;

        HIterator it = entrySet().iterator();
        HIterator it2 = map.entrySet().iterator();

        while (it.hasNext()) {
            Object e1 = it.next();
            Object e2 = it2.next();
            if (!(e1 == null ? e2 == null : e1.equals(e2)))
                return false;
        }

        return true;
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

    //TODO:introduzione implmentazone interfaccia e copiare sui metodi
    public static class EntryAdapter implements HMap.Entry{
        /*
         * Key element of the key-value pair that constitutes the Entry.
         * All keys within the Map are of the same type and cannot be null.
         * Each key is unique and cannot have duplicates, making it the preferred element for use as the Map favors efficient searching and the use of methods on the keys.
         */
        private Object key;
        /*
         * Value element of the key-value pair that constitutes the Entry
         *  All values within the Map are of the same type and cannot be null.
         * Values can have duplicates, which is not recommended for use as Maps favor efficient searching and the use of methods on keys.
         */
        private Object value;
        /*
         * Empty public constructor
         */
        public EntryAdapter(){
        }
        /*
         * Public constructor that allows initializing the object's variables.
         */
        public  EntryAdapter(Object key, Object value){
            this.key = key;
            this.value = value;
        }

        public Object getKey(){
            return key;
        }

        public Object getValue(){
            return value;
        }

        public Object setValue(Object value){
            if(value == null){
                throw new NullPointerException();
            }
             Object o = this.value;

             if(!value.getClass().isInstance(o)){
                throw new ClassCastException();
             }
             this.value = value;
             return o;
        }

        public boolean equals(Object o){
            if(o.getClass().isInstance(new EntryAdapter())){
                EntryAdapter entry = (EntryAdapter)o;
                if(key.equals(entry.getKey())&&value.equals(entry.getValue())){
                    return true;
                }
            }
            return false;
        }
        
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

    //TODO:introduzione setAdapter e copiare sui metodi tutti anche quelli già fatti perchè li ho sistemati
    private class SetAdapter implements HSet{

        /*
         * The attribute 'type' indicates the behavior of the returned set.
         * With type = 0, the set behaves as defined by 'keySet' (a set containing the keys).
         * With type = 1, it behaves as defined by 'values' (a collection containing the values).
         * With type = 2, it behaves as defined by 'entrySet' (a set containing 'Map.Entry' pairs of key-value).
         * In all three cases, removals are permitted using also an iterator, but additions are not.
         */
        private int type; 
        /*
         * Private constructor as it should only be possible to create Set/Collection through the aforementioned methods.
         * Respects the constraints of the previously described type.
         */
        private SetAdapter(int type){
            this.type = type;
        }

        /* Returns the number of elements in this set (its cardinality). If this set contains more than <code>Integer.MAX_VALUE</code> elements, returns <code>Integer.MAX_VALUE</code>.
        * <br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#size()">size</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * @return the number of elements in this set (its cardinality). 
        */
        public int size(){
            return size();
        }

        /* Returns <code>true</code> if this set contains no elements.
        * <br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#isEmpty()">isEmpty</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * @return  <code>true</code> if this set contains no elements.
        */
        public boolean isEmpty(){
            return  isEmpty();
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
        public boolean contains(Object o){
            if(type == 0){
                excKey(o);
                return hash.containsKey(o);
            }
            else if(type == 1){
                excKey(o);
                return hash.containsValue(o);
            }
            else{
                if(o == null){
                    throw new NullPointerException();
                }
                else if(!o.getClass().isInstance(new EntryAdapter())){
                    throw new ClassCastException();
                }
                EntryAdapter enter = (EntryAdapter) o;
                if(hash.containsKey(enter.getKey())){
                    if(enter.getValue()==hash.get(enter.getKey())){
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
            return new Object[size()];
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
        * 
        * @throws ArrayStoreException  the runtime type of the specified array is
        *                              not a supertype of the runtime type of every
        *                              element in this
        *                              collection.
        * @throws NullPointerException if the specified array is <code>null</code>.
        */
        public Object[] toArray(Object[] a) {
            if (a == null){
                throw new NullPointerException();
            }
    
            if (a.length < size()){
                a = new Object[size()];
            }
            int i = 0;
            HIterator it = iterator();
            while(it.hasNext()){
                a[i] = it.next();
            }
    
            if (a.length > size()){
                a[size()] = null;
            }
    
            return a;
        }

        /**
        *  Adds the specified element to this set if it is not already present(optional operation).  More formally, adds the specified element,
        *  <code>o</code>, to this set if this set contains no element <code>e</code> such that <code>(o==null ? e==null :o.equals(e))</code>.  If this set already contains the specified
        * element, the call leaves this set unchanged and returns <code>false</code>.
        * In combination with the restriction on constructors, this ensures tha sets never contain duplicate elements.
        * <br>
        * <br>
        * The stipulation above does not imply that sets must accept all elements; sets may refuse to add any particular element, including
        * <code>null</code>, and throwing an exception, as described in the specification for <code>Collection.add</code>.  Individual set
        * implementations should clearly document any restrictions on the the elements that they may contain.
        * <br>
        * Specified by: <br>
        * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#add()">add</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
        * <br>
        * @param a the array into which the elements of this collection are to be
        *          stored, if it is big enough; otherwise, a new array of the same
        *          runtime type is allocated for this purpose.
        * @return an array containing the elements of this collection
        * 
        * @throws ArrayStoreException  the runtime type of the specified array is
        *                              not a supertype of the runtime type of every
        *                              element in this
        *                              collection.
        * @throws NullPointerException if the specified array is <code>null</code>.
        */
        public boolean add(Object o){
            throw new UnsupportedOperationException();
            //Not supported (ne metodo values,ne entrySet,ne keySet supportano questo metodo)
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
        * @throws UnsupportedOperationException remove is not supported by this
        *                                       collection.
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
                    if(o == get(key)){
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
            boolean change  = true;
            while(c.iterator().hasNext()){
                if(contains(c.iterator().next())==false && change == true)
                {
                    change = false;
                }
            }
            return change;
        }

        /**
         * Adds all of the elements in the specified collection to this collection
         * (optional operation).If the specified collection is also a set, the <code>addAll</code> operation effectively modifies this set so that its value is the <i>union</i> of the two sets.
         * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress.
         * <br>
         * Specified by: <br>
         * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#addAll()">addAll</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
         * <br>
         * @param c elements to be inserted into this collection.
         * @return <code>true</code> if this collection changed as a result of the
         *         call
         * 
         * @throws UnsupportedOperationException if this collection does not
         *                                       support the <code>addAll</code> method.
         * @throws ClassCastException            if the class of an element of the
         *                                       specified collection prevents it from
         *                                       being added to this collection.
         * @throws NullPointerException          if the specified collection contains
         *                                       one or more null elements and this
         *                                       collection does not support null
         *                                       elements, or if the specified
         *                                       collection is <code>null</code>.
         * @throws IllegalArgumentException      some aspect of an element of the
         *                                       specified collection prevents it from
         *                                       being added to this collection.
         * @see #add(Object)
         */
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
            //Not supported (ne metodo values,ne entrySet,ne keySet supportano questo metodo)
        }

        public boolean retainAll(HCollection c){
            if(c==null){
                throw new NullPointerException();
            }
            HIterator it = iterator();
            boolean rtn = false;
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    rtn = true;
                }
            }
            return rtn;
        }

        public boolean removeAll(HCollection c){
            if(c==null){
                throw new NullPointerException();
            }
            HIterator it = iterator();
            boolean rtn = false;
            while (it.hasNext()) {
                if (c.contains(it.next())) {
                    it.remove();
                    rtn = true;
                }
            }
            return rtn;
        }

        public void clear()
        {
            hash.clear();
        }

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
            HCollection c = (SetAdapter) o;
            HIterator it = iterator();
            HIterator it2 = c.iterator();
            while(it.hasNext()){
                Object o1 = it.next();
                Object o2 = it2.next();
                if((o1 == null)||(o2==null)||(o1.equals(o2)==false)){
                    return false;
                }
            }
            return true;
        }

        public int hashCode(){
            int hash = 0;
            HIterator it = iterator();
            while(it.hasNext()){
                hash += it.next().hashCode();
            }
            return 0; 
        }


        //TODO:introduzione Iterator e copiare i commenti dei metodi
        private class IteratorAdapter implements HIterator{

            private boolean status = true;
            private int type;
            Enumeration en;
    
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
                status = true;
                if(!hasNext())
                {
                    throw new NoSuchElementException();
                }
                Object next = en.nextElement();
                if(type == 2 ){
                    Object nextValue = hash.get(next);
                    return new EntryAdapter(next, nextValue);
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
             * @throws UnsupportedOperationException if the <code>remove</code>
             *                                       operation is not supported by this
             *                                       Iterator.
             * 
             * @throws IllegalStateException         if the <code>next</code> method has not
             *                                       yet been called, or the
             *                                       <code>remove</code> method has already
             *                                       been called after the last call to the
             *                                       <code>next</code> method.
             */
            public void remove(){
                if(status != false){
                    status = false;
                    if(type == 1){
                        Object o = en.nextElement();
                        while(hash.keys().hasMoreElements()){
                            Object key = hash.keys().nextElement();
                                if(o == get(key)){
                                    hash.remove(key);
                                }
                        }
                        
                    }
                    else{
                        hash.remove(en.nextElement());
                    }
                }
                else{
                    throw new IllegalStateException();
                }
            }
    
    
        }
    }
    
}

