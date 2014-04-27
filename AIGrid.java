package cs2114.ultimatetic;

import java.util.Random;

// -------------------------------------------------------------------------
/**
 * Creates an AI to play against
 *
 * @author Brian Clarke
 * @version Apr 13, 2014
 */
public class AIGrid
    extends Grid
{

    // ----------------------------------------------------------
    /**
     * Create a new AI object.
     */
    public AIGrid()
    {
        super();
    }


    // ----------------------------------------------------------
    public void setCell(int row, int col)
    {
        super.setCell(row, col);
        this.makeMove();
    }


    /**
     * AI makes a move
     *
     * @return true if move is made
     */
    public boolean makeMove()
    {
        if (!checkForWin())
        {
            if (!blockLoss())
            {
                if (!createFork())
                {
                    if (!blockFork())
                    {
                        if (!playCenter())
                        {
                            if (!playOppositeCorner())
                            {
                                if (!playEmptyCorner())
                                {
                                    if (!playSide())
                                    {
                                        playRandom();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Win if possible
     *
     * @return true if win
     */
    public boolean checkForWin()
    {
        // check columns for win
        for (int i = 0; i <= 2; i++)
        {
            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 0) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 1))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(i, 0) == Cell.BLUE2)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(i, 2, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 1) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 2))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(i, 1) == Cell.BLUE2)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(i, 0, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 0) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 2))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(i, 0) == Cell.BLUE2)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(i, 1, Cell.BLUE2);
                return true;
            }
        }

        // check rows for win
        for (int i = 0; i <= 2; i++)
        {
            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, i) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, i))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(0, i) == Cell.BLUE2)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, i, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, i) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, i))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(1, i) == Cell.BLUE2)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, i, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, i) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, i))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(0, i) == Cell.BLUE2)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, i, Cell.BLUE2);
                return true;
            }
        }

        // check diagonals for win
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 2, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 1, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.BLUE2)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 0, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.BLUE2)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 0, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.BLUE2)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 1, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.BLUE2)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 2, Cell.BLUE2);
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Don't let opponent win
     *
     * @return true if accomplished
     */
    public boolean blockLoss()
    {
        // check to block columns
        for (int i = 0; i <= 2; i++)
        {
            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 0) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 1))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(i, 0) == Cell.RED1)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(i, 2, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 1) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 2))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(i, 1) == Cell.RED1)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(i, 0, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 0) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(i, 2))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(i, 0) == Cell.RED1)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(i, 1, Cell.BLUE2);
                return true;
            }
        }

        // check to block rows
        for (int i = 0; i <= 2; i++)
        {
            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, i) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, i))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(0, 1) == Cell.RED1)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, i, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, i) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, i))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(1, i) == Cell.RED1)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, i, Cell.BLUE2);
                return true;
            }

            if ((this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, i) == this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, i))
                && this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).getCell(0, i) == Cell.RED1)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, i, Cell.BLUE2);
                return true;
            }
        }

        // check to block diagonals
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 2, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 1, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 0, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 0, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 1, Cell.BLUE2);
            return true;
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 2, Cell.BLUE2);
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Create fork
     *
     * @return true if fork was created
     */
    public boolean createFork()
    {
        // 1) 3 corners
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
        }

        // 2) 2 corners and the middle
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }

        // 3 & 4) 2 sides and adjacent corner & middle and adjacent sides
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }

        // 5) corner, middle, and adjacent side
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }

        // 6 & 7) 2 adjacent corners and adjacent side &
        //opposite sides and corner
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.BLUE2)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Block opponent's fork
     *
     * @return true if blocked
     */
    public boolean blockFork()
    {
     // 1) 3 corners
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
        }

        // 2) 2 corners and the middle
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }

        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }

        // 3 & 4) 2 sides and adjacent corner & middle and adjacent sides
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }

        // 5) corner, middle, and adjacent side
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 1))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(1, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 1) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }

        // 6 & 7) 2 adjacent corners and adjacent side &
        //opposite sides and corner
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(2, 2))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        if ((this
            .getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == this.getBoard(
            this.getLastMove()[0] / 3,
            this.getLastMove()[1] / 3).getCell(0, 0))
            && this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 2) == Cell.RED1)
        {
            if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(2, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(2, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 0) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 0, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(0, 2) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(0, 2, Cell.BLUE2);
                return true;
            }
            else if (this.getBoard(
                this.getLastMove()[0] / 3,
                this.getLastMove()[1] / 3).getCell(1, 1) == Cell.EMPTY)
            {
                this.getBoard(
                    this.getLastMove()[0] / 3,
                    this.getLastMove()[1] / 3).setCell(1, 1, Cell.BLUE2);
                return true;
            }
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Play center
     *
     * @return true if move played
     */
    public boolean playCenter()
    {
        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 1) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 1, Cell.BLUE2);
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Play opposite corner
     *
     * @return true if move played
     */
    public boolean playOppositeCorner()
    {
        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 2, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 2, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 0, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 2) == Cell.RED1)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 0, Cell.BLUE2);
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Play empty corner
     *
     * @return true if move played
     */
    public boolean playEmptyCorner()
    {
        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 0) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 0, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 0) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 0, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 2) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 2, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 2) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 2, Cell.BLUE2);
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Play empty side
     *
     * @return true if move played
     */
    public boolean playSide()
    {
        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 0) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 0, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(0, 1) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(0, 1, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(1, 2) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(1, 2, Cell.BLUE2);
            return true;
        }

        if (this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
            .getCell(2, 1) == Cell.EMPTY)
        {
            this.getBoard(this.getLastMove()[0] / 3, this.getLastMove()[1] / 3)
                .setCell(2, 1, Cell.BLUE2);
            return true;
        }
        return false;
    }

    // ----------------------------------------------------------
    /**
     * Plays random move
     * @return true if move is played
     */
    public boolean playRandom()
    {
        int x = 4;
        int y = 4;
        while (this.getCell(x, y) != Cell.EMPTY)
        {
            x = new Random().nextInt(9);
            y = new Random().nextInt(9);
        }
        this.setCell(x, y);
        return true;
    }
}
