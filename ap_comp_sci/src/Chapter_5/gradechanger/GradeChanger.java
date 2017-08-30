public class GradeChanger
{

	private double grade;

	public GradeChanger(double grade)
	{
		this.grade = grade;
	}

	public String calculate()
	{
		if (grade >= 4.15)
		{
			return "A+";
		} else if (grade >= 4)
		{
			return "A";
		} else if (grade >= 3)
		{
			return "B";
		} else if (grade >= 2)
		{
			return "C";
		} else if (grade >= 1)
		{
			return "D";
		}
		return "F";
	}

}
