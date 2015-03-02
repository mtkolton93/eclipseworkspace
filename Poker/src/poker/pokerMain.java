package poker;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class pokerMain
{
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
		JFrame frame = new JFrame();
		frame.setSize(816, 636);
		frame.add(new Poker());
		frame.setVisible(true);
	}
}
