package cs2114.ultimatetic;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This tests the AIGrid
 *
 * @author Brian Clarke (golfboy1)
 * @author Samuel Bahr (sfbahr)
 * @author Charles Tenny (charten)
 * @version 2014.04.30
 */
public class AIGridTest
    extends TestCase
{
    /**
     * AIGrid object
     */
    private AIGrid AI;


    // ----------------------------------------------------------
    /**
     * Create a new AIGridTest object.
     */
    public AIGridTest()
    {
        // nothing
    }


    public void setUp()
    {
        AI = new AIGrid();
        AI.getBoard(2, 2).setIsPlayable(false);
        AI.getBoard(0, 1).setIsPlayable(false);
        AI.getBoard(0, 2).setIsPlayable(false);
        AI.getBoard(1, 0).setIsPlayable(false);
        AI.getBoard(1, 1).setIsPlayable(false);
        AI.getBoard(1, 2).setIsPlayable(false);
        AI.getBoard(2, 0).setIsPlayable(false);
        AI.getBoard(2, 1).setIsPlayable(false);
        AI.getBoard(2, 2).setIsPlayable(true);
    }


    // ----------------------------------------------------------
    /**
     * Test setCell(int row, int col)
     */
    public void testSetCell()
    {
        assertTrue(AI.setCell(2, 2));

    }


    // ----------------------------------------------------------
    /**
     * TestsundoMove()
     */
    public void testUndoMove()
    {
        assertFalse(AI.undoMove());
        AI.setCell(2, 2);
        assertTrue(AI.undoMove());
    }


    // ----------------------------------------------------------
    /**
     * tests whichIsPlayable()
     */
    public void testWhichIsPlayable()
    {
        AI.setCell(2, 2);
        AI.whichIsPlayable();
        assertTrue(AI.getBoard(1, 1).getIsPlayable());
        assertFalse(AI.getBoard(2, 2).getIsPlayable());
    }


    // ----------------------------------------------------------
    /**
     * Tests makeMove()
     */
    public void testMakeMove()
    {
        AI.setTurn(Cell.RED1);
        AI.whichIsPlayable();
        assertTrue(AI.makeMove());
        AI.setTurn(Cell.BLUE2);
        AI.whichIsPlayable();
        assertTrue(AI.makeMove());
    }


    // ----------------------------------------------------------
    /**
     * Tests checkForWin()
     */
    public void testCheckForWin()
    {
        // rows
        String grid = "BBEEEEEEE";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid1 = "BEBEEEEEE";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid2 = "EBBEEEEEE";
        AI.getBoard(2, 2).fromString(grid2);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid3 = "EEEBBEEEE";
        AI.getBoard(2, 2).fromString(grid3);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid4 = "EEEBEBEEE";
        AI.getBoard(2, 2).fromString(grid4);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid5 = "EEEEBBEEE";
        AI.getBoard(2, 2).fromString(grid5);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid6 = "EEEEEEBBE";
        AI.getBoard(2, 2).fromString(grid6);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid7 = "EEEEEEBEB";
        AI.getBoard(2, 2).fromString(grid7);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid8 = "EEEEEEEBB";
        AI.getBoard(2, 2).fromString(grid8);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        // columns

        String grid9 = "BEEBEEEEE";
        AI.getBoard(2, 2).fromString(grid9);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid10 = "BEEEEEBEE";
        AI.getBoard(2, 2).fromString(grid10);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid11 = "EEEBEEBEE";
        AI.getBoard(2, 2).fromString(grid11);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid12 = "EBEEBEEEE";
        AI.getBoard(2, 2).fromString(grid12);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid13 = "EBEEEEEBE";
        AI.getBoard(2, 2).fromString(grid13);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid14 = "EEEEBEEBE";
        AI.getBoard(2, 2).fromString(grid14);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid15 = "EEBEEBEEE";
        AI.getBoard(2, 2).fromString(grid15);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid16 = "EEBEEEEEB";
        AI.getBoard(2, 2).fromString(grid16);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid17 = "EEEEEBEEB";
        AI.getBoard(2, 2).fromString(grid17);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        // diagonals

        String grid18 = "BEEEBEEEE";
        AI.getBoard(2, 2).fromString(grid18);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid19 = "BEEEEEEEB";
        AI.getBoard(2, 2).fromString(grid19);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid20 = "EEEEBEEEB";
        AI.getBoard(2, 2).fromString(grid20);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid21 = "EEBEBEEEE";
        AI.getBoard(2, 2).fromString(grid21);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid22 = "EEBEEEBEE";
        AI.getBoard(2, 2).fromString(grid22);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());

        String grid23 = "EEEEBEBEE";
        AI.getBoard(2, 2).fromString(grid23);
        AI.whichIsPlayable();
        assertTrue(AI.checkForWin());
    }


    // ----------------------------------------------------------
    /**
     * Tests blockLoss()
     */
    public void testBlockLoss()
    {

        // rows
        String grid = "RREEEEEEE";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid1 = "REREEEEEE";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid2 = "ERREEEEEE";
        AI.getBoard(2, 2).fromString(grid2);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid3 = "EEERREEEE";
        AI.getBoard(2, 2).fromString(grid3);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid4 = "EEEREREEE";
        AI.getBoard(2, 2).fromString(grid4);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid5 = "EEEERREEE";
        AI.getBoard(2, 2).fromString(grid5);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid6 = "EEEEEERRE";
        AI.getBoard(2, 2).fromString(grid6);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid7 = "EEEEEERER";
        AI.getBoard(2, 2).fromString(grid7);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid8 = "EEEEEEERR";
        AI.getBoard(2, 2).fromString(grid8);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        // columns

        String grid9 = "REEREEEEE";
        AI.getBoard(2, 2).fromString(grid9);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid10 = "REEEEEREE";
        AI.getBoard(2, 2).fromString(grid10);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid11 = "EEEREEREE";
        AI.getBoard(2, 2).fromString(grid11);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid12 = "EREEREEEE";
        AI.getBoard(2, 2).fromString(grid12);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid13 = "EREEEEERE";
        AI.getBoard(2, 2).fromString(grid13);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid14 = "EEEEREERE";
        AI.getBoard(2, 2).fromString(grid14);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid15 = "EEREEREEE";
        AI.getBoard(2, 2).fromString(grid15);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid16 = "EEREEEEER";
        AI.getBoard(2, 2).fromString(grid16);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid17 = "EEEEEREER";
        AI.getBoard(2, 2).fromString(grid17);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        // diagonals

        String grid18 = "REEEREEEE";
        AI.getBoard(2, 2).fromString(grid18);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid19 = "REEEEEEER";
        AI.getBoard(2, 2).fromString(grid19);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid20 = "EEEEREEER";
        AI.getBoard(2, 2).fromString(grid20);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid21 = "EEREREEEE";
        AI.getBoard(2, 2).fromString(grid21);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid22 = "EEREEEREE";
        AI.getBoard(2, 2).fromString(grid22);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());

        String grid23 = "EEEEREREE";
        AI.getBoard(2, 2).fromString(grid23);
        AI.whichIsPlayable();
        assertTrue(AI.blockLoss());
    }


    // ----------------------------------------------------------
    /**
     * Tests createFork()
     */
    public void testCreateFork()
    {
        String grid = "BRBEEEEER";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid1 = "BRBEEEREE";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid2 = "BEEREEBER";
        AI.getBoard(2, 2).fromString(grid2);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid3 = "BERREEBEE";
        AI.getBoard(2, 2).fromString(grid3);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid4 = "BEEEREREB";
        AI.getBoard(2, 2).fromString(grid4);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid5 = "BEREREEEB";
        AI.getBoard(2, 2).fromString(grid5);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid6 = "EEBEREBER";
        AI.getBoard(2, 2).fromString(grid6);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid7 = "REBEREBEE";
        AI.getBoard(2, 2).fromString(grid7);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid8 = "EEBEERREB";
        AI.getBoard(2, 2).fromString(grid8);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid9 = "REBEEREEB";
        AI.getBoard(2, 2).fromString(grid9);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid10 = "EEREEEBRB";
        AI.getBoard(2, 2).fromString(grid10);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid11 = "REEEEEBRB";
        AI.getBoard(2, 2).fromString(grid11);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid12 = "BEEEBEEER";
        AI.getBoard(2, 2).fromString(grid12);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid13 = "EEBEBEREE";
        AI.getBoard(2, 2).fromString(grid13);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid14 = "EEREBEBEE";
        AI.getBoard(2, 2).fromString(grid14);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid15 = "REEEBEEEB";
        AI.getBoard(2, 2).fromString(grid15);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid16 = "BRBEEEEEE";
        AI.getBoard(2, 2).fromString(grid16);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid17 = "BEEREEBEE";
        AI.getBoard(2, 2).fromString(grid17);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid18 = "EEBEEREEB";
        AI.getBoard(2, 2).fromString(grid18);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid19 = "EEEEEEBRB";
        AI.getBoard(2, 2).fromString(grid19);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid20 = "EBEBREEEE";
        AI.getBoard(2, 2).fromString(grid20);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid21 = "RBEBEEEEE";
        AI.getBoard(2, 2).fromString(grid21);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid22 = "EBEERBEEE";
        AI.getBoard(2, 2).fromString(grid22);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid23 = "EBREEBEEE";
        AI.getBoard(2, 2).fromString(grid23);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid24 = "EEEBEERBE";
        AI.getBoard(2, 2).fromString(grid24);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid25 = "EEEBREEBE";
        AI.getBoard(2, 2).fromString(grid25);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid26 = "EEEEEBEBR";
        AI.getBoard(2, 2).fromString(grid26);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid27 = "EEEERBEBE";
        AI.getBoard(2, 2).fromString(grid27);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid28 = "EEEBBREEE";
        AI.getBoard(2, 2).fromString(grid28);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid29 = "EBEEBEERE";
        AI.getBoard(2, 2).fromString(grid29);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid30 = "EEERBBEEE";
        AI.getBoard(2, 2).fromString(grid30);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid31 = "EREEBEEBE";
        AI.getBoard(2, 2).fromString(grid31);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid32 = "EBEBEEEEE";
        AI.getBoard(2, 2).fromString(grid32);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid33 = "EBEEEBEEE";
        AI.getBoard(2, 2).fromString(grid33);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid34 = "EEEBEEEBE";
        AI.getBoard(2, 2).fromString(grid34);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid35 = "EEEEEBEBE";
        AI.getBoard(2, 2).fromString(grid35);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid36 = "EEBBEEEEE";
        AI.getBoard(2, 2).fromString(grid36);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid37 = "EEEBEEEEB";
        AI.getBoard(2, 2).fromString(grid37);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid38 = "BEEEEBEEE";
        AI.getBoard(2, 2).fromString(grid38);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());

        String grid39 = "EEEEEBBEE";
        AI.getBoard(2, 2).fromString(grid39);
        AI.whichIsPlayable();
        assertTrue(AI.createFork());
    }


    // ----------------------------------------------------------
    /**
     * Tests blockFork()
     */
    public void testBlockFork()
    {
        String grid = "RBREEEEEB";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid1 = "RBREEEBEE";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid2 = "REEBEEREB";
        AI.getBoard(2, 2).fromString(grid2);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid3 = "REBBEEREE";
        AI.getBoard(2, 2).fromString(grid3);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid4 = "REEEBEBER";
        AI.getBoard(2, 2).fromString(grid4);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid5 = "REBEBEEER";
        AI.getBoard(2, 2).fromString(grid5);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid6 = "EEREBEREB";
        AI.getBoard(2, 2).fromString(grid6);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid7 = "BEREBEREE";
        AI.getBoard(2, 2).fromString(grid7);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid8 = "EEREEBBER";
        AI.getBoard(2, 2).fromString(grid8);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid9 = "BEREEBEER";
        AI.getBoard(2, 2).fromString(grid9);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid10 = "EEBEEERBR";
        AI.getBoard(2, 2).fromString(grid10);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid11 = "BEEEEERBR";
        AI.getBoard(2, 2).fromString(grid11);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid12 = "REEEREEEB";
        AI.getBoard(2, 2).fromString(grid12);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid13 = "EEREREBEE";
        AI.getBoard(2, 2).fromString(grid13);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid14 = "EEBEREREE";
        AI.getBoard(2, 2).fromString(grid14);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid15 = "BEEEREEER";
        AI.getBoard(2, 2).fromString(grid15);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid16 = "RBREEEEEE";
        AI.getBoard(2, 2).fromString(grid16);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid17 = "REEBEEREE";
        AI.getBoard(2, 2).fromString(grid17);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid18 = "EEREEBEER";
        AI.getBoard(2, 2).fromString(grid18);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid19 = "EEEEEERBR";
        AI.getBoard(2, 2).fromString(grid19);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid20 = "ERERBEEEE";
        AI.getBoard(2, 2).fromString(grid20);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid21 = "BREREEEEE";
        AI.getBoard(2, 2).fromString(grid21);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid22 = "EREEBREEE";
        AI.getBoard(2, 2).fromString(grid22);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid23 = "ERBEEREEE";
        AI.getBoard(2, 2).fromString(grid23);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid24 = "EEEREEBRE";
        AI.getBoard(2, 2).fromString(grid24);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid25 = "EEERBEERE";
        AI.getBoard(2, 2).fromString(grid25);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid26 = "EEEEERERB";
        AI.getBoard(2, 2).fromString(grid26);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid27 = "EEEEBRERE";
        AI.getBoard(2, 2).fromString(grid27);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid28 = "EEERRBEEE";
        AI.getBoard(2, 2).fromString(grid28);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid29 = "EREEREEBE";
        AI.getBoard(2, 2).fromString(grid29);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid30 = "EEEBRREEE";
        AI.getBoard(2, 2).fromString(grid30);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid31 = "EBEEREERE";
        AI.getBoard(2, 2).fromString(grid31);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid32 = "EREREEEEE";
        AI.getBoard(2, 2).fromString(grid32);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid33 = "EREEEREEE";
        AI.getBoard(2, 2).fromString(grid33);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid34 = "EEEREEERE";
        AI.getBoard(2, 2).fromString(grid34);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid35 = "EEEEERERE";
        AI.getBoard(2, 2).fromString(grid35);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid36 = "EERREEEEE";
        AI.getBoard(2, 2).fromString(grid36);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid37 = "EEEREEEER";
        AI.getBoard(2, 2).fromString(grid37);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid38 = "REEEEREEE";
        AI.getBoard(2, 2).fromString(grid38);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());

        String grid39 = "EEEEERREE";
        AI.getBoard(2, 2).fromString(grid39);
        AI.whichIsPlayable();
        assertTrue(AI.blockFork());
    }


    // ----------------------------------------------------------
    /**
     * Tests playCenter
     */
    public void testPlayCenter()
    {
        String grid = "EEEEEEEEE";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.playCenter());

        String grid1 = "EEEEBEEEE";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertFalse(AI.playCenter());
    }


    // ----------------------------------------------------------
    /**
     * Tests playOppositeCorner()
     */
    public void testPlayOppositeCorner()
    {
        String grid = "EEEEBEEER";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.playOppositeCorner());

        String grid1 = "EEEEBEREE";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertTrue(AI.playOppositeCorner());

        String grid2 = "REEEBEEEE";
        AI.getBoard(2, 2).fromString(grid2);
        AI.whichIsPlayable();
        assertTrue(AI.playOppositeCorner());

        String grid3 = "EEREBEEEE";
        AI.getBoard(2, 2).fromString(grid3);
        AI.whichIsPlayable();
        assertTrue(AI.playOppositeCorner());
    }

    // ----------------------------------------------------------
    /**
     * Tests playEmptyCorner()
     */
    public void testPlayEmptyCorner()
    {
        String grid = "BEBEEEBEE";
        AI.getBoard(2, 2).fromString(grid);
        AI.whichIsPlayable();
        assertTrue(AI.playEmptyCorner());

        String grid1 = "BEBEEEEEB";
        AI.getBoard(2, 2).fromString(grid1);
        AI.whichIsPlayable();
        assertTrue(AI.playEmptyCorner());

        String grid2 = "BEEEEEBEB";
        AI.getBoard(2, 2).fromString(grid2);
        AI.whichIsPlayable();
        assertTrue(AI.playEmptyCorner());

        String grid3 = "EEBEEEBEB";
        AI.getBoard(2, 2).fromString(grid3);
        AI.whichIsPlayable();
        assertTrue(AI.playEmptyCorner());
    }

}
