/**
 * This is the main program for the Twenty-One game of Assignment 2.
 * 
 * Note: You should not modify this program. This program will use
 *       the Game and CardDeck classes you created as instructed in the
 *       Assignment paper.
 *       
 *       Understanding this code will help you implement the Game
 *       class.
 * 
 * @author Lei Ye
 *
 */
public class TwentyOneApp {

	public static void main(String[] args) {

		System.out.println("Twenty-One Game V.2 starts\n");

		// Create a Game object
		Game game = new Game();
		// Call the playGame method to start the game
		game.playAGame();
		
	}
}
