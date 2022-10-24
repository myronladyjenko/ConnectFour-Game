package connectfour;

/**
 * The ConnectFour class represents the game itself. This class controls the game.
 * The purpose of this class is by combining instances of other classes, facilitate the game flow
 * for the Connect Four game: the ConnectFour class is responsible for managing the turns, 
 * calling Board methods to update the state, asking the Board whether a win has been detected 
 * and telling the TextUI what to print.
 * 
 * @author Myron Ladyjenko
 */
public class ConnectFour {
    private Board board;
    private Player player;
    private FileHandling boardFileHandle;
    private TextUI connectFourUI;
    private boolean autoSave;
    private boolean skipMenuOption;
    private boolean improperLoading;
    private boolean fileProperlySaved;

    private final int checkCharacter = 0;
    private final int checkInteger = 1;
    private final int boardPosition = 2;
    private final int fileNameLoad = 3;
    private final int fileNameSave = 4;

    /**
     *  This is the constructor for the ConnectFour class. It is setting the default values for the
     *  private member variables.
     */
    public ConnectFour() {
        setAutoSave(false);
        setSkipMenuOption(false);
        setImproperLoading(false);
        setFileProperlySaved(false);
    }

    /**
     * This method is responsible for the main game loop, which is responsible for the game logic, and
     * the game flow. To display anything to the user, the print method from the TextUI class is called.
     * The instance of Board class (board) is used to indirectly act on board.
     */
    public void playGame() {
        connectFourUI.printString("\n\tWELCOME TO THE ConnectFour GAME!\n");

        while (getSkipMenuOption() || chooseTheMenuOptionAndInitialize() != 3) {
            StringBuilder messageAboutWinner = new StringBuilder("");
            connectFourUI.printString(board.toString() + "\n" + board.constructRowOfAllowedMoves());
            connectFourUI.printString("\nTurn - " + player.getTurn() + "\n");
            connectFourUI.getUserInput("Please choose a position between 1 and 7: ", getConstBoardPosition());
            
            board.updateBoard(player.getTurn());
            player.updateTurn(player.getTurn());

            if (board.checkBoardWinner(messageAboutWinner)) {
                connectFourUI.printString(messageAboutWinner + "\n");
                displayFinalResultsForGame();

                if (promptUser("Would you like to save the board?\n", "Please enter 'y' or 'n': ") == 'y') {
                    handleStepsToSaveToFile();
                } else {
                    connectFourUI.printString("The Board is not Saved\n\n");
                }
                setSkipMenuOption(false);
                continue;
            } else {
                connectFourUI.printString("\nCurrent board state: \n");
                connectFourUI.printString(board.toString() + "\n\n");
            }

            saveFileManuallyUserPrompts();
            if (getFileProperlySaved()) {
                if (promptUser("Would you like to continue?\n", "Please enter 'y' or 'n': ") == 'y') {
                    setSkipMenuOption(true);
                } else {
                    setSkipMenuOption(false);
                }
            } else {
                setSkipMenuOption(true);
            }
        }
        connectFourUI.closeScanner();
    }

    private int chooseTheMenuOptionAndInitialize() {
        displayStartGameMenu();

        do {
            if (getImproperLoading()) {
                displayStartGameMenu();
                setImproperLoading(false);
            }
            connectFourUI.getUserInput("Please, choose a correct option (1-3): ", getConstInteger());
            connectFourUI.printString("\n");
            if (connectFourUI.getIntegerInput() == 1) {
                player = new Player();
                board = new Board();
                break;
            } else if (connectFourUI.getIntegerInput() == 2) {
                hadleStepsToLoadFromFile();
                if (getImproperLoading()) {
                    continue;
                }
                break;
            } else if (connectFourUI.getIntegerInput() == 3) {
                break;
            }
        } while (true);
    
        promptForAutoSaveToASpecificFile();
        return connectFourUI.getIntegerInput();
    }

    private void promptForAutoSaveToASpecificFile() {
        if (connectFourUI.getIntegerInput() != 3) {
            if (promptUser("Would you like to turn on auto saving to a specified file?\n", 
                           "Please enter y for 'yes' and n for 'no': ") == 'y') {
                setAutoSave(true);
                connectFourUI.getUserInput("Please enter a name of the file to save to: ", getConstFileNameSave());
            } else {
                setAutoSave(false);
            }
        }
    }

