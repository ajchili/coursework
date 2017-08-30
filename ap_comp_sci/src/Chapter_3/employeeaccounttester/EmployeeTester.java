public class EmployeeTester
{
	public static void main(String[] args)
	{
		Employee harry = new Employee("Harry", 5000);
		harry.raiseSalary(4);
		System.out.println(harry.getSalary());
	}
}
