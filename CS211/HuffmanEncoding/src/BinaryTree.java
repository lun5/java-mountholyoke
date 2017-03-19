/**
* This is a linked list implementation of Binary Tree 
* Methods include: get/setLeftChild, get/setRightChild, 
* get/setValue, isLeaf.
* Assignment 3. Due 10/08/11
* @author Luong Nguyen <nguye28l@mtholyoke.edu>
* @version 3 Oct 2011
*/

public class BinaryTree<E> {
/**
 * Private node class linked to the previous node
 * the left and right nodes
 * and functions: set/get Left, right
 * setValue, getValue,
 */
	private E _val; // value of the root node
	private BinaryTree<E> _left;// the left tree
	private BinaryTree<E> _right;// the right tree
		
	// constructor constructs a tree with root value and 2 subtrees
	public BinaryTree(BinaryTree<E> left, E value, BinaryTree<E> right){
		_val = value;
		_left = left;
		_right = right;
	}
	
	/**
	 * getLeftChild
	 * @return the left child of a node in the tree
	 * @return null if there is no left child
	 */
	public BinaryTree<E> getLeftChild(){
		return _left;
	}
	
	/**
	 * getRightChild
	 * @return the left child of a node in the tree
	 * @return null if there is no right child
	 */
	public BinaryTree<E> getRightChild(){
		return _right;
	}
	
	/**
	 * setLeftChild
	 * @param a binary tree
	 * set the left subtree
	 */
	public void setLeftChild(BinaryTree<E> left){
		_left = left;
	}
	
	/**
	 * setRightChild
	 * @param a binary tree
	 * set the right subtree
	 */
	public void setRightChild(BinaryTree<E> right){
		_right = right;
	}

	/** 
	 * getValue
	 * @return value of the root
	 */
	public E getValue(){
		return _val;
	}
	
	/**
	 * setValue
	 * @param E value
	 * set value of the root
	 */
	public void setValue(E value){
		_val = value;
	}
	
	/**
	 * isLeaf()
	 * @return true if a node is a leaf, false if it is inner node
	 */
	public boolean isLeaf(){
		return getLeftChild() == null && getRightChild() == null;
	}
}
