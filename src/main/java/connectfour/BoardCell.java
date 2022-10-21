package connectfour;

/**
 * This class represents one cell on the board. The purpose of this class is to control
 * each cell of the board on the lowest level. This class provides some basic functionality 
 * that can be performed on eahc cell of the board individualy.
 * 
 * @author Myron Ladyjenko
 */
public class BoardCell {
    private char cellValue; 

    /**
     * This is used to set the value of the private member cellValue. 
     * 
     * @param valueToSetCellTo This is a value the Cell would be set to
     */
    public BoardCell(char valueToSetCellTo) {
        setCellValue(valueToSetCellTo);
    }

    /**
     * Returns true if the cell is empty, false otherwise
     * 
     * @return A boolean value.
     */
    public boolean isCellEmpty() {
        if (getCellValue() == '_') {
            return true;
        }
        return false;
    }

    /**
     * This function compares the value of the cell(this cell) to the value of the cell passed in as a parameter
     * 
     * @param cellToCompare The cell to compare to the current cell.
     * @return A boolean value.
     */
    public boolean compareCells(BoardCell cellToCompare) {
        if (Character.toString(this.getCellValue()).equals(cellToCompare.toString())) {
            return true;
        }
        return false;
    }

    private void setCellValue(char valueToSetCellTo) {
        this.cellValue = valueToSetCellTo;
    }

    /**
     * This function returns the value of the cell
     * 
     * @return The value of the cell as a character.
     */
    public char getCellValue() {
        return this.cellValue;
    }

    /**
     * This function returns a string representation of the cell's value.
     * 
     * @return The String value of the cell.
     */
    public String toString() {
        return Character.toString(getCellValue());
    }
}
