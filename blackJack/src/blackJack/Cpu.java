package blackJack;

public class Cpu extends Player {

	public void showCard(){
		System.out.println("CPU has drawn: [Hidden]" + " and " + getPlayHand().get(0).getName());
	}
	
	public void showCpuHand() {
		for (int i = 0; i < playHand.size(); i++){
			System.out.println(playHand.get(i).getName());
		}
		
	}
	
	
	public void strategy(Card[] drawDeck) {
		while(super.getHandValue() < 17) {
			super.drawHand("HIT", drawDeck);
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