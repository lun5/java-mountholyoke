/**
 * This is the test unit for Stack class
 * Test the 4 methods in Stack: push(), pop & pop from empty stack
 * peek and peek from empty stack
 * isEmpty() method
 * 
 * Assignment 1. Due 09/22/11
 * @author Luong Nguyen <nguye28l@mtholyoke.edu>
 *  @version 20 september 2011
 */

//package edu.mtholyoke.cs211;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.EmptyStackException;


public class StackATest {
	Stack<String> _stack;	
	
	@Before
	public void setUp() throws Exception {
		_stack = new Stack<String>(10);
	}

	// test push function
	@Test
	public void testPush() {
		// push 
		_stack.push("lemur");
		assertEquals("lemur", _stack.peek());
		_stack.push("aye-aye");
		assertEquals("aye-aye", _stack.peek());
		assertEquals("aye-aye", _stack.pop());
		assertEquals("lemur", _stack.pop());
	}
	
	// test pop function
	@Test
	public void testPop(){
		_stack.push("lemur");
		assertEquals("lemur",_stack.pop());
		// push in two strings, pop out first one
		// see if we see the second elt
		_stack.push("lemur");
		_stack.push("aye-aye");
		assertEquals("aye-aye", _stack.pop());
		assertEquals("lemur", _stack.peek());
		assertEquals("lemur", _stack.pop());
	}	
	
	// test pop function when the stack is empty
	@Test(expected=EmptyStackException.class)
	public void testPopEmpty(){
		_stack.pop();
	}
	
	// test peek function
	@Test
	public void testPeek(){
		// push in 1 string and see if the peek see it
		_stack.push("lemur");
		assertEquals("lemur",_stack.peek());
	}	

	// test peek function when the stack is empty
	@Test(expected=EmptyStackException.class)
	public void testPeekEmpty(){
		_stack.peek();
	}
	
	// test isEmpty function 
	@Test
	public void testEmpty(){
		assertTrue(_stack.isEmpty());
		_stack.push("lemur");
		assertFalse(_stack.isEmpty());
		_stack.pop();
		assertTrue(_stack.isEmpty());
	}
}