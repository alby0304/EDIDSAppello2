package myAdapter;

/* 
* A collection that contains no duplicate elements.  More formally, sets
* contain no pair of elements <code>e1</code> and <code>e2</code> such that
* <code>e1.equals(e2)</code>, and at most one null element.  As implied by
* its name, this interface models the mathematical <i>set</i> abstraction.
* <br>
* <br>
*
* The <code>Set</code> interface places additional stipulations, beyond those
* inherited from the <code>Collection</code> interface, on the contracts of all
* constructors and on the contracts of the <code>add</code>, <code>equals</code> and
* <code>hashCode</code> methods.  Declarations for other inherited methods are
* also included here for convenience.  (The specifications accompanying these
* declarations have been tailored to the <code>Set</code> interface, but they do
* not contain any additional stipulations.)
*<br>
*<br>
*
*The additional stipulation on constructors is, not surprisingly,
*that all constructors must create a set that contains no duplicate elements
* (as defined above).
*<br>
*<br>
*
* Note: Great care must be exercised if mutable objects are used as set
* elements.  The behavior of a set is not specified if the value of an object
* is changed in a manner that affects equals comparisons while the object is
* an element in the set.  A special case of this prohibition is that it is
* not permissible for a set to contain itself as an element.
*<br>
*<br>
*
* Some set implementations have restrictions on the elements that
* they may contain.  For example, some implementations prohibit null elements,
* and some have restrictions on the types of their elements.  Attempting to
* add an ineligible element throws an unchecked exception, typically
* <code>NullPointerException</code> or <code>ClassCastException</code>.  Attempting
* to query the presence of an ineligible element may throw an exception,
* or it may simply return false; some implementations will exhibit the former
* behavior and some will exhibit the latter.  More generally, attempting an
* operation on an ineligible element whose completion would not result in
* the insertion of an ineligible element into the set may throw an
* exception or it may succeed, at the option of the implementation.
* Such exceptions are marked as "optional" in the specification for this
* interface. 
*<br>
*<br>
*
* This class is a copy of the <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Set.html">Set</a> interface of <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Java Standard Edition 1.4.2</a>.
* 
* @see <a href=
*      "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Set.html">Set</a>
* @see <a href=
*      "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Java
*      Standard Edition 1.4.2</a>
*/

public interface HSet extends HCollection {

  /* Returns the number of elements in this set (its cardinality). If this set contains more than <code>Integer.MAX_VALUE</code> elements, returns <code>Integer.MAX_VALUE</code>.
  * <br>
  * Specified by: <br>
  * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#size()">size</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
  * <br>
  * @return the number of elements in this set (its cardinality). 
  */
  int size();

  /* Returns <code>true</code> if this set contains no elements.
  * <br>
  * Specified by: <br>
  * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#isEmpty()">isEmpty</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
  * <br>
  * @return  <code>true</code> if this set contains no elements.
  */
  boolean isEmpty();

  /* Returns <code>true</code> if this set contains the specified element. More formally, returns <code>true</code> if and only if this set contains an element e such that <code>(o==null ? e==null : o.equals(e))</code>.
  * <br>
  * Specified by: <br>
  * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#contains()">contains</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
  * <br>
  * @param o  element whose presence in this set is to be tested.
  * @return <code>true</code> if this collection contains the specified
     *         element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this collection (optional).
     * @throws NullPointerException if the specified element is null and this
     *                              collection does not support null elements
     *                              (optional).
  */
  boolean contains();

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
    HIterator iterator();
   
    /**
     * Returns an array containing all of the elements in this set. Obeys the general contract of the Collection.toArray method.
     * <br>
     * Specified by: <br>
     * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#toArray()">toArray</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
     * <br>
     * @return an array containing all of the elements in this set
     */
    Object[] toArray();

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
     Object[] toArray(Object a[]);

     /**
     *  Adds the specified element to this set if it is not already present(optional operation).  More formally, adds the specified element,
      *  <code>o</code>, to this set if this set contains no element <code>e</code> such that <code>(o==null ? e==null :o.equals(e))</code>.  If this set already contains the specified
       * element, the call leaves this set unchanged and returns <code>false</code>.
       * In combination with the restriction on constructors, this ensures tha sets never contain duplicate elements.
       * <br>
       * <br>
      *The stipulation above does not imply that sets must accept all elements; sets may refuse to add any particular element, including
      *<code>null</code>, and throwing an exception, as described in the specification for <code>Collection.add</code>.  Individual set
      *implementations should clearly document any restrictions on the the elements that they may contain.
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
    boolean add(Object o);

    /**
     * Removes the specified element from this set if it is present (optional operation). More formally,
     * removes an element <code>e</code> such that <code>(o==null ?  e==null :
     * o.equals(e))</code>, if the set contains such an element.
     * Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call).
     * (The set will not contain the specified element once the call returns).
     *<br>
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
    boolean remove(Object o);

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
    boolean containsAll(HCollection c);

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
    boolean addAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation). In other words, removes from
     * this set all of its elements that are not contained in the specified collection.
     * If the specified collection is also a set, this operation effectively modifies this set so that its value is the <i>intersection</i> of the two sets.
     *  <br>
     * Specified by: <br>
     * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#retainAll()">retainAll</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
     * <br>
     * @param c elements to be retained in this collection.
     * @return <code>true</code> if this collection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException if the <code>retainAll</code> method
     *                                       is not supported by this Collection.
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
    boolean retainAll(HCollection c);

    /**
     * 
     * Removes all this collection's elements that are also contained in the
     * specified collection (optional operation).
     * If the specified collection is also a set,
     * this operation effectively modifies this set so that its value is the <i>asymmetric set difference</i> of the two sets.
     * <br>
     * Specified by: <br>
     * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#removeAll()">removeAll</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
     * <br>
     * @param c elements to be removed from this collection.
     * @return <code>true</code> if this collection changed as a result of the
     *         call
     * 
     * @throws UnsupportedOperationException if the <code>removeAll</code> method
     *                                       is not supported by this collection.
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
    boolean removeAll(HCollection c);

     /**
     * Removes all of the elements from this set(optional operation).
     * This set will be empty after this method returns unless it
     * throws an exception.
     *<br>
     * Specified by: <br>
     * <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html#clear()">clear</a> in interface <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html">Collection</a>
     * <br>
     * @throws UnsupportedOperationException if the <code>clear</code> method is
     *                                       not supported by this collection.
     */
    void clear();

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
    boolean equals(Object o);

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
    int hashCode();
}
