package facebook;

public class User implements Comparable<User>
{
	String name;
	Character firstChar;
	
	public User(String n)
	{
		name = n;
		firstChar = new Character(name.charAt(0));
	}

	@Override
	public int compareTo(User other)
	{
		return firstChar.compareTo(other.firstChar);
	}
	
	public String getName()
	{
		return name;
	}
}