package connectfour;

public class ConnectFour {
    private Board board;
    private TextUI connectFourUI;
    private Player player;
    private final int menuOption = 0;
    private final int boardPositionOption = 1;

    public ConnectFour() {
        connectFourUI = new TextUI();
        player = new Player();
    }

    public void playGame() {
        int userInputInteger = -1;
        char userInputCharacter = '\0';
        StringBuilder messageAboutWinner = new StringBuilder("");

        displayStartGameMenu();
        userInputInteger = connectFourUI.getUserInputForIntegers("Please, choose a correct option (1-3): ", menuOption);
        connectFourUI.printString("\n");

        if (!(userInputInteger == 3)) {
            if (userInputInteger == 1) {
                board = new Board();
            } else if (userInputInteger == 2) {
                // load game from a file
                connectFourUI.printString("Got Here\n");
            }

            do {
                connectFourUI.printString(board.toString() + "\n");
                connectFourUI.printString(board.constructRowOfAllowedMoves());
                connectFourUI.printString("\nTurn - " + player.getTurn() + "\n");
                userInputInteger = connectFourUI.getUserInputForIntegers("Please choose a position between 1 and 7: ", 
                                                                         boardPositionOption);
                connectFourUI.printString("\n");
            
                if (board.updateBoard(userInputInteger, player.getTurn())) {
                    player.updateTurn(player.getTurn());
                }
            } while (!board.checkBoardWinner(messageAboutWinner));
            connectFourUI.printString(messageAboutWinner + "\n");
            displayFinalResultsForGame();

            userInputCharacter = connectFourUI.getUserInputForCharacters("Please enter y for 'yes' and n for 'n': ");
            if (userInputCharacter == 'y') {
                // save to file
                connectFourUI.printString("Were are here\n");
            }
        }
        connectFourUI.closeScanner();
    }

    private void displayStartGameMenu() {
        connectFourUI.printString("\n\t\tWELCOME TO THE ConnectFour GAME!\n\n");

        connectFourUI.printString("To play a Connect Four game please choose one of the options below:\n");
        connectFourUI.printString("1. Start a new game\n");
        connectFourUI.printString("2. Load a game from a file\n");
        connectFourUI.printString("3. Exit\n");
    }

    private void displayFinalResultsForGame() {
        connectFourUI.printString("\nFinal board:\n");
        connectFourUI.printString(board.toString() + "\n\n");
        connectFourUI.printString("Would you like the save the board to a file?\n");
    }
}