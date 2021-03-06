/**
* This is a linked list implementation of Stack. 
* Methods include: push(), pop(), peek() and isEmpty()
* Assignment 1. Due 09/22/11
* @author Luong Nguyen <nguye28l@mtholyoke.edu>
* @version 20 september 2011
*/

import java.util.EmptyStackException;

/**
 * Private class Node is the building block for the stack
 * it has next(), setNext(node), getValue() as methods
 */
public class Stack<E> {
	private static class Node<E>{
		private E _data;
		private Node<E> next;
		//Constructors
		private Node(E data, Node<E> node){
			_data = data;
			next = node;
		}
		
		private Node (E data){
			this(data,(Node<E>) null);
		}
		
		// return the next node of the current one
		private Node<E> next(){
			return next;
		}
		
		// set the next node to some node
		private void setNext(Node<E> node){
			next = node;
		}
		
		// return value of the node
		private E getValue(){
			return _data;
		}
	}
	
	// we implement a singly linked list
	private Node<E> _head;
	//private int _size;
	
	/**
	 * constructors
	 */
	public Stack(){
		_head = null;
		//_size = 0;
	}
	
	// constructor with size
	public Stack(int size){
		_head = null;
		//_head.setValue(null);
		//_size = size;
	}
	
	// push a value on top of the stacks
	public void push(E value){
		Node<E> tmp = new Node<E>(value);
		tmp.setNext(_head);
		_head = tmp;
	}
	
	
	// remove the node on top of the stack
	// and return the data held by the node
	public E pop() {
		Node<E> tmp = _head;
		if (isEmpty()){
			throw new EmptyStackException();
		}
		else{
			_head = _head.next();
			return tmp.getValue();
		}
	}
	
	// return the data held by the node on top of the stack
	public E peek(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
		return _head.getValue();
	}
	
	//check if the stack is empty	
	public boolean isEmpty(){
		if (_head == null) return true;
		else return false;
	}	
}
