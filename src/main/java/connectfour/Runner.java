package connectfour;

/**
 * This is the runner class. It used to start the game.
 * 
 * @author Myron Ladyjenko
 */
public class Runner {
    
    /**
     * The main function creates a new TextUI object, a new ConnectFour object. The bidirectional connection 
     * is established by: setting the game of thevTextUI object to the ConnectFour object and 
     * settinig the TextUI of the ConnectFour object to the TextUI object. After, the game is started.
     */
    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        ConnectFour game = new ConnectFour();

        textUI.setGame(game);
        game.setTextUI(textUI);

        game.playGame();
    }
}
