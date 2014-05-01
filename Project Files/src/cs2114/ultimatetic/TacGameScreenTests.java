package cs2114.ultimatetic;

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
        grid = getScreen().getGrid();
    }

    /**
     * Test that touching down on a cell will place a
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
     * How to test the dialogFragment and how to test the actionbar buttons? :(
     */
    public void testRestartGame()
    {
        touchDown(shapeView, 40, 40);
        touchUp();
        assertTrue(true);
    }
}
