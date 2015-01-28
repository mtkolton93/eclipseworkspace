package shooter;

public class Ship implements ShotListener
{
	protected SpaceShooter shooter;
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int speed;
	protected int health;
	protected int timeSinceFire = 0;
	protected int fireRate = 10;
	protected int damageTaken = 10;
	
	public void paint(){}
	
	public boolean contains(int testX, int testY)
	{
		if(testX > x-width/2 && testX < x+width/2 && testY > y-height/2 && testY < y+height/2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Called on collision, but different for players and enemies
	public void setDamageTaken(int damage)
	{
		damageTaken = damage;
	}
	
	public void hit(){}
	
	//Called by on every update
	public boolean shotMoved(int shotX, int shotY)
	{
		if(contains(shotX, shotY))
		{
			hit();
			return true;
		}
		return false;
	}
}
