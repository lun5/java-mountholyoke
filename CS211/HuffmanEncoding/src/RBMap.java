import java.util.Set;
import java.util.TreeSet;


public class RBMap<K extends Comparable<K>, V>  implements Map<K,V>{
    // enum used to mark nodes as red or black
    private static enum NodeColor {RED, BLACK};
    // null node to be used for all leaf nodes
    private Node _leafNode = new Node(null, null, null, null, null, NodeColor.BLACK);
    
    // the root of the binary tree storing our data
    private Node _root = null;
    // the set of all keys
    private Set<K> _keys;
    
    
    public RBMap(){
        _keys = new TreeSet<K>();
    }
    
    
    
    /**
     * Adds a value to an associated key in the map.
     * If the key is already present in the map, the value will be replaced.
     * If the key is not present, the key, value pair will be added to the map.
     * 
     * @param key the value used serving as the index to the value
     * @param value the actual value being stored in the map
     */
    public void put(K key, V value) {
        Node current = locateNode(key);
        
        if (current != null && current.getKey().equals(key)){ // we found the right node, set the value and leave
            current.setValue(value);
            return;
        }
        
        
        // did not find a matching key, insert a new node using current as the parent
        Node newNode =new Node(key, value, current, _leafNode, _leafNode, NodeColor.RED);
        _keys.add(key);
        
        if (current == null){ // no parent, so this is the new root
            _root = newNode;            
        }else{ // attach the node to the appropriate parent
            if (key.compareTo(current.getKey()) < 0){
                current.setLeftChild(newNode);
            }else{
                current.setRightChild(newNode);
            }
        }
        
        // TODO rebalance the tree
        // call rebalance on the added node
        rebalance(newNode);           
        
    }
    
    /**
     * rebalance
     * @param node- the node at the root of the subtree need to be rebalanced
     * make sure no red or black violation remain after insertion
     */
    private void rebalance(Node node){
    	 // if the parent of the node is the root then no need to balance
    	// if the parent of the node is the root then no need to balance
        if(node == _root){
        	// set the color to black and return
            node.setColor(NodeColor.BLACK);
            return;
        }        
        Node parent = node.getParent(); // parent
        Node gparent = parent.getParent();// grand parent
        Node aunt; // sibling of parent
        if (gparent.getLeftChild() == parent) aunt = gparent.getRightChild();
        else aunt= gparent.getLeftChild();
        
        // case 1: violating node's aunt is red
        if (aunt.getColor()==NodeColor.RED){
        	//Color the parent and uncle black and color the grandparent red
        	parent.setColor(NodeColor.BLACK);
        	aunt.setColor(NodeColor.BLACK);
        	gparent.setColor(NodeColor.RED);
        	// call rebalanc on the grandparent, potential violation now
        	rebalance(gparent);
        }
        
        // case 2: violating node is a right child and the node's aunt is black
        if (parent.getRightChild()==node && aunt.getColor() == NodeColor.BLACK){
        	// rotate the parent left
        	// set parent to be the node's left child
        	node.setLeftChild(parent);
        	// set the node to be the gparent's child
        	if(gparent.getLeftChild() == parent) gparent.setLeftChild(node);
        	else gparent.setRightChild(node);
        	// set node to be the parent of parent
        	parent.setParent(node);
        	parent.setRightChild(_leafNode);
        	// parent is the potential violation now
        	rebalance(parent);
        }
        
        // case 3: violating node is a left child and the node's aunt is black
        if (parent.getLeftChild()==node && aunt.getColor() == NodeColor.BLACK){
        	//Change parent’s color to black and grandparent’s color to red
        	parent.setColor(NodeColor.BLACK);
        	gparent.setColor(NodeColor.RED);
        	// rotate on the gparent
        	// if the parent is left child of gparent, rotate right the gparent
        	if(gparent.getLeftChild()==parent){
        		parent.setParent(gparent.getParent()); // connect parent to gparent's parent
        		gparent.setLeftChild(parent.getRightChild()); // set the left child of gparent to node's sibling
        		gparent.setParent(parent); 
        		parent.setRightChild(gparent);
        	} // done right rotation
        	// if the parent is right child of gparent, rotate left the gparent
        	// after this: node -> gparent's right child & gparent -> parent's left child
        	if(gparent.getRightChild()==parent){
        		parent.setParent(gparent.getParent()); // connect parent to gparent's parent
        		gparent.setRightChild(node); // set the left child of gparent to node
        		gparent.setParent(parent); 
        		parent.setLeftChild(gparent);
        	} // done right rotation      	
        } // no violation remains after this point       
    }

