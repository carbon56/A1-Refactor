package blackJack;

public class PlayerScore extends Player{
	private int wins = 0;
	private int losses = 0;
	private float moneyWon = 0;
	private float moneyLost = 0;
	
	
	public PlayerScore() {
		super();	// Call the parent constructor 
		
	}
	
	public void displayStats() {
		System.out.println("Rounds Won: " + wins + " Rounds Loss: " + losses +" Gain: $" + moneyWon + " Loss: $" + moneyLost);
	}
	
	
	// Getters and Setters 
	public float getMoneyLost() {
		return moneyLost;
	}
	public void setMoneyLost(float moneyLost) {
		this.moneyLost = moneyLost;
	}
	public float getMoneyWon() {
		return moneyWon;
	}
	public void setMoneyWon(float moneyWon) {
		this.moneyWon = moneyWon;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	
	
}
