/**
* This is a resizable array implementation of the Queue. 
* The array used is the circular array
* Methods include enQueue(), deQueue(), peek(), and isEmpty(). 
* Assignment 1. Due 09/22/11
* @author Luong Nguyen <nguye28l@mtholyoke.edu>
* @version 20 september 2011
*/
import java.util.NoSuchElementException;
public class Queue<E> {
	// underlying array
	private Object _data[];
	// current size of the Q
	private int _size;
	// the head is index of the oldest element in Q
	// first value we can dequeue
	private int _head;
	
	// Constructors with and without specific capacity
		public Queue(){
			_data = new Object[10];
			_size = 0;
			_head = 0;
		}

		public Queue(int capacity){
			_data = new Object[capacity];
			_size = 0;
			_head = 0;
		}
		
		/**
		 * enQueue take
		 * @param E value
		 * put it at the end of the queue 
		 */
		public void enQueue(E value){
			// the next enquealble index
			int _tail;
			// tail position 
			_tail = (_head + size()) %_data.length ;
			// if the queue is full and the queue is not empty, 
			// that means the queue is full, extend the queue
			if (_tail == _head && !isEmpty()){
				// this new element cannot be fit in the current array, so it needs to be expanded
				extend();
				// tail position 
				_tail = (_head + size()) %_data.length ;
			}
			// add the value at position size
			_data[_tail] = value;
			// increase the size
			_size ++;
		}
		
		/**
		 * deQueue
		 * @return the value that is first in the queue and remove it
		 * throw NoSuchElementException when the queue is empty
		 */
		public E deQueue(){ 
			// if the queue is empty
			// throw an exception
			if(isEmpty()){
				throw new NoSuchElementException();
			}
			// otherwise set head position to null
			// move the head position and decrease size
			// keep head in a temp
			E temp;
			temp =(E)_data[_head];
			_data[_head]=null;
			_head = (_head+1)%_data.length;
			_size --;
			// return the temp variable
			return temp;
		}
		
		/**
		 * peek
		 * @return the value that is first in the queue
		 * without removing it
		 * throw NoSuchElementException when the queue is empty
		 */
		public E peek(){
			// if the queue is empty
			// throw an exception
			if(isEmpty()){
				throw new NoSuchElementException();
			}
			// otherwise return the head
			return (E)_data[_head];
		}
		
		// size() return the size of the queue
		// is a private method, not accessible outside the class
		private int size(){
			return _size;
		}
		
		// extend() doubles the size of the underlying array
		// private method, not accessible outside the class
		private void extend(){
			// temporary array of double the size
			Object temp[];
			temp = new Object[size()*2];
			// copy _data over to temp
			for(int i = 0; i<size();i++){
				temp[i]=_data[i];
			}
			// set _data to temp
			_data = temp;
		}
		
		/**
		 * isEmpty
		 * @return true if the queue is empty, false otherwise
		 */
		public boolean isEmpty(){
			return size() == 0;
		}
}

