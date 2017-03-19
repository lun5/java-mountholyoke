import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test for the BSTMap class
 * Test the 4 methods: put, get, contains, remove
 * 
 * Assignment 6: due Nov 17th 2011
 * @author nguye28l Luong Nguyen
 * @version Nov 15th 2011
 */

public class BSTMapTest {
	Map<String,Integer> _map;
		
	@Before
	public void setUp() throws Exception {
		_map = new BSTMap<String,Integer>();
	}
	
	@Test
	// test put, get, contains, remove
	public void testPut(){
		_map.put("h", 10);
		_map.put("b", 20);
		_map.put("i", 5);
		_map.put("a", 9);
		_map.put("b", 12);
		assertEquals(9,_map.get("a").intValue());
		assertEquals(12,_map.get("b").intValue());
		assertEquals(5,_map.get("i").intValue());
		assertTrue(_map.containskey("a"));
		assertFalse(_map.containskey("c"));
		for(String mykey:_map.keySet()){
			System.out.println(mykey);
		}
		_map.remove("a");
		assertFalse(_map.containskey("a"));
		_map.remove("i");
		assertFalse(_map.containskey("i"));
		System.out.println("after deleting a, i");
		for(String mykey:_map.keySet()){
			System.out.print(mykey+" ");
			System.out.println(_map.get(mykey));
		}
	}
}
