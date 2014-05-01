package cs2114.ultimatetic;

import android.widget.Button;
import sofia.app.Screen;
import sofia.graphics.ShapeView;

/**
 *  Tests for the TacGameScreen class.
 *
 * @author Samuel Bahr (sfbahr)
 * @author Brian Clarke (golfboy1)
 * @author Charles Tenney (charten)
 * @version 2014.04.30
 */
public class TacGameScreenTests
    extends student.AndroidTestCase<TacGameScreen>
{
    private ShapeView shapeView;
    private TacGameScreen screen;
    private Grid grid;

    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public TacGameScreenTests()
    {
        super(TacGameScreen.class);
    }

    /**
     * Run before every test.
     */
    public void setUp()
    {
        screen = getScreen();
        grid = screen.getGrid();
    }

    /**
     * Test that touching down on a cell will place a cell
     */
    public void testTouchCell()
    {
        touchDown(shapeView, 40, 40);
        touchUp();
        assertEquals(grid.getCell(0, 0), Cell.RED1);
        touchDown(shapeView, 200, 200);
        touchUp();
        assertEquals(grid.getCell(1, 1), Cell.BLUE2);
    }

    /**
     * Tests that the reset method properly resets the board
     */
    public void testRestartGame()
    {
        touchDown(shapeView, 40, 40);
        touchUp();
        screen.onDialogPositiveClick(null);
        assertEquals(grid.getCell(0, 0), Cell.EMPTY);
    }

    /**
     * Tests that the undo button works
     */
    public void testUndo()
    {
        touchDown(shapeView, 40, 40);
        touchUp();
        screen.action_undoClicked();
        assertEquals(grid.getCell(0, 0), Cell.EMPTY);
    }

    /**
     * Tests that the gridLocation method returns the proper integer
     */
    public void testGridLocation()
    {
        assertEquals(0, screen.gridLocation(40));
    }
}
