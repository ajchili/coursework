public class Time
{

	private int hours, minutes, seconds;

	public Time(int hours, int minutes, int seconds)
	{
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public void setHour(int hours)
	{
		this.hours = hours;
	}

	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}

	public int getTime()
	{
		return (((hours * 60) * 60) + (minutes * 60) + seconds);
	}

}
