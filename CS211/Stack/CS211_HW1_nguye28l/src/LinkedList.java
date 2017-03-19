//import java.lang.Exception;

public class LinkedList<E> {
	
	private static class Node<E>{
	private E _data;
	private Node<E> next;
	
	private Node(E data, Node<E> node){
		_data = data;
		next = node;
	}
	
	private Node (E data){
		this(data,(Node<E>) null);
	}
	
	private Node<E> next(){
		return next;
	}
	
	private void setNext(Node<E> node){
		next = node;
	}
	
	private E getValue(){
		return _data;
	}
	
	}
	
	private Node<E> _head;
	private int _size;

		public LinkedList(){
			_head = null;
			_size = 0;
		}

		public void add(int i, E value){
			if (i > size() || i<0){
				throw new IndexOutOfBoundsException();
			}
			
			Node<E> tmp = new Node<E>(value);
			if (_head == null){
				_head = tmp;
			}else{
			Node<E> thumb = _head;
			for (int j = 0; j < i - 1 ; j++){
				thumb = thumb.next();
			}			
			tmp.setNext(thumb.next());
			thumb.setNext(tmp);		
			}
		}
		
		public void addFirst(E value){
			Node<E> tmp = new Node<E>(value);
			
			tmp.setNext(_head);
			_head = tmp;
		}
		
		public void addLast(E value){}
		
		public E remove(int i){ return null;}
		
		public E removeFirst(){ return null;}
		
		public E removeLast(){ return null;}

		public int size(){
			return _size;
		}
		
		public void clear(){}
		
		public boolean isEmpty(){
			return size() == 0;
		}
		
		public E get(int i){
			if (i > size() || i<0){
				throw new IndexOutOfBoundsException();
			}
			Node<E> thumb = _head;
			for (int j = 0; j < i ; j++){
				thumb = thumb.next();
			}
			return thumb.getValue();
		
		}
		
		public void set(E value, int i){}
		
		public int indexOf(E value){ return -1; }
		
		public boolean contains(E value){ return false; }
}