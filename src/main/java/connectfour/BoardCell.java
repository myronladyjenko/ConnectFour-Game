package connectfour;

public class BoardCell {
    private char cellValue; 

    public BoardCell(char valueToSetCellTo) {
        setCellValue(valueToSetCellTo);
    }

    private void setCellValue(char valueToSetCellTo) {
        this.cellValue = valueToSetCellTo;
    }

    public char getCellValue() {
        return this.cellValue;
    }

    public boolean isCellEmpty() {
        if (getCellValue() == '_') {
            return true;
        }
        return false;
    }

    public boolean compareCells(BoardCell cellToCompare) {
        if (Character.toString(this.getCellValue()).equals(cellToCompare.toString())) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Character.toString(getCellValue());
    }
}
