package exam;

public interface IStack<T>
{
	/**
	 * Pushes an element onto the top of the stack. 
	 * @param item - the element to be pushed onto the stack.
	 */
	public void push(T item);
	
	/**
	 * Removes the topmost element in the stack (filo). 
	 * @return the element that was removed from the stack.
	 * @throws IllegalStateException if the stack is empty. 
	 */	
	public T pop();
	
	
	/**
	 * Retrieves the topmost element in the stack.  Unlike pop, 
	 * the element is not removed from the stack.
	 * @return the topmost element in the stack.
	 * @throws IllegalStateException if the stack is empty.
	 */
	public T top();
	
	/** 
	 * Determines if the stack contains any elements.
	 * @return true if the stack is empty, false otherwise. 
	 */
	public boolean isEmpty();
	
	/** 
	 * Determines the number of elements in the stack.
	 * @return the number of elements in the stack. 
	 */	
	public int getSize();
}
