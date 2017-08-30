public class BankAccount
{
	private double balance;
	private int freeTransactions;

	public BankAccount()
	{
		balance = 0;
	}

	public BankAccount(double initialBalance)
	{
		balance = initialBalance;
	}
	

	public BankAccount(int freeTransactions)
	{
		this.freeTransactions = freeTransactions;
	}
	

	public BankAccount(double initialBalance, int freeTransactions)
	{
		balance = initialBalance;
		this.freeTransactions = freeTransactions;
	}

	public void deposit(double amount, double fee)
	{
		if (freeTransactions != 0) {
			balance = balance + amount;
			freeTransactions -= 1;
		} else {
			balance = balance + amount - fee;
		}
	}

	public void withdraw(double amount, double fee)
	{
		if (freeTransactions != 0) {
			balance = balance - amount;
			freeTransactions -= 1;
		} else {
			balance = balance - amount - fee;
		}
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
	
	public void setTransactions(int transactions) {
		freeTransactions = transactions;
	}
}
