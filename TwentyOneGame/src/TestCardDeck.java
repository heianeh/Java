/**
 * This is a testing program for CardDeck class for the Twenty-One 
 *   game of Assignment 2.
 * 
 * Note: You should not modify this program. This program will test and 
 *       help you develop your CardDeck class as instructed in the
 *       Assignment paper.
 *       
 *       Understanding this code will help you implement the CardDeck
 *       and Game classes.
 * 
 * @author Lei Ye
 *
 */
import java.util.Scanner;

public class TestCardDeck {

	public static void main(String[] args) {

		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K", "A" };

		CardDeck cardDeck;
		String card;

		boolean noBug = true;
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Test CardDeck class");

		// Create a Game object
		cardDeck = new CardDeck(ranks);

		// Draw one card to test the drawNextCard method
		System.out.println("\n1. Drawing the first card");
		card = cardDeck.drawNextCard();
		if("2".equals(card)){
			System.out.println("  The first card is correct.");
		} else {
			System.out.println("  The first is not correct.");
			System.out.println("Debug the drawNextCard method");
			noBug = false;
		}

		if (noBug) {
			// Display all cards in the initial order
			System.out.println("\n2. Display all cards in the initial order");

			cardDeck = new CardDeck(ranks);
			for(int i=0;i<ranks.length; i++){
				card = cardDeck.drawNextCard();
				System.out.print(" "+ card);
			}
			System.out.print("\n Are they in the initial order? (Y or N) ");
			if("N".equals(keyboard.nextLine().toUpperCase())) {
				System.out.println("Debug the card counter of the drawNextCard method");
				noBug = false;
			};
		}

		if (noBug) {
			// Display all cards in random orders
			System.out.println("\n3. Display all cards in random orders");

			// Try one deck
			cardDeck = new CardDeck(ranks);
			cardDeck.shufleDeck();
			for(int i=0;i<ranks.length; i++){
				card = cardDeck.drawNextCard();
				System.out.print(" "+ card);
			}
			System.out.println();
			// Try another deck
			cardDeck = new CardDeck(ranks);
			cardDeck.shufleDeck();
			for(int i=0;i<ranks.length; i++){
				card = cardDeck.drawNextCard();
				System.out.print(" "+ card);
			}
			System.out.print("\n Are they all in different random orders? (Y or N) ");
			if("N".equals(keyboard.nextLine().toUpperCase())) {
				System.out.println("Debug the shufleDeck method");
			} else {
				System.out.println("Ok, proceed to create the Game class");
			};
		}
		keyboard.close();
	}
}
