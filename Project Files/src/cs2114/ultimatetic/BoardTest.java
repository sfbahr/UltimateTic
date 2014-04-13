package cs2114.ultimatetic;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 *  Tests the Board class.
 *
 *  @author Charles Tenney (charten)
 *  @version 2014.04.13
 */
public class BoardTest
    extends TestCase
{
    // Fields
    private Board b1;
    private Board b2;

    // Set Up
    public void setUp()
        throws Exception
    {
        b1 = new Board();
        b2 = new Board();
    }

    // Test Methods
    // ----------------------------------------------------------
    /**
     * Tests the getWhoHasWon method.
     */
    public void testGetWhoHasWon()
    {
        assertTrue(b1.getWhoHasWon() == Cell.EMPTY);
    }

    // ----------------------------------------------------------
    /**
     * Tests the checkForTriple method with a single color.
     */
    public void testCheckForTripleSingleColor()
    {
        // Note that fromString calls the reset method internally.

        // An empty board
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // A single filled cell
        b1.reset();
        b1.fromString("REEEEEEEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Some partially filled rows
        b1.fromString("RREEEEEEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("EEEEEERER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("RRE"+"EEE"+"RER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Some partially filled columns;
        b1.fromString("REEREEEEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("EEREEEEER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("RER"+"REE"+"EER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Partial Diagonals
        b1.fromString("REE"+"ERE"+"EEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("REE"+"EEE"+"EER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("RER"+"ERE"+"EEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Filled columns
        b1.fromString("REEREEREE");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("EEREEREER");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("RERRERRER");
        assertEquals(b1.checkForTriple(), Cell.RED1);

        // Filled Rows
        b1.fromString("RRREEEEEE");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("EEEEEERRR");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("RRREEERRR");
        assertEquals(b1.checkForTriple(), Cell.RED1);

        // Filled Diagonals
        b1.fromString("REE"+"ERE"+"EER");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("EER"+"ERE"+"REE");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("RER"+"ERE"+"RER");
        assertEquals(b1.checkForTriple(), Cell.RED1);

    }

    // ----------------------------------------------------------
    /**
     * Re-tests the checkForTriple method with both colors.
     */
    public void testCheckForTripleMultiColor()
    {
        // Note that fromString calls the reset method internally.

        // An empty board
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // A single filled cell
        b1.reset();
        b1.fromString("REEEEBEEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Some partially filled rows
        b1.fromString("RREEBBEEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("BEBEEERER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("RRB"+"BEB"+"RER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Some partially filled columns;
        b1.fromString("RBERBEEEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("EBREEEEBR");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("RBR"+"REB"+"EBR");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Partial Diagonals
        b1.fromString("REE"+"ERE"+"EEB");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("REB"+"EEE"+"BER");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);
        b1.fromString("RER"+"ERE"+"EEE");
        assertEquals(b1.checkForTriple(), Cell.EMPTY);

        // Filled columns
        b1.fromString("RBBREERBB");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("BBRBBREER");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("RBRRBRRER");
        assertEquals(b1.checkForTriple(), Cell.RED1);

        // Filled Rows
        b1.fromString("RRREEBBBE");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("EEBBBERRR");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("RRRBBERRR");
        assertEquals(b1.checkForTriple(), Cell.RED1);

        // Filled Diagonals
        b1.fromString("REB"+"ERE"+"BER");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("BBR"+"ERE"+"RBB");
        assertEquals(b1.checkForTriple(), Cell.RED1);
        b1.fromString("RBR"+"BRB"+"RBR");
        assertEquals(b1.checkForTriple(), Cell.RED1);

    }

    // ----------------------------------------------------------
    /**
     * Tests the setCell method.
     */
    public void testSetCell()
    {
        // Test setting a cell under ideal conditions
        assertTrue(b1.getCell(0, 0) == Cell.EMPTY);
        assertTrue(b1.setCell(0, 0, Cell.RED1));
        assertTrue(b1.getCell(0, 0) == Cell.RED1 );

        // If a cell is out of bounds, should fail
        assertFalse(b1.setCell(-1, 0, Cell.RED1));
        assertFalse(b1.setCell(0, -1, Cell.RED1));
        assertFalse(b1.setCell(3, 0, Cell.RED1));
        assertFalse(b1.setCell(0, 3, Cell.RED1));

        // If a cell is already occupied, should fail
        assertFalse(b1.setCell(0, 0, Cell.RED1));

        // If the board is won, should fail
        assertTrue(b1.setCell(0, 1, Cell.RED1));
        assertTrue(b1.setCell(0, 2, Cell.RED1));
        assertEquals(b1.getWhoHasWon(), Cell.RED1);
        assertFalse(b1.setCell(1, 1, Cell.RED1)); // Unoccupied
        assertFalse(b1.setCell(0, 0, Cell.RED1)); // Occupied




    }

    // ----------------------------------------------------------
    /**
     * Tests the getCell method.
     */
    public void testGetCell()
    {
        // All cells should be empty to start.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(b1.getCell(i, j) == Cell.EMPTY);
            }
        }


    }

    // ----------------------------------------------------------
    /**
     * Tests the reset method.
     */
    public void testReset()
    {
        // Call on an empty board
        b1.reset();

        // All cells should be empty.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(b1.getCell(i, j) == Cell.EMPTY);
            }
        }

        // No one should be winning.
        assertEquals(b1.getWhoHasWon(), Cell.EMPTY);


        // Try a won board.
        assertTrue(b1.setCell(0, 0, Cell.RED1));
        assertTrue(b1.setCell(0, 1, Cell.RED1));
        assertTrue(b1.setCell(0, 2, Cell.RED1));
        assertEquals(b1.getWhoHasWon(), Cell.RED1);

        b1.reset();

        // All cells should be empty.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                assertTrue(b1.getCell(i, j) == Cell.EMPTY);
            }
        }

        // No one should be winning.
        assertEquals(b1.getWhoHasWon(), Cell.EMPTY);


    }

    // ----------------------------------------------------------
    /**
     * Tests the equals method.
     */
    public void testEqualsObject()
    {
        // Null check
        assertFalse(b1.equals(null));
        // Type mismatch
        assertFalse(b1.equals("a"));

        // Reflexive
        assertTrue(b1.equals(b1));

        // In general, two empty boards
        assertTrue(b1.equals(b2));

        // Different contents
        b1.setCell(0, 0, Cell.RED1);
        assertFalse(b1.equals(b2));

        // Same Contents
        b2.setCell(0, 0, Cell.RED1);
        assertTrue(b1.equals(b2));

        // Some more complex cases
        b1.fromString("RRRBEEBBE");
        assertFalse(b1.equals(b2));
        b2.fromString("RRRBEEBBE");
        assertTrue(b1.equals(b2));

    }

    // ----------------------------------------------------------
    /**
     * Tests the toString method.
     */
    public void testToString()
    {
        // An empty board
        assertEquals(b1.toString(), "EEEEEEEEE");

        // A more complex case
        b1.fromString("RRRBEEBBE");
        assertEquals(b1.toString(), "RRRBEEBBE");
    }

    // ----------------------------------------------------------
    /**
     * Tests the fromString method.
     */
    public void testFromString()
    {
        // An empty board
        assertEquals(b1.toString(), "EEEEEEEEE");
        b1.fromString("EEEEEEEEE");
        assertEquals(b1.toString(), "EEEEEEEEE");

        // An arbitrary complex but valid case
        b1.fromString("RRRBEEBBE");
        assertEquals(b1.toString(), "RRRBEEBBE");

        // Wrong string length
        try { b1.fromString("E"); }
        catch (Exception e)
        {
        assertEquals(e.getClass(),
            (new IllegalArgumentException()).getClass());
        assertEquals(e.getMessage(), "Your state identifying " +
                "string is the wrong length. It must be nine characters" +
                " long with no spaces.");
        }

        // Invalid cell characters
        try { b1.fromString("XXXXXXXXX"); }
        catch (Exception e)
        {
        assertEquals(e.getClass(),
            (new IllegalArgumentException()).getClass());
        assertEquals(e.getMessage(), "Your string has a " +
            "value in it that doesn't match one of our cell " +
            "types.");
        }



    }

}
