/**
 * This is the test unit for Queue class
 * Test the 4 methods in queue: enQueue(), dequeue & dequeue from empty queue
 * peek and peek from empty queue
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
import java.util.NoSuchElementException;

public class QueueTest {
	Queue<String> _queue;	
	
	@Before
	public void setUp() throws Exception {
		_queue = new Queue<String>(10);
	}

	// test enQueue function
	@Test
	public void testEnqueue() {
		// enQueue 
		_queue.enQueue("lemur");
		assertEquals("lemur", _queue.peek());
		_queue.enQueue("aye-aye");
		assertEquals("lemur", _queue.peek());
		assertEquals("lemur", _queue.deQueue());
		assertEquals("aye-aye", _queue.deQueue());
	}
	
	// test deQueue function
	@Test
	public void testDeQueue(){
		_queue.enQueue("lemur");
		assertEquals("lemur",_queue.deQueue());
		// enQueue in two strings, deQueue out head
		// see if we see the next one
		_queue.enQueue("lemur");
		_queue.enQueue("aye-aye");
		assertEquals("lemur", _queue.deQueue());
		assertEquals("aye-aye", _queue.peek());
		assertEquals("aye-aye", _queue.deQueue());
	}	
	
	// test deQueue function when the queue is empty
	@Test(expected=NoSuchElementException.class)
	public void testdeQueueEmpty(){
		_queue.deQueue();
	}
	
	// test peek function
	@Test
	public void testPeek(){
		// enQueue in 1 string and see if the peek see it
		_queue.enQueue("lemur");
		assertEquals("lemur",_queue.peek());
	}	

	// test peek function when the queue is empty
	@Test(expected=NoSuchElementException.class)
	public void testPeekEmpty(){
		_queue.peek();
	}
	
	// test isEmpty function 
	@Test
	public void testEmpty(){
		assertTrue(_queue.isEmpty());
		_queue.enQueue("lemur");
		assertFalse(_queue.isEmpty());
		_queue.deQueue();
		assertTrue(_queue.isEmpty());
	}
}