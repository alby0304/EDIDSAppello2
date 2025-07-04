package myAdapter;

/**
 * Thrown to indicate that the requested operation is not supported.
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