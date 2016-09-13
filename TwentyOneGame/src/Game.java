import java.util.Scanner;

public class Game {
	String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
			"J", "Q", "K", "A" };//initial ranks
	CardDeck cards;
	String[] player;
	int playerTotal;//record total number of player
	String[] dealer;
	int dealerTotal;//record total number of dealer
	Scanner input;//get the input from player
	
	//return the point of each card
	private int findCardPoint(String cardRank) {
		if (cardRank.equals("10") || cardRank.equals("J") || cardRank.equals("Q") || cardRank.equals("K")) {
			return 10;
		} else if (cardRank.equals("A")) {
			return 11;
		} else {
			return Integer.parseInt(cardRank);
		}
	}
	
	//player draw one more card
	private void playerDrawNextCard() {
		String[] draw = new String[player.length+1];
		System.out.print("Player:");
		for (int i = 0; i < player.length; i++) {
			System.out.print(" " + player[i]);
			draw[i] = player[i];
		}
		draw[draw.length-1] = cards.drawNextCard();
		playerTotal = playerTotal + findCardPoint(draw[draw.length-1]);
		System.out.print(" " + draw[draw.length-1]);
		System.out.println("  Total: "+playerTotal);
	}
	
	//dealer get cards until the total number is not less than 17
	private void dealerDrawCards() {
		String[] draw = new String[21];
		System.out.print("Dealer: "+dealer[0]);
		int i = 0;
		for (; dealerTotal < 17; i++) {
			draw[i] = cards.drawNextCard();
			System.out.print(" " + draw[i]);
			dealerTotal = dealerTotal + findCardPoint(draw[i]);
		}
		System.out.println("  Total: "+dealerTotal);
		dealer = new String[i];
		for (int j = 0; j < i; j++) {
			dealer[j] = draw[i];
		}
	}
	
	public void playAGame() {
		cards = new CardDeck(ranks);
		input = new Scanner(System.in);
		playerTotal = 0;
		dealerTotal = 0;
		cards.shufleDeck();
		//show the first card of dealer
		String first = cards.drawNextCard();
		dealer = new String[1];
		dealer[0] = first;
		dealerTotal = findCardPoint(first);
		System.out.println("Dealer: "+first+"  Total: "+dealerTotal);
		//show the first two cards of player
		String second = cards.drawNextCard();
		String third = cards.drawNextCard();
		player = new String[2];
		player[0] = second;
		player[1] = third;
		playerTotal = findCardPoint(second)+findCardPoint(third);
		System.out.println("Player: "+second+" "+third+"  Total: " + playerTotal);
		System.out.print("Hit(H or h) or Stand(S or s): ");
		String choice = input.nextLine();//get the input
		//while player choose hit and the total number is less than 21
		while (playerTotal <= 21 && choice.toUpperCase().equals("H")) {
			playerDrawNextCard();
			if (playerTotal <= 21) {
				System.out.print("Hit(H or h) or Stand(S or s): ");
				choice = input.nextLine();
			}
		}
		//show the result
		if (playerTotal > 21) {
			System.out.println("Player buts!");
		} else {
			dealerDrawCards();
			if (dealerTotal > 21) {
				System.out.println("Dealer buts!");
			} else if (dealerTotal > playerTotal) {
				System.out.println("Dealer wins");
			} else if (dealerTotal < playerTotal) {
				System.out.println("Player wins");
			} else if (dealerTotal == playerTotal) {
				System.out.println("It's a tie");
			}
		}
		input.close();
	}
}
