import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Midterm {

	private static JFrame frame;
	private static JScrollPane sPane;
	private static JTextArea text;
	
	public static void main(String[] args) {
		frame = new JFrame("Midterm");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(210, 570));
		frame.setLocationRelativeTo(null);
		
		setup();
		
		frame.setVisible(true);
	}
	
	private static void setup() {
		JPanel panel = new JPanel(null);
		frame.add(panel);
		
		final JSpinner spinner = new JSpinner();
		SpinnerModel model =
		        new SpinnerNumberModel(10,
		                               1,
		                               100,
		                               1);
		spinner.setModel(model);
		spinner.setBounds(10, 10, 50, 50);
		panel.add(spinner);
		
		JButton button = new JButton("Run");
		button.setBounds(70, 10, 125, 50);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.setText(loop((Integer)spinner.getValue()));
			}
		});
		panel.add(button);
		
		JButton button2 = new JButton("Clear");
		button2.setBounds(10, 480, 185, 50);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.setText(null);
			}
		});
		panel.add(button2);

		text = new JTextArea(3, 10);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);  
		text.setEditable(false);
		
		sPane = new JScrollPane();
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPane.setBounds(10, 70, 185, 400);
		sPane.setViewportView(text);
		panel.add(sPane);
		
	}
	
	private static String loop(int length) {
		String text = "";
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				text = "" + (i + 1);
			} else {
				text = text + "\n" + (i + 1);
				
			}
		}
		System.out.println(text);
		return text;
	}
	
}
