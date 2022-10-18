package connectfour;

import java.util.ArrayList;

public class Board {
    private ArrayList<Row> boardAsListOfRows;
    private final int numOfRows = 6;  

    // create an empty board
    public Board () {
        boardAsListOfRows = new ArrayList<Row>();
        for (int i = 0; i < numOfRows; i++) {
            boardAsListOfRows.add(new Row());
        }
    }

    private String getValue (int indexOfElement) {
        return boardAsListOfRows.get(indexOfElement).toString();
    }
    
    public String toString() {
        String str = "";
        for (int i = 0; i < numOfRows; i++) {
            str += getValue(i);
            str += "\n";
        }

        return str;
    }

/* this is a do-nothing method that was put here only so 
you could have an example of junit testing.  Once you have other
methods in the Board class and other tests you should delete
this method and this comment */
    public int returnSomething() {
        return 1;
    }

}