import java.util.ArrayList;
import java.util.Collections;

import javax.xml.stream.events.Characters;

public class Purse {

	private static final ArrayList<String> COINS = new ArrayList<String>();
	private static ArrayList<String> Purse = new ArrayList<String>();
	static {
		COINS.add("Penny");
		COINS.add("Nickel");
		COINS.add("Dime");
		COINS.add("Quarter");
	}
	private static ArrayList<String> Purse2 = new ArrayList<String>();
	static {
		Purse2.add("Penny");
		Purse2.add("Nickel");
		Purse2.add("Dime");
		Purse2.add("Quarter");
		Purse2.add("Penny");
		Purse2.add("Quarter");
		Purse2.add("Nickel");
		Purse2.add("Quarter");
		Purse2.add("Nickel");
	}
	
	public static void main(String[] args) {
		Purse.add(COINS.get(3));
		Purse.add(COINS.get(2));
		Purse.add(COINS.get(1));
		Purse.add(COINS.get(2));
		System.out.println("Original Purse: " + getCoins());
		reversePurse();
		System.out.println("Reversed Purse: " + getCoins()); 
		reversePurse();
		addPurse();
		System.out.println("Added Purse: " + getCoins());
	}
	
	private static String getCoins() {
		return Purse + "";
	}
	
	private static void reversePurse() {
		Collections.reverse(Purse);
	}
	
	private static void addPurse() {
		Purse.addAll(Purse2);
	}
	
}
