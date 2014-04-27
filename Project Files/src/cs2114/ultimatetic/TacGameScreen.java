package cs2114.ultimatetic;

import android.graphics.RectF;
import android.app.DialogFragment;
import sofia.graphics.Color;

import sofia.app.ShapeScreen;
import sofia.graphics.*;
import sofia.app.OptionsMenu;
import android.widget.*;

/**
 * The main game screen where Ultimate Tic-Tac-Toe goes down.
 *
 * @author Samuel Bahr (sfbahr)
 * @author Brian Clarke (golfboy1)
 * @author Charles Tenney (charten)
 * @version 2014.04.16
 */
@OptionsMenu("tacgamescreen")
public class TacGameScreen
    extends ShapeScreen
    implements RestartGameDialogFragment.RestartGameDialogListener
{
    // ~ Fields ................................................................
    private Grid               grid;                                  // the
// grid of nine tac boards
    private final int          gridPad         = 20;                  // The
// padding on the grid
    private final int          boardPad        = 15;
    private final int          gridLineWidth   = 10;
    private final int          boardLineWidth  = 5;
    private final Color        p1Color         = Color.indianRed;
    private final Color        p2Color         = Color.cornflowerBlue;
    private final Color        invalidColor    = Color.gray;
    private final int          boardOpacity    = 120;
    private final int          cellOpacity     = 225;
    private float              gridSize;
    private float              boardSize;
    private float              cellSize;
    private RectangleShape[][] cells;
    private RectangleShape[][] boards;
    private RectangleShape     guiGrid;
    private RectangleShape     turnInd;                               // turn
// indicator
    private float              xDown;
    private float              yDown;


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

        cells = new RectangleShape[9][9];
        boards = new RectangleShape[3][3];

        // Create the turn indicator
        if (getWidth() < getHeight())
        {
            turnInd =
                new RectangleShape(
                    0,
                    gridSize + 2 * gridPad,
                    getWidth(),
                    getHeight());
        }
        else
        {
            turnInd =
                new RectangleShape(
                    gridSize + 2 * gridPad,
                    0,
                    getWidth(),
                    getHeight());
        }
        turnInd.setImage("player_one");
        turnInd.setAlpha(150);
        add(turnInd);

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

                        cell.setFillColor(invalidColor);
                        cell.setAlpha(0); // Opacity (0-255)
                        add(cell);
                        cells[i * 3 + a][j * 3 + b] = cell;
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

                board.setFillColor(invalidColor);
                board.setAlpha(0); // Opacity (0-255);
                add(board);
                boards[i][j] = board;
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

        // Create a shape covering the whole grid
        guiGrid =
            new RectangleShape(gridPad, gridPad, gridSize + gridPad, gridSize
                + gridPad);
        guiGrid.setFillColor(invalidColor);
        guiGrid.setAlpha(0);
        add(guiGrid);
    }


    /**
     * Handles touch events by storing where the user pressed down.
     *
     * @param x
     *            Where the user touched on the x-axis
     * @param y
     *            Where the user touched on the y-axis
     */
    public void onTouchDown(float x, float y)
    {
        xDown = x;
        yDown = y;
    }


    /**
     * Handles touch events by setting a cell and then updating the GUI if the
     * user tapped in the cell.
     *
     * @param x
     *            Where the user let go on the screen in the x-axis
     * @param y
     *            Where the user let go on the screen in the y-axis
     */
    public void onTouchUp(float x, float y)
    {
        if (Math.abs(xDown - x) < 10 && Math.abs(yDown - y) < 10)
        {
            int gridX = gridLocation(x);
            int gridY = gridLocation(y);
            // Animates the cell entering the new cell if it was a valid touch
            if (grid.setCell(gridY, gridX))
            {
                Cell cell = grid.getCell(gridY, gridX);
                Color cellColor = (cell == Cell.RED1) ? p1Color : p2Color;
                zoom(cells[gridX][gridY], cellColor, cellOpacity, true);
            }
            this.reflectModel();
        }
    }


    /**
     * Updates the GUI to reflect the state of the model.
     */
    public void reflectModel()
    {
        // Check if someone won
        Cell whoHasWon = grid.getWhoHasWon();
        if (whoHasWon == Cell.RED1)
        {
            guiGrid.setFillColor(p1Color);
            guiGrid.setAlpha(75);
            turnInd.setImage("player_one_won");
            /*
             * Toast.makeText(getApplicationContext(), "Player One has won",
             * Toast.LENGTH_SHORT).show();
             */
        }
        else if (whoHasWon == Cell.BLUE2)
        {
            guiGrid.setFillColor(p2Color);
            guiGrid.setAlpha(75);
            turnInd.setImage("player_two_won");
            /*
             * Toast.makeText(getApplicationContext(), "Player Two has won",
             * Toast.LENGTH_SHORT).show();
             */
        }
        else
        {
            guiGrid.setAlpha(0);
        }

        // Get whose turn it is
        Cell turn = grid.getTurn();
        if (whoHasWon == Cell.EMPTY)
        {
            if (turn == Cell.RED1)
            {
                turnInd.setImage("player_one");
            }
            else
            {
                turnInd.setImage("player_two");
            }
        }

        // Get the boards
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                /*
                 * The screen does the 2d array (x, y) whereas the model does it
                 * (row, column), so the coordinates are switched on the call to
                 * the model.
                 */
                Cell boardWin = grid.getBoard(j, i).getWhoHasWon();
                if (boardWin == Cell.RED1)
                {
                    boards[i][j].setFillColor(p1Color);
                    boards[i][j].setAlpha(boardOpacity);
                }
                else if (boardWin == Cell.BLUE2)
                {
                    boards[i][j].setFillColor(p2Color);
                    boards[i][j].setAlpha(boardOpacity);
                }
                else
                {
                    if (!grid.getBoard(j, i).getIsPlayable())
                    {
                        boards[i][j].setFillColor(invalidColor);
                        boards[i][j].setAlpha(boardOpacity);
                    }
                    else
                    {
                        boards[i][j].setAlpha(0);
                    }
                }

            }
        }

        // Get each individual cell
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                Cell cellColor = grid.getCell(j, i);
                if (cellColor == Cell.RED1)
                {
                    cells[i][j].setFillColor(p1Color);
                    cells[i][j].setAlpha(cellOpacity);
                }
                else if (cellColor == Cell.BLUE2)
                {
                    cells[i][j].setFillColor(p2Color);
                    cells[i][j].setAlpha(cellOpacity);
                }
                else
                {
                    cells[i][j].setAlpha(0);
                }
            }
        }

        // Set the last move
        if (grid.getLastMove() != null)
        {
            int x = grid.getLastMove()[1];
            int y = grid.getLastMove()[0];
            // No need to set the FillColor since it's the same as all other
            // moves
            String last = (grid.getCell(y, x) == Cell.RED1) ? "last_move_one" : "last_move_two";
            cells[x][y].setImage(last);
        }
    }


    /**
     * @param opac
     *            The opacity that the shape ends with on a zoom in
     */
    public void zoom(RectangleShape shape, Color color, int opac, boolean in)
    {
        float centerX = shape.getBounds().centerX();
        float centerY = shape.getBounds().centerY();
        float left = centerX - shape.getBounds().width() / 2;
        float top = centerY - shape.getBounds().height() / 2;
        float right = centerX + shape.getBounds().width() / 2;
        float bottom = centerY + shape.getBounds().height() / 2;

        RectF centerBounds = new RectF(centerX, centerY, centerX, centerY);
        shape.setFillColor(color);
        RectF origBounds = new RectF(left, top, right, bottom);
        if (in)
        {
            shape.setAlpha(0);
            shape.setBounds(centerBounds);
            String imgClr = (color == p1Color) ? "last_move_one" : "last_move_two";
            shape.setImage(imgClr);
            shape.animate(100).bounds(origBounds).alpha(opac).play();
        }
        else
        {
            /*
             *  For some reason when this code is run the shapes no longer
             *  display
             */
            //shape.animate(100).bounds(centerBounds).alpha(0).play();
            /*
             * After it appears to have zoomed into oblivion, return it to it's
             * initial size
             */
            //shape.setBounds(origBounds);
        }
    }


    /**
     * The restart/refresh button was pressed, open the confirmation dialog.
     */
    public void action_refreshClicked()
    {
        DialogFragment restartDialog = new RestartGameDialogFragment();
        restartDialog.show(getFragmentManager(), "NoticeDialogFragment");
    }


    /**
     * The user decided to really restart so go ahead and do that.
     *
     * @param dialog
     *            The dialog object that received the positive click.
     */
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        grid.reset();
        this.reflectModel();
    }


    /**
     * The user decided not to restart, do nothing.
     *
     * @param dialog
     *            The dialog object that received the negative click.
     */
    public void onDialogNegativeClick(DialogFragment dialog)
    {
        // Do nothing
    }


    /**
     * The undo button was pressed, undo the move recent move.
     */
    public void action_undoClicked()
    {
        //if there was a last move show an animation zooming it out
        if (grid.getLastMove() != null)
        {
            int x = grid.getLastMove()[1];
            int y = grid.getLastMove()[0];

            Cell cell = grid.getCell(y, x);
            Color cellColor = (cell == Cell.RED1) ? p1Color : p2Color;
            zoom(cells[x][y], cellColor, 0, false);
            grid.undoMove();
            reflectModel();
        }
    }


    /**
     * Enter in the touch floating point value and this method will return the
     * corresponding grid location as an integer.
     *
     * @param touchWithPadding
     *            Where the user touched the screen
     * @return Where the touch corresponds to on the grid. It returns -1 if the
     *         touch wasn't on a value corresponding to the grid.
     */
    public int gridLocation(float touchWithPadding)
    {
        float touch = touchWithPadding - (gridPad + boardPad);
        int loca = -1;
        if (touch >= 0 && touch <= boardSize)
        {
            loca = 0;
        }
        else if (touch >= boardSize + boardPad * 2
            && touch <= boardSize * 2 + boardPad * 2)
        {
            loca = 3;
            touch = touch - (boardSize + boardPad * 2);
        }
        else if (touch >= boardSize * 2 + boardPad * 4
            && touch <= boardSize * 3 + boardPad * 4)
        {
            loca = 6;
            touch = touch - (boardSize * 2 + boardPad * 4);
        }

        if (loca != -1)
        {
            if (touch <= cellSize)
            {
                // loca is already set to the first cell, leave it alone
            }
            else if (touch <= cellSize * 2)
            {
                loca += 1;
            }
            else
            {
                loca += 2;
            }
        }
        return loca;
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
