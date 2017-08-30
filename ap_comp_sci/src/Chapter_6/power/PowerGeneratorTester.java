public class PowerGeneratorTester {

	public static void main(String[] args) {
		PowerGenerator pg = new PowerGenerator(1);
		System.out.println(1.0);	
		for (int i = 0; i < 11; i++) {
			System.out.println(pg.nextpower());			
		}
	}
	
}
