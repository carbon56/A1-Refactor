package blackJack;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {
	private String[] set = { "KING", "JACK", "QUEEN", "ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT","NINE", "TEN" };
	private byte[] value = { 10, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static ArrayList<Card> deck = new ArrayList<Card>(52);
	private static Card[] shuffledDeck = new Card[52];

	public static Card[] getDeck(){
		return shuffledDeck;
		
	}
	public Deck(){
	// Constructs Shuffled Deck 
		makeDeck();
		shuffle();
	}
	
	
    public Card[] getShuffledDeck() {
        return shuffledDeck;
    }
	
	public void makeDeck(){
		for(int i = 0; i < set.length;i++){
			for(int n = 0; n < 4; n++){
				deck.add(new Card(set[i], value[i]));
			}
		}
	}

	public void shuffle() {
		int randomIndex = 0;
		for (int i = 0; i < 52; i++) {
			randomIndex = (int) (Math.random() * deck.size());
			shuffledDeck[i] = deck.get(randomIndex);
			deck.remove(randomIndex);
		}
	}



}

