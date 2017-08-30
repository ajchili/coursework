import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BinaryConversion {

	private static boolean isRunning;
	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		isRunning = true;
		while(isRunning) {
			getChoice();
		}
	}
	
	private static void getChoice() {
		System.out.println("What would you like to do? \n1. Convert to Decimal \n2. Convert to Binary \n3. Quit");
		int input = s.nextInt();
		switch (input) {
			case 1:
				convertToDecimal();
				break;
			case 2:
				convertToBinary();
				break;
			case 3:
				isRunning = false;
				break;
 		}
	}

	private static void convertToDecimal() {
		System.out.print("Please enter your Binary value: ");
		String input = s.next();
		ArrayList<String> convert = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++) {
			convert.add((String) (input.subSequence(i, i + 1)));
		}
		Collections.reverse(convert);
		int decimal = 0;
		for (int i = 0; i < input.length(); i++) {
			if (convert.get(i).equals("1")) {
				decimal += Math.pow(2, i);
			}
		}
		System.out.println("Your Decimal number will be: " + decimal);
		space(3);
	}
	
	private static void convertToBinary() {
		System.out.print("Please enter your Decimal value: ");
		String input = s.next();
		int convert = Integer.parseInt(input);
		int binaryLogofNum;
		String binaryNum = "1";
		if (convert == 0) {
			System.out.println("Your Binary number will be: 0");
		} else {
			binaryLogofNum = (int) (Math.log(convert) / Math.log(2));
		    convert = convert - (int) Math.pow(2, binaryLogofNum);
			for (int i = binaryLogofNum - 1; i > -1; i--) {
				if (Math.pow(2, i) < convert || Math.pow(2, i) == convert) {
					convert = convert - (int) Math.pow(2, i);
					binaryNum = binaryNum + "1";
				} else {
					binaryNum = binaryNum + "0";						
				}
			}
		}
		System.out.println("Your Binary number will be: " + binaryNum);
		space(3);
	}
	
	
	private static void space(int size) {
		for (int i = 0; i < size; i++) {
			System.out.println();
		}
	}
}
