package connectfour;

/**
 * This class defines a user-defined exception 'ThrowExceptionForInvalidInput'. 
 * This exception occures when user input is in correct (non-correct characters). 
 * The passed message gets to the superclass where gets set and is accessible afterwards.
 * 
 * @author Myron Ladyjenko
 */
public class ThrowExceptionForInvalidInput extends Exception {
    public ThrowExceptionForInvalidInput(String exceptionMessage) {
            super(exceptionMessage);
    }
}
