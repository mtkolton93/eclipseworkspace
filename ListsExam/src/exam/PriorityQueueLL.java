package exam;

public class PriorityQueueLL<T> implements IQueue<T>
{
	private Node m_endOfQueue;
	private Node m_frontOfQueue;
	private int m_count;
	private int m_priorityCount;

	public PriorityQueueLL()
	{
		m_endOfQueue = new Node(null);
		m_frontOfQueue = new Node(null);
		m_endOfQueue.m_next = m_frontOfQueue;
		m_frontOfQueue.m_prev = m_endOfQueue;
	}

	public void enqueue(T item)
	{
		// runs in O(c) time ...

		// create a new node and insert it at the end of the queue...
		Node n = new Node(item);
		n.m_next = m_endOfQueue.m_next;
		n.m_next.m_prev = n;
		n.m_prev = m_endOfQueue;
		m_endOfQueue.m_next = n;
		m_count++;
	}

	public void priorityEnqueue(T item)
	{
		Node cur = m_frontOfQueue;
		for(int i = 0; i <= m_priorityCount; i++)
		{
			cur = cur.m_prev;
		}

		Node n = new Node(item);
		n.m_next = cur.m_next;
		n.m_next.m_prev = n;
		n.m_prev = cur;
		cur.m_next = n;
		m_count++;
		m_priorityCount++;
	}

	public T dequeue()
	{
		// runs in O(c) time

		if (isEmpty())
			throw new IllegalStateException("pop error: queue is empty!");

		T ret = m_frontOfQueue.m_prev.m_data;

		Node cur = m_frontOfQueue.m_prev.m_prev;
		cur.m_next = m_frontOfQueue;
		m_frontOfQueue.m_prev = cur;

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
			while (node.m_next.m_data != null)
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
	 	private Node m_prev;

		public Node(T data)
		{
			m_data = data;
			m_next = null;
			m_prev = null;
		}
	}
}
