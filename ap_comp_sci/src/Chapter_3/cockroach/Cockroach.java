public class Cockroach
{
	public static void main(String[] args)
	{
		Bug bug = new Bug(1);
		bug.move();
		bug.turn();
		bug.move();
		System.out.println(bug.getPosition());
		
		BugPopulation bp = new BugPopulation(1000);
		bp.breed();
		bp.spray();
		bp.spray();
		System.out.println(bp.getRoaches());
	}
}
