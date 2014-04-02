package cs2114.ultimatetic;

import sofia.graphics.Color;
import sofia.app.ShapeScreen;
import sofia.graphics.*;
import sofia.app.OptionsMenu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

/**
 * The main game screen where Ultimate Tic-Tac-Toe goes down.
 *
 * @author Samuel Bahr (sfbahr)
 * @version 03.27.2014
 */
@OptionsMenu("tacgamescreen")
public class TacGameScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    private Grid        grid;                                 // the grid of
// nine tac boards
    private final int   gridPad        = 20;                  // The padding on
// the grid
    private final int   boardPad       = 15;
    private final int   gridLineWidth  = 10;
    private final int   boardLineWidth = 5;
    private final Color p1Color        = Color.cornflowerBlue;
    private final Color p2Color        = Color.indianRed;
    private float       gridSize;
    private float       boardSize;
    private float       cellSize;


    // ~ Methods ...............................................................

    /**
     * What is run when the screen is first started. The grid and its shapes are
     * created along with each board and its shapes. The objects are created in
     * order of ascending layer, so the individual cells are the farthest back
     * and the grid lines are the farthest forward.
     */
    public void initialize()
    {
        grid = new Grid();
        gridSize = Math.min(getWidth(), getHeight()) - 2 * gridPad;
        boardSize = gridSize / 3 - 2 * boardPad;
        cellSize = boardSize / 3;

        // Create the 9 cells for each board
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int a = 0; a < 3; a++)
                {
                    float left =
                        gridPad + gridSize / 3 * i + boardPad + boardSize / 3
                            * a;
                    for (int b = 0; b < 3; b++)
                    {
                        float top =
                            gridPad + gridSize / 3 * j + boardPad + boardSize
                                / 3 * b;
                        RectangleShape cell =
                            new RectangleShape(
                                left,
                                top,
                                left + boardSize / 3,
                                top + boardSize / 3);
                        // Temporary code just to show how the colors look
                        if ((a + b) % 3 == 1)
                        {
                            cell.setFillColor(p1Color);
                        }
                        else if ((a + b) % 3 == 2)
                        {
                            cell.setFillColor(p2Color);
                        }
                        cell.setAlpha(255); // Opacity (0-255)
                        add(cell);
                    }
                }
            }
        }

        // Create each boards' lines
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                float xHorzLeft = gridPad + gridSize / 3 * i + boardPad;
                float xHorzRight = xHorzLeft + boardSize;
                for (int linNum = 1; linNum <= 2; linNum++)
                {
                    float xVert =
                        gridPad + gridSize / 3 * i + boardPad + boardSize / 3
                            * linNum;
                    float yVertTop = gridPad + gridSize / 3 * j + boardPad;
                    float yVertBottom = yVertTop + boardSize;
                    LineShape verticalLine =
                        new LineShape(xVert, yVertTop, xVert, yVertBottom);

                    float yHorz =
                        gridPad + gridSize / 3 * j + boardPad + boardSize / 3
                            * linNum;
                    LineShape horizontalLine =
                        new LineShape(xHorzLeft, yHorz, xHorzRight, yHorz);

                    verticalLine.setColor(Color.black);
                    horizontalLine.setColor(Color.black);
                    verticalLine.setStrokeWidth(boardLineWidth);
                    horizontalLine.setStrokeWidth(boardLineWidth);
                    add(verticalLine);
                    add(horizontalLine);
                }
            }
        }

        // Create the 9 boards for the grid
        for (int i = 0; i < 3; i++)
        {
            float left = gridPad + gridSize / 3 * i;
            for (int j = 0; j < 3; j++)
            {
                float top = gridPad + gridSize / 3 * j;
                RectangleShape board =
                    new RectangleShape(left, top, left + gridSize / 3, top
                        + gridSize / 3);
                // Temporary code just to show how the colors look
                if ((i + j) % 3 == 1)
                {
                    board.setFillColor(p1Color);
                }
                else if ((i + j) % 3 == 2)
                {
                    board.setFillColor(p2Color);
                }
                board.setAlpha(150); // Opacity (0-255)
                add(board);
            }
        }

        // Create the Grid's lines
        for (int i = 1; i <= 2; i++)
        {
            LineShape verticalLine =
                new LineShape(gridPad + gridSize / 3 * i, gridPad, gridPad
                    + gridSize / 3 * i, gridPad + gridSize);
            LineShape horizontalLine =
                new LineShape(gridPad, gridPad + gridSize / 3 * i, gridPad
                    + gridSize, gridPad + gridSize / 3 * i);
            verticalLine.setColor(Color.black);
            horizontalLine.setColor(Color.black);
            verticalLine.setStrokeWidth(gridLineWidth);
            horizontalLine.setStrokeWidth(gridLineWidth);
            add(verticalLine);
            add(horizontalLine);
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
     *
     * @return The grid.
     */
    public Grid getGrid()
    {
        return grid;
    }
}
