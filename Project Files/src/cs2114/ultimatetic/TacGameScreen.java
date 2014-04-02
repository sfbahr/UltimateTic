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
    private Grid grid; //the grid of nine tac boards
    private final int gridPad = 20; //The padding on the grid
    private final int boardPad = 15;
    private final int gridLineWidth = 10;
    private final int boardLineWidth = 5;
    private float gridSize;
    private float boardSize;
    private float cellSize;

    // ~ Methods ...............................................................

    /**
     * What is run when the screen is first started.
     */
    public void initialize()
    {
        grid = new Grid();
        gridSize = Math.min(getWidth(), getHeight()) - 2 * gridPad;
        boardSize = gridSize / 3 - 2 * boardPad;
        cellSize = boardSize / 3;

      //Create the Grid's lines
        for (int i = 1; i <= 2; i++)
        {
            LineShape verticalLine = new LineShape(gridPad + gridSize / 3 * i,
                gridPad, gridPad + gridSize / 3 * i, gridPad + gridSize);
            LineShape horizontalLine = new LineShape(gridPad,
                gridPad + gridSize / 3 * i, gridPad + gridSize,
                gridPad + gridSize / 3 * i);
            verticalLine.setColor(Color.black);
            horizontalLine.setColor(Color.black);
            verticalLine.setStrokeWidth(gridLineWidth);
            horizontalLine.setStrokeWidth(gridLineWidth);
            add(verticalLine);
            add(horizontalLine);
        }

        //Create each boards' lines
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int linNum = 1; linNum <= 2; linNum++)
                {
                    float xVert = gridPad + gridSize / 3 * i + boardPad +
                        boardSize / 3 * linNum;
                    float yVertTop = gridPad + gridSize / 3 * j + boardPad;
                    float yVertBottom = yVertTop + boardSize;
                    LineShape verticalLine = new LineShape(xVert, yVertTop,
                        xVert, yVertBottom);

                    float yHorz = gridPad + gridSize / 3 * j + boardPad +
                        boardSize / 3 * linNum;
                    float xHorzLeft = gridPad + gridSize / 3 * i + boardPad;
                    float xHorzRight = xHorzLeft + boardSize;
                    LineShape horizontalLine = new LineShape(xHorzLeft, yHorz,
                        xHorzRight, yHorz);

                    verticalLine.setColor(Color.black);
                    horizontalLine.setColor(Color.black);
                    verticalLine.setStrokeWidth(boardLineWidth);
                    horizontalLine.setStrokeWidth(boardLineWidth);
                    add(verticalLine);
                    add(horizontalLine);
                }
            }
        }
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
