package connectfour;

/**
 * This class defines a user-defined exception 'ThrowExceptionWrongBoardFormat'. 
 * This exception occures when the board read from the file is corrupted (in in-proper format). 
 * The passed message gets to the superclass where gets set and is accessible afterwards.
 * 
 * @author Myron Ladyjenko
 */
public class ThrowExceptionWrongBoardFormat extends Exception {
    public ThrowExceptionWrongBoardFormat(String exceptionMessage) {
            super(exceptionMessage);
    }
}
