/**
 * Heap of comparable objects
 * Max heap is a binary in which each node holds a value greater or equal both its children
 * The underlying structure is an array of comparable object. 
 * Interfaces include: findMax, removeMax, insert and size
 * 
 * Assignment 5: due Nov 4th 2011
 * @author nguye28l
 * @version Nov 1st 2011
 */

public class Heap<E extends Comparable<E>>{
	// array of data
	private E[] data;
	// number of item in the heap
	private int _size;
	/**
	 * Constructor
	 * @param integer i, the initial size of the heap. 
	 * Heap will need to be expanding 
	 */
	public Heap(int i){
		data = (E[]) new Comparable[i];
		_size = 0;
	}
	
	/**
	 * findMax()
	 * @return the largest item in the heap
	 */
	public E findMax(){
		// return the first element in the underlying array
		return data[0];
	}
	
	/**
	 * removeMax()
	 * @return the largest item in the heap. 
	 * and remove the largest item, move the last item in the array to root position
	 * and siftDown() to push the node into the right place
	 */
	public E removeMax(){
		// the largest item
		E maxVal = data[0];
		// set the root to be the the last item
		data[0] = data[size()-1];
		// decrease the size of the heap
		_size --;
		if(size()>0){// the heap is not empty 
			data[size()] = null;// set the last item to null	
			// do siftdown on the heap
			siftdown();
		} 
		return maxVal;
	}

	/**
	 * siftdown()
	 * push the root node to the right position
	 * compare the node at start index with its largest child
	 * if that child is larger than the node itself
	 * repeat the process until reaching leaf
	 */
	private void siftdown(){
		// starting index to do siftdown
		int index = 0;
		// current length of the heap
		int length = size();
		// check if the node at index has any children
		while (2*index+1 < length){
			// left, right child and the largest child
			E left, right, child;
			// index of the largest child
			int indexChild;
			// keep track if swap has been done by keeping the original index
			int oldIndex = index;
			// get left child
			left = data[index*2+1];
			// check if it has right child
			if (2*index+2<= length -1){
				right = data[index*2+2];
				// find the largest between left and right
				if(left.compareTo(right)<0){
					child = right;
					indexChild = index*2+2;
				}else{
					child = left;
					indexChild = index*2+1;
				}
			} else{// if there is no left child
			// set the largest to be the left
				child = left;
				indexChild = index*2+1;
			}
			// swap the node at index with its largest child if needed
			if(data[index].compareTo(child)<0){
				E temp = data[index];
				data[index] = child;
				data[indexChild] = temp;
				// update the index to continue siftdown
				index = indexChild;
			} 
			// if no swap happen, set index = length go get out of the while loop
			if (oldIndex == index) index =length;
		}
	}
	
	/**
	 * insert(E item)
	 * insert an item to the heap
	 * by inserting it to the last open slot and siftUp() to the correct position
	 * call the extend helper method if needed
	 */
	public void insert(E item){
		// if the array is full, extend
		if (size()== data.length) extend();
		// insert item to the last open slot
		data[size()] = item;
		// increase _size
		_size ++;
		// call siftup to move the item to its correct position
		siftup();
	}
	
	/**
	 * siftup()
	 * bubble the last node to the right position
	 * compare the node at end index with its parent
	 * if its parent is smaller then swap child-parent
	 * repeat the process until reaching the root
	 */
	private void siftup(){
		// starting index to do siftup
		int index = size()-1;
		// check if the node at root
		while (index > 0){
			int pIndex = (int) (index-1)/2;
			// get its parent
			E p = data[pIndex];
			int oldIndex = index; // keep track if any swap happened
			// compare the node at index with its parent
			// swap if it's greater than the parent
			if (data[index].compareTo(p)>0){
				E temp = data[index];
				data[index] = p;
				data[pIndex] = temp;
				index = pIndex; // update index				
			}
			// if no swap happen, set index to 0 and exit the while loop
			if (oldIndex == index) index = 0;// we are done
		}
	}
	
	/**
	 * extend() doubles the size of the underlying array
	 * private method, not accessible outside the class
	 */
	private void extend(){
		// temporary array of double the size
		E[] temp;
		temp = (E[]) new Comparable[size()*2];
		// copy _data over to temp
		for(int i = 0; i<size();i++){
			temp[i]= data[i];
		}
		// set _data to temp
		data = temp;
	}
	
	/**
	 * size 
	 * @return the number of items in the heap
	 */
	public int size(){
		return _size;
	}
	
	/**
	 * isEmpty
	 * @return true if the heap is empty
	 */
	public boolean isEmpty(){
		return size()==0;
	}	
}
