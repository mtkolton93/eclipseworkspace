package huffman_tree;
import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanTree
{
	ArrayList<Tree> treeList = new ArrayList<Tree>();
	Tree huffTree;

	public static void main(String[] args)
	{
		HuffmanTree h = new HuffmanTree();

		System.out.println("Finished Huffman Tree (pre-order traversal):");
		print(h.huffTree);

		System.out.println();
		System.out.println("Character Codes:");
		printCodes(h.huffTree, h.new pathCounter());

		System.out.println();
		while(true)
		{
			askForCode(h.huffTree);
		}
	}

	public HuffmanTree()
	{
		treeList.add(new Tree(3, 'm'));
		treeList.add(new Tree(3, 'c'));
		treeList.add(new Tree(7, 'r'));
		treeList.add(new Tree(15, 'e'));
		treeList.add(new Tree(6, 'n'));
		treeList.add(new Tree(5, 's'));
		treeList.add(new Tree(12, 't'));
		treeList.add(new Tree(2, 'g'));
		treeList.add(new Tree(2, 'k'));
		treeList.add(new Tree(5, 'j'));
		treeList.add(new Tree(10, 'a'));
		treeList.add(new Tree(4, 'i'));
		treeList.add(new Tree(4, 'd'));
		treeList.add(new Tree(8, 'o'));

		while(treeList.size() > 1)
		{
			_combineTrees();
		}

		huffTree = treeList.get(0);
	}

	public static void print(Tree t)
	{
		System.out.println(t.toString());

		if(t.getLeft() != null)
		{
			print(t.getLeft());
		}

		if(t.getRight() != null)
		{
			print(t.getRight());
		}
	}

	public static void printCodes(Tree t, pathCounter c)
	{
		//visit left children
		if(t.getLeft() != null)
		{
			c.pathReverse.push(0);
			printCodes(t.getLeft(), c);
		}

		//visit self
		if(t.getChar() != '*')
		{
			while(!c.pathReverse.isEmpty())
			{
				c.path.push(c.pathReverse.pop());
			}
			System.out.println(t.getChar() + ": " + c.path.toString());
			while(!c.path.isEmpty())
			{
				c.pathReverse.push(c.path.pop());
			}
		}

		//visit right children
		if(t.getRight() != null)
		{
			c.pathReverse.push(1);
			printCodes(t.getRight(), c);
		}

		//Done: time to move up (unless we're already on the root)
		if(!c.pathReverse.isEmpty())
		{
			c.pathReverse.pop();
		}
	}

	public static void askForCode(Tree t)
	{
		Tree root = t;
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a character code to find: ");
		String input = keyboard.nextLine();
		StringBuffer sb = new StringBuffer(input + " = ");
		boolean run = true;

		//check each character and move accordingly
		for(int i = 0; i < input.length(); i++)
		{
			//Traverse to a leaf
			if(input.charAt(i) == '0')
			{
				t = t.getLeft();
			}
			else if(input.charAt(i) == '1')
			{
				t = t.getRight();
			}
			//Something other than a 1 or 0 is entered
			else
			{
				System.out.println("Invalid Input");
				run = false;
			}

			//If the input was valid
			if(run)
			{
				//Whenever a leaf is reached
				if(t.getChar() != '*')
				{
					sb.append(t.getChar());
					t = root;
				}
				//Code ends on a *
				else if(i == input.length()-1)
				{
					System.out.println("Invalid Code");
					run = false;
				}
			}
		}

		//If it went all the way through successfully
		if(run)
		{
			System.out.println(sb.toString());
		}
	}

	private void _combineTrees()
	{
		Tree t1 = null;
		Tree t2 = null;
		int smallest = Integer.MAX_VALUE;

		//Find the smallest pair
		for(Tree x1 : treeList)
		{
			for(Tree x2 : treeList)
			{
				if(x1 != x2)
				{
					int total = x1.getValue() + x2.getValue();
					if(total < smallest)
					{
						t1=x1;
						t2=x2;
						smallest = total;
					}
				}
			}
		}
		//Create a parent tree using the smallest pair and replace the the pair in the list with their new parent
		Tree tNew = new Tree(t1, t2, t1.getValue() + t2.getValue());
		treeList.add(tNew);
		treeList.remove(t1);
		treeList.remove(t2);
	}

	private class pathCounter
	{
		IStack<Integer> pathReverse = new StackLL<Integer>();
		IStack<Integer> path = new StackLL<Integer>();
	}
}