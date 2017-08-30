public class Money
{

	private int quarters, dimes, nickels, pennies;
	private double money, dollars, change;
	
	public Money(int quarters, int dimes, int nickels, int pennies) {
		this.quarters = quarters;
		this.dimes = dimes;
		this.nickels = nickels;
		this.pennies = pennies;
		getCash();
	}
	
	private void getCash() {
		for (int i = 0; i < quarters; i++) {
			money += .25;
		}
		for (int i = 0; i < dimes; i++) {
			money += .10;
		}
		for (int i = 0; i < nickels; i++) {
			money += .05;
		}
		for (int i = 0; i < pennies; i++) {
			money += .01;
		}
		while (money > 1) {
			money -= 1;
			dollars += 1;
		}
		change = money;
	}
	
	public double printCash() {
		return dollars + change;
	}
	
	public double printDollars() {
		return dollars;
	}
	
	public double printChange() {
		return change;
	}
	
}
