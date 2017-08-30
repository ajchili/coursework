public class Bug
{
	private int position;
	
	public Bug(int initialPosition)
	{
		position = initialPosition;
	}

	public void turn()
	{

	}

	public void move()
	{
		position += 1;
	}
	
	public int getPosition() {
		return position;
	}
}
