import java.util.Scanner;

public class PairTester
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter a number: ");
		double a = s.nextDouble();
		System.out.print("Please enter another number: ");
		double b = s.nextDouble();
		s.close();
		Pair pair = new Pair(a, b);
		pair.out();
	}
}
