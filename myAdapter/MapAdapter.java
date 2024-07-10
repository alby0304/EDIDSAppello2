package myAdapter;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class MapAdapter implements HMap{

    private Hashtable hash = new Hashtable();

    /**
     * Returns the number of elements in this collection. If this collection
     * contains more than <code>Integer.MAX_VALUE</code> elements, returns
     * <code>Integer.MAX_VALUE</code>.
     * 
     * @return the number of elements in this collection
     */
    public int size(){
        return hash.size();
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
     * More formally, returns <code>true</code> if and only if this map contains at a mapping for a key k such that
     * <code>(key==null ? k==null : key.equals(k))</code>.
     *
     * @param key key whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains the specified key.
     * @throws ClassCastException   if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException if the key is null and this map does not not permit null keys (optional).
     */
    public boolean containsKey(Object key){
        excKey(key);
        return hash.containsKey(key);
    }

    /**
     * Returns <code>true</code> if this map maps one or more keys to the specified value.
     * More formally, returns <code>true</code> if and only if this map contains at a mapping to a value v such that
     * <code>(value==null ? v==null : value.equals(v))</code>.
     * This operation will probably require time linear in the map size for most implementations of the Map interface.
     *
     * @param value value whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains the specified key.
     * @throws ClassCastException   if the value is of an inappropriate type for this map (optional).
     * @throws NullPointerException if the value is null and this map does not not permit null value (optional).
     */
    public boolean containsValue(Object value){
        excValue(value);
        return hash.containsValue(value);
    }

    /**
     * Returns the value to which this map maps the specified key.
     * Returns <code>null</code> if the map contains no mapping for this key.
     * A return value of <code>null</code> does not <i>necessarily</i> indicate that the map contains no mapping for the key;
     * it's also possible that the map explicitly maps the key to <code>null</code>. The containsKey operation may be used to distinguish these two cases.
     *
     * <br>
     * <br>
     * More formally, if this map contains a mapping from a key <code>k</code> to a value <code>v</code> such that <code>(key==null ? k==null : key.equals(k))</code>,
     * then this method returns <code>v</code>; otherwise it returns <code>null</code>. 
     * (There can be at most one such mapping.)
     * 
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or <code>null</code> if the map contains no mapping for this key.
     * @throws ClassCastException   if the value is of an inappropriate type for this map (optional).
     * @throws NullPointerException if the value is null and this map does not not permit null value (optional).
     * @see #containsKey(Object)
     */
    public Object get(Object key){
        excKey(key);
        return hash.get(key);
    }

    /**
     * Associates the specified value with the specified key in this map (optional operation).
     * If the map previously contained a mapping for this key, the old value is replaced by the specified value.
     * (A map <code>m</code> is said to contain a mapping for a key <code>k</code> if and only if <a href=
    *   "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Map.html#containsKey">m.containsKey(k)</a> would return true.))
     * 
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key,
     *          or <code>null</code> if there was no mapping for key. 
     *          A <code>null</code> return can also indicate that the map previously associated <code>null</code> with the specified key,
     *          if the implementation supports <code>null</code> values.
     * @throws UnsupportedOperationException if the <code>put</code> operation is not supported by this map.
     * @throws ClassCastException    if the class of the specified key or value prevents it from being stored in this map.
     * @throws IllegalArgumentException  if some aspect of this key or value prevents it from being stored in this map.
     * @throws NullPointerException this map does not permit <code>null</code>s keys or values, and the specified key or value is null.
     */
    public Object put(Object key,Object value){
        excKey(key);
        excValue(value);
        if(hash.containsKey(key)){
            hash.remove(key);
        }
        return hash.put(key,value);
    }

    public Object remove(Object key){
        excKey(key);
        return hash.remove(key);
    }

    //TODO:putAll -> mi serve HSet 
    
    public void clear(){
        hash.clear();
    }

    //TODO:keySet -> mi serve HSet 
    public HSet keySet(){
        return new SetAdapter(0);
    }
    //TODO:vlaues -> mi serve HCollection 
    //TODO:entrySet -> mi serve HSet
    //TODO:equals -> mi serve Hset

    private void excKey(Object o) 
    {
        if (o == null) 
        {
            throw new NullPointerException();
        }
        if (!hash.isEmpty() && !hash.keys().nextElement().getClass().isInstance(o)) 
        {
            throw new ClassCastException();
        }
    }
    private void excValue(Object o) 
    {
        if (o == null) 
        {
            throw new NullPointerException();
        }
        if (!hash.isEmpty() && !hash.elements().nextElement().getClass().isInstance(o)) 
        {
            throw new ClassCastException();
        }
    }

    private class SetAdapter implements HSet{

        // 0 = KeySet
        // 1 = values
        // 2 = entrySet
        private int type; 

        private SetAdapter(int type)
        {
            this.type = type;
        }
        public int size(){
            return hash.size();
        }

        public boolean isEmpty(){
            return hash.isEmpty();
        }

        public boolean contains(Object o){
            if(type == 0){
                return hash.containsKey(o);
            }
            else if(type == 1){
                return hash.containsValue(o);
            }
            else{
                 return true; //TODO:Map.entry
            }
        }

        public HIterator iterator(){
            return new IteratorAdapter(type);
        }

        public Object[] toArray() {
            return new Object[size()];
        }

        public Object[] toArray(Object[] a) {
            if (a == null){
                throw new NullPointerException();
            }
    
            if (a.length < size()){
                a = new Object[size()];
            }
            int i = 0;
            while(hash.keys().hasMoreElements()){
                a[i] = hash.keys().nextElement();
            }
    
            if (a.length > size()){
                a[size()] = null;
            }
    
            return a;
        }

        public boolean add(Object o){
            return false;
            //Not supported (ne metodo values,ne entrySet,ne keySet supportano questo metodo)
        }

        public boolean remove(Object o)
        {
            if(type == 0){
                hash.remove(o);
            }
            else if(type == 1){
                while(hash.keys().hasMoreElements()){
                    Object key = hash.keys().nextElement();
                    if(o == get(key)){
                        remove(key);
                    }
                }
            }
            return true;//TODO:Map.Entry
        }

        public boolean containsAll(HCollection c){
            boolean change  = true;
            while(c.iterator().hasNext()){
                if(contains(c.iterator().next())==false && change == true)
                {
                    change = false;
                }
            }
            return change;
        }

        public boolean addAll(HCollection c)
        {
            return false;
            //Not supported (ne metodo values,ne entrySet,ne keySet supportano questo metodo)
        }

        private class IteratorAdapter implements HIterator{

            private boolean status = true;
            private int type;
    
            private IteratorAdapter(int type){
                this.type = type;
            }
    
            public boolean hasNext(){
                if(type == 0){
                    return hash.keys().hasMoreElements();
                }
                else if(type == 1)
                {
                    return hash.elements().hasMoreElements();
                }
    
                return true; //TODO:Map.Entry
            }
    
            public  Object next()
            {
                if(!hasNext())
                {
                    throw new NoSuchElementException();
                }
                    status = true;
                if(type == 0){
                    hash.keys().nextElement();
                }
                else if(type == 1)
                {
                    hash.elements().nextElement();
                }
    
                return true; //TODO:Map.Entry
            }
    
            public void remove(){
                if(status != false){
                    status = false;
                    if(type == 0){
                        hash.remove(hash.keys().nextElement());
                    }
                    else if(type == 1){
                        Object o = hash.elements().nextElement();
                        while(hash.keys().hasMoreElements()){
                            Object key = hash.keys().nextElement();
                                if(o == get(key)){
                                    hash.remove(key);
                                }
                            }
                        }
                    }
                    //TODO:Map.Entry
                }
            }
    
    
        }
    }

    
}

