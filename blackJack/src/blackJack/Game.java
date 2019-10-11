package blackJack;

import java.util.Scanner;

public class Game {
	private PlayerScore user = new PlayerScore();
	private Cpu cpu = new Cpu();
	float balance = 100;
	float cpuBalance = 100;
	float cpuBet = 0;
	float bet = 0;

	public static void main(String[] args) {
		Game ONE = new Game();
	}

	public Game() {
		startGame();
	}

	public void startGame() {
		System.out.println("Let's play BlackJack");


		Card[] playingDeck = new Deck().getShuffledDeck();
		Scanner input = new Scanner(System.in);
		Scanner moveinput = new Scanner(System.in);
		int move = 0;

		// Game Loop
		while (balance > 0) {
			System.out.print("Money: $" + balance + " Place your bet: $");
			bet = input.nextFloat();

			if (bet > balance) {
				System.out.println("You do not have enough money");
				System.out.println("Please place a smaller bet");
				bet = input.nextFloat();
			}

			user.drawHand("DRAW", playingDeck);
			System.out.println("Your Hand has a value of " + user.handValue() + ":");
			user.showHand();
			cpuBet = cpu.cpuBets(cpuBalance);
			System.out.println("Dealer's Balance: $" + cpuBalance + "   Dealer bets: $" + cpuBet);
			cpu.drawHand("DRAW", playingDeck);
			cpu.showCard();

			if (user.handValue() == 21) {
				updateBalance("WIN");
				updateStatsWin();

				if (cpuBalance == 0) {
					System.out.println("Dealer has no money");
					System.out.println("You Win!");
					System.exit(0);
				}
			}

			if (cpu.handValue() == 21) {
				System.out.println("You Lose!");
				updateBalance("LOSS");
				updateStatsLoss();
			}

			else {
				System.out.println("Please enter 1 for HIT or 2 for STAY:");
				move = moveinput.nextInt();

				while (move != 2) {
					if (move == 1) {
						user.drawHand("HIT", playingDeck);
						System.out.println("Your current hand is valued at " + user.handValue());
						user.showHand();
						if (user.handValue() == 21) {
							System.out.println("You win!");
							updateBalance("WIN");
							updateStatsWin();

							if (cpuBalance == 0) {
								System.out.println("Dealer has no money");
								System.out.println("You Win!");
								System.exit(0);
							}
							break;
						}

						if (user.handValue() > 21) {
							System.out.println("BUST! Dealer has won");
							updateBalance("LOSS");
							updateStatsLoss();

							break;
						}

						System.out.println("Please enter 1 for HIT or 2 for STAY:");
						move = moveinput.nextInt();
					}

				}
				if (move == 2) {
					System.out.println("Dealer's Hand is revealed:");
					cpu.showHand();
					// Logic of Dealer
					while (cpu.handValue() <= 17) {
						System.out.println("Dealer hits");
						cpu.drawHand("HIT", playingDeck);
						System.out.println("Dealer's new hand is valued at " + cpu.handValue());
						cpu.showHand();

						if (cpu.handValue() == 21) {
							break;
						}
					}

					if (cpu.handValue() > user.handValue() && cpu.handValue() <= 21) {
						System.out.println("Dealer has won!");
						updateBalance("LOSS");
						updateStatsLoss();
					}

					else {
						System.out.println("Player has won!");
						updateBalance("WIN");
						updateStatsWin();

						if (cpuBalance == 0) {
							System.out.println("Dealer has no money");
							System.out.println("You Win!");

							System.exit(0);
							break;
						}

					}

				}
			}
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

	void updateBalance(String status) {
		if (status.equals("WIN")) {
			balance += cpuBet;
			cpuBalance -= cpuBet;

		} else {
			cpuBalance += bet;
			balance -= bet;
		}

	}
	
	

}
