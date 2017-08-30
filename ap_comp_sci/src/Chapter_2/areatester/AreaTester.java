import java.awt.Rectangle;
import java.util.Scanner;

public class AreaTester
{
	static int x, y, width, height;

	public static void main(String[] args)
	{
		// A Program used to determine a box's dimensions and displays the area and dimensions

		Scanner s = new Scanner(System.in);

		System.out.print("Please Enter a X Value: ");
		x = s.nextInt();
		System.out.print("Please Enter a Y Value: ");
		y = s.nextInt();
		System.out.print("Please Enter a width Value: ");
		width = s.nextInt();
		System.out.print("Please Enter a height Value: ");
		height = s.nextInt();
		s.close();

		Rectangle box = new Rectangle(x, y, width, height);

		System.out.println("\n\nArea of Box: " + (box.getWidth() * box.getHeight()));
		System.out.println("X Position of Box: " + box.getX());
		System.out.println("Y Position of Box: " + box.getY());
		System.out.println("Width of Box: " + box.getWidth());
		System.out.println("Height of Box: " + box.getHeight());
		
	}
}
