package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * Holds information about an individual tac board.
 *
 * @author Charles Tenney (charten)
 * @version 26.03.2014
 */
public class Board
{
    // Fields
    private Cell[][] board;
    private Cell     whoHasWon;
    private static int currentRow = 0;
    private static int currentCol = 0;


    // Constructor
    /**
     * Create a new Board object.
     */
    public Board()
    {
        board = new Cell[3][3];

        // Fill with empty cells
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = Cell.EMPTY;
            }
        }

        // Set this to EMPTY to indicate that no one is winning.
        whoHasWon = Cell.EMPTY;
    }


    // Methods

    /**
     * Returns a Cell value to indicate the current winner. Returns empty if no
     * winner exists. (This exists so that the state of the board can be checked
     * without running all the code in checkForTriples().)
     *
     * @return Cell The side that is winning.
     */
    public Cell getWhoHasWon()
    {
        return whoHasWon;
    }


    /**
     * Checks if a player has made a triple on this board and returns the player
     * that has. If no triples exist, returns EMPTY. Also sets the whoHasWon
     * variable.
     *
     * @return Cell The player that has a triple.
     */
    public Cell checkForTriple()
    {
        // Method: Check that the first of the triple _is_ equal to the second
        // and third, but is _not_ an empty cell.

        // Check for completed columns
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0].equals(board[i][1])
                && board[i][0].equals(board[i][2])
                && !board[i][0].equals(Cell.EMPTY))
            {
                whoHasWon = board[i][0];
                return board[i][0];
            }
        }

        // Check for completed rows
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i].equals(board[1][i])
                && board[0][i].equals(board[2][i])
                && !board[0][i].equals(Cell.EMPTY))
            {
                whoHasWon = board[0][i];
                return board[0][i];
            }
        }

        // Check for completed diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])
            && !board[0][0].equals(Cell.EMPTY))
        {
            whoHasWon = board[0][0];
            return board[0][0];
        }

        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])
            && !board[0][2].equals(Cell.EMPTY))
        {
            whoHasWon = board[0][2];
            return board[0][2];
        }

        // If none of the above conditions is satisfied, no triples exist.
        whoHasWon = Cell.EMPTY;
        return Cell.EMPTY;
    }


    /**
     * Sets the specified cell and checks for triples. Note that indices start
     * at zero. Rows/columns are numbered from left to right and top to bottom.
     * Marks cannot be erased with this method. Next player's move must be
     * placed in grid[row][col]
     *
     * @param row
     *            The row index of the cell.
     * @param col
     *            The column index of the cell.
     */
    public boolean setCell(int row, int col, Cell turn)
    {

        boolean markWasPlaced = false;

        // Check in bounds
        if (row < 3 && row >= 0 && col < 3 && col >= 0)
        {
            // Check that cell is unoccupied and that the board is in play
            if (board[row][col].equals(Cell.EMPTY)
                && whoHasWon.equals(Cell.EMPTY))
            {
                board[row][col] = turn;
                setCurrentRow(row);
                setCurrentCol(col);
                markWasPlaced = true;
            }
        }

        checkForTriple();
        return markWasPlaced;
    }


    /**
     * Gets the specified cell. Note that indices start at zero. Rows/columns
     * are numbered from left to right and top to bottom.
     *
     * @param row
     *            The row index of the cell.
     * @param col
     *            The column index of the cell.
     * @return Cell The mark in the specified cell. Null if invalid indices.
     */
    public Cell getCell(int row, int col)
    {
        if (row < 3 && row >= 0 && col < 3 && col >= 0)
        {
            return board[row][col];
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Sets every cell on the board to empty, and sets whoHasWon to empty. This
     * method should really only be called from within the grid's reset method.
     */
    public void reset()
    {
        for (Cell[] r : board)
        {
            for (Cell c : r)
            {
                c = Cell.EMPTY;
            }
        }

        whoHasWon = Cell.EMPTY;
    }


    // ----------------------------------------------------------
    /**
     * Tests for equality of two boards.
     *
     * @param b
     *            The board to compare against.
     * @return boolean Whether the boards are equal.
     */
    @Override
    public boolean equals(Object b)
    {
        boolean areEqual = true;

        // Check that b is not null
        if (b == null)
        {
            return false;
        }

        // Check that the class is the same
        if (!b.getClass().equals(this.getClass()))
        {
            return false;
        }

        // Check that all the cells have the same value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (!this.getCell(i, j).equals(((Board)b).getCell(i, j)))
                {
                    areEqual = false;
                }
            }
        }

        return areEqual;
    }


    /**
     * Returns a representation of the board as a 9 character long string, one
     * letter for each cell.
     *
     * return String The string representation of the board.
     */
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                Cell cell = this.getCell(i, j);

                if (cell.equals(Cell.RED1))
                    s.append("R");
                else if (cell.equals(Cell.BLUE2))
                    s.append("B");
                else
                    s.append("E");
            }
        }

        return s.toString();
    }


    // ----------------------------------------------------------
    /**
     * @return the currentRow
     */
    public static int getCurrentRow()
    {
        return currentRow;
    }


    // ----------------------------------------------------------
    /**
     * @param currentRow the currentRow to set
     */
    public static void setCurrentRow(int currentRow)
    {
        Board.currentRow = currentRow;
    }


    // ----------------------------------------------------------
    /**
     * @return the currentCol
     */
    public static int getCurrentCol()
    {
        return currentCol;
    }


    // ----------------------------------------------------------
    /**
     * @param currentCol the currentCol to set
     */
    public static void setCurrentCol(int currentCol)
    {
        Board.currentCol = currentCol;
    }

}
