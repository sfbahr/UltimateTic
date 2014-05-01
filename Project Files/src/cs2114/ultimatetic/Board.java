package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * Holds information about an individual tac board.
 *
 * @author Samuel Bahr (sfbahr)
 * @author Brian Clarke (golfboy1)
 * @author Charles Tenney (charten)
 * @version 2014.04.16
 */
public class Board
{
    // Fields
    private Cell[][] board;
    private Cell     whoHasWon;
    private boolean  isPlayable;


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

        setIsPlayable(false);
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
     * @param turn
     *            A Cell value indicating which mark should be placed.
     * @return boolean A value indicating whether the cell was set.
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
                markWasPlaced = true;
            }
        }

        checkForTriple();
        return markWasPlaced;
    }


    // ----------------------------------------------------------
    /**
     * Sets the specified cell without any checks. Just does it. Used to allow
     * the undo function to "make moves" that normally wouldn't be allowed.
     *
     * @param row
     *            The row index of the cell.
     * @param col
     *            The column index of the cell.
     * @param mark
     *            A Cell value indicating which mark should be placed.
     * @param overload
     *            Overloads the method to do the checkless setcell.
     */
    public void setCell(int row, int col, Cell mark, boolean overload)
    {
        board[row][col] = mark;
        checkForTriple();
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
        // Row iterator
        for (int i = 0; i < 3; i++)
        {
            // Column iterator
            for (int j = 0; j < 3; j++)
            {
                // Don't use setCell since it has error checking.
                board[i][j] = Cell.EMPTY;
            }
        }

        whoHasWon = Cell.EMPTY;
        isPlayable = true;
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
     * @return String The string representation of the board.
     */
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder("");

        // Initialize variable
        Cell cell = null;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cell = this.getCell(i, j);

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
     * Takes a 9 character long string and configures the board state to match.
     * Only takes upper case values to allow toString/fromString compatibility.
     * R -> Cell.RED1, B -> Cell.BLUE2, E -> Cell.EMPTY. Reads in row by row
     * from left to right. It will throw an error if you put in something weird.
     *
     * @param state
     *            A string representing the desired state of the board.
     */
    public void fromString(String state)
    {
        if (state.length() != 9)
        {
            throw new IllegalArgumentException("Your state identifying " +
                "string is the wrong length. It must be nine characters" +
                " long with no spaces.");
        }

        // Reset the board first.
        this.reset();

        // Initialize a variable
        char c = 'X';

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                c = state.charAt((3 * i) + j);

                if (c == 'R')
                    board[i][j] = Cell.RED1;
                else if (c == 'B')
                    board[i][j] = Cell.BLUE2;
                else if (c == 'E')
                    board[i][j] = Cell.EMPTY;
                else
                {
                    throw new IllegalArgumentException("Your string has a " +
                        "value in it that doesn't match one of our cell " +
                        "types.");
                }
            }
        }
        checkForTriple();
    }


    // ----------------------------------------------------------
    /**
     * Getter for isPlayable.
     *
     * @return boolean The value of isPlayable.
     */
    public boolean getIsPlayable()
    {
        return isPlayable;
    }


    // ----------------------------------------------------------
    /**
     * Setter for isPlayable.
     *
     * @param isPlayable
     *            The desired value of isPlayable.
     */
    public void setIsPlayable(boolean isPlayable)
    {
        this.isPlayable = isPlayable;
    }


    // ----------------------------------------------------------
    /**
     * Checks if there is a cell to play in.
     * @return boolean true if all cells are marked
     */
    public boolean isFull()
    {
        // If any cell is empty, return true.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == Cell.EMPTY)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
