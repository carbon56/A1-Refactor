import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blackJack.Card;
import blackJack.Cpu;
import blackJack.Game;

public class CpuTest {


	@Test
	public void testStrategy() {
		Game test = new Game();
		Card a = new Card("KING", (byte)10);
		Card b = new Card("SEVEN", (byte) 7);
		
		ArrayList<Card> playHand = new ArrayList<Card>();
		playHand.add(a);
		playHand.add(b);

		// value of hand is 17, so CPU should not "Hit" it only does so when hand value < 17
		test.getCpu().setPlayHand(playHand);
		test.getCpu().strategy(test.getPlayingDeck());
		// number of cards in hand should be 2
		assertEquals(2, test.getCpu().getPlayHand().size()); 
		
		ArrayList<Card> newHand = new ArrayList<Card>();
		newHand.add(b);
		newHand.add(b);
		newHand.add(b);
		test.getCpu().setPlayHand(newHand);
		test.getCpu().strategy(test.getPlayingDeck());
		// cpu has black jack, should not hit
		assertEquals(3, test.getCpu().getPlayHand().size()); 
		
		newHand.remove(2);
		test.getCpu().strategy(test.getPlayingDeck());
		// cpu hand value = 14 with 2 cards, so we expect cpu
		// to hit and get at least 3 cards
		assertTrue((test.getCpu().getPlayHand().size()) >= 3); 
		
		
				
	}

	@Test
	public void testCpuBets() {
		Game test = new Game();
		float cpubet = test.getCpu().cpuBets(test.getCpuBalance());
		assertTrue(cpubet > 0);
	}

}
