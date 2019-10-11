package blackJack;

import java.util.ArrayList;

public class Player {
	// Create Hand for player 
	protected ArrayList<Card> playHand = new ArrayList<Card>();
//	private ArrayList<Card> cpuHand = new ArrayList<Card>();
	private static int turn = 0;
	private static Card [] drawDeck = Deck.getDeck();
	
	public Player(){
	}

	
    public void drawHand(String move, Card[] drawDeck ) {
        if(turn > drawDeck.length - 1){// Turn cannot exceed the index of drawDeck, so 51
            // When the index exceeds 51, the last index in our array, generate new deck and reset turn to 0
            turn = 0;
            try{ 
				drawDeck = new Deck().getShuffledDeck();
			} catch (Exception e) {
				drawDeck = new Deck().getShuffledDeck();
			}

		}
		if (move == "DRAW") {
			playHand.clear(); // clear hand
			playHand.add(drawDeck[turn]);
			playHand.add(drawDeck[turn + 1]);
			// receive 2 cards
			turn += 2; // Move index up by 2
		}

		if (move == "HIT") {
			// add one card to hand
			playHand.add(drawDeck[turn]);
			turn += 1;
		}
	}
	

	
	public void showHand(){
		for (int i = 0; i < playHand.size(); i++){
			System.out.println(playHand.get(i).getName());
		}
		
		
		
	}

	
	
	public int handValue() {
		int aceCount = 0;
		int sum = 0;
		// Iterate over hand, sum the values;
		for (int i = 0; i < playHand.size(); i++) {
			if (playHand.get(i).getName() == "ACE") {
				aceCount += 1; // checks if card is ace
			}
			sum += playHand.get(i).getNumber();
		}

		if (aceCount > 0) {
			// Default value of ace is 1
			// Cannot have 2 aces be 11 without going BUST
			// We let one ace be 11 by increasing hand value by 10, if less than or equal to 21
			if (sum + 10 <= 21) {
				return sum + 10; // If aces can be 11 without going over 21, then 10 is added to make ace be 11
			}
		}

		return sum;
	}
	
	
	// Getters and Setters
	public ArrayList<Card> getPlayHand() {
		return playHand;
	}
	public void setPlayHand(ArrayList<Card> playHand) {
		this.playHand = playHand;
	}
	public int getTurn() {
		return turn;
	}
	

	

	
	
	
	
	
	

}

