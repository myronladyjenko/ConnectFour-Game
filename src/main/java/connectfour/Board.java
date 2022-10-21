package connectfour;

import java.util.ArrayList;

public class Board {
    private ArrayList<BoardCell> cellBoard;
    private boolean isWinner;
    private int winPosition;
    private int availablePositionNumber;

    private final int numOfRows = 6;  
    private final int numOfColumns = 7;

    /**
     * This is an empty constructor and it is used to create an empty board
     */
    public Board() {
        cellBoard = new ArrayList<BoardCell>();

        createEmptyBoard();
        setWinnerValue(false);
        setWinPosition(-1);
        setAvailablePosNumber(-1);
    }

    private void createEmptyBoard() {
        for (int i = 0; i < numOfRows * numOfColumns; i++) {
            cellBoard.add(new BoardCell('_'));
        }
    }

    /**
     * 
     * @param currTurn
     */
    public void updateBoard(char currTurn) {
        cellBoard.set(getAvailablePosNumber(), new BoardCell((char)currTurn));
    }

    /**
     * 
     * @param userInputColumn
     * @return
     * @throws ThrowExceptionWrongMoveOnBoard
     */
    public void validateMoveOnBoard(int userInputColumn) throws ThrowExceptionWrongMoveOnBoard {
        do {
            if (!validateColumnInput(userInputColumn)) {
                throw new ThrowExceptionWrongMoveOnBoard("Inputed column is out of range: " + userInputColumn);
            }

            findAvailablePosition(userInputColumn);
            if (getAvailablePosNumber() == -1) {
                throw new ThrowExceptionWrongMoveOnBoard("The column is full");
            }
        } while(false);
    }

    /**
     * 
     * @param inputColumn
     */
    private void findAvailablePosition(int inputColumn) {
        setAvailablePosNumber(-1);
        int currentCellPosition = inputColumn - 1;
        do {
            if (cellBoard.get(currentCellPosition).isCellEmpty()) {
                setAvailablePosNumber(currentCellPosition);
                break;
            }
            currentCellPosition += numOfColumns;
        } while (currentCellPosition < numOfColumns * numOfRows);
    }

    private boolean validateColumnInput(int inputColumn) {
        if (inputColumn > numOfColumns || inputColumn <= 0) {
            return false;
        }
        return true;
    }

    public void setAvailablePosNumber(int posNumber) {
        availablePositionNumber = posNumber;
    }

    public int getAvailablePosNumber() {
        return availablePositionNumber;
    }

    /**
     * This method checks whether the board contains a winning condition for either 'X' or 'O'
     * @return true if 'X' or 'O' won or false otherwise
     */
    public boolean checkBoardWinner(StringBuilder stringToHoldMessage) {
        setWinnerValue(false);
        
        do {
            horizontalCheck();
            if (getWinnerValue()) {
                setMessageForWinOrTie("\nWinner is " + cellBoard.get(getWinPosition()).toString(), stringToHoldMessage);
                break;
            }

            verticalCheck();
            if (getWinnerValue()) {
                setMessageForWinOrTie("\nWinner is " + cellBoard.get(getWinPosition()).toString(), stringToHoldMessage);
                break;
            }

            forwardDiagonalCheck();
            if (getWinnerValue()) {
                setMessageForWinOrTie("\nWinner is " + cellBoard.get(getWinPosition()).toString(), stringToHoldMessage);
                break;
            }

            backwardDiagonalCheck();
            if (getWinnerValue()) {
                setMessageForWinOrTie("\nWinner is " + cellBoard.get(getWinPosition()).toString(), stringToHoldMessage);
                break;
            }

            if (checkForTie()) {
                setMessageForWinOrTie("\nThe Game is a Tie. Good luck next time!", stringToHoldMessage);
                setWinnerValue(true);
                break;
            }
        } while(false);

        return getWinnerValue();
    }

    private boolean checkForTie() {
        for (int i = 0; i < numOfColumns * numOfRows; i++) {
            if (!(cellBoard.get(i).toString().equals("X") || cellBoard.get(i).toString().equals("O"))) {
                return false;
            }
        }
        return true;
    }

