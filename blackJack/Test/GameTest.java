import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blackJack.Card;
import blackJack.Game;

public class GameTest {

	@Test
	public void testGame() {
		Game test = new Game();
		// check if variables are correctly initialized and the get Methods
		assertEquals(100, test.getBalance(),0.01);
		assertEquals(100, test.getCpuBalance(),0.01);
		assertEquals(0, test.getMove(),0.01);
		assertEquals(0, test.getBet(),0.01);
		assertEquals(0, test.getCpuBet(),0.01);	
	}



	@Test
	public void testRoundOver() {
		Game test = new Game();
		test.setCpuBet(80);
		Card a = new Card("KING", (byte)10);
		Card b = new Card("TEN", (byte) 10);
		
		ArrayList<Card> playHand = new ArrayList<Card>();
		playHand.add(a);
		playHand.add(b);

		test.getUser().setPlayHand(playHand); // Set this as playHand
		ArrayList<Card> CPUHand = new ArrayList<Card>();
		CPUHand.add(a);
		test.getCpu().setPlayHand(CPUHand);
		test.roundOver();
		// Player should be winner so his balance should increase by 80
		
		// New balance of player is 100 + 80 (gain) = 180
		assertEquals(180, test.getBalance(), 0.1); 
		assertEquals(20, test.getCpuBalance(), 0.1); 
		
		// SWAP hand, so player should lose
		test.setBet(100);
		test.getCpu().setPlayHand(playHand);
		test.getUser().setPlayHand(CPUHand);
		test.roundOver();
		assertEquals(80, test.getBalance(), 0.1);
		assertEquals(120, test.getCpuBalance(), 0.1); 
		// Balance of 180 - 100 = 80
		
		// Testing Tie 
		test.getUser().setPlayHand(playHand);
		test.roundOver();
		assertEquals(80, test.getBalance(), 0.1);
		assertEquals(120, test.getCpuBalance(), 0.1); 
		// there should be no change since hands are equal
			
	}

	@Test
	public void testDraw() {
		Game test = new Game();
		test.draw();
		
		int HandSize = test.getUser().getPlayHand().size();
		assertEquals(2, HandSize);
		int CPUHandSize = test.getCpu().getPlayHand().size();
		assertEquals(2, CPUHandSize);
		
	}

	@Test
	public void testInstantBust() {
		Game test = new Game();
		Card a = new Card("KING", (byte)10);
		Card b = new Card("TEN", (byte) 10);
		Card c = new Card("QUEEN",(byte) 10);
		
		ArrayList<Card> playHand = new ArrayList<Card>();
		playHand.add(a);
		playHand.add(b);
		playHand.add(c);
				
		test.getUser().setPlayHand(playHand); // Set this as playHand
		assertEquals(true, test.instantBust());
		
		playHand.remove(2); // Hand is now 20
		test.getUser().setPlayHand(playHand);
		assertEquals(false, test.instantBust());
			
		
	}

	@Test
	public void testHit() {
		Game test = new Game();
		test.draw();
		
		int HandSize = test.getUser().getPlayHand().size();
		test.playerHit();
		assertEquals(3, HandSize + 1);
	}

	@Test
	public void testStand() {
		Game test = new Game();
		test.draw();
		test.playerStand();
		int HandSize = test.getUser().getPlayHand().size();
		assertEquals(2, HandSize);
	}

	@Test
	public void testCheckCpuBalance() {
		Game test = new Game();
		assertEquals(false,test.isCpuBalanceZero());
		
		test.setCpuBalance(0);
		assertEquals(true,test.isCpuBalanceZero());
		
		
		
		
	}



//	@Test
//	public void testStartGame() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testUpdateStatsWin() {
		Game test = new Game();
		test.setCpuBet(13);
		test.updateStatsWin();
		assertEquals(0,test.getUser().getLosses());
		assertEquals(1,test.getUser().getWins());
		assertEquals(13,test.getUser().getMoneyWon(), 0.1);
	}

	@Test
	public void testUpdateStatsLoss() {
		Game test = new Game();
		test.setBet(12);
		test.updateStatsLoss();
		assertEquals(0,test.getUser().getWins());
		assertEquals(1,test.getUser().getLosses());
		assertEquals(12,test.getUser().getMoneyLost(), 0.1);
		
	}

	@Test
	public void testUpdateBalance() {
		Game test = new Game();
		test.setBet(100);
		test.setCpuBet(12);
		
		test.updateBalance("WIN");
		// add 12 to player balance of 100
		assertEquals(112,test.getBalance(),0.1);
		test.updateBalance("LOSS");
		// remove 100 from player balance
		assertEquals(12, test.getBalance(),0.1);
		
		
	}

	@Test
	public void testSetGetBalance() {
		Game test = new Game();
		test.setBalance(61);
		assertEquals(61, test.getBalance(), 0.01);
	}



	@Test
	public void testSetGetCpuBalance() {
		Game test = new Game();
		test.setCpuBalance(60);
		assertEquals(60, test.getCpuBalance(), 0.01);
	}


	@Test
	public void testSetandGetCpuBet() {
		Game test = new Game();
		test.setCpuBet(6);
		assertEquals(6, test.getCpuBet(), 0.01);
	}



	@Test
	public void testSetBetandGetBet() {
		Game test = new Game();
		test.setBet(2);
		assertEquals(2, test.getBet(), 0.01);
	}


	@Test
	public void testSetMove() {
		// test get and set move
		Game test = new Game();
		test.setMove(2);
		assertEquals(2, test.getMove());
		
	}



}