    private void hadleStepsToLoadFromFile() {
        boardFileHandle = new FileHandling();
        connectFourUI.getUserInput("Please enter a name of the file to load from: ", getConstFileNameLoad());
        if (boardFileHandle.getStatusOfLoadOrSaveFromFile()) {
            try {
                Board testBoard = new Board();
                testBoard.validateBoardFromFile(boardFileHandle.toString());
                board = new Board(boardFileHandle.toString());
                player = new Player(board.getPlayerTurnToGoNext());   
            } catch (ThrowExceptionWrongBoardFormat wrongFormatEx) {
                setImproperLoading(true);
                connectFourUI.printString("Couldn't load this file: " + wrongFormatEx.getMessage() + "\n");
            } catch (ThrowExceptionTheGameHasEnded gameEndedEx) {
                setImproperLoading(true);
                connectFourUI.printString(gameEndedEx.getMessage());
            }
        }
    }

    private void handleStepsToSaveToFile() {
        boardFileHandle = new FileHandling();

        if (!getAutoSaveValue()) {
            connectFourUI.getUserInput("Please enter a name of the file to save to: ", getConstFileNameSave());
        } else {
            try {
                boardFileHandle.saveToFile(connectFourUI.getFileNameInput(), 
                                           board.getFIleFormatStringRepresantionOfBoard());
            } catch (ThrowExceptionFileActionHasFailed wrongFileEx) {
                connectFourUI.printString(wrongFileEx.getMessage());
            }
        }

        if (boardFileHandle.getStatusOfLoadOrSaveFromFile()) {
            setFileProperlySaved(true);
            connectFourUI.printString("Board was successfully saved\n");
        }
    }

    private void saveFileManuallyUserPrompts() {
        if (!getAutoSaveValue()) {
            if (promptUser("Would you like to save the board?\n", "Please enter 'y' or 'n': ") == 'y') {
                handleStepsToSaveToFile();
            } else {
                setFileProperlySaved(false);
                connectFourUI.printString("The Board is not saved\n\n");
            }
        } else {
            handleStepsToSaveToFile();
        }
        connectFourUI.printString("\n");
    }

    private char promptUser(String questionToAsk, String inputToAskFor) {
        connectFourUI.printString(questionToAsk);            
        connectFourUI.getUserInput(inputToAskFor, getConstCharacter());
        return connectFourUI.getCharacterInput();
    }

    /**
     * This method checks if the user input is valid for the current situation (type of the input)
     * 
     * @param typeToCheckFor This is used to detect which type of input we are checking for
     * @param userInput The user input that is to be validated.
     * 
     * @throws ThrowExceptionForInvalidInput occurs when the inputed values doesn't meet the required criteria
     * @throws ThrowExceptionWrongMoveOnBoard occurs when the move on board is invalid due (i.e. column full)
     * @throws ThrowExceptionFileActionHasFailed occurs when the operation to read/write from/to file was unsuccesfull 
     */
    public void validateMove(int typeToCheckFor, String userInput) 
                throws ThrowExceptionForInvalidInput, ThrowExceptionWrongMoveOnBoard, ThrowExceptionFileActionHasFailed{
        if (typeToCheckFor == getConstCharacter()) {
            validateCharInput(userInput);
        } else if (typeToCheckFor == getConstInteger()) {
            checkRangeForMenuOptions(userInput);
        } else if (typeToCheckFor == getConstBoardPosition()) {
            int userIntegerInput = 0;
            try {
                userIntegerInput = Integer.parseInt(userInput);
            } catch (NumberFormatException ex) {
                throw new ThrowExceptionForInvalidInput("The input wasn't converted to an integer.");
            }
            checkMoveOnBoard(userIntegerInput);
        } else if (typeToCheckFor == getConstFileNameLoad()) {
            tryLoadingFromFile(userInput);
        } else if (typeToCheckFor == getConstFileNameSave()) {
            trySavingToFile(userInput);
        }
    }

