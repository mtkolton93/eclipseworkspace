package stack;

/**
 * <p>Concreted class for a Stack that is backed by an
 * array structure</p>
 *
 * @author Matt Kolton
 *
 * @param <T> the data type of the elements contained in the stack.
 */

@SuppressWarnings("unchecked")
public class StackAry<T> implements IStack<T>
{
	private int m_size = 0;
	private T[] ary = (T[])new Object[0];

	public void push(T item)
	{
		if(m_size == ary.length)
		{
			T[] a = (T[])new Object[m_size+10];
			for(int i = 0; i < m_size; i++)
			{
				a[i] = ary[i];
			}
			ary = a;
		}
		ary[m_size] = item;
		m_size++;
	}

	public T pop()
	{
		if (isEmpty())
			throw new IllegalStateException("pop error: stack is empty!");
		
		T item = ary[m_size-1];
		T[] a = (T[])new Object[m_size-1];
		for(int i = 0; i < m_size-1; i++)
		{
			a[i] = ary[i];
		}
		ary = a;
		m_size--;
		return item;
	}

	public T top()
	{
		if (isEmpty())
			throw new IllegalStateException("pop error: stack is empty!");
		
		return ary[m_size-1];
	}

	public boolean isEmpty()
	{
		return m_size == 0;
	}

	public int getSize()
	{
		return m_size;
	}
	
	public String toString()
	{	
		StringBuffer sb = new StringBuffer("top->");
		if (!isEmpty())
		{	
			sb.append(ary[m_size-1]);
			for(int i = m_size-2; i >= 0; i--)
			{
				sb.append("->");
				sb.append(ary[i]);
			}
			
		}
		return sb.toString();
	}
}
