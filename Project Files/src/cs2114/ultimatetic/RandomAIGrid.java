package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 *  This is a random AI
 *
 *  @author Brian Clarke
 *  @version Apr 28, 2014
 */
public class RandomAIGrid extends Grid
{

    // ----------------------------------------------------------
    /**
     * Create a new RandomAIGrid object.
     */
    /*public RandomAIGrid()
    {
        super();
    }*/

    @Override
    public boolean setCell(int row, int col)
    {
        boolean boo = super.setCell(row, col);
        if (boo)
        {
            int[] coords = playRandom();
            super.setCell(coords[0], coords[1]);
        }
        return boo;
    }
}