    /**
     * Looks up a key on the map and returns the associated value if it exists.
     * If the value does not exist in the map, this will return null.
     * 
     * @param key a value to be used as the key
     * @return the associated value or null if the key is not present
     */
    public V get(K key) {
        Node node = locateNode(key);        
        if (node != null && node.getKey().equals(key)){
            return node.getValue();
        }else{
            return null;
        }
    }

    
    /**
     * Returns true if the map contains a mapping for the specified key.
     * 
     * @param key the key that is being tested
     * @return true if this map contains the key
     */
    public boolean containskey(K key) {
        return get(key) != null;
    }

    
    /**
     * Removes the key and its associated value from the map.
     * If the key is not found, null is returned
     * 
     * @param key the key associated with the record to be removed
     * @return the value formerly mapped to the key or null if the key is not found
     */
    public V remove(K key) {
        _keys.remove(key); // remove the key from the keyset
        Node node = locateNode(key);
        V returnValue = null;
        
        if (node == null || ! node.getKey().equals(key)) { // didn't find the right node
            return null;
        }
        
        returnValue = node.getValue();
        
        // remove the node from the tree
        Node toRemove = null;
        Node child = null;
        
        if (node.getLeftChild() == _leafNode || node.getRightChild() == _leafNode){
            // we can just splice out this node since it only has one child
            toRemove = node;
        } else if (node.getLeftChild() != _leafNode 
                && node.getLeftChild().getRightChild() == _leafNode){
            // we can pass our right child down to the left child since it doesn't have a right child
            toRemove = node;
            node.getLeftChild().setRightChild(node.getRightChild());
            node.setRightChild(_leafNode);
        }else if (node.getRightChild() != _leafNode 
                && node.getRightChild().getLeftChild() == _leafNode){
            // we can pass our left child down to the right child since it doesn't have a left child

            toRemove = node;
            node.getRightChild().setLeftChild(node.getLeftChild());
            node.setLeftChild(_leafNode);
        }else{
            // we need to move the value of our successor here and splice out the successor's old node
            // we know this has only one child since it is the successor of this node, making it easy to splice out
            toRemove = successor(node);
            node.setValue(toRemove.getValue());
            node.setKey(toRemove.getKey());
        }
        
        
        // set child to the one child of toRemove (the child may or may not be a _leafNode)
        if (toRemove.getLeftChild() != _leafNode){
            child = toRemove.getLeftChild();
        }else{
            child = toRemove.getRightChild();
        }
        
        // if the child is not a leaf, set its parent to splice around the node to be removed
        if (child != _leafNode){
            child.setParent(toRemove.getParent());
        }     
                
        if (toRemove.getParent() == null){ 
            // we are removing the root, so the child is the new root
            // if the child is a leaf, set the root to null
            _root = child;
            if (_root == _leafNode)
                _root = null;
        } else if (toRemove.getParent().getLeftChild() == toRemove){ // parent exists, connect it back down to the child
            toRemove.getParent().setLeftChild(child);
        }else {
            toRemove.getParent().setRightChild(child);
        }
              
        // TODO rebalance the tree
        rebalance1(node);       
        
        return returnValue;
    }

    /**
     * rebalance1
     * @param node - the node at the root of the subtree need to be rebalanced
     * rebalance after deletion
     */
    private void rebalance1(Node node){
    	
    }
    /**
     * Returns a Set<K> of all keys found in the map.
     * 
     * @return the Set of keys
     */
    public Set<K> keySet() {
        return _keys;
    }
      
    
    
