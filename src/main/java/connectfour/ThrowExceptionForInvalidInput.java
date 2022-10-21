package connectfour;

public class ThrowExceptionForInvalidInput extends Exception {
    public ThrowExceptionForInvalidInput(String exceptionMessage) {
            super(exceptionMessage);
    }
}
