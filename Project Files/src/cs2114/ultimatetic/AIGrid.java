package cs2114.ultimatetic;

// -------------------------------------------------------------------------
/**
 * Creates an AI to play against
 *
 * @author Brian Clarke (golfboy1)
 * @author Samuel Bahr (sfbahr)
 * @author Charles Tenny (charten)
 * @version 2014.04.30
 */
public class AIGrid
    extends Grid
{

    private int[] board;


    // ----------------------------------------------------------
    /**
     * Create a new AI object.
     */
    public AIGrid()
    {
        super();
    }


    // ----------------------------------------------------------
    public boolean setCell(int row, int col)
    {
        boolean boo = super.setCell(row, col);
        if (boo)
        {
            this.whichIsPlayable();
            this.makeMove();
        }
        return boo;
    }


    public boolean undoMove()
    {
        if (this.getWhoHasWon() == Cell.EMPTY
            || this.getWhoHasWon() == Cell.BLUE2)
        {
            super.undoMove();
        }
        return super.undoMove();
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
                                int[] coords = playRandom();
                                super.setCell(coords[0], coords[1]);
                                return true;
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
     * Determines which board is playable
     */
    // ----------------------------------------------------------
    public void whichIsPlayable()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (this.getBoard(i, j).getIsPlayable())
                {
                    board = new int[] { i * 3, j * 3 };
                }
            }
        }
    }


    /**
     * Win if possible
     *
     * @return true if win
     */
    public boolean checkForWin()
    {

        // check columns for win
        for (int i = board[0]; i <= board[0] + 2; i++)
        {
            if ((this.getCell(i, board[1]) == this.getCell(i, board[1] + 1))
                && this.getCell(i, board[1]) == Cell.BLUE2
                && this.getCell(i, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(i, board[1] + 2);
                return true;
            }

            if ((this.getCell(i, board[1] + 1) == this.getCell(i, board[1] + 2))
                && this.getCell(i, board[1] + 1) == Cell.BLUE2
                && this.getCell(i, board[1]) == Cell.EMPTY)
            {
                super.setCell(i, board[1]);
                return true;
            }

            if ((this.getCell(i, board[1]) == this.getCell(i, board[1] + 2))
                && this.getCell(i, board[1]) == Cell.BLUE2
                && this.getCell(i, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(i, board[1] + 1);
                return true;
            }
        }

        // check rows for win
        for (int i = board[1]; i <= board[1] + 2; i++)
        {
            if ((this.getCell(board[0], i) == this.getCell(board[0] + 1, i))
                && this.getCell(board[0], i) == Cell.BLUE2
                && this.getCell(board[0] + 2, i) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, i);
                return true;
            }

            if ((this.getCell(board[0] + 1, i) == this.getCell(board[0] + 2, i))
                && this.getCell(board[0] + 1, i) == Cell.BLUE2
                && this.getCell(board[0], i) == Cell.EMPTY)
            {
                super.setCell(board[0], i);
                return true;
            }

            if ((this.getCell(board[0], i) == this.getCell(board[0] + 2, i))
                && this.getCell(board[0], i) == Cell.BLUE2
                && this.getCell(board[0] + 1, i) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, i);
                return true;
            }
        }

        // check diagonals for win
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0], board[1]) == Cell.BLUE2
            && this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1] + 2);
            return true;
        }

        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0], board[1]) == Cell.BLUE2
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
        {
            super.setCell(board[0] + 1, board[1] + 1);
            return true;
        }

        if ((this.getCell(board[0] + 1, board[1] + 1) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.BLUE2
            && this.getCell(board[0], board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1]);
            return true;
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0], board[1] + 2) == Cell.BLUE2
            && this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1]);
            return true;
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1]))
            && this.getCell(board[0], board[1] + 2) == Cell.BLUE2
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
        {
            super.setCell(board[0] + 1, board[1] + 1);
            return true;
        }

        if ((this.getCell(board[0] + 1, board[1] + 1) == this.getCell(
            board[0] + 2,
            board[1]))
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.BLUE2
            && this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1] + 2);
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
        // check to block rows
        for (int i = board[0]; i <= board[0] + 2; i++)
        {
            if ((this.getCell(i, board[1]) == this.getCell(i, board[1] + 1))
                && this.getCell(i, board[1]) == Cell.RED1
                && this.getCell(i, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(i, board[1] + 2);
                return true;
            }

            if ((this.getCell(i, board[1] + 1) == this.getCell(i, board[1] + 2))
                && this.getCell(i, board[1] + 1) == Cell.RED1
                && this.getCell(i, board[1]) == Cell.EMPTY)
            {
                super.setCell(i, board[1]);
                return true;
            }

            if ((this.getCell(i, board[1]) == this.getCell(i, board[1] + 2))
                && this.getCell(i, board[1]) == Cell.RED1
                && this.getCell(i, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(i, board[1] + 1);
                return true;
            }
        }

        // check to block columns
        for (int i = board[1]; i <= board[1] + 2; i++)
        {
            if ((this.getCell(board[0], i) == this.getCell(board[0] + 1, i))
                && this.getCell(board[0], i) == Cell.RED1
                && this.getCell(board[0] + 2, i) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, i);
                return true;
            }

            if ((this.getCell(board[0] + 1, i) == this.getCell(board[0] + 2, i))
                && this.getCell(board[0] + 1, i) == Cell.RED1
                && this.getCell(board[0], i) == Cell.EMPTY)
            {
                super.setCell(board[0], i);
                return true;
            }

            if ((this.getCell(board[0], i) == this.getCell(board[0] + 2, i))
                && this.getCell(board[0], i) == Cell.RED1
                && this.getCell(board[0] + 1, i) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, i);
                return true;
            }
        }

        // check to block diagonals
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0], board[1]) == Cell.RED1
            && this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1] + 2);
            return true;
        }

        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0], board[1]) == Cell.RED1
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
        {
            super.setCell(board[0] + 1, board[1] + 1);
            return true;
        }

        if ((this.getCell(board[0] + 1, board[1] + 1) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.RED1
            && this.getCell(board[0], board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1]);
            return true;
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0], board[1] + 2) == Cell.RED1
            && this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1]);
            return true;
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1]))
            && this.getCell(board[0], board[1] + 2) == Cell.RED1
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
        {
            super.setCell(board[0] + 1, board[1] + 1);
            return true;
        }

        if ((this.getCell(board[0] + 1, board[1] + 1) == this.getCell(
            board[0] + 2,
            board[1]))
            && this.getCell(board[0] + 1, board[1] + 1) == Cell.RED1
            && this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1] + 2);
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
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0],
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0], board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        if ((this.getCell(board[0] + 2, board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0] + 2, board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
        }

        // 2) 2 corners and the middle
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1)) && this.getCell(board[0], board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0], board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 2, board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 2, board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0],
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0], board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0] + 2, board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }

        // 3 & 4) 2 sides and adjacent corner & middle and adjacent sides
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1])) && this.getCell(board[0], board[1] + 1) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 2))
            && this.getCell(board[0], board[1] + 1) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1]))
            && this.getCell(board[0] + 2, board[1] + 1) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 2))
            && this.getCell(board[0] + 2, board[1] + 1) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }

        // 5) corner, middle, and adjacent side
        if ((this.getCell(board[0] + 1, board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 1, board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0], board[1] + 1) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 1, board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 2, board[1] + 1) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        // 6 & 7) 2 adjacent corners and adjacent side &
        // opposite sides and corner
        if ((this.getCell(board[0] + 1, board[1]) == this.getCell(
            board[0],
            board[1] + 2))
            && this.getCell(board[0] + 1, board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2))
            && this.getCell(board[0] + 1, board[1]) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 2);
                return true;
            }
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1] + 2) == this.getCell(
            board[0],
            board[1]))
            && this.getCell(board[0] + 1, board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1]);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1]))
            && this.getCell(board[0] + 1, board[1] + 2) == Cell.BLUE2)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1]);
                return true;
            }
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
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
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0],
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0], board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        if ((this.getCell(board[0] + 2, board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0] + 2, board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
        }

        // 2) 2 corners and the middle
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1)) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }

        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1)) && this.getCell(board[0], board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1)) && this.getCell(board[0] + 2, board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 2, board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0],
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0], board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0], board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0], board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0] + 2, board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }

        // 3 & 4) 2 sides and adjacent corner & middle and adjacent sides
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1])) && this.getCell(board[0], board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 2)) && this.getCell(board[0], board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1])) && this.getCell(board[0] + 2, board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 2))
            && this.getCell(board[0] + 2, board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
            else if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }

        // 5) corner, middle, and adjacent side
        if ((this.getCell(board[0] + 1, board[1]) == this.getCell(
            board[0] + 1,
            board[1] + 1)) && this.getCell(board[0] + 1, board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 1)) && this.getCell(board[0], board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1] + 2) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 1, board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 1))
            && this.getCell(board[0] + 2, board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1])) && this.getCell(board[0], board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0], board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 2)) && this.getCell(board[0], board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1])) && this.getCell(board[0] + 2, board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }
        if ((this.getCell(board[0] + 2, board[1] + 1) == this.getCell(
            board[0] + 1,
            board[1] + 2))
            && this.getCell(board[0] + 2, board[1] + 1) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
        }

        // 6 & 7) 2 adjacent corners and adjacent side &
        // opposite sides and corner
        if ((this.getCell(board[0] + 1, board[1]) == this.getCell(
            board[0],
            board[1] + 2)) && this.getCell(board[0] + 1, board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1]) == this.getCell(
            board[0] + 2,
            board[1] + 2)) && this.getCell(board[0] + 1, board[1]) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1]);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 2);
                return true;
            }
            if (this.getCell(board[0], board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1]);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1] + 2) == this.getCell(
            board[0],
            board[1])) && this.getCell(board[0] + 1, board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1]);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
        }
        if ((this.getCell(board[0] + 1, board[1] + 2) == this.getCell(
            board[0] + 2,
            board[1])) && this.getCell(board[0] + 1, board[1] + 2) == Cell.RED1)
        {
            if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1] + 1);
                return true;
            }
            if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0] + 2, board[1] + 2);
                return true;
            }
            if (this.getCell(board[0] + 1, board[1]) == Cell.EMPTY)
            {
                super.setCell(board[0] + 1, board[1]);
                return true;
            }
            if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
            {
                super.setCell(board[0], board[1] + 2);
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
        if (this.getCell(board[0] + 1, board[1] + 1) == Cell.EMPTY)
        {
            super.setCell(board[0] + 1, board[1] + 1);
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
        if (this.getCell(board[0], board[1]) == Cell.RED1
            && this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1] + 2);
            return true;
        }

        if (this.getCell(board[0] + 2, board[1]) == Cell.RED1
            && this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1] + 2);
            return true;
        }

        if (this.getCell(board[0], board[1] + 2) == Cell.RED1
            && this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1]);
            return true;
        }

        if (this.getCell(board[0] + 2, board[1] + 2) == Cell.RED1
            && this.getCell(board[0], board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1]);
            return true;
        }
        return false;
    }


    public boolean playEmptyCorner()
    {
        if (this.getCell(board[0], board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1]);
            return true;
        }
        if (this.getCell(board[0] + 2, board[1]) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1]);
            return true;
        }
        if (this.getCell(board[0], board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0], board[1] + 2);
            return true;
        }
        if (this.getCell(board[0] + 2, board[1] + 2) == Cell.EMPTY)
        {
            super.setCell(board[0] + 2, board[1] + 2);
            return true;
        }
        return false;

    }

}
