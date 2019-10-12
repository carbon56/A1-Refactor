import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blackJack.Card;

public class CardTest {

	@Test
	public void testGetNumber() {
		Card a = new Card("KING", (byte) 10);
		assertEquals((byte) 10, a.getNumber());
	}

	@Test
	public void testSetNumber() {
		Card a = new Card("KING", (byte) 10);
		a.setNumber((byte) 2);
		assertEquals((byte) 2, a.getNumber()); 
	}

	@Test
	public void testGetName() {
		Card a = new Card("KING", (byte) 10);
		assertEquals("KING", a.getName()); 
	}

	@Test
	public void testSetName() {
		Card a = new Card("KING", (byte) 10);
		a.setName("QUEEN");
		assertEquals("QUEEN", a.getName()); 
	}

}
