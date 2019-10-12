package blackJack;

import java.util.Scanner;

public class Game {
	// Instance variables for Game Class
	private PlayerScore user = new PlayerScore();
	private Cpu cpu = new Cpu();
	private float balance = 100;
	private float cpuBalance = 100;
	private float cpuBet = 0;
	private float bet = 0;

	private Card[] playingDeck = new Deck().getShuffledDeck();
	private Scanner input = new Scanner(System.in);
	private Scanner moveinput = new Scanner(System.in);
	private int move = 0;

	public static void main(String[] args) {
		Game ONE = new Game();
		ONE.startGame();

	}

	public Game() {
		
	}

	public void collectBet() {
		System.out.print("Money: $" + balance + " Place your bet: $");
		bet = input.nextFloat();

		if (bet > balance) {
			System.out.println("You do not have enough money");
			System.out.println("Please place a smaller bet");
			bet = input.nextFloat();
		}

	}

	public void roundOver() {
		if (cpu.handValue() > user.handValue() && cpu.handValue() <= 21) {
			System.out.println("Dealer has won!");
			updateBalance("LOSS");
			updateStatsLoss();
		}

		else if (user.handValue() == cpu.handValue()) {
			System.out.println("Tie between Player and Dealer!");


		}

		else if (user.handValue() <= 21) {
			System.out.println("Player has won!");
			updateBalance("WIN");
			updateStatsWin();

		}

	}

	public void draw() {
		user.drawHand("DRAW", playingDeck);
		System.out.println("Your Hand has a value of " + user.handValue() + ":");
		user.showHand();
		cpuBet = cpu.cpuBets(cpuBalance);
		System.out.println("Dealer's Balance: $" + cpuBalance + "   Dealer bets: $" + cpuBet);
		cpu.drawHand("DRAW", playingDeck);
		cpu.showCard();
	}

	public boolean instantBust() {
		if (user.handValue() > 21) {
			updateBalance("LOSS");
			updateStatsLoss();

			return true;
		}
		return false;

	}

	public void hit() {
		if (move == 1) {
			user.drawHand("HIT", playingDeck);
			System.out.println("Your current hand is valued at " + user.handValue());
			user.showHand();

		}

	}

	public void stand() {
		if (move == 2) {
			System.out.println("Dealer's Hand is revealed:");
			System.out.println("Dealer's hand is valued at " + cpu.handValue());
			cpu.showHand();
			// 4. Can use for feature envy later, 3. use enumeration for cards later
			while (cpu.handValue() <= 17) {
				System.out.println("Dealer hits");
				cpu.drawHand("HIT", playingDeck);
				System.out.println("Dealer's new hand is valued at " + cpu.handValue());
				cpu.showHand();

				if (cpu.handValue() == 21) {
					break;
				}
			}

		}

	}

	public boolean checkCpuBalance() {
		if (cpuBalance == 0) {
			System.out.println("Dealer has no money");
			System.out.println("You Win!");
			return true;
			
			// break;
		}
		return false;

	}

	public void checkMove() {

		move = moveinput.nextInt();
		if (move != 2 && move != 1) {
			System.out.println("Invalid Input");
			System.out.println("Please enter 1 for HIT or 2 for STAND:");
			move = moveinput.nextInt();

		}

	}

	public void startGame() {
		System.out.println("Let's play BlackJack");
		// Game Loop
		while (balance > 0) {
			collectBet();
			draw();

			System.out.println("Please enter 1 for HIT or 2 for STAND:");
			checkMove();

			while (move != 2) {
				hit();
				if (instantBust()) {
					System.out.println("BUST! Dealer has won");
					break;
				}

				System.out.println("Please enter 1 for HIT or 2 for STAND:");
				checkMove();

			}
			stand();
			roundOver(); // Round is over, check for if players wins, loses, or ties
			if(checkCpuBalance() == true) {
				System.exit(0);
			}; 
			// Money is won/loss - check if CPU has money left

		}
		System.out.println("You have no more money. It's Game Over");

	}

	public void updateStatsWin() {
		float moneyGain = user.getMoneyWon();
		user.setMoneyWon(moneyGain + cpuBet);
		int wins = user.getWins();
		user.setWins(wins += 1);
		user.displayStats();

	}

	public void updateStatsLoss() {
		float moneyLoss = user.getMoneyLost();
		user.setMoneyLost(moneyLoss += bet);
		int losses = user.getLosses();
		user.setLosses(losses += 1);
		user.displayStats();

	}

	public void updateBalance(String status) {
		if (status.equals("WIN")) {
			balance += cpuBet;
			cpuBalance -= cpuBet;

		} else {
			cpuBalance += bet;
			balance -= bet;
		}

	}
	
	
	// Getters and Setters 
	public PlayerScore getUser() {
		return user;
	}

	public void setUser(PlayerScore user) {
		this.user = user;
	}

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getCpuBalance() {
		return cpuBalance;
	}

	public void setCpuBalance(float cpuBalance) {
		this.cpuBalance = cpuBalance;
	}

	public float getCpuBet() {
		return cpuBet;
	}

	public void setCpuBet(float cpuBet) {
		this.cpuBet = cpuBet;
	}

	public float getBet() {
		return bet;
	}

	public void setBet(float bet) {
		this.bet = bet;
	}

	public Card[] getPlayingDeck() {
		return playingDeck;
	}

	public void setPlayingDeck(Card[] playingDeck) {
		this.playingDeck = playingDeck;
	}

	public Scanner getInput() {
		return input;
	}

	public void setInput(Scanner input) {
		this.input = input;
	}

	public Scanner getMoveinput() {
		return moveinput;
	}

	public void setMoveinput(Scanner moveinput) {
		this.moveinput = moveinput;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}
	

}
