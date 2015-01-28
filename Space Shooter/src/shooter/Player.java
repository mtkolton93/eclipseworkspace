package shooter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

@SuppressWarnings("unused")
public class Player extends Ship implements KeyListener, ActionListener
{
	private int lives;
	private int xVel;
	private int xMin;
	private int xMax;
	private int weapon = 0;

	private boolean firing = false;;
	private Timer autoFire;
	private ImageIcon sprite = new ImageIcon("images/playersprite.png");
	private ArrayList<Shot> ammo = new ArrayList<Shot>();
	private ArrayList<ShotListener> listeners = new ArrayList<ShotListener>();

	public Player(SpaceShooter s)
	{
		shooter = s;
		health = 100;
		lives = 3;
		speed = 15;
		x = 500;
		y = 550;
		xMin = 250-(width/2-10);;
		xMax = 800-(width/2+10);
		width = 70;
		height = 46;
		timeSinceFire = 0;
		autoFire = new Timer(50, this);
		autoFire.start();
	}

	public void paint(Graphics g)
	{
		updatePosition();
		for(int i = 0; i < ammo.size(); i++)
		{
			ammo.get(i).paint(g);
		}
		sprite.paintIcon(shooter, g, x-width/2, y-height/2);
		cleanUpShots();
	}

	//Removes any shots that have left the game screen
	public void cleanUpShots()
	{
		for(int i = 0; i < ammo.size(); i++)
		{
			if(!ammo.get(i).isFired())
			{
				ammo.remove(i);
			}
		}
	}
	
	//Player stores shot listeners so that shots don't have to be pre-made
	public void addShotListener(ShotListener l)
	{
		listeners.add(l);
	}
	
	//When an enemy is made inactive it stops listening until it's activated again
	public void removeShotListener(ShotListener l)
	{
		listeners.remove(l);
	}
	
	public void updatePosition()
	{
		x = x + xVel;
		if(x > xMax)
		{
			x = xMax;
		}
		if(x < xMin)
		{
			x = xMin;
		}
	}
	
	//Called when an enemy shoots the player
	public void hit()
	{
		health = health - damageTaken;
		if(health <= 0)
		{
			lives--;
			health = 100;
			x = 500;
			
			if(lives < 0)
			{
				shooter.gameOver();
			}
		}
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int h)
	{
		health = h;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public void extraLife()
	{
		lives++;
	}
	
	public void setWeapon(int w)
	{
		weapon = w;
		{
			if(weapon == 0)
			{
				fireRate = 10;
			}
			if(weapon == 1)
			{
				fireRate = 15;
			}
			if(weapon == 2)
			{
				fireRate = 2;
			}
			if(weapon == 3)
			{
				fireRate = 15;
			}
		}
	}
	
	@Override
	public void setDamageTaken(int damage)
	{
		damageTaken = 10;
	}
	
	public int getWeapon()
	{
		return weapon;
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			xVel = -speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			xVel = speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			firing = true;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && xVel < 0)
		{
			xVel = 0;
		}
		if((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && xVel > 0)
		{
			xVel = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			firing = false;
		}
	}
	public void keyTyped(KeyEvent e){}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		timeSinceFire++;
		if(timeSinceFire > fireRate && firing)
		{
			Shot temp = new Shot(shooter, false);
			for(int i = 0; i < listeners.size(); i++)
			{
				temp.addListener(listeners.get(i));
			}
			temp.setType(weapon);
			ammo.add(temp);
			temp.fire(x, y);
			timeSinceFire = 0;
		}
	}
}