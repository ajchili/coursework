import java.util.ArrayList;
import java.util.Random;

public class PermutationGenerator {

	private int length;
	
	public PermutationGenerator(int length) {
		this.length = length;
	}
	
	public int[] nextPermutation() {
		int[] nextPermutation = new int[length];
		ArrayList<Integer> possiblePermutation = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			possiblePermutation.add(i);
		}
		Random random = new Random();
		
		for (int i = 0; i < length; i++) {
			int rand = random.nextInt(possiblePermutation.size());
			nextPermutation[i] = possiblePermutation.get(rand);
			possiblePermutation.remove(rand);
		}
		
		return nextPermutation;
	}
	
}
