package connectfour;

import java.util.ArrayList;

public class Board {
    private ArrayList<BoardCell> cellBoard;
    private ValidatePlayerMove correctMove;
    private boolean successfullUpdate;
    private boolean isWinner;
    private int winPosition;

    private final int numOfRows = 6;  
    private final int numOfColumns = 7;

    /**
     * This is an empty constructor and it is used to create an empty board
     */
    public Board() {
        correctMove = new ValidatePlayerMove();
        cellBoard = new ArrayList<BoardCell>();

        createEmptyBoard();
        setBoardUpdateValue(false);
        setWinnerValue(false);
        setWinPosition(-1);
    }

    private void createEmptyBoard() {
        for (int i = 0; i < numOfRows * numOfColumns; i++) {
            cellBoard.add(new BoardCell('_'));
        }
    }

    /**
     * This method updates the board in the strBoardBuilder object
     * @param inputColumn position on the board inputed by the user
     * @param currTurn current player turn ('X' or 'O')
     * @return true if board got updated successfully and false otherwise
     */
    public boolean updateBoard(int inputColumn, char currTurn) {
        setBoardUpdateValue(false);
        int availablePosition = findAvailablePosition(inputColumn);

        if (availablePosition != -1 && correctMove.checkBoardMove(availablePosition, cellBoard)) {
            cellBoard.add(availablePosition + 1, new BoardCell((char)currTurn));
            cellBoard.remove(availablePosition);
            setBoardUpdateValue(true);
        }
        return getBoardUpdateValue();
    }

    private int findAvailablePosition(int inputColumn) {
        int emptyPositionNumber = -1;
        if (correctMove.validateColumnInput(inputColumn)) {
            int currentCellPosition = inputColumn - 1;
            do {
                if (cellBoard.get(currentCellPosition).isCellEmpty()) {
                   emptyPositionNumber = currentCellPosition;
                    break;
                }
                currentCellPosition += numOfColumns;
            } while (currentCellPosition < numOfColumns * numOfRows);
        
            if (emptyPositionNumber == -1) {
                System.out.println("The column is filled. Please choose a different column");
            }
        }
        return emptyPositionNumber;
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

    private void setMessageForWinOrTie(String messageToPrint, StringBuilder passedStringToHoldMessage) {
        passedStringToHoldMessage.append(messageToPrint);
    }

    private void setWinnerValue(boolean valueToUpdateTo) {
        isWinner = valueToUpdateTo;
    }

    private boolean getWinnerValue() {
        return isWinner;
    }

    private void setBoardUpdateValue(boolean valueToUpdateTo) {
        successfullUpdate = valueToUpdateTo;
    }

    private boolean getBoardUpdateValue() {
        return successfullUpdate;
    }

    private void setWinPosition(int foundWinPosition) {
        winPosition = foundWinPosition;
    }

    private int getWinPosition() {
        return this.winPosition;
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
                row += "|  " + cellBoard.get(j) + "  ";
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

/* this is a do-nothing method that was put here only so 
you could have an example of junit testing.  Once you have other
methods in the Board class and other tests you should delete
this method and this comment */
    public int returnSomething() {
        return 1;
    }

}