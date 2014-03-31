package cs2114.ultimatetic;

import sofia.app.ShapeScreen;
import sofia.graphics.*;
import sofia.app.OptionsMenu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

/**
 *  The main game screen where Ultimate Tic-Tac-Toe goes down.
 *
 *  @author Samuel Bahr (sfbahr)
 *  @version 03.27.2014
 */
@OptionsMenu("tacgamescreen")
public class TacGameScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    private Grid grid; //the array of nine tac boards

    // ~ Methods ...............................................................

    /**
     * What is run when the screen is first started.
     */
    public void initialize()
    {


    }

    /**
     * The restart/refresh button was pressed, reset the board after a
     * confirmation dialog.
     */
    public void action_refreshClicked()
    {

    }

    /**
     * Return the grid, which is the array of nine tac boards.
     * @return The grid.
     */
    public Grid getGrid()
    {
        return grid;
    }
}
