import java.awt.*;

/**
* This class represents a tile that will used in the maze game.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public abstract class Tile {
	public static final int width = 30, height = 30;
	protected int row, col;

	/**
	* Sets row and column of tile.
	*
	* @param row The row that the tile is on.
	* @param col The column that the tile is on.
	*/

	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	* Draws the tile.
	*
	* @param g The graphics object.
	*/

	public abstract void draw(Graphics g);

	/**
	* Indicates whether or not tile is passable.
	*
	* @return A boolean indicating if the tile is passable.
	*/

	public abstract boolean isPassable();

	/**
	* Getter for tile's row.
	*
	* @return The tile's row.
	*/

	public int getRow() {
		return row;
	}

	/**
	* Getter for tile's column.
	*
	* @return The tile's column.
	*/

	public int getCol() {
		return col;
	}
}

