package connectfour;

public class Cell {
    private char cellValue; 

    public Cell (char valueToSetCellTo) {
        setCellValue(valueToSetCellTo);
    }

    private void setCellValue (char valueToSetCellTo) {
        this.cellValue = valueToSetCellTo;
    }

    private char getCellValue () {
        return this.cellValue;
    }

    public String toString () {
        return Character.toString(getCellValue());
    }
}
