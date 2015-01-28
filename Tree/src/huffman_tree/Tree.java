package huffman_tree;

public class Tree
{
	private Tree m_left = null;
	private Tree m_right = null;
	private int m_value;
	private char m_char; //use * for non-leaf trees
	
	public Tree(int value)
	{
		m_value = value;
		m_char = '*';
	}
	
	public Tree(Tree left, Tree right, int value)
	{
		m_left = left;
		m_right = right;
		m_value = value;
		m_char = '*';
	}
	
	public Tree(int value, char c)
	{
		m_value = value;
		m_char = c;
	}
	
	public void setLeft(Tree l)
	{
		m_left = l;
	}
	
	public void setRight(Tree r)
	{
		m_right = r;
	}
	
	public Tree getLeft()
	{
		return m_left;
	}
	
	public Tree getRight()
	{
		return m_right;
	}
	
	public int getValue()
	{
		return m_value;
	}
	
	public char getChar()
	{
		return m_char;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if(m_char != '*');
		{
			sb.append(m_char);
		}
		sb.append(" (" + m_value + ")");
		return sb.toString();
	}
}
