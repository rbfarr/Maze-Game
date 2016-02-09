import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
* This class represents a shiny gem on a floor tile.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class Gem extends Item {

	/**
	* Sets row and column of gem.
	*
	* @param row The row that the gem is on.
	* @param col The column that the gem is on.
	*/

	public Gem(int row, int col) {
		super(row, col);
		this.value = 5;
	}


	/**
	* Draws the gem as a red square.
	*
	* @param g The graphics object.
	*/

	public void draw(Graphics g) {
		g.setColor(Color.red);
		Graphics2D g2 = (Graphics2D) g;
		RoundRectangle2D rect = new RoundRectangle2D.Float(col*Tile.width, row*Tile.height, Tile.width, Tile.height, 5, 5);
		g2.fill(rect);
	}
}

