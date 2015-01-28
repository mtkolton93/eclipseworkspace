package exam;

public class QueueLL<T> implements IQueue<T>
{
	private Node m_endOfQueue;
	int m_count;
	
	public QueueLL()
	{
		m_endOfQueue = new Node(null);
	}
	
	public void enqueue(T item)
	{
		// runs in O(c) time ...
		
		// create a new node and insert it at the end of the queue...
		Node n = new Node(item);
		n.m_next = m_endOfQueue.m_next;
		m_endOfQueue.m_next = n;
		m_count++;
	}

	public T dequeue()
	{
		// runs in O(n) time
		
		if (isEmpty())
			throw new IllegalStateException("pop error: queue is empty!");
		
		T ret = null;
		Node cur = m_endOfQueue;
		
		// while not at the front of the queue...
		while (cur.m_next != null)
		{
			// if I am at the second person in the line...
			if (cur.m_next.m_next == null)
			{
				// grab the data from the first person in the line...
				ret = cur.m_next.m_data;
				
				// detatch the first person in the line...
				cur.m_next = null;
				
				break;
			}
			else
			{
				// otherwise, continue moving towards the front of the line...
				cur = cur.m_next;
			}
		}
		
		m_count--;
		return ret;
	}

	public boolean isEmpty()
	{ 
		return getSize() == 0;
	}
	
	public int getSize()
	{
		return m_count;
	}
	
	public String toString()
	{	
		// runs in O(n) time
		
		// creates a string representation of the list 
		// from the rear to the front...
		
		StringBuffer sb = new StringBuffer("rear->");
		if (!isEmpty())
		{
			Node node = m_endOfQueue.m_next;
			sb.append(node.m_data.toString());
			while (node.m_next != null)
			{
				node = node.m_next;
				sb.append("->");
				sb.append(node.m_data.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * <p>Internal class used to represent a link in a linked list
	 * structure.  Each node in the list contains the element 
	 * that was added to the queue, as well as a reference to the
	 * next item in the queue.</p>  
	 */
	private class Node
	{
	 	private T m_data;
	 	private Node m_next;
		
		public Node(T data)
		{
			m_data = data;
			m_next = null;
		}
	}
}
