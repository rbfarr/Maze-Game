import java.awt.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

/**
* This class represents a level in the maze game.
*
* "I worked on this assignment alone."
*
* @author Brad Farr
* @version 1.0 11/29/11
*/

public class Level {
	private Tile[][] tiles;
	private Game game;
	private int gems = 0;
	private int rows, cols;
	private Player player;
	private ArrayList<Monster> monsterList;
	private ArrayList<TeleportingTile> teleports;

	/**
	* Sets up instance variables.
	*
	* @param game The instance of the maze game.
	* @param player The player of the maze game.
	*/

	public Level(Game game, Player player) {
		rows = game.getRows();
		cols = game.getCols();

		tiles = new Tile[rows][cols];

		this.game = game;
		this.player = player;

		monsterList = new ArrayList<Monster>();
		teleports = new ArrayList<TeleportingTile>();

		levelConstruct();
	}

	/**
	* Reads in a text file representing a level or generates a random level.
	*/

	public void levelConstruct() {
		Random rand = new Random();

		try {
			// Scan in the level file.
			Scanner fileReader = new Scanner(new File("level.txt"));

			// Initialize a line parser.
			Scanner lineParser;

			// Set up counters to index the tile array.
			int rCount = 0, cCount = 0;

			// Initialize strings.
			String line, token;

			// Iterate through each line.
			while (rCount < 15) {
				line = fileReader.nextLine();
				lineParser = new Scanner(line);

				// Iterate through each character.
				while (cCount < 15) {
					token = lineParser.next();

					if (token.equals("x")) {
						tiles[rCount][cCount] = new Wall(rCount, cCount);
					} else if (token.equals("p")) {
						tiles[rCount][cCount] = new Floor(rCount, cCount);
						player.setPosition(rCount, cCount);
					} else if (token.equals("g")) {
						gems++;
						tiles[rCount][cCount] = new Floor(rCount, cCount, new Gem(rCount, cCount));
					} else {
						tiles[rCount][cCount] = new Floor(rCount, cCount);
					}

					cCount++;
				}

				cCount = 0;
				rCount++;
			}

		// Generate random level if file not found.
		} catch (Exception e) {

			// Player will always be at first position.
			tiles[0][0] = new Floor(0, 0);

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {

					if (i != 0 || j != 0) {
						if (rand.nextInt(100) + 1 <= 15) {
							tiles[i][j] = new Wall(i, j);
						} else {
							if (rand.nextInt(100) + 1 <= 10) {
								gems++;
								tiles[i][j] = new Floor(i, j, new Gem(i, j));
							} else {
								tiles[i][j] = new Floor(i, j);
							}
						}
					}
				}
			}

		// Place monsters and teleport tiles randomly.
		} finally {
			int r, c, dr, dc;

			for (int i = 0; i < 10; i++) {
				r = rand.nextInt(15);
				c = rand.nextInt(15);
				dr = rand.nextInt(2) + 1;
				dc = rand.nextInt(2) + 1;

				int count = 0;

				while (tiles[r][c] instanceof Wall && count < 25) {
					r = rand.nextInt(15);
					c = rand.nextInt(15);

					count++;
				}


				monsterList.add(new SimpleMonster(game, r, c, dr, dc));

				if (rand.nextInt(3) == 1) {
					((SimpleMonster)(monsterList.get(i))).setGhost(true);
				}
			}

			for (int i = 0; i < 4; i++) {
				r = rand.nextInt(15);
				c = rand.nextInt(15);

				while (tiles[r][c] instanceof Wall || ((Floor)(tiles[r][c])).collectItem() != null) {
					r = rand.nextInt(15);
					c = rand.nextInt(15);
				}

				tiles[r][c] = new TeleportingTile(r, c);
				teleports.add((TeleportingTile)tiles[r][c]);
			}
		}
	}

	/**
	* Draw the entire level.
	*
	* @param g The graphics object.
	*/

	public void draw(Graphics g) {
		// Draw tiles.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				tiles[i][j].draw(g);
			}
		}

		// Draw monsters.
		for (Monster m : monsterList) {
			m.draw(g);
		}

		// Draw players.
		player.draw(g);
	}

	/**
	* Moves all monsters randomly and attacks player.
	*/

	public void move() {
		for (Monster m : monsterList) {
			m.move();
			m.attackPlayer(player);
		}
	}

	/**
	* Plays sound when gem is found or teleports user to random tile.
	*
	* @param row The row to act on.
	* @param col The column to act on. 
	*/

	public void tileAction(int row, int col) {
		if (tiles[row][col] instanceof Floor) {
			Item item = ((Floor)tiles[row][col]).collectItem();

			if (item != null) {
				try {
					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("beep.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audio);
					clip.start();
				} catch (UnsupportedAudioFileException uae) {
				  	System.out.println(uae);
				} catch (IOException ioe) {
				  	System.out.println(ioe);
				} catch (LineUnavailableException lua) {
				  	System.out.println(lua);
				}

				player.updateScore(item.getValue());
				gems--;

				game.updateStatus();

				if (gems == 0) {
					game.win();
				}
			}

		} else if (tiles[row][col] instanceof TeleportingTile) {
			Random rand = new Random();
			int select = rand.nextInt(4);

			while (teleports.get(select) == tiles[row][col]) {
				select = rand.nextInt(4);
			}

			player.setPosition(teleports.get(select).getRow(), teleports.get(select).getCol());
		}
	}

	/**
	* Getter for the tile at the specified row and column.
	*
	* @param row The row of the tile to get.
	* @param col The column of the tile to get.
	* @return The tile.
	*/

	public Tile getTile(int row, int col) {
		return tiles[row][col];
	}

	/**
	* Getter for number of gems left.
	*
	* @return The number of gems left.
	*/

	public int getGems() {
		return gems;
	}

	/**
	* Getter for the monster list.
	*
	* @return The monster list.
	*/

	public ArrayList<Monster> getMonsterList() {
		return monsterList;
	}
}

