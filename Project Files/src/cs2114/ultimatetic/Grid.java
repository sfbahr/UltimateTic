package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * The array of nine tac boards. Follow it with additional details about its
 * purpose, what abstraction it represents, and how to use it.
 *
 * @author Charles Tenney (charten)
 * @version 2014.03.28
 */
public class Grid
{
    private Board[][] grid;
    private Cell      WhoHasWon;

    // will be used for Sam to determine whose turn it is
    private Cell      turn;


    /**
     * Create a new Grid object.
     */
    public Grid()
    {
        grid = new Board[3][3];
        setWhoIsWinning(Cell.EMPTY);
        turn = Cell.RED1;
    }


    /**
     * Checks if a player has made a triple on this grid and returns the player
     * that has. If no triples exist, returns EMPTY. Also sets the WhoHasWon
     * variable.
     *
     * @return Cell The player that has a triple.
     */
    public Cell checkForTriple()
    {

        // TODO This could probably be written more neatly.

        // Check for completed columns
        for (int i = 0; i < 3; i++)
        {
            if (grid[i][0].getWhoHasWon().equals(
                grid[i][1].getWhoHasWon())
                && grid[i][0].getWhoHasWon().equals(
                    grid[i][2].getWhoHasWon())
                && !grid[i][0].getWhoHasWon().equals(Cell.EMPTY))
            {
                setWhoIsWinning(grid[i][0].getWhoHasWon());
                return grid[i][0].getWhoHasWon();
            }
        }

        // Check for completed rows
        for (int i = 0; i < 3; i++)
        {
            if (grid[0][i].getWhoHasWon().equals(
                grid[1][i].getWhoHasWon())
                && grid[0][i].getWhoHasWon().equals(
                    grid[2][i].getWhoHasWon())
                && !grid[0][i].getWhoHasWon().equals(Cell.EMPTY))
            {
                setWhoIsWinning(grid[0][i].getWhoHasWon());
                return grid[0][i].getWhoHasWon();
            }
        }

        // Check for completed diagonals
        if (grid[0][0].getWhoHasWon().equals(grid[1][1].getWhoHasWon())
            && grid[0][0].getWhoHasWon()
                .equals(grid[2][2].getWhoHasWon())
            && !grid[0][0].getWhoHasWon().equals(Cell.EMPTY))
        {
            setWhoIsWinning(grid[0][0].getWhoHasWon());
            return grid[0][0].getWhoHasWon();
        }

        if (grid[0][2].getWhoHasWon().equals(grid[1][1].getWhoHasWon())
            && grid[0][2].getWhoHasWon()
                .equals(grid[2][0].getWhoHasWon())
            && !grid[0][2].getWhoHasWon().equals(Cell.EMPTY))
        {
            setWhoIsWinning(grid[0][2].getWhoHasWon());
            return grid[0][2].getWhoHasWon();
        }

        // If none of the above conditions is satisfied, no triples exist.
        setWhoIsWinning(Cell.EMPTY);
        return Cell.EMPTY;
    }


    // ----------------------------------------------------------
    /**
     * Returns the board in the specified position. No error checking yet, and
     * rows/columns are numbered from the upper left corner.
     *
     * @param row
     *            Row index of the board.
     * @param col
     *            Column index of the board.
     * @return The board at the specified index.
     */
    public Board getBoard(int row, int col)
    {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2)
        {
            return grid[row][col];
        }
        // possibly throw exception?
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Displays who is winning the game currently
     *
     * @return the WhoHasWon
     */
    public Cell getWhoHasWon()
    {
        return WhoHasWon;
    }


    // ----------------------------------------------------------
    /**
     * Set a new winner
     *
     * @param WhoHasWon
     *            the WhoHasWon to set
     */
    public void setWhoIsWinning(Cell WhoHasWon)
    {
        this.WhoHasWon = WhoHasWon;
    }
}
