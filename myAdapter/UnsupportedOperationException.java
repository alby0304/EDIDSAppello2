package myAdapter;

/**
 * Signals that a method has been invoked at an illegal or inappropriate time.
 * In other words, the Java environment or Java application is not in an
 * appropriate state for the requested operation.
 * <br>
 * Implemented in this package because not present in CLDC 1.1
 */
public class UnsupportedOperationException extends RuntimeException {

    /**
     * Constructs an IllegalStateException with no detail message.
     */
    public UnsupportedOperationException(){
    }

    /**
     * Constructs an IllegalStateException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }

}