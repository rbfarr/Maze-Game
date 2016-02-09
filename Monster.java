import java.awt.*;

/**
* This class represents a scary monster.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public abstract class Monster {
	protected int row, col;
	protected Game game;

	/**
	* Sets row and column of monster.
	*
	* @param game The instance of the maze game.
	* @param row The row that the monster is on.
	* @param col The column that the monster is on.
	*/

	public Monster(Game game, int row, int col) {
		this.game = game;
		this.row = row;
		this.col = col;
	}

	/**
	* Attacks encountered players.
	*
	* @param p The player to attack.
	*/

	public abstract void attackPlayer(Player p);

	/**
	* Draws the monster.
	*
	* @param g The graphics object.
	*/

	public abstract void draw(Graphics g);

	/**
	* Moves the monster at random.
	*/

	public abstract void move();
}

