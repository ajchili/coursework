import java.util.Scanner;

public class NamePrinter
{
	public static void main(String[] args)
	{
		//A Program used to get a username and display a box around it
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("Please enter your name: ");
		String name = s.nextLine();
		s.close();
		printLine(name);
		System.out.println("|" + name + "|");
		printLine(name);
	}

	public static void printLine(String name)
	{
		System.out.print("+");
		for (int i = 0; i < name.length(); i++)
		{
			System.out.print("-");
		}
		System.out.println("+");
	}
}
