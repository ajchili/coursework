public class BugPopulation
{
	private int population;

	public BugPopulation(int population)
	{
		this.population = population;
	}

	public void breed()
	{
		population = population * 2;
	}

	public void spray()
	{
		population = (int) (population * .9);
	}

	public int getRoaches()
	{
		return population;
	}
	
}
