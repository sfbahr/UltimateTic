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
    private Cell      whoIsWinning;


    /**
     * Create a new Grid object.
     */
    public Grid()
    {
        grid = new Board[3][3];
        whoIsWinning = Cell.EMPTY;
    }


    /**
     * Checks if a player has made a triple on this grid and returns the player
     * that has. If no triples exist, returns EMPTY. Also sets the whoIsWinning
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
            if (grid[i][0].getWhoIsWinning().equals(
                grid[i][1].getWhoIsWinning())
                && grid[i][0].getWhoIsWinning().equals(
                    grid[i][2].getWhoIsWinning())
                && !grid[i][0].getWhoIsWinning().equals(Cell.EMPTY))
            {
                whoIsWinning = grid[i][0].getWhoIsWinning();
                return grid[i][0].getWhoIsWinning();
            }
        }

        // Check for completed rows
        for (int i = 0; i < 3; i++)
        {
            if (grid[0][i].getWhoIsWinning().equals(
                grid[1][i].getWhoIsWinning())
                && grid[0][i].getWhoIsWinning().equals(
                    grid[2][i].getWhoIsWinning())
                && !grid[0][i].getWhoIsWinning().equals(Cell.EMPTY))
            {
                whoIsWinning = grid[0][i].getWhoIsWinning();
                return grid[0][i].getWhoIsWinning();
            }
        }

        // Check for completed diagonals
        if (grid[0][0].getWhoIsWinning().equals(grid[1][1].getWhoIsWinning())
            && grid[0][0].getWhoIsWinning()
                .equals(grid[2][2].getWhoIsWinning())
            && !grid[0][0].getWhoIsWinning().equals(Cell.EMPTY))
        {
            whoIsWinning = grid[0][0].getWhoIsWinning();
            return grid[0][0].getWhoIsWinning();
        }

        if (grid[0][2].getWhoIsWinning().equals(grid[1][1].getWhoIsWinning())
            && grid[0][2].getWhoIsWinning()
                .equals(grid[2][0].getWhoIsWinning())
            && !grid[0][2].getWhoIsWinning().equals(Cell.EMPTY))
        {
            whoIsWinning = grid[0][2].getWhoIsWinning();
            return grid[0][2].getWhoIsWinning();
        }

        // If none of the above conditions is satisfied, no triples exist.
        whoIsWinning = Cell.EMPTY;
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
        // TODO error checking
        return grid[row][col];
    }
}