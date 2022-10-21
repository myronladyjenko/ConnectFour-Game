package connectfour;

/**
 * This class defines a user-defined exception 'ThrowExceptionWrongMoveOnBoard'. 
 * This exception gets thrown when the user tries to make an invalid move on the board. 
 * The passed message gets to the superclass where gets set and is accessible afterwards.
 * 
 * @author Myron Ladyjenko
 */
public class ThrowExceptionWrongMoveOnBoard extends Exception {
    public ThrowExceptionWrongMoveOnBoard(String exceptionMessage) {
            super(exceptionMessage);
    }
}
