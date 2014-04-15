package cs2114.ultimatetic;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * /** Tests the Grid class.
 *
 * @author Charles Tenney (charten)
 * @version 2014.04.13
 */
public class GridTest
    extends TestCase
{

    private Grid g1;
    private Grid g2;


    protected void setUp()
        throws Exception
    {
        g1 = new Grid();
        g2 = new Grid();
    }


    // ----------------------------------------------------------
    /**
     * Tests the initial setup of the grid.
     */
    public void testConstructor()
    {
        // No one is winning, it's red's turn, and no moves have been made
        assertEquals(g1.getTurn(), Cell.RED1);
        assertEquals(g1.getWhoHasWon(), Cell.EMPTY);
        assertNull(g1.getLastMove());

        // Everywhere is playable, no boards have been won
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(g1.getBoard(i, j).getIsPlayable());
                assertEquals(g1.getBoard(i, j).getWhoHasWon(), Cell.EMPTY);
            }
        }

        // Every cell is empty everywhere
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                assertEquals(g1.getCell(i, j), Cell.EMPTY);
            }
        }

    }


    // ----------------------------------------------------------
    /**
     * Tests the behavior of the isPlayable variable.
     */
    public void testRefreshIsPlayable()
    {
        // Initially, everywhere is playable
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(g1.getBoard(i, j).getIsPlayable());
                assertEquals(g1.getBoard(i, j).getWhoHasWon(), Cell.EMPTY);
            }
        }

        // ----------------------------
        // You cannot make a move on a board that is not playable
        // ----------------------------
        // This sequence should win the (0,0) board for red
        int[][] moves =
            new int[][] { { 1, 1 }, { 3, 3 }, { 2, 2 }, { 6, 6 }, { 0, 0 } };

        // Win a board
        for (int[] move : moves)
        {
            Cell turn = g1.getTurn();
            g1.setCell(move[0], move[1]);
            // Check that the mark was placed
            // (I'm checking that my move sequence is valid too)
            assertEquals(g1.getCell(move[0], move[1]), turn);
        }

        // Check that isplayable is set correctly
        // (since the last move directs to a won board, everything else should
        // be playable.)
        assertFalse(g1.getBoard(0, 0).getIsPlayable());
        assertTrue(g1.getBoard(1, 0).getIsPlayable());
        assertTrue(g1.getBoard(2, 0).getIsPlayable());
        assertTrue(g1.getBoard(0, 1).getIsPlayable());
        assertTrue(g1.getBoard(1, 1).getIsPlayable());
        assertTrue(g1.getBoard(2, 1).getIsPlayable());
        assertTrue(g1.getBoard(0, 2).getIsPlayable());
        assertTrue(g1.getBoard(1, 2).getIsPlayable());
        assertTrue(g1.getBoard(2, 2).getIsPlayable());

        // Try to play in that non-playable board
        assertEquals(g1.getCell(0, 1), Cell.EMPTY);
        Cell turn = g1.getTurn();
        g1.setCell(0, 1);
        // Turn doesn't change
        assertEquals(g1.getTurn(), turn);
        // Cell value doesn't change
        assertEquals(g1.getCell(0, 1), Cell.EMPTY);

    }


    // ----------------------------------------------------------
    /**
     * Tests the behavior of the grid after someone has won.
     */
    public void testPostVictoryBehavior()
    {
        // Nowhere should be playable

        // (hopefully) a series of 17 moves leading to grid-level victory for red
        String moves = "1133226600352661036414342577174708";

        //int row = Integer.parseInt(moves.charAt(0) + "");
        //int col = Integer.parseInt(moves.charAt(1) + "");

        //g1.setCell(row, col);


        // Make all the moves
        for (int i = 0;i<17;i++)
        {
            int row = Integer.parseInt(moves.charAt(2*i) + "");
            int col = Integer.parseInt(moves.charAt(1 + 2*i) + "");
            //int row = moves.charAt(2*i);
            //int col = moves.charAt(1+ (2*i));

            g1.setCell(row, col);
        }

        // not playable anywhere
        assertFalse(g1.getBoard(0, 0).getIsPlayable());
        assertFalse(g1.getBoard(1, 0).getIsPlayable());
        assertFalse(g1.getBoard(2, 0).getIsPlayable());
        assertFalse(g1.getBoard(0, 1).getIsPlayable());
        assertFalse(g1.getBoard(1, 1).getIsPlayable());
        assertFalse(g1.getBoard(2, 1).getIsPlayable());
        assertFalse(g1.getBoard(0, 2).getIsPlayable());
        assertFalse(g1.getBoard(1, 2).getIsPlayable());
        assertFalse(g1.getBoard(2, 2).getIsPlayable());

        // Try to play, just for kicks.
        assertEquals(g1.getCell(0, 1), Cell.EMPTY);
        Cell turn = g1.getTurn();
        g1.setCell(0, 1);
        // Turn doesn't change
        assertEquals(g1.getTurn(), turn);
        // Cell value doesn't change
        assertEquals(g1.getCell(0, 1), Cell.EMPTY);


        // Haswon should equal getTriple
        assertEquals(g1.getWhoHasWon(), g1.checkForTriple());
        assertEquals(g1.getWhoHasWon(), Cell.RED1);

        //
    }


    // ----------------------------------------------------------
    /**
     * Tests the checkForTriple method.
     */
    public void testCheckForTriple()
    {
        // A won board to use.
        Board b1 = new Board();
        b1.fromString("REEREEREE");

        // An empty board
        assertEquals(g1.checkForTriple(), Cell.EMPTY);

        // A single filled board
        g1.getBoard(2, 2).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.EMPTY);

        // Some partially filled rows
        g1.getBoard(2, 1).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.EMPTY);

        // Some partially filled cols
        g1.getBoard(1, 2).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.EMPTY);

        // Some partially filled diags
        g1.getBoard(1, 1).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.EMPTY);

        // Filled Row
        g1.getBoard(2, 0).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.RED1);

        // Filled Col
        g1.getBoard(2, 0).reset();
        g1.getBoard(0, 2).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.RED1);

        // Filled Diag
        g1.getBoard(2, 2).reset();
        g1.getBoard(2, 0).fromString(b1.toString());
        assertEquals(g1.checkForTriple(), Cell.RED1);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getBoard method.
     */
    public void testGetBoard()
    {
        g1.setCell(0, 0);
        g2.setCell(0, 0);
        g1.setCell(8, 8);
        g2.setCell(8, 8);

        assertEquals(g1.getBoard(0, 0), g2.getBoard(0, 0));
        assertEquals(g1.getBoard(2, 2), g2.getBoard(2, 2));

    }


    // ----------------------------------------------------------
    /**
     * Tests the getWhoHasWon method.
     */
    public void testGetWhoHasWon()
    {
        assertEquals(g1.getWhoHasWon(), Cell.EMPTY);

        // Make an empty board
        Board b = new Board();

        // Try a won grid.
        // Make a won board
        b.setCell(0, 0, Cell.RED1);
        b.setCell(0, 1, Cell.RED1);
        b.setCell(0, 2, Cell.RED1);
        // Put three in a row in the grid
        g1.getBoard(0, 0).fromString(b.toString());
        g1.getBoard(0, 1).fromString(b.toString());
        g1.getBoard(0, 2).fromString(b.toString());
        assertEquals(g1.checkForTriple(), Cell.RED1);
        // Check
        assertEquals(g1.getWhoHasWon(), Cell.RED1);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getTurn method.
     */
    public void testGetTurn()
    {
        assertEquals(g1.getTurn(), Cell.RED1);

        g1.setCell(0, 0);

        assertEquals(g1.getTurn(), Cell.BLUE2);

    }


    // ----------------------------------------------------------
    /**
     * Tests the setCell method.
     */
    public void testSetCell()
    {
        assertEquals(g1.getTurn(), Cell.RED1);

        g1.setCell(0, 0);

        // Should change the turn
        assertEquals(g1.getTurn(), Cell.BLUE2);

        // Should place a mark
        assertEquals(g1.getCell(0, 0), Cell.RED1);

        // Shouldn't change isPlayable
        assertTrue(g1.getBoard(0, 0).getIsPlayable());

        g1.setCell(0, 1);

        // Should change the turn
        assertEquals(g1.getTurn(), Cell.RED1);

        // Should place a mark
        assertEquals(g1.getCell(0, 1), Cell.BLUE2);

        // isPlayable should move
        assertFalse(g1.getBoard(0, 0).getIsPlayable());
        assertTrue(g1.getBoard(0, 1).getIsPlayable());

        // Suppose we try to push isplayable to a won board.
        // A won board to use.
        Board b1 = new Board();
        b1.fromString("REEREEREE");
        g1.getBoard(0, 2).fromString(b1.toString());
        g1.setCell(0, 5);
        // Everything else should be playable.
        assertFalse(g1.getBoard(0, 2).getIsPlayable());
        assertTrue(g1.getBoard(0, 0).getIsPlayable());
        assertTrue(g1.getBoard(0, 1).getIsPlayable());
        assertTrue(g1.getBoard(1, 0).getIsPlayable());
        assertTrue(g1.getBoard(1, 1).getIsPlayable());
        assertTrue(g1.getBoard(1, 2).getIsPlayable());
        assertTrue(g1.getBoard(2, 0).getIsPlayable());
        assertTrue(g1.getBoard(2, 1).getIsPlayable());
        assertTrue(g1.getBoard(2, 2).getIsPlayable());

    }


    // ----------------------------------------------------------
    /**
     * Tests the getCell method.
     */
    public void testGetCell()
    {
        assertEquals(g1.getCell(0, 0), Cell.EMPTY);

        g1.setCell(2, 2);

        // Should place a mark
        assertEquals(g1.getCell(2, 2), Cell.RED1);
        assertEquals(g1.getCell(2, 2), g1.getBoard(0, 0).getCell(2, 2));

        g1.setCell(6, 8);

        // Should place a mark
        assertEquals(g1.getCell(6, 8), Cell.BLUE2);
        assertEquals(g1.getCell(6, 8), g1.getBoard(2, 2).getCell(0, 2));
    }


    // ----------------------------------------------------------
    /**
     * Tests the reset method.
     */
    public void testReset()
    {
        // Call on an empty grid
        g1.reset();

        // Make an empty board
        Board b = new Board();

        // All cells should be empty.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(g1.getBoard(i, j).equals(b));
            }
        }

        // No one should be winning.
        assertEquals(g1.getWhoHasWon(), Cell.EMPTY);

        // Try a won grid.
        // Make a won board
        b.setCell(0, 0, Cell.RED1);
        b.setCell(0, 1, Cell.RED1);
        b.setCell(0, 2, Cell.RED1);
        // Put three in a row in the grid
        g1.getBoard(0, 0).fromString(b.toString());
        g1.getBoard(0, 1).fromString(b.toString());
        g1.getBoard(0, 2).fromString(b.toString());
        assertEquals(g1.checkForTriple(), Cell.RED1);
        // Check
        assertEquals(g1.getWhoHasWon(), Cell.RED1);

        g1.reset();
        b.reset();

        // All cells should be empty.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(g1.getBoard(i, j).equals(b));
            }
        }

        // No one should be winning.
        assertEquals(g1.getWhoHasWon(), Cell.EMPTY);

        // everything should be playable
        assertTrue(g1.getBoard(0, 0).getIsPlayable());
        assertTrue(g1.getBoard(1, 0).getIsPlayable());
        assertTrue(g1.getBoard(2, 0).getIsPlayable());
        assertTrue(g1.getBoard(0, 1).getIsPlayable());
        assertTrue(g1.getBoard(1, 1).getIsPlayable());
        assertTrue(g1.getBoard(2, 1).getIsPlayable());
        assertTrue(g1.getBoard(0, 2).getIsPlayable());
        assertTrue(g1.getBoard(1, 2).getIsPlayable());
        assertTrue(g1.getBoard(2, 2).getIsPlayable());
    }


    // ----------------------------------------------------------
    /**
     * Tests the equals method.
     */
    public void testEqualsObject()
    {
        // Null check
        assertFalse(g1.equals(null));
        // Type mismatch
        assertFalse(g1.equals("a"));

        // Reflexive
        assertTrue(g1.equals(g1));

        // In general, two empty boards
        assertTrue(g1.equals(g2));

        // Different contents
        g1.getBoard(0, 0).setCell(0, 0, Cell.RED1);
        assertFalse(g1.equals(g2));

        // Same Contents
        g2.getBoard(0, 0).setCell(0, 0, Cell.RED1);
        assertTrue(g1.equals(g2));

        // Some more complex cases
        g1.getBoard(0, 0).fromString("RRRBEEBBE");
        assertFalse(g1.equals(g2));
        g2.getBoard(0, 0).fromString("RRRBEEBBE");
        assertTrue(g1.equals(g2));
    }


    // ----------------------------------------------------------
    /**
     * Tests the fromString method.
     */
    public void testFromString()
    {
        // An empty board
        String e = "EEEEEEEEE";
        String E = e + e + e + e + e + e + e + e + e;
        assertEquals(g1.toString(), E);

        // An arbitrary complex but valid case
        String f = "RRRBEEBBE";
        g1.getBoard(0, 0).fromString(f);
        assertEquals(g1.toString(), f + e + e + e + e + e + e + e + e);

        // Wrong string length
        try
        {
            g1.fromString("E");
        }
        catch (Exception ex)
        {
            assertEquals(ex.getClass(),
                (new IllegalArgumentException()).getClass());
            assertEquals(ex.getMessage(), "Your state identifying " +
                "string is the wrong length. It must be 81 characters" +
                " long with no spaces.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests the toString method.
     */
    public void testToString()
    {
        // An empty board
        String e = "EEEEEEEEE";
        assertEquals(g1.toString(), e + e + e + e + e + e + e + e + e);

        // A more complex case
        String f = "RRRBEEBBE";
        g1.getBoard(0, 0).fromString(f);
        assertEquals(g1.toString(), f + e + e + e + e + e + e + e + e);

        g1.getBoard(2, 2).fromString(f);
        assertEquals(g1.toString(), f + e + e + e + e + e + e + e + f);
        g1.getBoard(1, 1).fromString(f);
        assertEquals(g1.toString(), f + e + e + e + f + e + e + e + f);
    }

}
