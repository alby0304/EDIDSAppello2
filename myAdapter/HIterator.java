package myAdapter;

/**
 * An iterator over a collection. Iterator takes the place of Enumeration in
 * the Java collections framework. Iterators differ from enumerations in two
 * ways:
 * <ul>
 * <li>Iterators allow the caller to remove elements from the underlying
 * collection during the iteration with well-defined semantics.</li>
 * <li>Method names have been improved.</li>
 * </ul>
 * <br>
 * This class is a copy of the <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Iterator.html">Iterator</a> interface of <a href="https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Java Standard Edition 1.4.2</a>.
 * 
 * @see <a href=
 *      "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Iterator.html">Iterator</a>
 * @see <a href=
 *      "https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html">Java
 *      Standard Edition 1.4.2</a>
 */
public interface HIterator {
    /**
     * Returns <code>true</code> if the iteration has more elements. (In other
     * words, returns <code>true</code> if <code>next</code> would return an element
     * rather than throwing an exception.)
     *
     * @return <code>true</code> if the iterator has more elements.
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws NoSuchElementException iteration has no more elements.
     */
    Object next();

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
    void remove();
}

