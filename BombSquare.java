import java.util.*;
/**
 * This class represents a square on a Bombsweeper game board that may or may not contain a bomb.
 * It extends the GameSquare class, which is part of a Bombsweeper game implementation.
 *
 * The BombSquare class is responsible for handling the logic related to bombs, revealing neighboring
 * squares, and managing the state of each square on the game board.
 * @author colmantee
 */
public class BombSquare extends GameSquare
{
	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;
	private boolean revealed = false;

	/**
     * Constructs a BombSquare object at the specified coordinates on the game board.
     *
     * @param x      The x-coordinate of the square.
     * @param y      The y-coordinate of the square.
     * @param board  The game board to which this square belongs.
     */
	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);
		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	/**
     * Handles a click event on this square. If the square contains a bomb, it sets the square's image
     * to a bomb image. Otherwise, it reveals the number of neighboring bombs or empty neighboring squares.
     */
	public void clicked() {
		if (thisSquareHasBomb) {
			// Set the square image to a bomb image if this square contains a bomb.
			setImage("images/bomb.png");
		} else {
			// If not a bomb, count the number of neighboring bombs.
			int bombCount = countSurroundingBomb();

			//if bomb count more than zero
			if (bombCount > 0) {
				// Set the image to a number image ("1.png", "2.png", etc.) if there are neighboring bombs.
				setImage("images/" + bombCount + ".png");
			} else {
				// Set the image to an empty square image ("0.png") if there are no neighboring bombs.
				setImage("images/0.png");
			}
			// Mark this square as revealed.
			revealed = true;
			if (bombCount == 0) {
				// If there are no neighboring bombs, recursively reveal empty neighboring squares.
				revealEmptyNeighbors();
			}
		}
	}	

	/**
 	* Checks if the given coordinates (x, y) are within the valid bounds of the game board.
 	*
 	* @param x The x-coordinate to check.
 	* @param y The y-coordinate to check.
 	* @return True if the coordinates are within the valid bounds, false otherwise.
 	*/
	public boolean isValidLocation(int x, int y) {
		// Check if the coordinates (x, y) are within the valid bounds of the game board
		return x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight();
	}

	/**
	 * Counts the number of neighboring squares that contain a bomb.
	 *
	 * @return The number of neighboring squares containing bombs.
	 */
	public int countSurroundingBomb()
	{
		int bombCount = 0;
		for(int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int x = xLocation + i; //add current location of x axis to i
				int y = yLocation + j; //add current location of y axis to j

				if(isValidLocation(x,y)) {
					GameSquare adjacentSq = board.getSquareAt(x, y);
					if (adjacentSq instanceof BombSquare) {
						if (((BombSquare) adjacentSq).thisSquareHasBomb) {
							bombCount++;
						}
					}
				}
			}
		}
		return bombCount;
	}

	/**
	 * Checks if this square has been revealed.
	 *
	 * @return True if this square has been revealed, false otherwise.
	 */
	public boolean isRevealed(){
		return revealed;
	}

	/**
	 * Recursively reveals neighboring squares that are empty and haven't been revealed yet.
	 * This method is called when a square with no neighboring bombs is clicked.
	 */
	public void revealEmptyNeighbors() {
		if(countSurroundingBomb() != 0){
			return;
		}

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue; // Skip the current square
				}
				int x = xLocation + i;
				int y = yLocation + j;
	
				if (isValidLocation(x, y)) {
					BombSquare neighborSquare = (BombSquare) board.getSquareAt(x, y);
					if (neighborSquare != null && !neighborSquare.isRevealed()) {
						if (!(neighborSquare.thisSquareHasBomb)) {
						// if (neighborSquare.countSurroundingBomb() == 0) {
							neighborSquare.clicked();
							neighborSquare.revealEmptyNeighbors();
						}
					}
				}
			}
		}
	}
}
