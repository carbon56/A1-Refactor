import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blackJack.Deck;

public class DeckTest {


	@Test
	public void testDeck() {
		Deck a = new Deck();
		// test if 52 card deck is made
		assertEquals(52,a.getDeck().length);
		
	}


}

