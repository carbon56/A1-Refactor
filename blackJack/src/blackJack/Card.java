package blackJack;

public class Card {
	private byte number;
	private String name;
	
	// Constructor for Card class 
	public Card (String name, byte number){
		this.number = number;
		this.name = name;	
	}
	
	public String toString(){
		return this.name + " " + this.number;
	}
	
	// Getter and Setters for number
	public byte getNumber() {
		return number;
	}
	public void setNumber(byte number) {
		this.number = number;
	}
	
	// Getter and Setters for suit
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
