import java.util.LinkedList;


/**
 * This is the the implementation of stack
 * built around a LinkedList that is capable of storing
 * an arbitrarily large amount of data
 * @author Luong Nguyen
 * @date 4/20/11
 */
public class MyStack<T> {
	private LinkedList<T> linkL;
	// constructor
	public MyStack()
	{
		linkL = new LinkedList<T>();
	}

	/**
	 * push(T data) add the element data to the top of the stack
	 */
	public void push(T data)
	{
		linkL.addFirst(data);
	}
		
	/**
	 * pop remove the top elt from the stack
	 * throws a NoSuchElementException
	 */
	public T pop()
	{
		// the data to be returned
		return linkL.removeFirst(); 
	}
	
	/**
	 * top examine the top elt on the stack
	 * @return the top elt, i.e. the last one in the linked list
	*/
	public T top()
	{
		return linkL.getFirst();
	}
	
	/**
	 * isEmpty() 
	 * @return true if empty stack, false otherwise
	 */
	public boolean isThisEmpty()
	{
		//if(this.size()==0) return true;
		//else return false;
		// check if the head is null then list is empty
		return linkL.isEmpty();
	}
}