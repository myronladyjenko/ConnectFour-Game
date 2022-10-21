package connectfour;

public class ThrowExceptionWrongMoveOnBoard extends Exception {
    public ThrowExceptionWrongMoveOnBoard(String exceptionMessage) {
            super(exceptionMessage);
    }
}
