public class CashRegister
{
	private double balance;
	private double amountDue;
	private int count = 0;

	public void recordPurchace(double amountDue)
	{
		this.amountDue = amountDue;
		count++;
	}

	public void enterDollars(int dollars)
	{
		balance += dollars;
	}

	public void enterQuarters(int quarters)
	{
		balance += (quarters * .25);
	}

	public void enterDimes(int dimes)
	{
		balance += (dimes * .1);
	}

	public void enterNickels(int nickels)
	{
		balance += (nickels * .05);
	}

	public void enterPennies(int pennies)
	{
		balance += (pennies * .01);
	}

	public String giveChange()
	{
		if ((balance - amountDue) < 0)
		{
			return "You owe: " + (-1 * (balance - amountDue));
		}
		return "Your change is: " + (balance - amountDue);
	}

	public int getItemCount()
	{
		return count;
	}

}
