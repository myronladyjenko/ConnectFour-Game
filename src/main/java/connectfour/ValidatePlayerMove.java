package connectfour;

import java.util.ArrayList;

public class ValidatePlayerMove {
    private final int numberOfColumns = 7;
    private final int numberOfRows = 6;
    private boolean allowedMove;
    
    public ValidatePlayerMove() {
        setAllowedMove();
    }

    /**
     * The method checks whether the move inputed by the user is allowed on the board
     * @param inputColumn position of where to do a move on the board
     * @param boardOfCells List that represents values on stored on the board (1 to 9, 'X', or 'O')
     * @return whether the user is allowed to make a move (true) or not
     */
    public boolean checkBoardMove(int inputColumn, ArrayList<BoardCell> boardOfCells) {
        setAllowedMove();
        
        checkOutOfBounds(inputColumn);
        do {
            if (!getAllowedMove()) {
                printErrorMessage("Error - Entered Board Position is out of Bounds");
                allowedMove = false;
                break;
            }
        
            validateMove(inputColumn, boardOfCells);
            if (!getAllowedMove()) {
                printErrorMessage("Error - Illegal Move: entered position is filled");
                allowedMove = false;
            }
        } while(false);

        return getAllowedMove();
    }

    private void validateMove(int inputColumn, ArrayList<BoardCell> boardOfCells) {
        BoardCell cell = boardOfCells.get(inputColumn);

        if (!cell.isCellEmpty()) {
            allowedMove = false;
        }
    }

    public boolean validateColumnInput(int inputColumn) {
        if (inputColumn > numberOfColumns || inputColumn <= 0) {
            return false;
        }
        return true;
    }

    private void checkOutOfBounds(int inputColumn) {
        if (inputColumn > (numberOfColumns * numberOfRows - 1) || inputColumn < 0) {
            allowedMove = false;
        }
    }

    private void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    private void setAllowedMove() {
        allowedMove = true;
    }

    private boolean getAllowedMove() {
        return allowedMove;
    }

}
