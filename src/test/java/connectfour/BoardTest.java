package connectfour;

import org.junit.Assert;
import org.junit.Before;
// import org.junit.Test;
import org.junit.Test;

/* you will need to add test methods and likely change the
setup method as well.  The samples that are here are just so that
you can see how junit works.

Tests are run on build unless specifically excluded with -x test.
The test results are reported in the reports subfolder of the build directory */


public class BoardTest{
    private Board tester;

    @Before
    public void setup(){
        //set up for the test
        tester = new Board();

    }

    @Test
    public void testPrintBoard() {
        Board board = new Board();
        String stuff = 
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


        Assert.assertEquals(stuff, board.toString());
    }

    // @Test
    public void testPrintUpdatedBoard() {
        Board board = new Board();
        String stuff = 
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

        board.setAvailablePosNumber(0);
        board.updateBoard('X');
        board.setAvailablePosNumber(1);
        board.updateBoard('O');
        board.setAvailablePosNumber(4);
        board.updateBoard('X');
        Assert.assertEquals(stuff, board.toString());
    }

}