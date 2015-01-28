package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SearchEngine
{
	File[] files = new File("text files/").listFiles();
	HashMap<String, ArrayList<File>> index = new HashMap<String, ArrayList<File>>();
	
	public static void main(String[] args)
	{
		SearchEngine s = new SearchEngine();
		Scanner keyboard = new Scanner(System.in);
		String search = null;
		
		s.createIndex();
		System.out.println("Index Created!");
		System.out.println("");
		
		System.out.print("Enter a word to search: ");
		search = keyboard.nextLine();
		s.search(search);
		
		keyboard.close();
	}
	
	public void createIndex()
	{
		Scanner fileReader = null;
		
		for(File f: files)
		{
			try
			{
				fileReader = new Scanner(f);
				
				while(fileReader.hasNext())
				{
					String s = fileReader.next().toLowerCase();
					
					//Create the list for the key if it doesn't exist already
					if(!index.containsKey(s))
					{
						index.put(s, new ArrayList<File>());
					}
					//Add the file containing the word to it's list if it's not already there
					if(!index.get(s).contains(f))
					{
						index.get(s).add(f);
					}
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File: " + f.getPath() + " not found.");
			}
			finally
			{
				fileReader.close();
			}
		}
	}
	
	public void search(String s)
	{
		if(s.contains("+"))
		{
			if(s.contains("*"))
			{
				System.out.println("Query too complicated: use only * or +");
			}
			else
			{
				String[] queries = s.split("\\+");
				StringBuffer sb = new StringBuffer(s + ": ");
				
				for(String q: queries)
				{
					try
					{
						for(File f : index.get(q))
						{
							sb.append(f.getPath().substring(11) + " ");
						}
					}
					catch(NullPointerException e)
					{
						System.out.println(q + " not found!");
					}
				}
				
				System.out.println(sb.toString());
			}
		}
		if(s.contains("*"))
		{
			if(s.contains("+"))
			{
				System.out.println("Query too complicated: use only * or +");
			}
			else
			{
				String[] queries = s.split("\\*");
				
				//The previous set of hits will be the first word's list. The rest will be checked against these hits
				// to narrow the results down to common hits
				ArrayList<File> baseList = index.get(queries[0]);
				ArrayList<File> results = new ArrayList<File>();
				
				//Starting from the second word in the search, check each word's hits against the previous set of hits
				for(int i = 1; i < queries.length; i++)
				{
					for(File f : index.get(queries[i]))
					{
						//If the previous set of hits contains this one, add it to the overall results
						if(baseList.contains(f))
						{
							results.add(f);
						}
					}
					
					//Set the base list to the results and repeat
					//This will leave only the hits that are common to all the search criteria
					baseList = results;
				}
				
				StringBuffer sb = new StringBuffer(s + ": ");
				for(File f : results)
				{
					sb.append(f.getPath().substring(11) + " ");
				}
				System.out.println(sb.toString());
			}
		}
		if(!s.contains("*") && !s.contains("+"))
		{
			StringBuffer sb = new StringBuffer(s + ": ");
			
			for(File f : index.get(s))
			{
				sb.append(f.getPath().substring(11) + " ");
			}
			
			System.out.println(sb.toString());
		}
	}
}
