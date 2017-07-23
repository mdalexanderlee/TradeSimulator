import java.util.Random;

public class Stock {
	private double price;
	private double volatility; //the maximum change in a stock price
	private int currentHoldTime; //superficial - current time
	private int maxHoldTime; //superficial - time until volatility changes
	private Random gen;
	
	public Stock(double price, double volatility, int holdTime) {
		this.price = price;
		this.volatility = volatility;
		this.maxHoldTime = holdTime;
		this.currentHoldTime = 0;
		gen = new Random((int)Math.random() * 1000);
	}
	public double getPrice() {
		return price;
	}
	
	private void updateVola() {
		this.volatility = gen.nextDouble();
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setVola(double volatility) {
		this.volatility = volatility;
	}
	
	public void setMaxHold(int maxHoldTime) {
		this.maxHoldTime = maxHoldTime;
	}
	
	public void refresh() {
		if(currentHoldTime == maxHoldTime) {
			currentHoldTime = 0;
			updateVola();
		}
		double rnd, change_percent, change_amount;
		rnd = gen.nextDouble(); 
		change_percent = 2 * volatility * rnd;
		if (change_percent > volatility)
		    change_percent -= (2 * volatility);
		change_amount = price * change_percent;
		price += change_amount;
		currentHoldTime++;
	}
	
	public String toString() {
		return Double.toString(price);
	}
}
