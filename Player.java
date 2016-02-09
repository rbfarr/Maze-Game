import java.awt.*;

/**
* This class represents a player for the maze game.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class Player {
	private int row, col;
	private int score;
	private Game game;

	public Player(Game game, int row, int col) {
		this.game = game;
		this.row = row;
		this.col = col;

		score = 100;
	}

	public void move(int drow, int dcol) {
		if (row+drow >= 0 && row+drow < 15 && col+dcol >= 0 && col+dcol < 15) {
			if (game.getLevel().getTile(row+drow, col+dcol).isPassable()) {
				row += drow;
				col += dcol;
			}
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(col*Tile.width, row*Tile.height, Tile.width, Tile.height);
	}

	public int getScore() {
		return score;
	}

	public void updateScore(int dscore) {
		score += dscore;

		game.updateStatus();

		if (score <= 0) {
			game.lose();
		}
	}

	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}

