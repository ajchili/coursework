import java.util.Scanner;
import java.util.Vector;

public class Temperature {

	private static Vector<Double> temperature = new Vector<Double>(0, 1);
	
	public static void main(String[] args) {
		getTemp();
		double max = getMaxTemp();
		for (int i = 0; i < 12; i++) {
			if (max == temperature.get(i)) {
				System.out.println("The max temperature was in month " + (i + 1) + ", with a temperature of " + max + ".");
			}
		}
	}
	
	public static void getTemp() {
		Scanner s = new Scanner(System.in);
		for (int i = 0; i < 12; i++) {
			System.out.print("Please enter a temperature for month " + (i + 1) +": ");
			temperature.add(s.nextDouble());
		}
		s.close();
	}
	
	public static double getMaxTemp() {
		double max = 0;
		for (int i = 0; i < 12; i++) {
			max = Math.max(max, temperature.get(i));
		}
		return max;
	}
	
}
