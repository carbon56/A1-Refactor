
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blackJack.Player;
import blackJack.Deck;
import blackJack.Card;

public class TestPlayer {


	@Test
	public void testDrawHand() {
		Card[] playingDeck = new Deck().getShuffledDeck();
		Player bob = new Player();
		bob.drawHand("DRAW", playingDeck);
		// player initially get two cards
		assertEquals(2, bob.getPlayHand().size());
		// Hitting after drawing, gets 3 cards 
		bob.drawHand("HIT", playingDeck);
		assertEquals(3, bob.getPlayHand().size());
		
		
	}




	@Test
	public void testGetHandValue() {
		Card[] playingDeck = new Deck().getShuffledDeck();
		Player bob = new Player();
		
		bob.drawHand("DRAW", playingDeck);
		//bob.getHandValue();
		ArrayList<Card> playHand = bob.getPlayHand();
	
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
				sum = sum + 10; // If aces can be 11 without going over 21, then 10 is added to make ace be 11
			}
		}
		
		// generate deck and compares it hand value with calculation of hand value
		assertEquals(sum, bob.getHandValue());
		

		
	}

	@Test
	public void testGetPlayHand() {
		Card[] playingDeck = new Deck().getShuffledDeck();
		Player bob = new Player();
		
		Card a = new Card("TWO", (byte) 2);
		
		ArrayList<Card> playHand = new ArrayList<Card>();
		playHand.add(a);
		bob.setPlayHand(playHand);
		
		assertEquals((byte) 2, bob.getPlayHand().get(0).getNumber());
		assertEquals("TWO", bob.getPlayHand().get(0).getName());

		
		
	}

	@Test
	public void testSetPlayHand() {
		Card[] playingDeck = new Deck().getShuffledDeck();
		Player bob = new Player();
		
		Card a = new Card("KING", (byte)10);
		Card b = new Card("NINE", (byte) 9);
		
		ArrayList<Card> playHand = new ArrayList<Card>();
		playHand.add(a);
		playHand.add(b);
				
		bob.setPlayHand(playHand); // Set this as playHand
		byte firstCardNum = bob.getPlayHand().get(0).getNumber();
		byte secondCardNum = bob.getPlayHand().get(1).getNumber();
		// check if cards in Bob's play hand are correct 
		assertEquals((byte) 10, firstCardNum);
		assertEquals((byte) 9, secondCardNum);
		assertEquals("KING", bob.getPlayHand().get(0).getName());
		assertEquals("NINE", bob.getPlayHand().get(1).getName());
		
	}

	@Test
	public void testGetTurn() {
		// test if turn is initially set to 0 
		Player bob = new Player();
		assertEquals(0, bob.getTurn());
	}

}
