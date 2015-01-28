package heap;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> implements IHeap<T>
{
	ArrayList<T> nodes = new ArrayList<T>();
	private boolean m_max = true;

	@Override
	public void setMaxHeap(boolean asMax)
	{
		//Only runs if changing from min to max
		if(asMax && !m_max)
		{
			//If there;s anything in the heap already, it needs to be rearranged
			if(nodes.size() > 0)
			{
				//Add everything to a temp list and remove it from the heap
				ArrayList<T> temp = new ArrayList<T>();
				for(T item : nodes)
				{
					temp.add(item);
				}
				clear();
				
				//Change the heap type and re-add everything
				m_max = true;
				insertAll(temp);
			}
		}
		
		//Same as above, but other way around
		if(!asMax && m_max)
		{
			if(nodes.size() > 0)
			{
				ArrayList<T> temp = new ArrayList<T>();
				for(T item : nodes)
				{
					temp.add(item);
				}
				clear();
				
				m_max = false;
				insertAll(temp);
			}
		}
	}

	@Override
	public void insert(T item)
	{
		//Add to first available leaf
		nodes.add(item);
		
		//reheap up, unless the inserted node is the first one
		if(nodes.size() > 1)
		{
			reheapUp(nodes.size()-1);
		}
	}

	@Override
	public void insertAll(List<T> items)
	{
		for(T item: items)
		{
			insert(item);
		}
	}

	@Override
	public int size()
	{
		return nodes.size();
	}

	@Override
	public T peekTop()
	{
		return nodes.get(0);
	}

	@Override
	public T removeTop()
	{
		if(nodes.size() == 0)
		{
			System.out.println("Heap empty!");
			return null;
		}
		
		//Get the top
		T ret = nodes.get(0);
		
		//Replace it with the last leaf and remove it
		nodes.set(0, nodes.get(nodes.size()-1));
		nodes.remove(nodes.size()-1);
		
		//reheap down
		if(nodes.size() > 0)
		{
			reheapDown(0);
		}
		
		return ret;
	}

	@Override
	public void clear()
	{
		nodes = new ArrayList<T>();;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for(T item : nodes)
		{
			sb.append(item.toString() + " ");
		}
		return sb.toString();
	}
	
	private void reheapUp(int index)
	{
		//Find the new node's parent:
		int parentIndex = 0;
		
		//Odd number index means it's a left branch (2i+1 is always odd)
		if(index%2 == 1)
		{
			parentIndex = (index-1)/2;
		}
		//Even number index means it's a right branch
		if(index%2 == 0 && index > 0)
		{
			parentIndex = (index-2)/2;
		}
		
		//Max reheap
		if(m_max)
		{
			//Base case: parent is larger or equal
			if(nodes.get(index).compareTo(nodes.get(parentIndex)) <= 0)
			{
				return;
			}
			//Else swap and reheap again
			else
			{
				T temp = nodes.get(parentIndex);
				nodes.set(parentIndex, nodes.get(index));
				nodes.set(index, temp);
				reheapUp(parentIndex);
			}
		}
		
		//Min reheap
		if(!m_max)
		{
			//Base case: parent is smaller or equal
			if(nodes.get(index).compareTo(nodes.get(parentIndex)) >= 0)
			{
				return;
			}
			//Else swap and reheap again
			else
			{
				T temp = nodes.get(parentIndex);
				nodes.set(parentIndex, nodes.get(index));
				nodes.set(index, temp);
				reheapUp(parentIndex);
			}
		}
	}
	
	private void reheapDown(int index)
	{
		int leftIndex = (index*2) + 1;
		int rightIndex = (index*2) + 2;
		int maxIndex = index;
		int minIndex = index;
		
		//Both children exist
		if(leftIndex < nodes.size() && rightIndex < nodes.size())
		{
			if(nodes.get(leftIndex).compareTo(nodes.get(rightIndex)) >= 0)
			{
				maxIndex = leftIndex;
				minIndex = rightIndex;
			}
			else
			{
				maxIndex = rightIndex;
				minIndex = leftIndex;
			}
		}
		//Left child exists, but right doesn't
		else if(rightIndex >= nodes.size() && leftIndex < nodes.size())
		{
			maxIndex = leftIndex;
			minIndex = leftIndex;
		}
		//If neither exists, the maxIndex/minIndex is by default the same as the index
		
		//Max reheap
		if(m_max)
		{
			//Base Case
			if(nodes.get(index).compareTo(nodes.get(maxIndex)) >= 0)
			{
				return;
			}
			else
			{
				T temp = nodes.get(maxIndex);
				nodes.set(maxIndex, nodes.get(index));
				nodes.set(index, temp);
				reheapDown(maxIndex);
			}
		}
		
		//Min reheap
		if(!m_max)
		{
			//Base Case
			if(nodes.get(index).compareTo(nodes.get(minIndex)) <= 0)
			{
				return;
			}
			else
			{
				T temp = nodes.get(minIndex);
				nodes.set(minIndex, nodes.get(index));
				nodes.set(index, temp);
				reheapDown(minIndex);
			}	
		}
	}
}
