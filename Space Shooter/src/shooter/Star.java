package shooter;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Star
{
	private int speed;
	private int x;
	private int y;
	private int size;
	private Random RNGesus = new Random();

	public Star()
	{
		x = RNGesus.nextInt(630) + 260;
		speed = RNGesus.nextInt(10) + 4;
		size = RNGesus.nextInt(2) + 1;
		y = speed-5;
	}

	public void paint(Graphics g)
	{
		y = y + speed;
		g.setColor(Color.white);
		g.fillRect(x-size/2, y-size/2, size, size);
	}
}