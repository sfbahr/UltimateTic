package cs2114.ultimatetic;

import student.TestCase;

public class RandomAIGridTest extends TestCase
{

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

    public void testSetCell()
    {
        assertTrue(random.setCell(0, 0));
    }

    public void testUndoMove()
    {
        assertFalse(random.undoMove());
        random.setCell(0, 0);
        assertTrue(random.undoMove());
    }

}
