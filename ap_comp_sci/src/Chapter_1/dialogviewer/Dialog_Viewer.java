import javax.swing.JOptionPane;

public class Dialog_Viewer
{
	public static void main(String[] args)
	{
		String name = JOptionPane.showInputDialog("What is your name?");
		String input = JOptionPane.showInputDialog("What can I do for ya?");
		JOptionPane.showMessageDialog(null, "I'm sorry, " + name + " , I am unable to do " + input + ".");
		System.exit(0);
	}
}
