package connectfour;

public class Runner {
    
    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        ConnectFour game = new ConnectFour();

        textUI.setGame(game);
        game.setTextUI(textUI);

        game.playGame();
    }
}
