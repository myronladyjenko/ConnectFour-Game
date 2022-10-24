package connectfour;

import java.util.ArrayList;

/**
 * This class represents a board for the Connect Four game. The board is build from the BoardCells. 
 * The purpose of this class is construct and manipulate the board based on the user input. This
 * class provides a string representation of the board, provides a way to detect whether certain
 * move on the board is allowed and the conditions for the win
 * 
 * @author Myron Ladyjenko
 */
public class Board {
    private ArrayList<BoardCell> cellBoard;
    private boolean isWinner;
    private int winPosition;
    private int availablePositionNumber;
    private char playerToGoNext;

    private final int numOfRows = 6;  
    private final int numOfColumns = 7;

    /**
     * This is an empty constructor. It is to create an empty Board
     * This constructor also initializes some private instance variables
     */
    public Board() {
        cellBoard = new ArrayList<BoardCell>();

        createEmptyBoard();
        setWinnerValue(false);
        setWinPosition(-1);
        setAvailablePosNumber(-1);
        setPlayerToGoNext('-');
    }

    /**
     * This is an overloaded construcotr. This constructor is used to create a board from a 
     * stringRepresentation. This constructor also initializes some private instance variables.
     * 
     * @param stringBoard this a stringRepresentation of the board
     */
    public Board(String stringBoard) {
        cellBoard = new ArrayList<BoardCell>();

        setPlayerToGoNext('-');
        createBoardFromString(stringBoard);
        setWinnerValue(false);
        setWinPosition(-1);
        setAvailablePosNumber(-1);
    }

    private void createEmptyBoard() {
        for (int i = 0; i < numOfRows * numOfColumns; i++) {
            cellBoard.add(new BoardCell('_'));
        }
    }

    private void createBoardFromString(String stringBoard) {
        String sBoard = stripString(stringBoard);
        for (int i = numOfRows - 1; i >= 0; i--) {
            for (int j = i * numOfColumns; j < ((i + 1) * numOfColumns); j++) {
                if (sBoard.charAt(j) == '1') {
                    cellBoard.add(new BoardCell('X'));
                } else if (sBoard.charAt(j) == '2') {
                    cellBoard.add(new BoardCell('O'));
                } else {
                    cellBoard.add(new BoardCell('_'));
                }
            }
        }
        determineNextPlayerToGo(sBoard);
    }

    private void determineNextPlayerToGo(String sBoard) {
        int countOnes = 0;
        int countTwos = 0;
        for (int i = 0; i < numOfColumns * numOfRows; i++) {
            if (sBoard.charAt(i) == '1') {
                countOnes++;
            } else if (sBoard.charAt(i) == '2') {
                countTwos++;
            }
        }

        if (countOnes >= countTwos) {
            setPlayerToGoNext('O');
        } else {
            setPlayerToGoNext('X');
        }
    }

    private String stripString(String stringBoard) {
        stringBoard = stringBoard.replaceAll(",", "");
        stringBoard = stringBoard.replaceAll("\n", "");
        stringBoard = stringBoard.replaceAll(" ", "");

        return stringBoard;
    }

    /**
     * This method checks if the board read from the file is valid. If not throws two exceptions:
     * first one if the string read from the file can't be transformed into a board
     * second one if the game has already finished. 
     * 
     * @param strBoard The board read from the file
     * @throws ThrowExceptionWrongBoardFormat this exception is thrown when Board doesn't follow the proper format
     * @throws ThrowExceptionTheGameHasEnded this exception is thrown when the current state of the game has a winner
     */
    public void validateBoardFromFile(String strBoard) 
                                      throws ThrowExceptionWrongBoardFormat, ThrowExceptionTheGameHasEnded {

        String sBoard = stripString(strBoard);
        if (sBoard.length() != numOfColumns * numOfRows) {
            throw new ThrowExceptionWrongBoardFormat("Length of the board read from"
                                                     + " file doesn't match the expected one. Please restart\n");
        }

        if (checkForUnexpectedSymbols(sBoard)) {
            throw new ThrowExceptionWrongBoardFormat("The file contains unexpected symbols. Please restart\n");
        }

        StringBuilder playerWithWrongNumOfMoves = new StringBuilder("");
        if (checkIncorrectNumberOfMoves(sBoard, playerWithWrongNumOfMoves)) {
            throw new ThrowExceptionWrongBoardFormat("Player: " + playerWithWrongNumOfMoves.toString()
                                                     + " did 2 or more moves then the other player. Please restart\n");
        }

        // check if the game has been already won
        StringBuilder message = new StringBuilder("");
        cellBoard = new ArrayList<BoardCell>();
        createBoardFromString(strBoard);
        if (checkBoardWinner(message)) {
            throw new ThrowExceptionTheGameHasEnded("The game on this board has finished. "
                                                    + message + ". Please restart\n");
        }

        if (checkIfBoardContainsFloatingCell(sBoard)) {
            throw new ThrowExceptionWrongBoardFormat("Board contains floating cells, " 
                                                    + "which is not allowed in ConnectFour. Please restart\n");
        }
    }

