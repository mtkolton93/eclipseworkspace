package telephone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Telephone
{
	//Global Variables
	private JTextField textField = new JTextField();
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	private JButton button4 = new JButton("4");
	private JButton button5 = new JButton("5");
	private JButton button6 = new JButton("6");
	private JButton button7 = new JButton("7");
	private JButton button8 = new JButton("8");
	private JButton button9 = new JButton("9");
	
	public static void main(String[] args)
	{
		new Telephone();
	}
	
	public Telephone()
	{
		GUILayout();
	}
	
	private void GUILayout()
	{
		//Frame
		JFrame frame = new JFrame("Telephone");
		frame.setSize(400, 400);
		JPanel mainPanel = new JPanel();
		frame.add(mainPanel);
		frame.setVisible(true);
		
		//Text panel
		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(400, 100));
		textPanel.add(textField);
		
		//Buttons
		JPanel buttonPanel = new JPanel();
		GridLayout buttonLayout = new GridLayout(3, 3);
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.setPreferredSize(new Dimension(200, 200));
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(button7);
		buttonPanel.add(button8);
		buttonPanel.add(button9);
		
		//Padding panels
		JPanel westPad = new JPanel();
		westPad.setPreferredSize(new Dimension(100, 400));
		JPanel eastPad = new JPanel();
		eastPad.setPreferredSize(new Dimension(100, 400));
		JPanel southPad = new JPanel();
		southPad.setPreferredSize(new Dimension(400, 100));
		
		//Main panel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		mainPanel.add(southPad, BorderLayout.SOUTH);
		mainPanel.add(eastPad, BorderLayout.EAST);
		mainPanel.add(westPad, BorderLayout.WEST);
		mainPanel.add(textPanel, BorderLayout.NORTH);
	}
}
