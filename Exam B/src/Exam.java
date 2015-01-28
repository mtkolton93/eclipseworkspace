import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exam
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Exam exam = new Exam();
		Heap<Customer> customers = new Heap<Customer>();
		Customer[] winners = new Customer[5];
		
		//read input
		customers.insertAll(exam.readFile());
		System.out.println("File input complete!");
		System.out.println("");
		
		//pull winners
		for(int i = 0; i < 5; i++)
		{
			winners[i] = customers.removeTop();
		}
		
		//print winners
		for(int i = 0; i < 5; i++)
		{
			System.out.println((i+1) + ". " + winners[i].getName() + ": " + winners[i].getPriority());
		}
	}
	
	public ArrayList<Customer> readFile() throws FileNotFoundException
	{
		File file = new File("customers.txt");
		Scanner fileScanner = new Scanner(file);
		ArrayList<Customer> ret = new ArrayList<Customer>();
		
		while(fileScanner.hasNextLine())
		{
			
			String[] line = fileScanner.nextLine().split(", ");
			String n = line[0];
			int s = Integer.parseInt(line[1]);
			int y = Integer.parseInt(line[2]);
			int r = Integer.parseInt(line[3]);
			ret.add(new Customer(n, s, y, r));
		}
		
		fileScanner.close();
		return ret;
	}
	
	private class Customer implements Comparable<Customer>
	{
		String name;
		int spent;
		int years;
		int registration;
		int pn;
		
		public Customer(String n, int s, int y, int r)
		{
			name = n;
			spent = s;
			years = y;
			registration = r;
			pn = (spent/1000) + years - registration;
		}
		
		public int getPriority()
		{
			return pn;
		}
		
		public String getName()
		{
			return name;
		}

		@Override
		public int compareTo(Customer other)
		{
			return getPriority() - other.getPriority();
		}
	}
}


