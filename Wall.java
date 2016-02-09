import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
* This class represents a wall that players and 
* most monsters cannot pass through.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class Wall extends Tile {

	/**
	* Sets row and column of wall.
	*
	* @param row The row that the wall is on.
	* @param col The column that the wall is on.
	*/

	public Wall(int row, int col) {
		super(row, col);
	}

	/**
	* Draws the wall tile.
	*
	* @param g The graphics object.
	*/

	public void draw(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		RoundRectangle2D rect = new RoundRectangle2D.Float(col*Tile.width, row*Tile.height, Tile.width, Tile.height, 5, 5);
		g2.fill(rect);
	}

	/**
	* Indicates that the wall tile is not passable.
	*
	* @return A boolean indicating that the tile is not passable.
	*/

	public boolean isPassable() {
		return false;
	}
}

