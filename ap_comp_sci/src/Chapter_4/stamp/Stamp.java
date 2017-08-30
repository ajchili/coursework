public class Stamp
{

	public static final int FIRST_CLASS_STAMP_PRICE = 49;
	private int balance;

	public Stamp()
	{
		balance = 0;
	}

	public void insert(int dollars)
	{
		balance += 100 * dollars;
	}
	
	public int giveFirstClassStamps()
	{
		int firstClassStamps = balance / FIRST_CLASS_STAMP_PRICE;
		balance -= firstClassStamps * FIRST_CLASS_STAMP_PRICE;
		return firstClassStamps;
	}
	
	public int givePennyStamps()
	{
		int pennyStamps = balance;
		balance = 0;
		return pennyStamps;
	}
	
	public int getDollars()
	{
		return balance / 100;
	}
	
	public int getPennies()
	{
		int pennies = balance - ((balance / 100) * 100);
		return pennies;
	}

}
