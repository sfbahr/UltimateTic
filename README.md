Here's that link to free private GitHub repos for students: https://education.github.com/

===============================================================================
Proposed Naming Conventions ---------------------------------------------------

tac     - Shorthand for tic-tac-toe
Grid    - The array of nine tac boards
Board   - A single tac board
Mark    - An X or an O that is used to mark possesion of a cell.
Cell    - One of the nine regions in a single tac board

e.g. The grid's bottom left board has a mark in its bottom left cell.

===============================================================================
To Do -------------------------------------------------------------------------
(Outline is still very unfinished)

- Create the base of the app from scratch (sfbahr)

1. Layout (User Interface) (sfbahr)
	Initialization
		Draw nine boards in a grid on the screen
	User Interaction
		Determine what board and what cell in that board the user is touching
		Figure out how to limit the user to place their mark only on one board.
	Visual feedback
		Indicate which board in which the user is allowed to place their mark.
		Indicate whose turn it is
		Indicate where a marks have been placed, and by whom (X and O's, or colored squares?)
		Indicate whether a board has been won.

2. Model (Game Mechanics) (BJClarke & charten)
	Board methods
		public int countTriples() - Determine whether a board has three-in-a-row
			Return number of three-in-a-row's
			(option) Determine how many triples a board has
		public boolean isWon() - Determine whether a board has at least one three-in-a-row
			return true if the board has at least one.
			might also want to call count triples 
		public void setCell(arguments) - Place a mark on one of the cells
			Could create a cell class, or a board could be represented by an array of ints.
			Make sure the cell isn't already marked
			should probably call isWon()
		public Cell getCell()
		
3. Algorithm (AI)


===============================================================================
Quick Git Howto ---------------------------------------------------------------

1. git pull
2. git status
3. git add . -A *(or specific files)*
4. git status
5. git commit -m "comment"
6. git push origin master

===============================================================================
UltimateTic -------------------------------------------------------------------
===============================================================================

Ultimate Tic Tac Toe for Android.

