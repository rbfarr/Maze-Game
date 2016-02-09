import java.util.Random;
import java.awt.*;

/**
* This class represents a simple monster in the maze game.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class SimpleMonster extends Monster {
	private int drow, dcol;
	private boolean isGhost = false;

	public SimpleMonster(Game game, int row, int col, int drow, int dcol) {
		super(game, row, col);
		this.drow = drow;
		this.dcol = dcol;
	}

	public void attackPlayer(Player p) {
		if (row == game.getPlayer().getRow() && col == game.getPlayer().getCol()) {
			p.updateScore(-30);
		}
	}

	public void draw(Graphics g) {
		if (!isGhost) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.orange);
		}
		
		g.fillOval(col*Tile.width, row*Tile.height, Tile.width, Tile.height);
	}

	public void setGhost(boolean isGhost) {
		this.isGhost = isGhost;
	}

	public void move() {
		Random rand = new Random();

		int select = rand.nextInt(8) + 1;
		int mrow = 0, mcol = 0;

		switch (select) {
			case 1:
				mrow = -drow;
				mcol = -dcol;
				break;
			case 2:
				mrow = -drow;
				mcol = dcol;
				break;
			case 3:
				mrow = drow;
				mcol = -dcol;
				break;
			case 4:
				mrow = drow;
				mcol = dcol;
				break;
			case 5:
				mrow = drow;
				break;
			case 6:
				mrow = -drow;
				break;
			case 7:
				mcol = dcol;
				break;
			case 8:
				mcol = -dcol;
				break;
		}

		if (row+mrow >= 0 && row+mrow < 15 && col+mcol >= 0 && col+mcol < 15) {
			if (isGhost) {
				row += mrow;
				col += mcol;
			} else {
				if (game.getLevel().getTile(row+mrow, col+mcol).isPassable()) {
					row += mrow;
					col += mcol;
				}
			}
		}
	}
}

