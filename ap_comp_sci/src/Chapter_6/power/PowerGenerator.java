public class PowerGenerator {

	private double factor = 0;
	
	public PowerGenerator(double factor) {
		this.factor = factor;
	}
	
	public double nextpower() {
		factor =  factor * 10;
		return factor;
	}
	
}
