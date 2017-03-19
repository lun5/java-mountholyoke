/**
 * Node class
 * has properties: key, value, left node, right node, parent node
 * @author nguye28l Luong Nguyen
 *
 * @param <K>
 * @param <V>
 * @version Nov 15, 2011
 */

public class Node<K extends Comparable<K>, V> {
	private V _value;
	private K _key;
	private Node<K,V> _left;
	private Node<K,V> _right;
	private Node<K,V> _parent;
	
	public Node(K key, V value){
		_value = value;
		_key = key;
		_left = null;
		_right = null;
		_parent = null;
	}
	
	
	public V get_value() {
		return _value;
	}


	public void set_value(V _value) {
		this._value = _value;
	}


	public K get_key() {
		return _key;
	}


	public void set_key(K _key) {
		this._key = _key;
	}


	public Node<K, V> get_left() {
		return _left;
	}


	public void set_left(Node<K, V> _left) {
		this._left = _left;
	}


	public Node<K, V> get_right() {
		return _right;
	}


	public void set_right(Node<K, V> _right) {
		this._right = _right;
	}


	public Node<K, V> get_parent() {
		return _parent;
	}


	public void set_parent(Node<K, V> _parent) {
		this._parent = _parent;
	}
}