    /**
     * Finds the node in the tree or its parent, if the node does not exist.
     * 
     * @param key the key that should identify the node
     * @return the node associated with the key, or the parent of where the key should be
     */
    private Node locateNode(K key){
        Node current = _root;
        Node parent = null;
        
        while (current != null && current != _leafNode){
             parent = current;
             if (current.getKey().equals(key)){ // found the key, return the node
                return current;
            }else if (key.compareTo(current.getKey()) < 0){ // key < current key
                current = current.getLeftChild();
            }else{
                current = current.getRightChild();
            }
        }        
        // we have exhausted the tree, the key isn't in it, return the parent
        return parent;
    }
           
    /**
     * Return the immediate successor of a node.
     * If the node has a right child, then this is just the minimum value in the 
     * node's right subtree. 
     * If the node doesn't have a value, the successor will be the first parent
     * to the left of the node.
     * 
     * @param node the node we are finding the successor for
     * @return the successor node or null if this is the largest node in the tree
     */
    private Node successor(Node node){
        if (node.getRightChild() != _leafNode){
            return minimum(node.getRightChild());
        }
        
        Node parent = node.getParent();
        while (parent != null && node == parent.getRightChild()){
            node = parent;
            parent = parent.getParent();
        }
        
        return parent;
        
    }
        
    /**
     * Return the minimum value in the tree rooted at node.
     * This requires a simple walk down the left side of the tree.
     * 
     * @param node the node at the root of the subtree we are exploring
     * @return the smallest node in the subtree rooted at node
     */
    private Node minimum(Node node){
        while (node.getLeftChild() != _leafNode){
            node = node.getLeftChild();
        }
        
        return node;
        
    }
    
    /**
     * redViolationExists()
     * @return true if there is a red node with red child
     * @return false if all red nodes have black children
     */
    public boolean redViolationExists(){
    	return redViolationExists(_root);
    }
    
    /**
     * redViolationExists
     * @param node: the node at the root of the substree we are exploring
     * @return true if there is a red violation in that subtree
     */
    private boolean redViolationExists(Node node){
    	// if leaf node
    	if (node == _leafNode) return false;
    	//if the node is red and any of its children is red, return true
    	// otherwise return the call on its children
    	if (node.getColor() == NodeColor.RED && (node.getLeftChild().getColor() == NodeColor.RED ||
    			node.getRightChild().getColor() == NodeColor.RED)) return true;
    	if (redViolationExists(node.getLeftChild())||redViolationExists(node.getRightChild())) return true;  	
    	return false;
    }
    
    
    /**
     * The Node class forms the basis of the internal structure of this map. It provides
     * the structure for a basic binary tree with the addition of fields for storing 
     * a key and a color.
     */
    private class Node {
        private K _key; // the key value, this is the indexing field
        private V _value; // the actual value stored in the map
        private Node _parent, _leftChild, _rightChild; // references allowing for the creation of the binary tree
        private NodeColor _color; // the color of the node, either red or black
        
        public Node(K key, V value, Node parent, Node leftChild, Node rightChild, NodeColor color){
            _key = key;
            _value = value;
            _parent = parent;
            _leftChild = leftChild;
            _rightChild = rightChild;
            _color = color;
        }

        public NodeColor getColor() {
            return _color;
        }

        public void setColor(NodeColor color) {
            _color = color;
        }

        public K getKey() {
            return _key;
        }

        public void setKey(K key) {
            _key = key;
        }
        
        public Node getParent(){
            return _parent;
        }
        
        public void setParent(Node parent){
            _parent = parent;
        }

        public Node getLeftChild() {
            return _leftChild;
        }

        public void setLeftChild(Node leftChild) {
            _leftChild = leftChild;
        }

        public Node getRightChild() {
            return _rightChild;
        }

        public void setRightChild(Node rightChild) {
            _rightChild = rightChild;
        }

        public V getValue() {
            return _value;
        }

        public void setValue(V value) {
            _value = value;
        }
 
    } // end of Node

}// end of RBMap class
