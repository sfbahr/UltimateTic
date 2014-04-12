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
    private Cell      whoHasWon;

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
     * that has. If no triples exist, returns EMPTY. Also sets the whoHasWon
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
     * @return the whoHasWon
     */
    public Cell getWhoHasWon()
    {
        return whoHasWon;
    }


    // ----------------------------------------------------------
    /**
     * Returns the value of the turn variable.
     *
     * @return Cell A value representing whose turn it is.
     */
    public Cell getTurn()
    {
        return turn;
    }


    // ----------------------------------------------------------
    /**
     * Set a new winner
     *
     * @param whoHasWon
     *            the whoHasWon to set
     */
    public void setWhoIsWinning(Cell whoHasWon)
    {
        this.whoHasWon = whoHasWon;
    }


    // ----------------------------------------------------------
    /**
     * Place a mark in any cell in the grid. The cell value is determined by the
     * turn variable. If the cell is invalid, nothing happens and the turn is
     * not changed.
     *
     * @param row
     *            The row (0-8) of the cell on the grid.
     * @param col
     *            The column (0-8) of the cell on the grid.
     */
    public void setCell(int row, int col)
    {
        int boardRow = row / 3;
        int cellRow = row % 3;
        int boardCol = col / 3;
        int cellCol = col % 3;

        boolean markWasPlaced =
            grid[boardRow][boardCol].setCell(cellRow, cellCol, turn);

        if (markWasPlaced)
        {
            if (turn == Cell.RED1)
            {
                turn = Cell.BLUE2;
            }
            else
            {
                turn = Cell.RED1;
            }
        }

        checkForTriple();

    }


    // ----------------------------------------------------------
    /**
     * Sets every cell on the grid to empty, sets whoHasWon to empty everywhere,
     * and sets the turn to Red/player1.
     */
    public void reset()
    {
        for (Board[] r : grid)
        {
            for (Board b : r)
            {
                b.reset();
            }
        }

        whoHasWon = Cell.EMPTY;

        turn = Cell.RED1;
    }

    // ----------------------------------------------------------
    /**
     * Tests for equality of two grids.
     *
     * @param g
     *            The grid to compare against.
     * @return boolean Whether the grids are equal.
     */
    public boolean equals(Grid g)
    {
        boolean areEqual = true;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (!this.getBoard(i, j).equals(g.getBoard(i, j)))
                {
                    areEqual = false;
                }
            }
        }

        return areEqual;
    }
}
