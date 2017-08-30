public class BankAccountTester {
	public static void main(String[] args) {
		BankAccount harrysChecking = new BankAccount(4);
		SavingsAccount momsSavings = new SavingsAccount(1000, 10);
		harrysChecking.deposit(1000, 10);
		harrysChecking.withdraw(500, 5);
		harrysChecking.withdraw(400, 5);
		System.out.println("Harry's Checking: " + harrysChecking.getBalance());
		System.out.println("Harry's Checking Expected: 100");
		System.out.println("Mom's Saving: " + momsSavings.getBalance());
		System.out.println("Mom's Saving Expected: 1100");
	}
}
