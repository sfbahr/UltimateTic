package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * Creates an AI to play against
 *
 * @author Brian Clarke
 * @version Apr 13, 2014
 */
public class AI
{
    /**
     * a new grid to use
     */
    private Grid grid;


    /**
     *
     */

    // ----------------------------------------------------------
    /**
     * Create a new AI object.
     * @param grid is the main grid
     */
    public AI(Grid grid)
    {
        this.grid = grid;
    }


    // ----------------------------------------------------------
    /**
     * AI makes a move
     */
    public void makeMove()
    {
        // check columns for win
        for (int i = 0; i <= 2; i++)
        {
            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(i, 0) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(i, 1))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(i, 0) == Cell.BLUE2)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(i, 2, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(i, 1) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(i, 2))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(i, 1) == Cell.BLUE2)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(i, 0, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(i, 0) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(i, 2))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(i, 0) == Cell.BLUE2)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(i, 1, Cell.BLUE2);
            }
        }

        // check rows for win
        for (int i = 0; i <= 2; i++)
        {
            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, i) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(1, i))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(0, i) == Cell.BLUE2)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(2, i, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, i) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(2, i))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(1, i) == Cell.BLUE2)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(0, i, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, i) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(2, i))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(0, i) == Cell.BLUE2)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(1, i, Cell.BLUE2);
            }
        }

        // check diagonals for win
        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 0) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(1, 1))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, 0) == Cell.BLUE2)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 2, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 0) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 2))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, 0) == Cell.BLUE2)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(1, 1, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 1) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 2))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, 1) == Cell.BLUE2)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 0, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 2) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(1, 1))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, 2) == Cell.BLUE2)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 0, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 2) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 0))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, 1) == Cell.BLUE2)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 2, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 1) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 0))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, 1) == Cell.BLUE2)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 2, Cell.BLUE2);
        }

        // block opponents win - same as check for win
        // check to block columns
        for (int i = 0; i <= 2; i++)
        {
            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(i, 0) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(i, 1))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(i, 0) == Cell.RED1)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(i, 2, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(i, 1) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(i, 2))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(i, 1) == Cell.RED1)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(i, 0, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(i, 0) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(i, 2))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(i, 0) == Cell.RED1)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(i, 1, Cell.BLUE2);
            }
        }

        // check to block rows
        for (int i = 0; i <= 2; i++)
        {
            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, i) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(1, i))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(0, 1) == Cell.RED1)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(2, i, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, i) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(2, i))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(1, i) == Cell.RED1)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(0, i, Cell.BLUE2);
            }

            if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, i) == grid.getBoard(
                Board.getCurrentRow(),
                Board.getCurrentCol()).getCell(2, i))
                && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .getCell(0, i) == Cell.RED1)
            {
                grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                    .setCell(1, i, Cell.BLUE2);
            }
        }

        // check to block diagonals
        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 0) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(1, 1))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, 0) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 2, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 0) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 2))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, 0) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(1, 1, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 1) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 2))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, 1) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 0, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 2) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(1, 1))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(0, 2) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 0, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 2) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 0))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, 1) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 2, Cell.BLUE2);
        }

        if ((grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 1) == grid.getBoard(
            Board.getCurrentRow(),
            Board.getCurrentCol()).getCell(2, 0))
            && grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .getCell(1, 1) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 2, Cell.BLUE2);
        }

        // create fork

        // block fork

        // check for open center
        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 1) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(1, 1, Cell.BLUE2);
        }

        // play opposite corner of opponent
        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 0) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 2, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(2, 0) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 2, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 2) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 0, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(2, 2) == Cell.RED1)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 0, Cell.BLUE2);
        }

        // play empty corner
        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 0) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 0, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(2, 0) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 0, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 2) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 2, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(2, 2) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 2, Cell.BLUE2);
        }

        // play empty side
        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 0) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(1, 0, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(0, 1) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(0, 1, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(1, 2) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(1, 2, Cell.BLUE2);
        }

        if (grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
            .getCell(2, 1) == Cell.EMPTY)
        {
            grid.getBoard(Board.getCurrentRow(), Board.getCurrentCol())
                .setCell(2, 1, Cell.BLUE2);
        }

    }
}
