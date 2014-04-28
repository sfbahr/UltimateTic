package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 *  This is a random AI
 *
 *  @author Brian Clarke
 *  @version Apr 28, 2014
 */
public class RandomAIGrid extends AIGrid
{

    // ----------------------------------------------------------
    /**
     * Create a new RandomAIGrid object.
     */
    public RandomAIGrid()
    {
        super();
    }

    public boolean setCell(int row, int col)
    {
        boolean boo = super.setCell(row, col);
        if (boo)
        {
            super.whichIsPlayable();
            super.playRandom();
        }
        return boo;
    }

}
