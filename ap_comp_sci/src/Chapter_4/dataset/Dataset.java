import java.util.Scanner;
import java.util.Vector;

public class Dataset
{
	private Vector<Integer> dataset = new Vector<Integer>(0, 1);
	private int sum;
	private int min, max;

	public Dataset()
	{

	}

	public void getDataset()
	{
		System.out.println("Would you like to \"add\" an Integer, get the \"sum\", get the \"avg\", get the \"min\"\\\"max\", or \"stop\"?");
		Scanner s = new Scanner(System.in);
		String input;
		boolean run = true;
		while (run)
		{
			input = s.next();
			switch (input)
			{
			case "stop":
				run = false;
				break;
			case "add":
				System.out.print("Please enter an Integer to add: ");
				addValue(s.nextInt());
				break;
			case "sum":
				System.out.println("Sum of the dataset: " + getSum());
				break;
			case "avg":
				System.out.println("Average of the dataset: " + getAverage());
				break;
			case "min":
				System.out.println("The smallest number in the dataset is: " + getMin());
				break;
			case "max":
				System.out.println("The largest number in the dataset is: " + getMax());
				break;
			}
		}
		s.close();
	}

	public void addValue(int a)
	{
		dataset.addElement(a);
	}

	public int getSum()
	{
		sum = 0;
		if (dataset.capacity() > 0)
		{
			for (int i = 0; i < dataset.capacity(); i++)
			{
				sum = sum + dataset.get(i);
			}
			return sum;
		}
		return 0;
	}

	public double getAverage()
	{
		if (dataset.capacity() > 0)
		{
			return getSum() / dataset.capacity();
		}
		return 0;
	}

	public int getMin()
	{
		min = dataset.get(0);
		int a = 0;
		for (int i = 0; i < dataset.capacity(); i++)
		{
			min = Math.min(min, dataset.get(i));
		}
		return min;
	}

	public int getMax()
	{
		max = 0;
		int a = 0;
		for (int i = 0; i < dataset.capacity(); i++)
		{
			max = Math.max(max, dataset.get(i));
		}
		return max;
	}

}
