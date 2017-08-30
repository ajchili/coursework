import java.util.Scanner;

public class MagpieTester {

	public static void main(String[] args) {

		Magpie maggie = new Magpie();
		
		System.out.println(maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		
		while (!statement.equalsIgnoreCase("bye")) {
			System.out.println (maggie.getResponse(statement));
			statement = in.nextLine();
		}
		
		System.out.println(maggie.getFarewell());
	}
	
}
