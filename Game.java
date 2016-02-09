import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

/**
* This class represents a fun maze game.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class Game {
	private Player player;
	private Level level;
	private JLabel statusLabel;
	private LevelPanel levPanel;
	private Timer timer;
	private int rows, cols;
	private JFrame mainFrame;

	/**
	* Sets up instance variables and starts timer.
	*
	* @param mainFrame The JFrame that everything goes on.
	*/

	public Game(JFrame mainFrame) {
		// Hard-coded size of the board.
		rows = 15;
		cols = 15;

		this.mainFrame = mainFrame;

		player = new Player(this, 0, 0);
		level = new Level(this, player);

		statusLabel = new JLabel("Gems Left: " + level.getGems() + "     Score: " + player.getScore());
		statusLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		levPanel = new LevelPanel(this);
		timer = new Timer(1000, new listener());

		timer.start();
	}

	/**
	* Listener for timer events.
	*/

	private class listener implements ActionListener {

		/**
		* Moves monsters and players and repaints panel.
		*
		* @param e The timer's action event.
		*/

		public void actionPerformed(ActionEvent e) {
			level.move();
			levPanel.repaint();
		}
	}

	/**
	* Tells the user whether or not he/she won.
	*
	* @param result The outcome of the game.
	*/

	public void prompt(String result) {
		JOptionPane.showMessageDialog(mainFrame, result, "Press OK to Close", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	/**
	* Stops the timer and tells the player he/she won.
	*/

	public void win() {
		timer.stop();
		prompt("You Win!!!");
	}

	/**
	* Stops the timer and tells the player he/she lost.
	*/

	public void lose() {
		timer.stop();
		prompt("LOSER!");
	}

	/**
	* Getter for rows.
	*
	* @return The rows.
	*/

	public int getRows() {
		return rows;
	}

	/**
	* Getter for cols.
	*
	* @return The cols.
	*/

	public int getCols() {
		return cols;
	}

	/**
	* Getter for level.
	*
	* @return The level.
	*/

	public Level getLevel() {
		return level;
	}

	/**
	* Getter for player.
	*
	* @return The player.
	*/

	public Player getPlayer() {
		return player;
	}

	/**
	* Getter for statusLabel.
	*
	* @return The statusLabel.
	*/

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	/**
	* Updates game status.
	*/

	public void updateStatus() {
		statusLabel.setText("Gems Left: " + level.getGems() + "     Score: " + player.getScore());
	}

	/**
	* Getter for levPanel.
	*
	* @return The level panel.
	*/

	public JPanel getLevelPanel() {
		return levPanel;
	}

	/**
	* Sets up the game.
	*/

	public static void main(String[] args) {
		JFrame frame = new JFrame("Game!");
		Game game = new Game(frame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

		frame.getContentPane().add(game.getStatusLabel());
		Dimension dim = new Dimension(0, 10);
		frame.getContentPane().add(new Box.Filler(dim, dim, dim));
		frame.getContentPane().add(game.getLevelPanel());

		frame.pack();
		frame.setVisible(true);
	}
}

