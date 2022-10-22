package connectfour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/* you will need to add test methods and likely change the
setup method as well.  The samples that are here are just so that
you can see how junit works.

Tests are run on build unless specifically excluded with -x test.
The test results are reported in the reports subfolder of the build directory */


public class BoardTest{
    private Board tester;

    @Before
    public void setup() {
        //set up for the test
        tester = new Board();
    }

    @Test
    public void testEmptyBoard() {
        // Arrange 
        String emptyBoard = 
       "\n|     |     |     |     |     |     |     |\n"
        +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|     |     |     |     |     |     |     |\n"
        +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
        +"|_____|_____|_____|_____|_____|_____|_____|";

        // Act
        String stringBoard = tester.toString();

        // Assert
        Assert.assertEquals(emptyBoard, stringBoard);
    }

    @Test
    public void testBoardWinnerValue() {
        // Arrange, Act
        boolean winnerValueByConstructor = tester.getWinnerValue();

        // Assert
        Assert.assertEquals(false, winnerValueByConstructor);
    }

    @Test
    public void testBoardWinPosition() {
        // Arrange, Act
        int winPositionByConstructor = tester.getWinPosition();

        // Assert
        Assert.assertEquals(-1, winPositionByConstructor);
    }

    @Test
    public void testBoardAvailableValue() {
        // Arrange, Act
        int availablePosNumberByConstructor = tester.getAvailablePosNumber();

        // Assert
        Assert.assertEquals(-1, availablePosNumberByConstructor);
    }

    @Test
    public void testBoardPlayerToGoNext() {
        // Arrange, Act
        char nextPlayerToGo = tester.getPlayerTurnToGoNext();

        // Assert
        Assert.assertEquals('-', nextPlayerToGo);
    }

    @Test
    public void testFileFormatForStringBoard() {
        // Arrange
        String stringBoard = "0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0";
        Board emptyBoard = new Board();

        // Act
        String stringBoardFileRepresantation = emptyBoard.getFIleFormatStringRepresantionOfBoard();

        // Assert
        Assert.assertEquals(stringBoard, stringBoardFileRepresantation);
    }

    @Test
    public void testBoardOverloadedConstructor() {
        // Arrange
        String stringBoard = "0,0,0,0,0,0,0\n"
                            +"0,0,0,2,0,0,0\n"
                            +"0,0,2,1,1,0,0\n"
                            +"0,0,1,1,2,0,0\n"
                            +"2,1,1,2,1,2,0\n"
                            +"2,2,2,1,1,2,0";

        Board boardOverLoaded = new Board(stringBoard);

        // Act
        String stringRepresentationOfBoard = boardOverLoaded.getFIleFormatStringRepresantionOfBoard();

        // Assert
        Assert.assertEquals(stringBoard, stringRepresentationOfBoard);
    }

    @Test
    public void testBoardToString() {
        // Arrange
        String stringBoard  =  "\n|     |     |     |     |     |     |     |\n"
                                +"|  _  |  _  |  _  |  _  |  _  |  _  |  _  |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|  _  |  _  |  _  |  O  |  _  |  _  |  _  |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|  _  |  _  |  O  |  X  |  X  |  _  |  _  |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|  _  |  _  |  X  |  X  |  O  |  _  |  _  |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|  O  |  X  |  X  |  O  |  X  |  O  |  _  |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|     |     |     |     |     |     |     |\n"
                                +"|  O  |  O  |  O  |  X  |  X  |  O  |  _  |\n"
                                +"|_____|_____|_____|_____|_____|_____|_____|";

        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,2,0,0,0\n"
                                         +"0,0,2,1,1,0,0\n"
                                         +"0,0,1,1,2,0,0\n"
                                         +"2,1,1,2,1,2,0\n"
                                         +"2,2,2,1,1,2,0");

        // Act
        String stringRepresentationOfBoard = boardOverLoaded.toString();

