public class Pair
{
	private double aFirst, aSecond;

	public Pair(double aFirst, double aSecond)
	{
		this.aFirst = aFirst;
		this.aSecond = aSecond;
	}

	public double getSum()
	{
		return aFirst + aSecond;
	}

	public double getDifference()
	{
		return aFirst - aSecond;
	}

	public double getProduct()
	{
		return aFirst * aSecond;
	}

	public double getAverage()
	{
		return (aFirst + aSecond) / 2;
	}

	public double getDistance()
	{
		if (getDifference() < 0)
		{
			return getDifference() * -1;
		}
		return getDifference();
	}

	public double getMax()
	{
		if (getDifference() < 0)
		{
			return aSecond;
		}
		return aFirst;
	}

	public double getMin()
	{
		if (getDifference() < 0)
		{
			return aFirst;
		}
		return aSecond;
	}

	public void print(String name, double number)
	{
		System.out.println(name + ": " + number);
	}

	public void out()
	{
		print("Sum of the two numbers", getSum());
		print("Difference of the two numbers", getDifference());
		print("Product of the two numbers", getProduct());
		print("Average of the two numbers", getAverage());
		print("Distance between the two numbers", getDistance());
		print("Largest number", getMax());
		print("Smallest number", getMin());
	}
}
