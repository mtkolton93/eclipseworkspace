package facebook;

import java.util.List;

public interface IHeap<T extends Comparable<T>>
{
	// This determines the state of the heap, which 
	// can be either max or min.  In a max heap, the
	// topmost item is the largest item in the heap.
	// In a min heap, the topmost item is the smallest
	// item in the heap.  Passing true to this method
	// will set the heap state to max, passing false to
	// this method will set the heap state to min.
	// The default heap state should be max.  If the 
	// heap has items in it when this method is called,
	// the items should be rearranged accordingly.
	public void setMaxHeap(boolean asMax);
	
	// Adds an item to the heap.
    public void insert(T item);
    
    // Inserts all the items in the list into  
    // the heap.
    public void insertAll(List<T> items);
    
    // Returns the number of items in the heap.
    public int size();
    
    // Returns the item at the top of the heap, 
    // but does not remove it from the heap.
    // The topmost item will either be the largest or
    // the smallest item in the heap, depending on the 
    // the state of the heap (max or min).    
    public T peekTop();
    
    // Returns and removes the item at the top of the heap.
    // The topmost item will either be the largest or
    // the smallest item in the heap, depending on the 
    // the state of the heap (max or min).
    public T removeTop();
    
    // Removes all items in the heap.
    public void clear();
}