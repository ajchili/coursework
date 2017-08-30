public class SavingsAccount
{
	private double balance;

	public SavingsAccount()
	{
		balance = 0;
	}

	public SavingsAccount(double initialBalance)
	{
		balance = initialBalance;
	}
	
	public SavingsAccount(double initialBalance, double rate)
	{
		balance = initialBalance + (initialBalance * (rate / 100));
	}

	public void deposit(double amount)
	{
		balance = balance + amount;
	}

	public void withdraw(double amount)
	{
		balance = balance - amount;
	}

	public double getBalance()
	{
		return balance;
	}
	
	public double addInterest(double rate) 
	{
		balance = balance + (balance * (rate / 100));
		return balance;
	}
}