    private void horizontalCheck() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < 4; j++) {
                if (!getBoardValue(i * numOfColumns + j).equals("_")
                    && getBoardValue(i * numOfColumns + j).equals(getBoardValue(i * numOfColumns + 1 + j))
                    && getBoardValue(i * numOfColumns + 1 + j).equals(getBoardValue(i * numOfColumns + 2 + j))
                    && getBoardValue(i * numOfColumns + 2 + j).equals(getBoardValue(i * numOfColumns + 3 + j))) {
                    setWinPosition(i * numOfColumns + j);
                    setWinnerValue(true);
                    break;
                }
            }

            if (getWinnerValue()) {
                break;
            }
        }
    }

    private void verticalCheck() {
        int startPos = 0;
        for (int i = 0; i < numOfRows - 3; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                startPos = i * numOfColumns + j;
                if (!getBoardValue(startPos).equals("_")
                    && getBoardValue(startPos).equals(getBoardValue((numOfColumns * 1) + startPos))
                    && getBoardValue((numOfColumns * 1) + startPos).equals(getBoardValue((numOfColumns * 2)
                                                                                                   + startPos))
                    && getBoardValue((numOfColumns * 2) + startPos).equals(getBoardValue((numOfColumns * 3)
                                                                                                    + startPos))) {
                    setWinPosition(startPos);                                                                           
                    setWinnerValue(true);
                    break;
                }
            }

            if (getWinnerValue()) {
                break;
            }
        }
    }

    private void forwardDiagonalCheck() {
        int startPos = 0;

        for (int i = 0; i < numOfRows - 3; i++) {
            for (int j = 0; j < 4; j++) {
                startPos = i * numOfColumns + j;
                if (!getBoardValue(startPos).equals("_")
                    && getBoardValue(startPos).equals(getBoardValue(startPos + (numOfColumns * 1 + 1)))
                    && getBoardValue((numOfColumns * 1 + 1) + startPos).equals(getBoardValue((numOfColumns * 2)
                                                                                              + startPos + 2))
                    && getBoardValue((numOfColumns * 2 + 2) + startPos).equals(getBoardValue((numOfColumns * 3)
                                                                                              + startPos + 3))) {
                    setWinPosition(startPos);
                    setWinnerValue(true);
                    break;
                }
            }

            if (getWinnerValue()) {
                break;
            }
        }
    }

    private void backwardDiagonalCheck() {
        int startPos = 0;

        for (int i = 0; i < numOfRows - 3; i++) {
            for (int j = numOfColumns - 1; j >= 3; j--) {
                startPos = i * numOfColumns + j;
                if (!getBoardValue(startPos).equals("_")
                    && getBoardValue(startPos).equals(getBoardValue(startPos + (numOfColumns * 1 - 1)))
                    && getBoardValue((numOfColumns * 1 - 1) + startPos).equals(getBoardValue((numOfColumns * 2)
                                                                                                + startPos - 2))
                    && getBoardValue((numOfColumns * 2 - 2) + startPos).equals(getBoardValue((numOfColumns * 3)
                                                                                              + startPos - 3))) {
                    setWinPosition(startPos);
                    setWinnerValue(true);
                    break;
                }
            }

            if (getWinnerValue()) {
                break;
            }
        }
    }

    /**
     * This method gets a String representation of the value stored in a specific cell
     * @param indexOfElement
     * @return
     */
    private String getBoardValue(int indexOfElement) {
        return cellBoard.get(indexOfElement).toString();
    }

    /**
     * 
     * @return
     */
    public String constructRowOfAllowedMoves() {
        String rowOfAllowedMoves = " ----- ----- ----- ----- ----- ----- ----- \n";

        rowOfAllowedMoves += "|     |     |     |     |     |     |     |\n";
        for (int i = 0; i <numOfColumns; i++) {
            rowOfAllowedMoves += "|  " + (i + 1) + "  ";
        }
        rowOfAllowedMoves += "|\n";

        rowOfAllowedMoves += "|     |     |     |     |     |     |     |\n";
        rowOfAllowedMoves += " ----- ----- ----- ----- ----- ----- ----- \n";
        return rowOfAllowedMoves;
    }
    
    /**
     * 
     */
    public String toString() {
        String stringBoard = "\n";

        for (int i = numOfRows - 1; i >= 0; i--) {
            String row = "";

            row += "|     |     |     |     |     |     |     |\n";
            for (int j = i * numOfColumns; j < (i + 1) * numOfColumns; j++) {
                row += "|  " + cellBoard.get(j).toString() + "  ";
            }
            row += "|\n";
            if (i != 0) {
                row += "|     |     |     |     |     |     |     |\n";
            } else {
                row += "|_____|_____|_____|_____|_____|_____|_____|";
            }

            stringBoard += row;
        }
        return stringBoard;
    }

    private void setMessageForWinOrTie(String messageToPrint, StringBuilder passedStringToHoldMessage) {
        passedStringToHoldMessage.append(messageToPrint);
    }

    private void setWinnerValue(boolean valueToUpdateTo) {
        isWinner = valueToUpdateTo;
    }

    private boolean getWinnerValue() {
        return isWinner;
    }

    private void setWinPosition(int foundWinPosition) {
        winPosition = foundWinPosition;
    }

    private int getWinPosition() {
        return this.winPosition;
    }

/* this is a do-nothing method that was put here only so 
you could have an example of junit testing.  Once you have other
methods in the Board class and other tests you should delete
this method and this comment */
    public int returnSomething() {
        return 1;
    }

}