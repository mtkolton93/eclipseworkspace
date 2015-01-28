package binary_tree;

public interface IBinaryTree<T extends Comparable<T>>
{
	public T getItem();
	
	public IBinaryTree<T> getParent();
	
	public boolean isBatman();
	
	public IBinaryTree<T> getLeft();
	
	public IBinaryTree<T> getRight();
	
	public IBinaryTree<T> search(T item);
	
	public IBinaryTree<T> min();
	
	public IBinaryTree<T> max();
	
	public int getLevel();
	
	public void inOrder();
	
	public boolean insert(T item);
	
	public boolean delete(T item);
}
