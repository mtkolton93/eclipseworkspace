package facebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Facebook
{
	ArrayList<User> userList = new ArrayList<User>();
	Heap<User> users = new Heap<User>();
	Dictionary<String, ArrayList<String>> userLikes;
	Dictionary<String, ArrayList<String>> userLikedBy;
	boolean active = true;
	File friends = new File("documents/friends.txt");
	
	public static void main(String[] args)
	{
		Facebook fb = new Facebook();
		Scanner keyboard = new Scanner(System.in);
	
		System.out.println("The following commands are supported:");
		System.out.println("	LIST: Lists all users in the database");
		System.out.println("	PEOPLEILIKE: Lists all the users that a given user likes");
		System.out.println("	PEOPLEWHOLIKEME: Lists all the users that like a given user");
		System.out.println("	EXIT: Exits the program");
		
		while(fb.active)
		{
			System.out.println();
			System.out.print("Enter a command: ");
			String input = keyboard.nextLine().toUpperCase();
			fb.processInput(input);
		}
		
		keyboard.close();
	}
	
	public Facebook()
	{
		users.setMaxHeap(false);
		generateUserList();
		connectLikes();
	}
	
	public void generateUserList()
	{
		Scanner fileReader = null;
		
		try
		{
			fileReader = new Scanner(friends);
			
			while(fileReader.hasNextLine())
			{
				//Insert into users heap and create keys and lists in the dictionaries for associated likes
				User temp = new User(fileReader.nextLine().toUpperCase().split(",")[0]);
				users.insert(temp);
				userList.add(temp);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void connectLikes()
	{
		Scanner fileReader = null;
		
		userLikes = new Dictionary<String, ArrayList<String>>(users.size());
		userLikedBy = new Dictionary<String, ArrayList<String>>(users.size());
		
		for(User u : userList)
		{
			userLikes.insert(u.getName(), new ArrayList<String>());
			userLikedBy.insert(u.getName(), new ArrayList<String>());
		}
		
		try
		{
			fileReader = new Scanner(friends);
			
			while(fileReader.hasNextLine())
			{
				String[] line = fileReader.nextLine().toUpperCase().split(",");
				
				for(int i = 1; i < line.length; i++)
				{
					userLikes.search(line[0]).add(line[i]);
					userLikedBy.search(line[i]).add(line[0]);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			fileReader.close();
		}
	}
	
	public void processInput(String command)
	{
		if(command.equals("LIST"))
		{
			list();
		}
		else if(command.equals("PEOPLEILIKE"))
		{
			peopleILike();
		}
		else if(command.equals("PEOPLEWHOLIKEME"))
		{
			peopleWhoLikeMe();
		}
		else if(command.equals("EXIT"))
		{
			active = false;
		}
		else
		{
			System.out.println("Invalid Input!");
		}
	}
	
	public void list()
	{
		Heap<User> temp = new Heap<User>();
		temp.setMaxHeap(false);
		
		while(users.size() > 0)
		{
			User currentUser = users.removeTop();
			temp.insert(currentUser);
			
			System.out.print(currentUser.getName());
			if(users.size() > 0)
			{
				System.out.print(", ");
			}
			else
			{
				System.out.println();
			}
		}
		
		users = temp;
	}
	
	public void peopleILike()
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter a Facebook user: ");
		String input = kb.next().toUpperCase();
		
		if(userLikes.search(input) != null)
		{
			ArrayList<String> likes = userLikes.search(input);
			
			if(likes.size() > 0)
			{
				StringBuffer sb = new StringBuffer(input + " likes ");
				sb.append(likes.toString());
				System.out.println(sb);
			}
			else
			{
				System.out.println(input + " doesn't like anyone.");
			}
		}
		else
		{
			System.out.println("User not found!");
		}
	}
	
	public void peopleWhoLikeMe()
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter a Facebook user: ");
		String input = kb.nextLine().toUpperCase();
		
		if(userLikedBy.search(input) != null)
		{
			ArrayList<String> likedBy = userLikedBy.search(input);
			
			if(likedBy.size() > 0)
			{
				StringBuffer sb = new StringBuffer();
				sb.append(likedBy.toString());
				sb.append(" like " + input);
				System.out.println(sb);
			}
			else
			{
				System.out.println("Nobody likes " + input + " :(");
			}
		}
		else
		{
			System.out.println("User not found!");
		}
	}
}
