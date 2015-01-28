package stack;

import java.util.Scanner;

public class Maze
{
	private int x = 0;
	private int y = 0;
	private boolean play = true;
	private IStack<String> undo = new StackAry<String>();
	private IStack<String> redo = new StackAry<String>();

	public static void main(String[] args)
	{
		Maze maze = new Maze();
		Scanner keyboard = new Scanner(System.in);
		String input;
		
		maze.printMap();
		while(maze.play)
		{
			System.out.println();
			maze.askForInput();
			input = keyboard.nextLine().toUpperCase().substring(0, 1);
			if(input.equals("N") || input.equals("S") || input.equals("E") || input.equals("W"))
			{
				maze.clearRedoStack();
			}
			
			maze.processInput(input, 0);
			maze.printMap();
		}
		keyboard.close();
	}
	
	public void askForInput()
	{
		System.out.println("What have you,  mage?");
		System.out.println("N: North");
		System.out.println("S: South");
		System.out.println("E: East");
		System.out.println("W: West");
		System.out.println("G: Grog");
		System.out.println("U: Undo");
		System.out.println("R: Redo");
		System.out.println("Q: Quit");
	}
	
	public void printMap()
	{
		System.out.println("Undo Stack: " + undo.toString());
		System.out.println("Redo Stack: " + redo.toString());
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(x == j && y == i)
				{
					System.out.print("* ");
				}
				else
				{
					System.out.print("- ");
				}
			}
			System.out.println();
		}
	}
	
	public void clearRedoStack()
	{
		IStack<String> temp = new StackAry<String>();
		redo = temp;
	}
	
	public void processInput(String s, int method) //method corresponds to how this method is being called. 0 = regular input, 1 = undo, 2 = redo
	{
		if(s.equals("N"))
		{
			//regular input
			if(method == 0 || method == 2)
				undo.push("S");
			//on an undo
			if(method == 1)
				redo.push("S");
			//check bounds
			if(y>0)
			{
				y--;
			}
			else
			{
				System.out.println("That's a wall, dumbass");
			}
		}
		else if(s.equals("S"))
		{
			if(method == 0 || method == 2)
				undo.push("N");
			//on an undo
			if(method == 1)
				redo.push("N");
			//check bounds
			if(y<10)
			{
				y++;
			}
			else
			{
				System.out.println("That's a wall, dumbass");
			}
		}
		else if(s.equals("E"))
		{
			if(method == 0 || method == 2)
				undo.push("W");
			//on an undo
			if(method == 1)
				redo.push("W");
			//check bounds
			if(x<10)
			{
				x++;
			}
			else
			{
				System.out.println("That's a wall, dumbass");
			}
		}
		else if(s.equals("W"))
		{
			if(method == 0 || method == 2)
				undo.push("E");
			//on an undo
			if(method == 1)
				redo.push("E");
			//check bounds
			if(x>0)
			{
				x--;
			}
			else
			{
				System.out.println("That's a wall, dumbass");
			}
		}
		else if(s.equals("G"))
		{
			//regular input
			if(method == 0)
			{
				undo.push("G");
				System.out.println();
				System.out.println("Gulp!");
				System.out.println();
			}
			//on an undo
			if(method == 1)
			{
				redo.push("G");
				System.out.println();
				System.out.println("Blah!");
				System.out.println();
			}
			//on a redo
			if(method == 2)
			{
				System.out.println();
				System.out.println("I wouldn't do that...");
				System.out.println();
			}
		}
		else if(s.equals("U"))
		{
			//processInput with top of undo stack
			if(!undo.isEmpty())
			{
				processInput(undo.pop(), 1);
			}
			else
			{
				System.out.println("Nothing to undo...");
			}
		}
		else if(s.equals("R"))
		{
			//processInput with top of redo stack
			if(!redo.isEmpty())
			{
				processInput(redo.pop(), 2);
			}
			else
			{
				System.out.println("Nothing to redo...");
			}
		}
		else if(s.equals("Q"))
		{
			play = false;
		}
		//invalid input
		else
		{
			System.out.println("Please enter a valid input.");
		}
	}
}
