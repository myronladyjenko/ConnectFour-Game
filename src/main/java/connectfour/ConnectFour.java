package connectfour;

public class ConnectFour {

    private Board board;
   
    public ConnectFour() {
        board = new Board();
    }

    public void playGame() {
         System.out.println(board.toString());
    }
}