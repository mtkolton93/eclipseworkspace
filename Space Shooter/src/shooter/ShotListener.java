package shooter;

public interface ShotListener
{
	public boolean shotMoved(int shotX, int shotY);
	
	public void setDamageTaken(int damage);
}
