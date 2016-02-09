import java.awt.*;

/**
* This class represents a floor tile that may or may not contain an item.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class Floor extends Tile {
	// Tiles may or may not have an item.
	private Item item;

	/**
	* Sets row and column of tile and adds item.
	*
	* @param row The row that the tile is on.
	* @param col The column that the tile is on.
	* @param item The item on the tile.
	*/

	public Floor(int row, int col, Item item) {
		super(row, col);
		this.item = item;
	}

	/**
	* Sets row and column of title and sets item to null.
	*
	* @param row The row that the tile is on.
	* @param col The column that the tile is on.
	*/

	public Floor(int row, int col) {
		this(row, col, null);
	}

	/**
	* Collects the item from the floor tile.
	*
	* @return The item.
	*/

	public Item collectItem() {
		Item temp = item;
		item = null;

		return temp;
	}

	/**
	* Draws the floor tile and item (if one exists).
	*
	* @param g The graphics object.
	*/

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(col*Tile.width, row*Tile.height, Tile.width, Tile.height);

		if (item != null) {
			item.draw(g);
		}
	}

	/**
	* Indicates that the floor tile is passable.
	*
	* @return A boolean indicating that the tile is passable.
	*/

	public boolean isPassable() {
		return true;
	}
}

