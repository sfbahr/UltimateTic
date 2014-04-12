Here's that link to free private GitHub repos for students: https://education.github.com/

[Markdown formatting](https://github.com/adam-p/markdown-here/wiki/Markdown-Here-Cheatsheet#html) ([Live markdown conversion](http://markdown-here.com/livedemo.html))

Proposed Naming Conventions 
---------------------------
| Name   | Meaning
| ------ | -------
| Board  | A single tac board
| Cell   | One of the nine regions in a single tac board
| Grid   | The array of nine tac boards
| Mark   | An X or an O that is used to mark possesion of a cell.
| tac    | Shorthand for tic-tac-toe
| Triple | Shorthand for three-in-a-row

e.g. The grid's bottom left board has a mark in its bottom left cell.

To Do
-----
(Outline is still very unfinished)

1. View (User Interface) (sfbahr)
  - Initialization
    * (DONE) ~~Draw nine boards in a grid on the screen~~
  - User Interaction
    * Determine what board and what cell in that board the user is touching
    * Figure out how to limit the user to place their mark only on one board.
  - Visual feedback
    * Indicate which board in which the user is allowed to place their mark.
    * Indicate whose turn it is
    * Indicate where a marks have been placed, and by whom (X and O's, or colored squares?)
    * Indicate whether a board has been won.
  - Methods
    * setCell
	  - grid.setCell(0-8,0-8) (change turn and check who has won)
	* reflectModel
	  - grid.getwhohaswon                         (Cell)
	  - grid.getturn                              (Cell)
	  - grid.getBoard(0-2, 0-2).getwhohaswon()    (Cell)
	  - grid.getBoard(0-2, 0-2).getCell(0-2, 0-2) (Cell)
	* reset
	  - grid.reset

2. Model (Game Mechanics) (BJClarke & charten)
  - Create Board Class
  - Board methods
    * (DONE) ~~Determine whether a board has three-in-a-row~~
      + (option) Return current winner
      + (option) Determine how many triples a board has
	  + Set a variable to indicate current winner
    * (DONE) ~~Determine if someone has won the board~~
      + return the winner 
    * (DONE) ~~Place a mark on one of the cells~~
      + Uses Cell class to indicate who has marked the cell
      + Make sure the cell isn't already marked
      + should probably call a method to check for victory
    * public Cell getCell()
  - (DONE) ~~Create Cell Class~~ (charten)
    * Cell will just be an enum type with values EMPTY, X, and O.
		
3. Algorithm (AI)


Quick Git Howto
---------------

1. git pull
2. git status
3. git add . -A *(or specific files)*
4. git status
5. git commit -m "comment"
6. git push origin master


UltimateTic
===========

Ultimate Tic Tac Toe for Android.

