import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
* This class represents a floor tile that will teleport players to
* another random floor tile.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class TeleportingTile extends Tile {
	public TeleportingTile(int row, int col) {
		super(row, col);
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		RoundRectangle2D rect = new RoundRectangle2D.Float(col*Tile.width, row*Tile.height, Tile.width, Tile.height, 5, 5);
		g2.draw(rect);
	}

	public boolean isPassable() {
		return true;
	}
}