    private void checkRangeForMenuOptions(String userChoice) throws ThrowExceptionForInvalidInput {
        int userIntegerInput = 0;
        try {
            userIntegerInput = Integer.parseInt(userChoice);
        } catch (NumberFormatException ex) {
            throw new ThrowExceptionForInvalidInput("The input wasn't able to be converted to an integer.");
        }
        
        if (userIntegerInput < 1 || userIntegerInput > 3) {
            throw new ThrowExceptionForInvalidInput("The input: " + userChoice + " is out of range for options 1-3.");
        }
    }

    private void validateCharInput(String userChoice) throws ThrowExceptionForInvalidInput {
        if (userChoice.length() != 1) {
            throw new ThrowExceptionForInvalidInput("The input value is: " + userChoice
                                                    + ", which not the right length: " + userChoice.length());
        } else {
            if (userChoice.charAt(0) != 'y' && userChoice.charAt(0) != 'n') {
                throw new ThrowExceptionForInvalidInput("The input:" + userChoice
                                                        + " is not 'y' or 'n'. Please enter correct format");
            }
        }
    }

    private void checkMoveOnBoard(int userChoice) throws ThrowExceptionWrongMoveOnBoard {
        board.validateMoveOnBoard(userChoice);
    }

    private boolean tryLoadingFromFile(String userString) throws ThrowExceptionFileActionHasFailed {
        boardFileHandle.loadFile(userString);
        return true;
    }

    private boolean trySavingToFile(String userString) throws ThrowExceptionFileActionHasFailed {
        boardFileHandle = new FileHandling();
        boardFileHandle.saveToFile(userString, board.getFIleFormatStringRepresantionOfBoard());
        return true;
    }

    private void displayStartGameMenu() {
        connectFourUI.printString("\nChoose one of the options below to proceed:\n");
        connectFourUI.printString("1) Start a new game\n");
        connectFourUI.printString("2) Load a game from a file\n");
        connectFourUI.printString("3) Exit\n");
    }

    private void displayFinalResultsForGame() {
        connectFourUI.printString("\nFinal board:\n");
        connectFourUI.printString(board.toString() + "\n\n");
    }

    /**
     * This function sets the private member variable connectForUI to the textUI (of type TextUI) passed in
     * 
     * @param textUI The TextUI object that will be used to display the game to the user.
     */
    public void setTextUI(TextUI textUI) {
        connectFourUI = textUI;
    }

    /**
     * This function returns the checkCharacter of the current object.
     * @return The checkCharacter variable is being returned.
     */
    public int getConstCharacter() {
        return checkCharacter;
    }

    /**
     * This function returns the value of the checkInteger variable.
     * @return The value of the checkInteger variable.
     */
    public int getConstInteger() {
        return checkInteger;
    }

    /**
     * This function returns the board position of the piece.
     * @return The board position of the piece.
     */
    public int getConstBoardPosition() {
        return boardPosition;
    }

    /**
     * This function returns the value of the constant fileNameLoad.
     * @return The fileNameLoad variable is being returned.
     */
    public int getConstFileNameLoad() {
        return fileNameLoad;
    }

    /**
     * This function returns the value of the constant fileNameSave
     * @return The fileNameSave variable is being returned.
     */
    public int getConstFileNameSave() {
        return fileNameSave;
    }

    private void setAutoSave(boolean boolValue) {
        autoSave = boolValue;
    }

    private boolean getAutoSaveValue() {
        return autoSave;
    }

    private void setSkipMenuOption(boolean boolValue) {
        skipMenuOption = boolValue;
    }

    private boolean getSkipMenuOption() {
        return skipMenuOption;
    }

    private void setImproperLoading(boolean boolValue) {
        improperLoading = boolValue;
    }

    private boolean getImproperLoading() {
        return improperLoading;
    }

    private void setFileProperlySaved(boolean boolValue) {
        fileProperlySaved = boolValue;
    }

    private boolean getFileProperlySaved() {
        return fileProperlySaved;
    }

    /**
     * The function returns a string that contains the current state of the game
     * 
     * @return The current state of the game is being returned.
     */
    public String toString() {
        return "Current state of the game is:\n" + board.toString();
    }
}