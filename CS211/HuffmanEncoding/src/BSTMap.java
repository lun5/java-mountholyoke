import java.util.Set;
import java.util.TreeSet;
/**
 * Note to self:
 * need to build a node class then build the BSTMap based on that
 * need to create a wrapper node for the priority queue <freq, a tree> that have a compareTo method in it
 * the wrapper implements Comparable<WrapperNode>
 */


/**
 * Class BSTMap<K extends comparable, V> implements the interface map
 * is an associative structure
 * Methods include: put, get, containsKey, remove
 * BSTMap is a map of binary search trees
 * underlying resembles a binary search tree
 * the functionality: put = add
 *
 * @author nguye28l
 * @version Nov 13
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K,V>{
	/**
	 * Properties: root node + key
	 */
	private Node<K,V> _root;
	private Set<K> _keyset;
	
	/**
	 * Constructor
	 */
	public BSTMap(){
		//_root = new Node<K,V>(null, null);
		_keyset = new TreeSet<K>();
	}
	
	/**
	 * put
	 * @param K key, V value
	 * load the value into the map at the location associated with the key
	 * replacing preexisting value associated with this key
	 * this resembles the add method in binary search tree
	 */
	public void put(K key, V value){
		Node<K,V> p = null;//parent
		Node<K,V> r = _root;// the tree
		Node<K,V> tmp = new Node<K,V>(key, value);// node to add
		
		// traverse the tree until reaching leaf position or found the right position to add
		while(r!= null && r.get_key().compareTo(key) !=0){
			p = r;
			// if the key < root's key, traverse down the left branch
			if(key.compareTo(r.get_key())<0){
				r = r.get_left();
			}else{
				r = r.get_right(); // go down the right branch
			}
		}
		
		// if found the position to put the node in
		if (r != null){
			r.set_value(value); // reset the value at that position 
			return;
		}
		
		tmp.set_parent(p);// found a good position for tmp's parent
		if (p==null){// empty tree
			_root = tmp;
		}else{
			if(key.compareTo(p.get_key())<1)
				p.set_left(tmp); // add to the left
			else p.set_right(tmp);// add to the right
		}
		_keyset.add(key);
	}
	
	/**
	 * get
	 * @param K key
	 * @return the value V associated with Key key
	 * resemble the get method in binary search tree
	 */
	private Node<K,V> getNode(K key){
		Node<K,V> tmp = _root;
		// when the tree is not empty, search for the key
		// by traversing down the tree left/right depending on the value of key
		while(tmp!=null && tmp.get_key().compareTo(key)!=0){
			// if the key is < the key held by tmp
			if(key.compareTo(tmp.get_key())<1)
				tmp = tmp.get_left(); // go to the left branch
			else tmp = tmp.get_right(); // go to the right branch
		}
		return tmp;
	}
	
	/**
	 * get
	 * @param K key
	 * @return the value V associated with Key key
	 * resemble the get method in binary search tree
	 */
	public V get(K key){
		return getNode(key).get_value();
	}
	
	/**
	 * containsKey
	 * @param K key
	 * @return true if there is a value associated with the value key in the map
	 * @return false otherwise
	 */
	public boolean containskey(K key){
		return getNode(key) != null;
	}
	
	/**
	 * remove
	 * @param K key
	 * remove the entry associated with key
	 * @return the associated value
	 * resemble the remove in binary search tree, need the successor and minimum method
	 * if the node with key is a left then get the value and delete the node
	 * if the node has just 1 child then splice the node
	 * if the node has 2 children. Swap the node it its childless child ( if there is one)
	 * otherwise fine the successor of the node,
	 * splice the node out and put the successor in the position of the node
	 */
	public V remove(K key){
		Node<K,V> tmp = null;
		Node<K,V> child = null;
		// find the node with key
		Node<K,V> node = this.getNode(key);
		// first identify the node that needed to be splice out, put into tmp
		// the node has no child or 1 child
		if (node.get_left()!= null || node.get_right()!=key){
			// delete the node
			tmp = node;
		}else{// have both children
			if(node.get_left()!=null && node.get_left().get_right()==null){
				// the node has left child but the left child has no right child
				// need to disconnect the node and put the left child to the position of the node
				tmp = node;
				node.get_left().set_right(node.get_right());
				node.set_right(null);// disconnect node
				_keyset.remove(key);
				return node.get_value();
			}else if(node.get_right()!=null && node.get_right().get_left() == null){
				// the node has right child but the right child has no left child
				// splice the node and put the right child to the position of the node
				tmp = node;
				node.get_right().set_left(node.get_left());
				node.set_left(null);
				_keyset.remove(key);
				return node.get_value();
			}else{
				// the node have 2 children and none of the above 2 cases satisfy
				tmp = successor(node);// find its succesor
			}
		}
		// splice out the tmp, tmp has either no child or 1 child
		// if tmp has left child, set child to be its left child
		if(tmp.get_left()!=null) child = tmp.get_left();
		else child = tmp.get_right();
		// if tmp has a child, set child's parent to be tmp parent
		// i.e. we disconnect tmp from its parent and connect tmp's child to tmp's parent
		if(child!=null) child.set_parent(tmp.get_parent());
		// if tmp is a left child of its parent
		if(tmp.get_parent().get_left()==tmp)
			tmp.get_parent().set_left(child);
		else tmp.get_parent().set_right(child);
		_keyset.remove(key);
		return node.get_value();
	}
	
	/**
	 * minimum
	 * @param a node
	 * @return the node with minimum key of the map rooted at node
	 */
	private Node<K,V> minimum(Node<K,V> node){
		// keep going down the left branch
		Node<K,V> tmp = node;
		while(tmp.get_left()!= null){
			tmp = tmp.get_left();
		}
		return tmp;
	}
	
	/**
	 * successor
	 * @param a node of the tree
	 * @return a node immediate successor of a node
	 * the node that has key - directly greater
	 */
	private Node<K,V> successor(Node<K,V> node){
		Node<K,V> tmp = node;
		// if the node has right child, return the min in its right branch
		if(tmp.get_right()!= null) return minimum(tmp.get_right());
		// otherwise traverse up the tree until the node is a left child
		Node<K,V> p = tmp.get_parent();
		while(p!= null && p.get_right()==tmp){
			tmp = p;
			p = p.get_parent();
		}
		return p;
	}
	
	
	/**
	 * keySet()
	 * @return a set of all variable keys in the map
	 * can build on the fly using a traversal or collect as new keys are added to the map
	 */
	public Set<K> keySet(){
		return _keyset;
	}
}
