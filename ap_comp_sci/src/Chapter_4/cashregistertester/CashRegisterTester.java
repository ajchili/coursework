public class CashRegisterTester
{
	public static void main(String[] args)
	{
		CashRegister register = new CashRegister();
		register.recordPurchace(20.37);
		register.enterDollars(20);
		register.enterQuarters(2);
		System.out.println(register.giveChange() + " : " + register.getItemCount());
		register.recordPurchace(20.37);
		register.enterDollars(20);
		register.enterQuarters(2);
		System.out.println(register.giveChange() + " : " + register.getItemCount());
		register.recordPurchace(20.37);
		register.enterDollars(20);
		register.enterQuarters(2);
		System.out.println(register.giveChange() + " : " + register.getItemCount());
		register.recordPurchace(20.37);
		register.enterDollars(20);
		register.enterQuarters(2);
		System.out.println(register.giveChange() + " : " + register.getItemCount());
		register.recordPurchace(20.37);
		register.enterDollars(20);
		register.enterQuarters(2);
		System.out.println(register.giveChange() + " : " + register.getItemCount());
		System.out.println("Expected: 0.13");
	}
}
