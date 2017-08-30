public class StampTester
{

	public static void main(String[] args)
	{
		Stamp s = new Stamp();
		s.insert(2);
		System.out.print("First Class Stamps: ");
		System.out.println(s.giveFirstClassStamps());
		System.out.println("Expected: 4");
		System.out.println("Dollars left over: " + s.getDollars() + "\nPennies left over: " + s.getPennies());
		System.out.print("Penny Stamps: ");
		System.out.println(s.givePennyStamps());
		System.out.println("Expected: 4");
		System.out.println("Dollars left over: " + s.getDollars() + "\nPennies left over: " + s.getPennies());
	}
	
}
