package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Calculator implements ActionListener
{
	//Global Variables
	private JTextField textField = new JTextField();
	private StringBuffer input = new StringBuffer();
	private JButton button0 = new JButton("0");
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	private JButton button4 = new JButton("4");
	private JButton button5 = new JButton("5");
	private JButton button6 = new JButton("6");
	private JButton button7 = new JButton("7");
	private JButton button8 = new JButton("8");
	private JButton button9 = new JButton("9");
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton mult = new JButton("x");
	private JButton divide = new JButton("/");
	private JButton mod = new JButton("%");
	private JButton decimal = new JButton(".");
	private JButton clear = new JButton("C");
	private JButton bkspc = new JButton("<--");
	private JButton sign = new JButton("+/-");
	private JButton enter = new JButton("=");
	private double result = 0;

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		new Calculator();
	}

	public Calculator()
	{
		button0.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		mult.addActionListener(this);
		divide.addActionListener(this);
		mod.addActionListener(this);
		decimal.addActionListener(this);
		clear.addActionListener(this);
		bkspc.addActionListener(this);
		sign.addActionListener(this);
		enter.addActionListener(this);

		GUILayout();
	}

	private void GUILayout()
	{
		//Frame
		JFrame frame = new JFrame("Calculator");
		frame.setSize(400, 400);
		JPanel mainPanel = new JPanel();
		frame.add(mainPanel);
		frame.setVisible(true);

		//Text panel
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.setPreferredSize(new Dimension(300, 100));
		textPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(textField, BorderLayout.CENTER);

		//Buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 4, 3, 3));
		buttonPanel.setPreferredSize(new Dimension(200, 200));
		buttonPanel.add(clear);
		buttonPanel.add(bkspc);
		buttonPanel.add(mod);
		buttonPanel.add(divide);
		buttonPanel.add(button7);
		buttonPanel.add(button8);
		buttonPanel.add(button9);
		buttonPanel.add(mult);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(minus);
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(plus);
		buttonPanel.add(sign);
		buttonPanel.add(button0);
		buttonPanel.add(decimal);
		buttonPanel.add(enter);

		//Padding panels
		JPanel westPad = new JPanel();
		westPad.setPreferredSize(new Dimension(50, 400));
		JPanel eastPad = new JPanel();
		eastPad.setPreferredSize(new Dimension(50, 400));
		JPanel southPad = new JPanel();
		southPad.setPreferredSize(new Dimension(400, 50));

		//Main panel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		mainPanel.add(southPad, BorderLayout.SOUTH);
		mainPanel.add(eastPad, BorderLayout.EAST);
		mainPanel.add(westPad, BorderLayout.WEST);
		mainPanel.add(textPanel, BorderLayout.NORTH);
	}


	private void invertSign()
	{
		if(input.length() == 0)
			input.append("-");
		else if(input.charAt(input.length()-1) == ' ')
			input.append("-");
		else if(input.lastIndexOf(" ") != 0)
			input.insert(input.lastIndexOf(" ")+1, "-");
	}

	private void backspace()
	{
		if(input.length() > 0)
		{
			if(input.charAt(input.length()-1) == ' ')
				input.delete(input.length()-3, input.length());
			else
				input.delete(input.length()-1, input.length());
		}
	}

	private void clearInput()
	{
		if(input.length() > 0)
			input.delete(0, input.length());
	}

	private void calculate()
	{
		try
		{
			String[] ops = orderOfOperations();
			double[] numbers = simplify(ops);
			result = numbers[0];
			int index = 1;

			for(int i = 1; i < numbers.length; i++)
			{
				if(ops[index].equals("+"))
					result = result + numbers[i];
				if(ops[index].equals("-"))
					result = result - numbers[i];
				index = index + 2;
			}

			textField.setText(Double.toString(result));
			clearInput();;
		}
		catch(Exception e)
		{
			textField.setText("SYNTAX ERROR");
			clearInput();
			result = 0;
		}
	}

	private String[] orderOfOperations() throws Exception
	{
		StringBuffer temp = new StringBuffer();
		ArrayList<String> groups = new ArrayList<String>();
		String[] in = input.toString().split(" ");

		//If the expression ends in an operator: syntax error
		if(in[in.length-1].equals("+") || in[in.length-1].equals("-") || in[in.length-1].equals("*")
				|| in[in.length-1].equals("/") || in[in.length-1].equals("%"))
			throw new Exception();

		for(int i = 0; i < in.length; i++)
		{
			//split groups up by + and -
			if(in[i].equals("+") || in[i].equals("-"))
			{
				groups.add(temp.toString());
				groups.add(in[i]);
				temp = new StringBuffer();
			}
			//until a + or - is reached, add each character to a buffer
			else
				temp.append(in[i]);
		}
		groups.add(temp.toString());
		groups.trimToSize();

		//finally trim, convert to an array, and return
		String[] ret = new String[groups.size()];
		for(int i = 0; i < groups.size(); i++)
			ret[i] = groups.get(i);
		return ret;
	}

	private double[] simplify(String[] exp)
	{
		ArrayList<Double> numbers = new ArrayList<Double>();

		for(String s : exp)
		{
			if(s.contains("*") || s.contains("/") || s.contains("%"))
			{
				//Find index of first operator
				int index = 0; //index of the last operator
				while(index < s.length() && !(s.charAt(index) == '*' || s.charAt(index) == '/' || s.charAt(index) == '%'))
					index++;

				//initialize result to first number
				double result = Double.parseDouble(s.substring(0, index));

				//for each subsequent number, perform the given operation on the total result
				for(int i = index+1; i < s.length(); i++)
				{
					if(s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '%')
					{
						double num = Double.parseDouble(s.substring(index+1, i));
						if(s.charAt(index) == '*')
							result = result*num;
						if(s.charAt(index) == '/')
							result = result/num;
						if(s.charAt(index) == '%')
							result = result%num;
						index = i;
					}
				}

				//finally perform the operation on the last number
				double num = Double.parseDouble(s.substring(index+1));
				if(s.charAt(index) == '*')
					result = result*num;
				if(s.charAt(index) == '/')
					result = result/num;
				if(s.charAt(index) == '%')
					result = result%num;

				numbers.add(result);
			}
			else if(!(s.equals("+") || s.equals("-")))
				numbers.add(Double.parseDouble(s));
		}

		double[] ret = new double[numbers.size()];
		for(int i = 0; i < numbers.size(); i++)
			ret[i] = numbers.get(i);
		return ret;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button0)
			input.append("0");
		if(e.getSource() == button1)
			input.append("1");
		if(e.getSource() == button2)
			input.append("2");
		if(e.getSource() == button3)
			input.append("3");
		if(e.getSource() == button4)
			input.append("4");
		if(e.getSource() == button5)
			input.append("5");
		if(e.getSource() == button6)
			input.append("6");
		if(e.getSource() == button7)
			input.append("7");
		if(e.getSource() == button8)
			input.append("8");
		if(e.getSource() == button9)
			input.append("9");

		if(e.getSource() == plus)
		{
			if(input.length() == 0)
				input.append(result);
			if(input.charAt(input.length()-1) == ' ')
				input.replace(input.length()-3, input.length(), " + ");
			else
				input.append(" + ");
		}
		if(e.getSource() == minus)
		{
			if(input.length() == 0)
				input.append(result);
			if(input.charAt(input.length()-1) == ' ')
				input.replace(input.length()-3, input.length(), " - ");
			else
				input.append(" - ");
		}
		if(e.getSource() == mult)
		{
			if(input.length() == 0)
				input.append(result);
			if(input.charAt(input.length()-1) == ' ')
				input.replace(input.length()-3, input.length(), " * ");
			else
				input.append(" * ");
		}
		if(e.getSource() == divide)
		{
			if(input.length() == 0)
				input.append(result);
			if(input.charAt(input.length()-1) == ' ')
				input.replace(input.length()-3, input.length(), " / ");
			else
				input.append(" / ");
		}
		if(e.getSource() == mod)
		{
			if(input.length() == 0)
				input.append(result);
			if(input.charAt(input.length()-1) == ' ')
				input.replace(input.length()-3, input.length(), " % ");
			else
				input.append(" % ");
		}

		if(e.getSource() == decimal)
		{
			if(input.length() == 0 || input.charAt(input.length()-1) == ' ')
				input.append(result);
			else if(!input.substring(input.lastIndexOf(" ")+1).contains("."))
				input.append(".");
		}
		if(e.getSource() == sign)
			invertSign();
		if(e.getSource() == bkspc)
			backspace();
		if(e.getSource() == clear)
		{
			clearInput();
			result = 0;
		}
		if(e.getSource() == enter)
		{
			if(!input.toString().isEmpty())
				calculate();
		}
		else
			textField.setText(input.toString());

		if(input.length() > 0 && input.charAt(0) == ' ')
			input.insert(0, "0");
	}
}
