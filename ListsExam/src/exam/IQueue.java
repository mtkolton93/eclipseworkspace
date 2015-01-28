package exam;

public interface IQueue<T>
{
	/**
	 * Adds an element to the rear of the queue. 
	 * @param item - the element to be added to the end of the queue.
	 */
	public void enqueue(T item);
	
	/**
	 * Removes the element at the front of the queue (fifo). 
	 * @return the element that was removed from the queue.
	 * @throws IllegalStateException if the queue is empty. 
	 */	
	public T dequeue();
	
	
	/** 
	 * Determines if the queue contains any elements.
	 * @return true if the queue is empty, false otherwise. 
	 */
	public boolean isEmpty();
	
	/** 
	 * Determines the number of elements in the queue.
	 * @return the number of elements in the queue. 
	 */	
	public int getSize();
}
