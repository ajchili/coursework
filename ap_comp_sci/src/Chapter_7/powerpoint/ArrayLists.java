import java.util.ArrayList;
import java.util.Scanner;

public class ArrayLists {

	public static void main(String[] args) {
		double[] input = new double[10];
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 6; i++) {
			double a = in.nextDouble();
			input[i] = a;
		}
		for(int j = 0; j < input.length; j++) {
			System.out.println(input[j]);
		}
	}
	
}
