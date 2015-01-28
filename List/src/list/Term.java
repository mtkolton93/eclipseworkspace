package list;

public class Term 
{
	private int power;
	private int coefficient;
	
	public Term(int c, int p)
	{
		coefficient = c;
		power = p;	
	}
	
	public int getPower()
	{
		return power;
	}
	
	public int getCoefficient()
	{
		return coefficient;
	}
	
	//should include logic to print out terms correctly: i.e. 0*x^2 should not be included and x^0 should be 1
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		if(power == 0)
		{
			sb.append(coefficient);
		}
		if(power == 1)
		{
			if(coefficient != 1)
			{
				sb.append(coefficient);
			}
			sb.append("x");
		}
		if(power > 1)
		{
			if(coefficient != 1)
			{
				sb.append(coefficient);
			}
			sb.append("x^");
			sb.append(power);
		}
		
		return sb.toString();
	}
}
