package connectfour;

/**
 * This class defines a user-defined exception 'ThrowExceptionNoSuchFileExists'. 
 * This exception occures when the file inputed by the user doesn't exist or in wrong format. 
 * The passed message gets to the superclass where gets set and is accessible afterwards.
 * 
 * @author Myron Ladyjenko
 */
public class ThrowExceptionNoSuchFileExists extends Exception {
    public ThrowExceptionNoSuchFileExists(String exceptionMessage) {
            super(exceptionMessage);
    }
}
