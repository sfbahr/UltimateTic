package cs2114.ultimatetic;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Random AI
 *
 *  @author Brian Clarke
 *  @version Apr 30, 2014
 */
public class RandomAIGridTest extends TestCase
{

    /**
     * random generator
     */
    RandomAIGrid random;

    /*public RandomAIGridTest()
    {
        // TODO Auto-generated constructor stub
    }
    */

    public void setUp()
    {
        random = new RandomAIGrid();
    }

    // ----------------------------------------------------------
    /**
     * tests setCell()
     */
    public void testSetCell()
    {
        assertTrue(random.setCell(0, 0));
    }

    // ----------------------------------------------------------
    /**
     * tests undoMove()
     */
    public void testUndoMove()
    {
        assertFalse(random.undoMove());
        random.setCell(0, 0);
        assertTrue(random.undoMove());
    }

}
