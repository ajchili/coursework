public class Employee
{
	String name;
	double currentSalary = 0;

	public Employee(String employeeName, double currentSalary)
	{
		this.currentSalary = currentSalary;
		name = employeeName;
	}

	public String getName()
	{
		return name;
	}

	public double getSalary()
	{
		return currentSalary;
	}

	public void raiseSalary(double byPercent)
	{
		currentSalary = currentSalary + (currentSalary * (byPercent / 100));
	}
}
