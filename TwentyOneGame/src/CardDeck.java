
public class CardDeck {
	private String[] ranks;
	private int counter;
	
	//construct an initial card ranks
	public CardDeck(String[] array) {
		ranks = array;
		counter = 0;
	}
	
	//return next card and increase counter by 1
	public String drawNextCard() {
		counter++;
		return ranks[counter-1];
	}
	
	//shuffle the ranks
	public void shufleDeck() {
		String[] random = new String[ranks.length];
		int[] numbers = new int[ranks.length];
		for (int i=0; i < ranks.length; i++) {
			numbers[i] = -1;
		}
		for (int i=0; i < ranks.length; i++) {
			int number = (int) (Math.random()*ranks.length);
			for (int j = 0; j < numbers.length; j++) {
				if (number == numbers[j]) {
					number = (int) (Math.random()*ranks.length);
					j = -1;
				}
			}
			numbers[i] = number;
			random[i] = ranks[number];
		}
		ranks = random;
	}
}
