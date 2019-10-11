package blackJack;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Game ONE = new Game();
	}
	
	public Game(){
		startGame();
	}
    	
	public void startGame(){
		System.out.println("Let's play BlackJack");
		PlayerScore user = new PlayerScore();
		Cpu cpu = new Cpu();
		
        Card[] playingDeck = new Deck().getShuffledDeck();
		
		float balance = 100;
		float cpuBalance = 100;
		Scanner input = new Scanner(System.in);
		Scanner moveinput = new Scanner(System.in);
		int move = 0;
		
		float bet = 0;
		int wins = 0;
		int losses = 0;
		float moneyGain = 0;
		float moneyLoss = 0;
		float cpuBet = 0;
		
		//Game Loop
		while(balance > 0){
			System.out.print("Money: $" + balance + " Place your bet: $");
			bet = input.nextFloat();
			
			if(bet > balance){
				System.out.println("You do not have enough money");
				System.out.println("Please place a smaller bet");
				bet = input.nextFloat();
			}
			
			user.drawHand("DRAW",playingDeck);
			System.out.println("Your Hand has a value of " + user.handValue() + ":");
			user.showHand();
			// add this to CPU class 
			//cpu.drawHand("DRAW");
			//System.out.println("CPU has drawn: [Hidden]" + " and " + cpu.showCard().getName());
			cpuBet = cpu.cpuBets(cpuBalance);
			System.out.println("Dealer's Balance: $" + cpuBalance + "   Dealer bets: $" + cpuBet);
			cpu.drawHand("DRAW", playingDeck);
			//System.out.println("Here is show CPU hand");
			cpu.showCard();
			
			if(user.handValue() == 21){
				System.out.println("You Win!");
				balance += cpuBet;
				cpuBalance -= cpuBet;
				
				moneyGain = user.getMoneyWon();
				user.setMoneyWon(moneyGain + cpuBet);
				
				wins = user.getWins();
				user.setWins(wins += 1);
				user.displayStats();
				
				if(cpuBalance == 0){
					System.out.println("Dealer has no money");
					System.out.println("You Win!");
					System.exit(0);
			}
			}
			
			if(cpu.handValue() == 21){
				System.out.println("You Lose!");
				balance -= bet ;
				cpuBalance += bet;
				
				
				moneyLoss = user.getMoneyLost();
				user.setMoneyLost(moneyLoss += bet);
				
				losses = user.getLosses();
				user.setLosses(losses += 1);
				user.displayStats();
			}
			
			else{
				System.out.println("Please enter 1 for HIT or 2 for STAY:");
				move = moveinput.nextInt();
				
				while(move != 2){
					if(move == 1){
						user.drawHand("HIT", playingDeck);
						System.out.println("Your current hand is valued at " + user.handValue());
						user.showHand();
						if (user.handValue() == 21){
							System.out.println("You win!");
							balance += cpuBet;
							cpuBalance -= cpuBet;
							
							moneyGain = user.getMoneyWon();
							user.setMoneyWon(moneyGain + cpuBet);
							
							wins = user.getWins();
							user.setWins(wins += 1);
							
							user.displayStats();
							
							
							if(cpuBalance == 0){
								System.out.println("Dealer has no money");
								System.out.println("You Win!");
								System.exit(0);
							}
							break;
						}
						
						if (user.handValue() > 21){
							System.out.println("BUST! Dealer has won");
							balance -= bet;
							
							moneyLoss = user.getMoneyLost();
							user.setMoneyLost(moneyLoss += bet);
							
							losses = user.getLosses();
							user.setLosses(losses += 1);
							
							user.displayStats();
							
							
							
							break;
						}
						
						System.out.println("Please enter 1 for HIT or 2 for STAY:");
						move = moveinput.nextInt();
					}
			
			
		}		
				if(move == 2){
					System.out.println("Dealer's Hand is revealed:");
					cpu.showCpuHand();
					// Logic of Dealer 
					while (cpu.handValue() <= 17){
						System.out.println("Dealer hits");
						cpu.drawHand("HIT", playingDeck);
						System.out.println("Dealer's new hand is valued at " + cpu.handValue());
						cpu.showCpuHand();
						
						if (cpu.handValue() == 21){
							break;
						}
					}
					
					if (cpu.handValue() > user.handValue() && cpu.handValue() <= 21){
						System.out.println("Dealer has won!");
						cpuBalance += bet;
						balance -= bet;
						
						moneyLoss = user.getMoneyLost();
						user.setMoneyLost(moneyLoss += bet);
						
						losses = user.getLosses();
						user.setLosses(losses += 1);
						user.displayStats();
					}
					
					else{
						System.out.println("Player has won!");
						balance += cpuBet;
						cpuBalance -= cpuBet;
						
						moneyGain = user.getMoneyWon();
						user.setMoneyWon(moneyGain + cpuBet);
						
						wins = user.getWins();
						user.setWins(wins += 1);
						user.displayStats();
						
						if(cpuBalance == 0){
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
	

}

	
	
	




