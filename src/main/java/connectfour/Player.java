package connectfour;

public class Player {
        private char playerTurn;

    public Player() {
        setFirstTurn();
    }

    public Player(char currPLayerTurn) {
        setCurrentTurn(currPLayerTurn);
    }

    /**
     * This method gets the turn of the player
     * @return next players turn: 'X' or 'O'
     */
    public char getTurn() {
        return playerTurn;
    }

    /**
     * This method sets the next players turn 'X' or 'O' into 
     * the variable playerTurn
     * @param currTurn current player turn
     */
    public void updateTurn(char currTurn) {
        if (currTurn == 'X') {
            playerTurn = 'O';
        } else {
            playerTurn = 'X';
        }
    }

    private void setFirstTurn() {
        int max = 2;
        int min = 1;
        int range = max - min + 1;

        int rand = (int)(Math.random() * range) + min;
        if (rand == 1) {
            playerTurn = 'X';
        } else {
            playerTurn = 'O';
        }
    }

    private void setCurrentTurn(char currPlayerTurn) {
        playerTurn = currPlayerTurn;
    }
    
}