        // Assert
        Assert.assertEquals(stringBoard, stringRepresentationOfBoard);
    }

    @Test
    public void testNextPlayerToGoO() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,0,0,0,0\n"
                                         +"0,0,0,0,0,0,0\n"
                                         +"0,0,0,1,0,0,0\n"
                                         +"0,0,1,2,0,0,0\n"
                                         +"0,2,2,1,1,2,0");
        
        // Act
        char nextPlayerToGo = boardOverLoaded.getPlayerTurnToGoNext();

        // Assert
        Assert.assertEquals('O', nextPlayerToGo);
    }

    @Test
    public void testNextPlayerToGoX() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,2,0,0,0\n"
                                         +"0,0,2,1,1,0,0\n"
                                         +"0,0,1,1,2,0,0\n"
                                         +"2,0,1,2,1,2,0\n"
                                         +"2,2,2,1,1,0,0");
        
        // Act
        char nextPlayerToGo = boardOverLoaded.getPlayerTurnToGoNext();

        // Assert
        Assert.assertEquals('X', nextPlayerToGo);
    }

    @Test
    public void testStripString() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,2,0,0,0\n"
                                         +"0,0,2,1,1,0,0\n"
                                         +"0,0,1,1,2,0,0\n"
                                         +"2,0,1,2,1,2,0\n"
                                         +"2,2,2,1,1,0,0");
        String nakedString = "0000000" + "0002000" + "0021100"
                            +"0011200" + "2012120" + "2221100";
        
        // Act
        Board boardOverLoadFromNakedString = new Board(nakedString);

        // Assert
        Assert.assertEquals(boardOverLoadFromNakedString.toString(), boardOverLoaded.toString());
    }

    @Test
    public void testFormatRowOfAllowableMoves() {
        // Arrange
        String rowOfMoves = " ----- ----- ----- ----- ----- ----- ----- \n"
                           +"|     |     |     |     |     |     |     |\n"
                           +"|  1  |  2  |  3  |  4  |  5  |  6  |  7  |\n"
                           +"|     |     |     |     |     |     |     |\n"
                           +" ----- ----- ----- ----- ----- ----- ----- \n";

        // Act
        String obtainedRow = tester.constructRowOfAllowedMoves();

        // Assert
        Assert.assertEquals(rowOfMoves, obtainedRow);
    }

    @Test
    public void testCheckWinIncomplete() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,2,0,0,0\n"
                                         +"0,0,2,1,2,1,0\n"
                                         +"0,0,1,1,2,2,0\n"
                                         +"2,0,1,2,1,2,0\n"
                                         +"2,2,2,1,1,1,0");
        StringBuilder obtainedMessage = new StringBuilder("");
        StringBuilder message = new StringBuilder(""); 

        // Act
        boolean result = boardOverLoaded.checkBoardWinner(message);

        // Assert
        Assert.assertEquals(false, result);
        Assert.assertEquals(obtainedMessage.toString(), message.toString());
    }

    @Test
    public void testCheckWinHorizontal() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,2,0,0,0\n"
                                         +"0,0,1,1,1,1,0\n"
                                         +"0,0,2,1,2,2,0\n"
                                         +"2,0,1,2,1,2,0\n"
                                         +"2,2,2,1,1,1,0");
        StringBuilder obtainedMessage = new StringBuilder("\nWinner is " + "X");
        StringBuilder message = new StringBuilder(""); 

        // Act
        boolean result = boardOverLoaded.checkBoardWinner(message);

        // Assert
        Assert.assertEquals(true, result);
        Assert.assertEquals(obtainedMessage.toString(), message.toString());
    }

    @Test
    public void testCheckWinVertical() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,1,0,0,0\n"
                                         +"0,0,2,2,1,1,0\n"
                                         +"0,0,2,1,1,2,0\n"
                                         +"2,0,2,1,1,2,0\n"
                                         +"2,2,2,1,1,1,0");
        StringBuilder obtainedMessage = new StringBuilder("\nWinner is " + "O");
        StringBuilder message = new StringBuilder(""); 

        // Act
        boolean result = boardOverLoaded.checkBoardWinner(message);

        // Assert
        Assert.assertEquals(true, result);
        Assert.assertEquals(obtainedMessage.toString(), message.toString());
    }

    @Test
    public void testCheckWinUpSlopeDiagonal() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,0,2,0,0,0\n"
                                         +"0,0,1,1,1,2,0\n"
                                         +"0,0,2,1,2,1,0\n"
                                         +"2,0,1,2,1,2,0\n"
                                         +"2,2,2,1,1,1,0");
        StringBuilder obtainedMessage = new StringBuilder("\nWinner is " + "O");
        StringBuilder message = new StringBuilder(""); 

        // Act
        boolean result = boardOverLoaded.checkBoardWinner(message);

        // Assert
        Assert.assertEquals(true, result);
        Assert.assertEquals(obtainedMessage.toString(), message.toString());
    }

    @Test
    public void testCheckWinDownSlopeDiagonal() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,0,0,0,0,0\n"
                                         +"0,0,2,2,0,0,0\n"
                                         +"0,0,1,2,1,1,0\n"
                                         +"1,1,2,1,2,2,0\n"
                                         +"2,1,1,2,2,2,0\n"
                                         +"1,2,2,1,1,1,0");
        StringBuilder obtainedMessage = new StringBuilder("\nWinner is " + "O");
        StringBuilder message = new StringBuilder(""); 

        // Act
        boolean result = boardOverLoaded.checkBoardWinner(message);

        // Assert
        Assert.assertEquals(true, result);
        Assert.assertEquals(obtainedMessage.toString(), message.toString());
    }

    @Test
    public void testCheckWinGameIsTie() {
        // Arrange
        Board boardOverLoaded = new Board("1,2,1,2,1,2,1\n"
                                         +"1,2,1,2,1,2,1\n"
                                         +"1,2,1,2,1,2,1\n"
                                         +"2,1,2,1,2,1,2\n"
                                         +"2,1,2,1,2,1,2\n"
                                         +"2,1,2,1,2,1,2");
        StringBuilder obtainedMessage = new StringBuilder("\nThe Game is a Tie. Good luck next time!");
        StringBuilder message = new StringBuilder(""); 

        // Act
        boolean result = boardOverLoaded.checkBoardWinner(message);

        // Assert
        Assert.assertEquals(true, result);
        Assert.assertEquals(obtainedMessage.toString(), message.toString());
    }

    @Test
    public void testUpdateCellCorrectPosition() {
        // Arrange
        int inputColumn = 3;
        String expectOutput = "1";
        char expectCharInput = 'X';
        // max size of the string 42 * 2 - one row (14) + 2 (for commas)
        int offset = 72;

        // Act
        try {
            tester.validateMoveOnBoard(inputColumn);
            tester.updateBoard(expectCharInput);
    
            String strToWork = tester.getFIleFormatStringRepresantionOfBoard();
            strToWork = strToWork.replaceAll("\n, ", "");
            Assert.assertEquals(expectOutput, Character.toString(tester.getFIleFormatStringRepresantionOfBoard().charAt(offset 
                                                                 + tester.getAvailablePosNumber())));
        } catch (ThrowExceptionWrongMoveOnBoard e) {
            throw new AssertionError(e.getMessage());
        }
    }

    @Test(expected = ThrowExceptionWrongMoveOnBoard.class)
    public void testValidateMoveOnBoardWrongColumn() throws ThrowExceptionWrongMoveOnBoard {
        // Arrange
        int column = 0;

        // Assert
        tester.validateMoveOnBoard(column);
    }

    @Test
    public void testValidateMoveOnBoardFullColumn() {
        // Arrange
        Board boardOverLoaded = new Board("0,0,2,0,0,0,0\n"
                                         +"0,0,2,2,0,0,0\n"
                                         +"0,1,1,2,1,1,0\n"
                                         +"1,1,2,1,2,2,0\n"
                                         +"2,1,1,2,2,2,0\n"
                                         +"2,2,2,1,1,1,0");
        int column = 3;

        // Assert
        ThrowExceptionWrongMoveOnBoard exception = Assert.assertThrows(ThrowExceptionWrongMoveOnBoard.class, () -> {boardOverLoaded.validateMoveOnBoard(column);} );
        Assert.assertEquals("The column is full", exception.getMessage());
    }

    @Test
    public void testTooLongBoard() {
        // Arrange
        String stringBoard = "0,0,2,0,0,0,0\n"
                            +"0,0,2,2,0,0,0\n"
                            +"0,1,1,2,1,1,0\n"
                            +"1,1,2,1,2,2,0\n"
                            +"2,1,1,2,2,2,0\n"
                            +"2,2,2,1,1,1,0,2";
        Board boardOverLoaded = new Board(stringBoard);

        // Assert
        try {
            boardOverLoaded.validateBoardFromFile(stringBoard);
            Assert.fail();
        } catch (ThrowExceptionWrongBoardFormat ex) {
            Assert.assertTrue(ex instanceof ThrowExceptionWrongBoardFormat);
            Assert.assertEquals("Length of the board read from file doesn't match the expected one. Please restart\n", ex.getMessage());
        } catch (ThrowExceptionTheGameHasEnded ex) {
            Assert.fail();
        }
    }

    /*
    @Test
    public void testTooShortBoard() {
        // Arrange
        String stringBoard = "0,0,2,0,0,0,0\n"
                            +"0,0,2,2,0,0,0\n"
                            +"0,1,1,2,1,1,0\n"
                            +"1,1,2,1,2,2,0\n"
                            +"2,1,1,2,2,2,0\n"
                            +"2,2,2,1,1,1";
        Board boardOverLoaded = new Board(stringBoard);

        // Assert
        try {
            boardOverLoaded.validateBoardFromFile(stringBoard);
            Assert.fail();
        } catch (ThrowExceptionWrongBoardFormat ex) {
            Assert.assertTrue(ex instanceof ThrowExceptionWrongBoardFormat);
            Assert.assertEquals("Length of the board read from file doesn't match the expected one. Please restart\n", ex.getMessage());
        } catch (ThrowExceptionTheGameHasEnded ex) {
            Assert.fail();
        }
    } */

    @Test
    public void testUnexpectedSymbols() {
        // Arrange
        String stringBoard = "0,0,2,0,0,0,0\n"
                            +"0,0,.,2,0,0,0\n"
                            +"0,1,1,2,1,1,0\n"
                            +"1,1,2,1,2,2,0\n"
                            +"2,1,1,2,2,2,0\n"
                            +"2,2,2,1,1,1,0";
        Board boardOverLoaded = new Board(stringBoard);

        // Assert
        try {
            boardOverLoaded.validateBoardFromFile(stringBoard);
            Assert.fail();
        } catch (ThrowExceptionWrongBoardFormat ex) {
            Assert.assertTrue(ex instanceof ThrowExceptionWrongBoardFormat);
            Assert.assertEquals("The file contains unexpected symbols. Please restart\n", ex.getMessage());
        } catch (ThrowExceptionTheGameHasEnded ex) {
            Assert.fail();
        }
    }

    @Test
    public void testIncorrectNumberOfMoves() {
        // Arrange
        String stringBoard = "0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,1,1,0,0,0,0\n"
                            +"1,1,0,0,0,0,0\n"
                            +"2,1,1,0,0,0,1\n"
                            +"2,2,0,1,2,1,1";
        Board boardOverLoaded = new Board(stringBoard);

        // Assert
        try {
            boardOverLoaded.validateBoardFromFile(stringBoard);
            Assert.fail();
        } catch (ThrowExceptionWrongBoardFormat ex) {
            Assert.assertTrue(ex instanceof ThrowExceptionWrongBoardFormat);
            Assert.assertEquals("One player did too many moves. Please restart\n", ex.getMessage());
        } catch (ThrowExceptionTheGameHasEnded ex) {
            Assert.fail();
        }
    }

    @Test
    public void testGameHasWinner() {
        // Arrange
        String stringBoard = "0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,0,0,2,1,2,0\n"
                            +"1,1,2,1,0,2,0\n"
                            +"2,1,1,1,1,2,0\n"
                            +"2,2,2,1,2,1,1";
        Board boardOverLoaded = new Board(stringBoard);
        StringBuilder message = new StringBuilder("");

        // Act
        boardOverLoaded.checkBoardWinner(message);

        // Assert
        try {
            boardOverLoaded.validateBoardFromFile(stringBoard);
            Assert.fail();
        } catch (ThrowExceptionWrongBoardFormat ex) {    
            Assert.fail();
        } catch (ThrowExceptionTheGameHasEnded ex) {
            Assert.assertTrue(ex instanceof ThrowExceptionTheGameHasEnded);
            Assert.assertEquals("The game on this board has finihsed. " + message, ex.getMessage());
        }
    }

    @Test
    public void testFloatingCell() {
        // Arrange
        String stringBoard = "0,0,0,0,0,0,0\n"
                            +"0,0,0,0,0,0,0\n"
                            +"0,0,0,2,0,2,0\n"
                            +"0,0,0,1,0,0,0\n"
                            +"0,0,0,1,0,2,0\n"
                            +"0,0,0,1,1,2,1";
        Board boardOverLoaded = new Board(stringBoard);

        // Assert
        try {
            boardOverLoaded.validateBoardFromFile(stringBoard);
            Assert.fail();
        } catch (ThrowExceptionWrongBoardFormat ex) {    
            Assert.assertTrue(ex instanceof ThrowExceptionWrongBoardFormat);
            Assert.assertEquals("Board contains floating cells, which is not allowed in ConnectFour. Please restart\n", ex.getMessage());
        } catch (ThrowExceptionTheGameHasEnded ex) {
            Assert.fail();
        }
    }
}