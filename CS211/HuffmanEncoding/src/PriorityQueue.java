/**
 * Priority queue of comparable objects
 * implement using Heap
 * Interfaces include: getFirst(), remove, add, isEmpty, and size
 * 
 * Assignment 5: due Nov 4th 2011
 * @author nguye28l
 * @version Nov 2nd 2011
 */

public class PriorityQueue<E extends Comparable<E>>{
	private Heap<E> _heap; 
	/**
	 * Constructor creap the heap
	 */
	public PriorityQueue(){
		_heap = new Heap<E>(10);
	}
	
	/**
	 * getFirst
	 * @return the first element without removing
	 */
	public E getFirst(){
		return _heap.findMax();
	}
	
	/**
	 * remove
	 * @return first element and remove it from the queue
	 */
	public E remove(){
		return _heap.removeMax();
	}
	
	/**
	 * add
	 * @param item of type E
	 * add item to the queue
	 */
	public void add(E item){
		_heap.insert(item);
	}
	
	/**
	 * isEmpty
	 * @return true if the heap is Empty
	 * false otherwise
	 */
	public boolean isEmpty(){
		return _heap.size() ==0;
	}
	
	/**
	 * size
	 * @return the size of the queue
	 */
	public int size(){
		return _heap.size();
	}
}
