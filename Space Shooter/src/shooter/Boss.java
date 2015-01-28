package shooter;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Boss extends Enemy
{
	private ImageIcon[] sprites = {
			new ImageIcon("images/bossSprite0.png"), 
			new ImageIcon("images/bossSprite1.png"), 
			new ImageIcon("images/bossSprite2.png")};
	
	public Boss(SpaceShooter s)
	{
		shooter = s;
		health = 100;
		speed = 10;
		scoreValue = 1000;
		width = 150;
		height = 101;
		x = 500;
		y = -100;
		shot[0] = new Shot(shooter, true);
		active = false;
		destroyed = false;
		isBoss = true;
		autoFire = new Timer(10, this);
		autoFire.start();
	}
	
	@Override
	public void setType()
	{
		int t = RNGesus.nextInt(100);
		if(t < 33)
		{
			type = 0;
			sprite = sprites[0];
			shot[0].addListener(player);
			shot[0].setType(0);
			health = 120;
			speed = 8;
			fireRate = 10;
		}
		else if(t < 66)
		{
			type = 1;
			sprite = sprites[1];
			shot[0].addListener(player);
			shot[0].setType(0);
			health = 150;
			speed = 5;
			fireRate = 15;
		}
		else if(t < 100)
		{
			type = 2;
			sprite = sprites[2];
			
			shot = new Shot[3];
			for(int i = 0; i < 3; i++)
			{
				shot[i] = new Shot(shooter, true);
				shot[i].addListener(player);
				shot[i].setType(2);
			}
			
			health = 100;
			speed = 12;
			fireRate = 2;
			burstRate = 5;
			burstFires = 3;
			burstTime = 0;
			reset();
		}
	}
	
	@Override
	public void updatePosition()
	{
		if(y < 150)
		{
			y = y + speed;
		}
		else
		{
			x = x + speed;
			if(x > 730)
			{
				x = 730;
			}
			if(x < 320)
			{
				x = 320;
			}
			if(RNGesus.nextInt(100) > 95)
			{
				speed = -speed;
			}
		}
	}
}
