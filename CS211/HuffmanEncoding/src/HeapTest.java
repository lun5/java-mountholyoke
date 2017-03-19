import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test for the Heap class
 * Test the 4 methods: findMax, removeMax, insert, size
 * @author nguye28l
 * 
 * Assignment 5: due Nov 4th 2011
 * @author nguye28l
 * @version Nov 1st 2011
 */

public class HeapTest {
	Heap<Integer> myHeap;
	
	@Before
	public void setUp() throws Exception {
		myHeap = new Heap<Integer>(2);
	}
	
	//test insert, extend, removeMax, findMax
	@Test
	public void testInsert(){
		myHeap.insert(10);
		myHeap.insert(12);
		myHeap.insert(3);
		myHeap.insert(4);
		myHeap.insert(15);
		assertEquals(0,myHeap.findMax().compareTo(15));
		assertEquals(0,myHeap.removeMax().compareTo(15));
		assertEquals(0,myHeap.findMax().compareTo(12));
		assertEquals(0,myHeap.removeMax().compareTo(12));
		assertEquals(0,myHeap.findMax().compareTo(10));
		assertEquals(0,myHeap.removeMax().compareTo(10));
		assertEquals(0,myHeap.findMax().compareTo(4));
		assertEquals(0,myHeap.removeMax().compareTo(4));
		assertEquals(0,myHeap.findMax().compareTo(3));
		assertEquals(0,myHeap.removeMax().compareTo(3));
	}
	
	// test size and isEmpty function
	@Test
	public void testSize() {
		myHeap.insert(10);
		myHeap.insert(12);
		myHeap.insert(3);
		myHeap.insert(4);
		myHeap.insert(15);
		assertFalse(myHeap.isEmpty());
		assertEquals(5,myHeap.size());
		for(int i=0; i<5; i++){
			myHeap.removeMax();
		}
		assertTrue(myHeap.isEmpty());
	}
}
