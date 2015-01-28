package shooter;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.Random;

public class PowerUp
{
	Random RNGesus = new Random();
	private Player player;
	private SpaceShooter shooter;
	private static boolean active = false;
	private int type;
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed = 5;
	private ImageIcon[] sprites = {
			new ImageIcon("images/shot0pickup.png"), 
			new ImageIcon("images/shot1pickup.png"), 
			new ImageIcon("images/shot2pickup.png"), 
			new ImageIcon("images/shot3pickup.png"), 
			new ImageIcon("images/shieldpickup.png"), 
			new ImageIcon("images/healthpickup.png"), 
			new ImageIcon("images/lifepickup.png")};
	private ImageIcon sprite;
	
	public PowerUp(SpaceShooter s, Player p)
	{
		player = p;
		shooter = s;
		setType();
		x = RNGesus.nextInt(530) + 260;
		y = -height;
		speed = 5;
		active = true;
	}
	
	public void paint(Graphics g)
	{
		if(active)
		{
			updatePosition();
			sprite.paintIcon(shooter, g, x-width/2, y-height/2);
		}
	}
	
	public void setType()
	{
		int t = RNGesus.nextInt(100);
		
		//health
		if(t > 90)
		{
			type = 5;
			width = 19;
			height = 25;
		}
		//shield
		else if(t > 80)
		{
			type = 4;
			width = 19;
			height = 25;
		}
		//laser
		else if(t > 60)
		{
			type = 3;
			width = 19;
			height = 25;
		}
		//machine gun
		else if(t > 40)
		{
			type = 2;
			width = 20;
			height = 35;
		}
		//missile
		else if(t > 20)
		{
			type = 1;
			width = 15;
			height = 30;
		}
		//basic shot
		else
		{
			type = 0;
			width = 15;
			height = 33;
		}
		
		sprite = sprites[type];
	}
	
	public void updatePosition()
	{
		y = y + speed;
		if(player.contains(x-width/2, y-width/2))
		{
			activate();
			active = false;
		}
		else if(y > 600 + height/2)
		{
			active = false;
		}
	}
	
	public void activate()
	{
	
		if(type < 4)
		{
			player.setWeapon(type);
		}
		if(type == 4)
		{
			
		}
		if(type == 5)
		{
			player.setHealth(100);
		}
		if(type == 6)
		{
			player.extraLife();
		}
	}
	
	public static boolean isActive()
	{
		return active;
	}
}
