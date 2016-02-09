import java.awt.*;

/**
* This class represents an item on a floor tile.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public abstract class Item {
	protected int value;
	protected int row, col;

	/**
	* Sets row and column of the item.
	*
	* @param row The row that the item is on.
	* @param col The column that the item is on.
	*/

	public Item(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	* Draws the item.
	*
	* @param g The graphics object.
	*/

	public abstract void draw(Graphics g);

	/**
	* Getter for item value.
	*
	* @return The item value.
	*/

	public int getValue() {
		return value;
	}
}

