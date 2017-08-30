public class CountingChange
{

	public static void main(String[] args)
	{
		Money money = new Money(25, 64, 5, 87);
		System.out.println("# of Dollars in the jar: " + money.printDollars());
		System.out.println("Leftover Change in the jar: " + money.printChange());
	}

}
