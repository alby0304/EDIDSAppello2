package myAdapter;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class MapAdapter implements HMap{

    private Hashtable hash = new Hashtable();

    public int size(){
        return hash.size();
    }

    public boolean isEmpty(){
        return hash.isEmpty();
    }

    public boolean containsKey(Object key){
        excKey(key);
        return hash.containsKey(key);
    }
    public boolean containsValue(Object value){
        excValue(value);
        return hash.containsValue(value);
    }

    public Object get(Object key){
        excKey(key);
        return hash.get(key);
    }

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

