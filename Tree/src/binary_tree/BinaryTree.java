package binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T>
{
	private IBinaryTree<T> m_parent;
	private IBinaryTree<T> m_left;
	private IBinaryTree<T> m_right;
	private T m_item;
	private int m_level;
	
	public BinaryTree(T item)
	{
		m_item = item;
	}

	@Override
	public T getItem()
	{
		return m_item;
	}

	@Override
	public IBinaryTree<T> getParent()
	{
		return m_parent;
	}
	
	@Override
	public boolean isBatman()
	{
		return m_parent == null;
	}

	@Override
	public IBinaryTree<T> getLeft()
	{
		return m_left;
	}

	@Override
	public IBinaryTree<T> getRight()
	{
		return m_right;
	}

	@Override
	public IBinaryTree<T> search(T item)
	{
		return _search(this, item);
	}

	@Override
	public IBinaryTree<T> min()
	{
		IBinaryTree<T> ret = this;
		
		while(ret.getLeft() != null)
		{
			ret = ret.getLeft();
		}
		return ret;
	}

	@Override
	public IBinaryTree<T> max()
	{
		IBinaryTree<T> ret = this;
		
		while(ret.getRight() != null)
		{
			ret = ret.getRight();
		}
		return ret;
	}
	
	@Override
	public int getLevel()
	{
		return m_level;
	}

	@Override
	public void inOrder()
	{
		
	}

	@Override
	public boolean insert(T item)
	{

		return _insert(this, item);
	}

	@Override
	public boolean delete(T item)
	{
		IBinaryTree<T> t = search(item);
		if(t == null)
		{
			return false;
		}
		else
		{
			return _delete(this);
		}
	}
	
	private IBinaryTree<T> _search(IBinaryTree<T> tree, T item)
	{
		IBinaryTree<T> ret = null;
		
		if(tree != null)
		{
			if(tree.getItem().equals(item))
			{
				ret = tree;
			}
			else
			{
				if(item.compareTo(tree.getItem()) == -1)
				{
					ret = _search(tree.getLeft(), item);
				}
				if(item.compareTo(tree.getItem()) == 1)
				{
					ret = _search(tree.getRight(), item);
				}
			}
		}
		
		return ret;
	}
	
	private boolean _insert(IBinaryTree<T> p, T item)
	{
		boolean ret = true;
		BinaryTree<T> parent = (BinaryTree<T>)p;
		
		//traverse left
		if(item.compareTo(p.getItem()) == -1)
		{
			IBinaryTree<T> left = p.getLeft();
			if(left == null)
			{
				BinaryTree<T> newTree = new BinaryTree<T>(item);
				parent.setLeft(newTree);
			}
			else
			{
				ret = _insert(left, item);
			}
		}
		//traverse right
		else
		{
			IBinaryTree<T> right = p.getRight();
			if(right == null)
			{
				BinaryTree<T> newTree = new BinaryTree<T>(item);
				parent.setRight(newTree);
			}
			else
				ret = _insert(right, item);
		}
		
		return ret;
	}
	
	private boolean _delete(IBinaryTree<T> p)
	{
		boolean ret = false;
		BinaryTree<T> tree = (BinaryTree<T>)p;
		
		if(tree != null)
		{
			BinaryTree<T> parent = (BinaryTree<T>)tree.getParent();
			
			//If the tree is a leaf
			if(tree.getLeft() == null && tree.getRight() == null)
			{
				if(tree.isBatman())
				{
					ret = false;
				}
				if(parent.getLeft() == tree)
				{
					parent.setLeft(null);
				}
				else
				{
					parent.setRight(null);
				}
			}
			
			//Tree has a left branch
			else if(tree.getLeft() != null)
			{
				BinaryTree<T> largestToLeft = (BinaryTree<T>)tree.getLeft().max();
				tree.setItem(largestToLeft.getItem());
				_delete(largestToLeft);
			}
			//Tree has a right branch
			else if(tree.getLeft() != null)
			{
				BinaryTree<T> smallestToRight = (BinaryTree<T>)tree.getLeft().min();
				tree.setItem(smallestToRight.getItem());
				_delete(smallestToRight);
			}
		}
		
		return ret;
	}

	private void setLeft(BinaryTree<T> left)
	{
		m_left = left;
	}
	
	private void setRight(BinaryTree<T> right)
	{
		m_right = right;
	}
	
	private void setItem(T item)
	{
		m_item = item;
	}
}
