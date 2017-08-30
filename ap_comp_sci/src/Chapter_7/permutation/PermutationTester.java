public class PermutationTester {

	public static void main(String[] args) {
		PermutationGenerator gen = new PermutationGenerator(10);      
	      
	      for (int i = 1; i <= 10; i++)
	      {  
	         for (int n : gen.nextPermutation())
	            System.out.print(" " +  n);
	         System.out.println();
	      }
	}
	
}
