import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* This class represents a graphical panel on which the user interacts with the maze game.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class LevelPanel extends JPanel {
	private Player player;
	private Level level;

	/**
	* Sets up the instance variables and listener.
	* Also gives input focus to this panel.
	*/

	public LevelPanel(Game game) {
		setFocusable(true);
		setPreferredSize(new Dimension(Tile.width*15, Tile.height*15));

		player = game.getPlayer();
		level = game.getLevel();

		addKeyListener(new listener());
	}

	/**
	* Paints each frame of game play.
	*
	* @param g The graphics object.
	*/

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		level.draw(g);
	}

	/**
	* Listens for all keyboard actions and moves player accordingly.
	*/

	private class listener implements KeyListener {

		/**
		* Moves the player according to key press.
		*
		* @param k The key event.
		*/

		public void keyPressed(KeyEvent k) {
			if (k.getKeyCode() == KeyEvent.VK_UP) {
				player.move(-1, 0);
			} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
				player.move(1, 0);
			} else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
				player.move(0, -1);
			} else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.move(0, 1);
			}

			repaint();

			for (Monster m : level.getMonsterList()) {
				m.attackPlayer(player);
			}

			level.tileAction(player.getRow(), player.getCol());
		}

		public void keyTyped(KeyEvent k) {}

		public void keyReleased(KeyEvent k) {}
	}
}

