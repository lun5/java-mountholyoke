/**
 * This is the test unit for Stack class
 * Test the methods of binary tree: get/set Left/RightChild
 * get/setValue, isLeaf
 * 
 * Assignment 3. Due 10/08/11
 * @author Luong Nguyen <nguye28l@mtholyoke.edu>
 *  @version 3 Oct 2011
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {
    BinaryTree<String> _btree;	
	
    // test the set up
	@Before
	public void setUp() throws Exception {
		// create binary tree with just 1 node
		_btree = new BinaryTree<String>(null, "sunday", null);
	}
	
	// test getLeftChild when there is no left child
	@Test//(expected=NoSuchElementException.class)
	public void testGetLeftEmpty(){
		assertNull(_btree.getLeftChild());
	}
		
		
	// test getRightChild when there is no right child
	@Test//(expected=NoSuchElementException.class)
	public void testGetRightEmpty(){
		assertNull(_btree.getRightChild());
	}
	
	// test the setLeftChild and getLeftChild 
	@Test
	public void testSetLeft() {
		// new subtree
		BinaryTree<String> left = new BinaryTree<String>(null,"monday", null);
		_btree.setLeftChild(left);
		assertEquals(left,_btree.getLeftChild());
	}

	// test the setRightChild and getRightChild
	@Test
	public void testSetRight() {
		// new subtree
		BinaryTree<String> right = new BinaryTree<String>(null,"tuesday", null);
		_btree.setRightChild(right);
		assertEquals(right,_btree.getRightChild());
	}

	// test the isLeaf() function
	@Test
	public void testLeaf(){
		assertTrue(_btree.isLeaf());
		BinaryTree<String> right = new BinaryTree<String>(null,"tuesday", null);
		_btree.setRightChild(right);
		assertFalse(_btree.isLeaf());
		assertTrue(_btree.getRightChild().isLeaf());
	}
	
	// test the getValue() function
	@Test
	public void testGetVal(){
		assertEquals("sunday",_btree.getValue());
	}
	
	// test the setValue() function
	@Test
	public void testSetVal(){
		_btree.setValue("mountain day");
		assertEquals("mountain day",_btree.getValue());
	}
}
