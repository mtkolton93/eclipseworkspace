package list;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AddPolynomials
{

	//returns 1 polynomial from 1 file
	public IList<Integer, Term> readPolynomial(String filename) throws IOException
	{
		Scanner fileScanner = null;
		IList<Integer, Term> ret = new LList<Integer, Term>();
		try
		{
			fileScanner = new Scanner(new File(filename));
			String term = fileScanner.nextLine();
			while(fileScanner.hasNextLine())
			{
				if(term.split(",").length != 2)
						throw new IllegalStateException("Expected 2 values");
				int coefficient = Integer.parseInt(term.split(",")[0]);
				int power = Integer.parseInt(term.split(",")[1]);
				Term t = new Term(coefficient, power);
				ret.add(power, t);
			}
		}
		finally
		{
			if(fileScanner != null)
					fileScanner.close();
		}
		
		return ret;
	}
	
	public IList<Integer, Term> add(IList<Integer, Term> p1, IList<Integer, Term> p2)
	{
		IList<Integer, Term> ret = new LList<Integer, Term>();
		for(int i = 0; i < 10; i++)
		{
			int c1 = 0;
			int c2 = 0;
			while(p1.find(i) != null)
			{
				c1 = c1 + p1.find(i).getCoefficient();
				p1.remove(i);
			}
			while(p2.find(i) != null)
			{
				c2 = c2 + p2.find(i).getCoefficient();
				p2.remove(i);
			}
			int coefficient = c1 + c2;
			
			if(coefficient != 0)
			{
				Term t = new Term(coefficient, i);
				ret.add(i, t);
			}
		}
		return ret;
	}
}
