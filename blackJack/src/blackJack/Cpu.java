package blackJack;

public class Cpu extends Player {

	public void showCard(){
		System.out.println("CPU has drawn: [Hidden]" + " and " + getPlayHand().get(0).getName());
	}
	
	
	public void strategy(Card[] drawDeck) {
		while(super.handValue() < 17) {
			System.out.println("Dealer hits");
			super.drawHand("HIT", drawDeck);
			System.out.println("Dealer's new hand is valued at " + super.handValue());
			super.showHand();

			if (super.handValue() == 21) {
				break;
			}
		}
	}
	
	
	
	
	public float cpuBets(float balance) {
		double random = Math.random();
		if(balance <= 5) {
			return balance;
		}
		else if (random == 0) {
			return balance;
			
		}else {
			return (float) ((int)(random*balance));
		}
	}
	
	
	

}
