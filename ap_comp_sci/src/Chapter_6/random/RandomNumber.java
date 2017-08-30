import java.util.Random;
import java.util.Vector;

public class RandomNumber {
	
	private static Random r = new Random();
	private static Vector<Integer> rng = new Vector<Integer>(); 

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			int a = generate(1000); 
			rng.add(a);
		}
		double avg = getAverage();
		int min = getMin();
		System.out.println(rng);
		System.out.println("Average: " + avg);
		System.out.println("Minimum: " + min);
	}
	
	public static int generate(int max) {
		int number = r.nextInt(max) + 1;
		return number;
	}
	
	public static int getSum()
	{
		int sum = 0;
		if (rng.capacity() > 0)
		{
			for (int i = 0; i < 100; i++)
			{
				sum = sum + rng.get(i);
			}
			return sum;
		}
		return 0;
	}
	
	public static double getAverage()
	{
		if (rng.capacity() > 0)
		{
			return getSum() / rng.capacity();
		}
		return 0;
	}
	
	public static int getMin()
	{
		int min = rng.get(0);
		int a = 0;
		for (int i = 0; i < 100; i++)
		{
			min = Math.min(min, rng.get(i));
		}
		return min;
	}
	
}
