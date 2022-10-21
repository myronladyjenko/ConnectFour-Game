package connectfour;

public class ConnectFour {
    private Board board;
    private Player player;
    private BoardFileHandling boardFileHandle;
    private TextUI connectFourUI;
    private boolean skipVariable;
    private StringBuilder strToStoreBoard;
    private boolean autoSave;
    private boolean playingTillTheEnd;

    private final int character = 0;
    private final int integer = 1;
    private final int boardPosition = 2;
    private final int fileNameLoad = 3;
    private final int fileNameSave = 4;

    public ConnectFour() {
        setSkipVariable(false);
        setAutoSave(false);
        setPlayingTillTheEnd(false);
        strToStoreBoard = new StringBuilder("");
    }

    private int chooseTheMenuOption() {
        displayStartGameMenu();

        do {
            connectFourUI.getUserInput("Please, choose a correct option (1-3): ", integer);
            connectFourUI.printString("\n");
            if (connectFourUI.getIntegerInput() == 1) {
                player = new Player();
                board = new Board();
                break;
            } else if (connectFourUI.getIntegerInput() == 2) {
                boardFileHandle = new BoardFileHandling();
                connectFourUI.getUserInput("Please enter a name of the file to load from: ", fileNameLoad);
                if (boardFileHandle.getStatusOfLoadOrSaveFromFile()) {
                    try {
                        Board testBoard = new Board();
                        testBoard.validateBoardFromFile(strToStoreBoard);
                        board = new Board(strToStoreBoard);
                        player = new Player(board.getPlayerTurnToGoNext());   
                        break;
                    } catch (ThrowExceptionWrongBoardFormat wrongFormatEx){
                        connectFourUI.printString("Couldn't load this file: " + wrongFormatEx.getMessage() + "\n");
                    }
                }
            } else if (connectFourUI.getIntegerInput() == 3) {
                break;
            }
        } while (true);
    
        promptForAutoSaveAndContinuousGame();
        return connectFourUI.getIntegerInput();
    }

    private void promptForAutoSaveAndContinuousGame() {
        if (connectFourUI.getIntegerInput() != 3) {
            connectFourUI.printString("Would you like to turn on auto saving?\n");
            connectFourUI.getUserInput("Please enter y for 'yes' and n for 'no': ", character);
            if (connectFourUI.getCharacterInput() == 'y') {
                setAutoSave(true);
            } else {
                setAutoSave(false);
            }
        }

        if (connectFourUI.getIntegerInput() != 3) {
            connectFourUI.printString("Would you like to play the game till the end (no prompts for continuing)?\n");
            connectFourUI.getUserInput("Please enter y for 'yes' and n for 'no': ", character);
            if (connectFourUI.getCharacterInput() == 'y') {
                setPlayingTillTheEnd(true);
                setSkipVariable(true);
            } else {
                setPlayingTillTheEnd(false);
            }
        }
    }

    public void playGame() {
        connectFourUI.printString("\n\t\tWELCOME TO THE ConnectFour GAME!\n\n");

        while (getSkipVariable() || chooseTheMenuOption() != 3) {
            StringBuilder messageAboutWinner = new StringBuilder("");
            connectFourUI.printString(board.toString() + "\n");
            connectFourUI.printString(board.constructRowOfAllowedMoves());
            connectFourUI.printString("\nTurn - " + player.getTurn() + "\n");
            connectFourUI.getUserInput("Please choose a position between 1 and 7: ", boardPosition);
            connectFourUI.printString("\n");
            
            board.updateBoard(player.getTurn());
            player.updateTurn(player.getTurn());

            if (board.checkBoardWinner(messageAboutWinner)) {
                connectFourUI.printString(messageAboutWinner + "\n");
                displayFinalResultsForGame();
                setSkipVariable(false);
                continue;
            } else {
                connectFourUI.printString("Current board state: \n");
                connectFourUI.printString(board.toString() + "\n\n");
            }

            additionalUserPrompts();
        }
        connectFourUI.closeScanner();
    }

