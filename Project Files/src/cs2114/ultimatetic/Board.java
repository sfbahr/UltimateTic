package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * Holds information about an individual tac board.
 *
 * @author charten
 * @version 26.03.2014
 */
public class Board
{
    // Fields
    private Cell[][] board;
    private Cell     whoIsWinning;


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
        whoIsWinning = Cell.EMPTY;
    }


    // Methods

    /**
     * Returns a Cell value to indicate the current winner. Returns empty if no
     * winner exists.
     *
     * @return Cell The side that is winning.
     */
    public Cell getWhoIsWinning()
    {
        return whoIsWinning;
    }


    /**
     * Checks if a player has made a triple on this board. If no triples exist,
     * returns EMPTY.
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
                return board[0][i];
            }
        }

        // Check for completed diagonals
        if (board[0][0].equals(board[1][1])
            && board[0][0].equals(board[2][2])
            && !board[0][0].equals(Cell.EMPTY))
        {
            return board[0][0];
        }

        if (board[0][2].equals(board[1][1])
            && board[0][2].equals(board[2][0])
            && !board[0][2].equals(Cell.EMPTY))
        {
            return board[0][2];
        }

        // If none of the above conditions is satisfied, no triples exist.
        return Cell.EMPTY;
    }

    /**
     * Place a description of your method here.
     * @param row
     * @param col
     */
    public void setCell(int row, int col)
    {
        // TODO Implement this.
    }
}