    private boolean checkForUnexpectedSymbols(String sBoard) {
        for (int i = 0; i < numOfColumns * numOfRows; i++) {
            if (sBoard.charAt(i) != '0' 
                && sBoard.charAt(i) != '1'
                && sBoard.charAt(i) != '2') {
                return true;
            }
        }
        return false;
    }

    private boolean checkIncorrectNumberOfMoves(String sBoard, StringBuilder playerWhoExceedsNumTurns) {
        int countOnes = 0;
        int countTwos = 0;

        for (int i = 0; i < numOfColumns * numOfRows; i++) {
            if (sBoard.charAt(i) == '1') {
                countOnes++;
            }
            if (sBoard.charAt(i) == '2') {
                countTwos++;
            }
        }

        if (Math.abs(countOnes - countTwos) >= 2) {
            if (countOnes >= countTwos) {
                playerWhoExceedsNumTurns.append("X");
            } else {
                playerWhoExceedsNumTurns.append("O");
            }
            return true;
        }
        return false;
    }

    private boolean checkIfBoardContainsFloatingCell(String sBoard) {
        String column = "";
        for (int i = 0; i < numOfColumns; i++) {
            for (int j = numOfRows; j > 0; j--) {
                int startPos = (numOfColumns * j) - 1 - i;
                column += sBoard.charAt(startPos);
            }

            if (!checkColumn(column)) {
                return true;
            }
            column = "";
        }
        return false;
    }

    private boolean checkColumn(String column) {
        char prevChar = column.charAt(0);
        for (int i = 1; i < column.length(); i++) {
            if (prevChar == '0' && column.charAt(i) != '0') {
                return false;
            }
            prevChar = column.charAt(i);
        }
        return true;
    }

    /**
     * This function updates the board with the current turn
     * 
     * @param currTurn The current player's turn.
     */
    public void updateBoard(char currTurn) {
        cellBoard.set(getAvailablePosNumber(), new BoardCell((char)currTurn));
    }

    /**
     * The function validates the user input column and throws an exception if the input is out of
     * range or the column is full
     * 
     * @param userInputColumn the column number that the user inputted
     * @throws ThrowExceptionWrongMoveOnBoard this exception is thrown the move suggested by the user is invalid
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

    /**
     * The function checks for a winner by checking for a horizontal, vertical, forward diagonal, and
     * backward diagonal win condition. If there is no winner, it checks for a tie
     * 
     * @param stringToHoldMessage This is the string that will hold the message to be displayed to the
     * user by the TextUI class.
     * @return The method is returning a boolean value.
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

            upSlopeDiagonalCheck();
            if (getWinnerValue()) {
                setMessageForWinOrTie("\nWinner is " + cellBoard.get(getWinPosition()).toString(), stringToHoldMessage);
                break;
            }

            downSlopeDiagonalCheck();
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

    private void upSlopeDiagonalCheck() {
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

    private void downSlopeDiagonalCheck() {
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
     * It returns a string that contains a row of allowed moves (Columns 1 - 7)
     * 
     * @return A string of the allowed moves for the game board.
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
     * This function returns a string representation of the board in a format that can be written to a
     * file
     * 
     * @return A string representation of the board.
     */
    String getFIleFormatStringRepresantionOfBoard() {
        String stringBoardForFile = "";
        for (int i = numOfRows - 1; i >= 0; i--) {
            for (int j = i * numOfColumns; j < ((i + 1) * numOfColumns); j++) {
                if (cellBoard.get(j).toString().equals("X")) {
                    stringBoardForFile += "1";
                } else if (cellBoard.get(j).toString().equals("O")) {
                    stringBoardForFile += "2";
                } else {
                    stringBoardForFile += "0";
                }

                if ((j + 1) % numOfColumns != 0) {
                    stringBoardForFile += ",";
                }
            }
            if (i != 0) {
                stringBoardForFile += "\n";
            }
        }
        return stringBoardForFile;
    }

    private String getBoardValue(int indexOfElement) {
        return cellBoard.get(indexOfElement).toString();
    }

    private void setMessageForWinOrTie(String messageToPrint, StringBuilder passedStringToHoldMessage) {
        passedStringToHoldMessage.append(messageToPrint);
    }

    void setWinnerValue(boolean valueToUpdateTo) {
        isWinner = valueToUpdateTo;
    }

    boolean getWinnerValue() {
        return isWinner;
    }

    void setWinPosition(int foundWinPosition) {
        winPosition = foundWinPosition;
    }

    int getWinPosition() {
        return this.winPosition;
    }

    void setPlayerToGoNext(char nextPlayer) {
        playerToGoNext = nextPlayer;
    }

    /**
     * This function returns the character that represents the player whose turn it is to go next.
     * This is used to properly set the player in Player class
     * 
     * @return The player who is to go next.
     */
    char getPlayerTurnToGoNext() {
        return playerToGoNext;
    }

    void setAvailablePosNumber(int posNumber) {
        availablePositionNumber = posNumber;
    }

    int getAvailablePosNumber() {
        return availablePositionNumber;
    }

    /**
     * This function returns a string representation of the board
     * 
     * @return A string representation of the board.
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
}