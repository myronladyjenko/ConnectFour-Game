package connectfour;

import java.util.ArrayList;

public class Row {
    private ArrayList<Cell> rowOfCells;
    private final int numOfColumns = 7;

    /*
    public Row (int rowNumber, int columnNumber, char valueToSet) {
        createRow();
    } */

    public Row () {
        rowOfCells = new ArrayList<Cell>();
        createEmptyRow();
    }

    private void createEmptyRow () {
        for (int i = 0; i < numOfColumns; i++) {
            rowOfCells.add(new Cell('0'));
        }
    }

    private String getValue (int indexOfElement) {
        return rowOfCells.get(indexOfElement).toString();
    }
    
    public String toString () {
        String str = "";
        for (int i = 0; i < numOfColumns; i++) {
            str += getValue(i);
        }

        return str;
    }
}