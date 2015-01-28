package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueLL<T> implements IQueue<T>, Iterable<T>
{
	private Node m_end;
	private int m_count;
	
	public QueueLL()
	{
		m_end = new Node(null);
	}

	@Override
	public void enqueue(T item) 
	{
		//Runs in constant time
		
		Node n = new Node(item);
		n.m_next = m_end.m_next;
	}

	@Override
	public T dequeue() 
	{
		//Runs in Linear time
		
		if(isEmpty())
			throw new IllegalStateException("dequeue error: queue is empty");
		
		T ret = null;
		Node cur = m_end;
		
		//Find node in front
		while(cur.m_next != null)
		{
			//We need what is going to make the new front of the line. i.e. second in line
			if(cur.m_next.m_next == null)
			{
				//Grab the data then detach the end
				ret = cur.m_next.m_data;
				cur.m_next = null;
			}
			else
			{
				//Move on to next node
				cur = cur.m_next;
			}
		}
		return ret;
	}

	@Override
	public boolean isEmpty() 
	{
		//Runs in linear time
		
		return m_count == 0;
	}

	@Override
	public int getSize() 
	{
		//Runs in linear time
		
		return m_count;
	}
	
	public Iterator<T> iterator()
	{
		return new QIterator();
	}
	
	private class QIterator implements Iterator<T>
	{
		private int m_loc = getSize();
		
		public boolean hasNext()
		{
			return m_loc > 0;
		}
		
		public T next()
		{
			if(m_loc == 0)
				throw new NoSuchElementException();
			
			Node cur = m_end;
			for(int i = 0; i < m_loc; i++)
			{
				cur = cur.m_next;
			}
			m_loc--;
			return cur.m_data;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	private class Node
	{
		Node m_next;
		private T m_data;
		
		public Node(T item)
		{
			m_data = item;
		}
	}
}
