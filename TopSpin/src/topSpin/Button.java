package topSpin;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Button
{
	private int x;
	private int y;
	private int width;
	private int height;
	private ImageIcon icon;
	private TopSpin topSpin;
	
	public Button(TopSpin t)
	{
		topSpin = t;
	}
	
	public void paint(Graphics g)
	{
		icon.paintIcon(topSpin, g, x, y);
	}
	
	public void setPosition(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
	}
	
	public void setSize(int w, int h)
	{
		width = w;
		height = h;
	}
	
	public void setIcon(String path)
	{
		icon = new ImageIcon(path);
	}
	
	public boolean contains(int testX, int testY)
	{
		if(testX > x && testX < x+width && testY > y && testY < y+height)
		{
			return true;
		}
		return false;
	}
}
