package facebook;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> implements IHeap<T>
{
	private boolean m_max;
    private T[] m_items;
    private int m_count;
    private static final int CHUNK_SIZE = 1;
    
    
    public Heap()
    {
    	clear();
        m_count = 0;
        m_max = true;
    }

    public void setMaxHeap(boolean asMax)
    {
    	if (asMax != m_max)
    	{
	    	m_max = asMax;
	    	List<T> temp = new ArrayList<T>();
	    	for (T item : m_items)
	    	{
	    		if (item != null)
	    			temp.add(item);
	    	}
	    	clear();
	    	insertAll(temp);
    	}
    }
    
    public void insert(T item)
    {
    	grow();
    	m_items[size()] = item;
        moveUp(m_count);
        m_count++;
    }

    public void insertAll(List<T> items)
    {
    	for (T i : items)
			insert(i);
    }
    
    public int size()
    {
        return m_count;
    }
    
	@SuppressWarnings("unchecked")
	public void clear() 
	{
		m_items = (T[])new Comparable[0];
		m_count = 0;
	}

    public T peekTop()
    {
        return m_items[0];
    }

    public T removeTop()
    {
    	m_count--;
        T root = m_items[0];
        if (m_count > 0)
        {
            T farLeft = m_items[m_count];
            m_items[0] = farLeft;
            m_items[m_count] = null;
            moveDown(0);
        }
        return root;
    }
    
    @Override
    public String toString()
    {
    	StringBuffer sb = new StringBuffer("Heap: ");
    	for (T i : m_items)
    	{
    		if (i == null)
    			break;
    		sb.append(i.toString() + " ");
    	}
        return sb.toString();
    }
    
    private void moveUp(int i)
    {
        if (i > 0)
        {
            if (compare(m_items[i], m_items[getParent(i)]) > 0)
            {
                swap(i, getParent(i));
                moveUp(getParent(i));
            }
        }
    }

    private void moveDown(int i)
    {
        T me = m_items[i];
        // no children, can't move down...
        if (getLeft(i) >= m_count && getRight(i) >= m_count)
        {
            return;
        }
        // two children
        else if (getLeft(i) < m_count && getRight(i) < m_count)
        {
            T left = m_items[getLeft(i)];
            T right = m_items[getRight(i)];

            // I am bigger than both children, can't move down...
            if (compare(me, left) > 0 && compare(me, right) > 0)
            {
                return;
            }
            // I am smaller than the left, so down to the left... 
            else if (compare(me, right) >= 0  && compare(me, left) < 0)
            {
                swap(i, getLeft(i));
                moveDown(getLeft(i));
            }
            // I am smaller than the right, so down to the right...
            else if (compare(me, right) < 0 && compare(me, left) >= 0 )
            {
                swap(i, getRight(i));
                moveDown(getRight(i));
            }
            // I am smaller than both children, so move towards the larger child...
            else if (compare(me, right) < 0 && compare(me, left) < 0)
            {
                if (compare(left, right) > 0)
                {
                    swap(i, getLeft(i));
                    moveDown(getLeft(i));
                }
                else
                {
                    swap(i, getRight(i));
                    moveDown(getRight(i));
                }
            }
        }
        // one child to left, move left...
        else if (getLeft(i) < m_count)
        {
            T left = m_items[getLeft(i)];
            if (compare(me, left) < 0)
            {
                swap(i, getLeft(i));
                moveDown(getLeft(i));
            }
        }
        // one child to right, move right...
        else if (getRight(i) < m_count)
        {
            T right = m_items[getRight(i)];
            if (compare(me, right) < 0)
            {
                swap(i, getRight(i));
                moveDown(getRight(i));
            }
        }

    }
    
    private int getParent(int index)
    {
        return (index == 0) ? -1 : (index - 1)/2;
    }

    private int getLeft(int index)
    {
        return (2 * index) + 1; 
    }

    private int getRight(int index)
    {
        return (2 * index) + 2;
    }

    private void swap(int i1, int i2)
    {
        T item1 = m_items[i1];
        T item2 = m_items[i2];
        m_items[i1] = item2;
        m_items[i2] = item1;
    }
    
    private int compare(T item1, T item2)
    {
    	if (item1 == null || item2 == null)
    		return 0;
    	
    	if (m_max)
    		return item1.compareTo(item2);
    	else
    		return item2.compareTo(item1);
    }
    
	private void grow()
	{
		// runs in linear time if the stack needs to grow... 
		if (size() == m_items.length)
		{
			@SuppressWarnings("unchecked")
			T[] temp = (T[])new Comparable[m_items.length + CHUNK_SIZE];
			for (int i = 0; i < m_items.length; i++)
			{
				temp[i] = m_items[i];
			}
			m_items = temp;
		}
	}

}