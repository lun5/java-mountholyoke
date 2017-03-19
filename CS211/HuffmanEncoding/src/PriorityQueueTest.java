import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test for the Priority class
 * Test the 4 methods: getFirst, remove, add, isEmpty, size
 * @author nguye28l
 * 
 * Assignment 5: due Nov 4th 2011
 * @author nguye28l
 * @version Nov 2nd 2011
 */

public class PriorityQueueTest {
	PriorityQueue<Integer> _queue;
	
	@Before
	public void setUp() throws Exception {
		_queue = new PriorityQueue<Integer>();
	}
	
	//test add, getFirst, remove
	@Test
	public void testAddRemove(){
		_queue.add(10);
		_queue.add(12);
		_queue.add(3);
		_queue.add(15);
		_queue.add(4);
		assertEquals(0,_queue.getFirst().compareTo(15));
		assertEquals(0,_queue.remove().compareTo(15));
		assertEquals(0,_queue.getFirst().compareTo(12));
		assertEquals(0,_queue.remove().compareTo(12));
		assertEquals(0,_queue.getFirst().compareTo(10));
		assertEquals(0,_queue.remove().compareTo(10));
		assertEquals(0,_queue.getFirst().compareTo(4));
		assertEquals(0,_queue.remove().compareTo(4));
		assertEquals(0,_queue.getFirst().compareTo(3));
		assertEquals(0,_queue.remove().compareTo(3));
	}
	
	// test size and isEmpty function
	@Test
	public void testSize() {
		_queue.add(10);
		_queue.add(12);
		_queue.add(3);
		_queue.add(15);
		_queue.add(4);
		assertFalse(_queue.isEmpty());
		assertEquals(5,_queue.size());
		for(int i=0; i<5; i++){
			_queue.remove();
		}
		assertTrue(_queue.isEmpty());
	}

}