    private void additionalUserPrompts() {
        if (!getAutoSaveValue()) {
            connectFourUI.printString("Would you like to save the board?\n");            
            connectFourUI.getUserInput("Please enter y for 'yes' and n for 'no': ", character);
            if (connectFourUI.getCharacterInput() == 'y') {
                boardFileHandle = new BoardFileHandling();
                connectFourUI.getUserInput("Please enter a name of the file to save to: ", fileNameSave);
                if (boardFileHandle.getStatusOfLoadOrSaveFromFile()) {
                    connectFourUI.printString("Board for successfully saved\n");
                }
            } else {
                connectFourUI.printString("The Board is not Saved\n\n");
            }
        } else {
            boardFileHandle = new BoardFileHandling();
            connectFourUI.getUserInput("Please enter a name of the file to save to: ", fileNameSave);
            if (boardFileHandle.getStatusOfLoadOrSaveFromFile()) {
                connectFourUI.printString("Board for successfully saved\n");
            }
        }

        if (!getPlayingTillTheEnd()) {
            connectFourUI.printString("Would you like to return to the main menu or continue playing?\n");
            connectFourUI.getUserInput("Please enter y to return and n to continue: ", character);
            if (connectFourUI.getCharacterInput() == 'n') {
                setSkipVariable(true);
            } else {
                setSkipVariable(false);
            }
        }
        connectFourUI.printString("\n");
    }

    public void validateMove(int typeToCheckFor, String userInput) 
                throws ThrowExceptionForInvalidInput, ThrowExceptionWrongMoveOnBoard, ThrowExceptionNoSuchFileExists{
        if (typeToCheckFor == character) {
            validateCharInput(userInput);
        } else if (typeToCheckFor == integer) {
            checkRangeForMenuOptions(userInput);
        } else if (typeToCheckFor == boardPosition) {
            int userIntegerInput = 0;
            try {
                userIntegerInput = Integer.parseInt(userInput);
            } catch (NumberFormatException ex) {
                throw new ThrowExceptionForInvalidInput("The input wasn't converted to an integer.");
            }
            checkMoveOnBoard(userIntegerInput);
        } else if (typeToCheckFor == fileNameLoad) {
            tryLoadingFromFile(userInput);
        } else if (typeToCheckFor == fileNameSave) {
            trySavingToFile(userInput);
        }
    }

    private boolean tryLoadingFromFile(String userString) throws ThrowExceptionNoSuchFileExists {
        boardFileHandle.loadFile(userString, strToStoreBoard);
        return true;
    }

    private boolean trySavingToFile(String userString) throws ThrowExceptionNoSuchFileExists {
        boardFileHandle.saveToFile(userString, board.getFIleFormatStringRepresantionOfBoard());
        return true;
    }

    private void checkRangeForMenuOptions(String userChoice) throws ThrowExceptionForInvalidInput {
        int userIntegerInput = 0;
        try {
            userIntegerInput = Integer.parseInt(userChoice);
        } catch (NumberFormatException ex) {
            throw new ThrowExceptionForInvalidInput("The input wasn't converted to an integer.");
        }
        
        if (userIntegerInput < 1 || userIntegerInput > 3) {
            throw new ThrowExceptionForInvalidInput("The input is out of range for 1-3: " + userChoice);
        }
    }

    private void validateCharInput(String userChoice) throws ThrowExceptionForInvalidInput {
        if (userChoice.length() != 1) {
            throw new ThrowExceptionForInvalidInput("The input value is not the right length: " + userChoice);
        } else {
            if (userChoice.charAt(0) != 'y' && userChoice.charAt(0) != 'n') {
                throw new ThrowExceptionForInvalidInput("The input is not 'y' or 'n': " + userChoice);
            }
        }
    }

    private void checkMoveOnBoard(int userChoice) throws ThrowExceptionWrongMoveOnBoard {
        board.validateMoveOnBoard(userChoice);
    }

    private void displayStartGameMenu() {
        connectFourUI.printString("Choose one of the options below to proceed:\n");
        connectFourUI.printString("1. Start a new game\n");
        connectFourUI.printString("2. Load a game from a file\n");
        connectFourUI.printString("3. Exit\n");
    }

    private void displayFinalResultsForGame() {
        connectFourUI.printString("\nFinal board:\n");
        connectFourUI.printString(board.toString() + "\n\n");
        connectFourUI.printString("Would you like the save the board to a file?\n");
    }

    public void setTextUI(TextUI textUI) {
        connectFourUI = textUI;
    }

    private void setSkipVariable(boolean boolValue) {
        skipVariable = boolValue;
    }

    private boolean getSkipVariable() {
        return skipVariable;
    }

    private void setAutoSave(boolean boolValue) {
        autoSave = boolValue;
    }

    private boolean getAutoSaveValue() {
        return autoSave;
    }

    private void setPlayingTillTheEnd(boolean boolValue) {
        playingTillTheEnd = boolValue;
    }

    private boolean getPlayingTillTheEnd() {
        return playingTillTheEnd;
    }
}