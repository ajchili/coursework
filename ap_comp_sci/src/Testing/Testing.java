public class Testing {
	
	public static void main(String[] args)
	{
		System.out.print("This is factorial of yo mama: " + factorial(4));
	}
	
	static int factorial(int num) {
		if (num == 1) {
			return 1;
		} else {
			return num * factorial(num-1);
		}	
	}
}
