package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * This is a random AI
 *
 * @author Brian Clarke (golfboy1)
 * @author Samuel Bahr (sfbahr)
 * @author Charles Tenny (charten)
 * @version 2014.04.30
 */
public class RandomAIGrid
    extends Grid
{

    // ----------------------------------------------------------

    /**
     * Sets the player's cell and then has the AI move to a random cell.
     *
     * @param row
     *            The row of the cell to set
     * @param col
     *            The column of the cell to set
     * @return true if the cell was set properly, false if not (it was an
     *         invalid location)
     */
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


    /**
     * Undos two times to account for the AI's move
     *
     * @return true if the undo was successful
     */
    @Override
    public boolean undoMove()
    {
        if (this.getWhoHasWon() == Cell.EMPTY
            || this.getWhoHasWon() == Cell.BLUE2)
        {
            super.undoMove();
        }
        return super.undoMove();
    }
}
