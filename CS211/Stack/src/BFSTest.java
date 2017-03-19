import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.lang.StringIndexOutOfBoundsException;

public class BFSTest {
	//new BFS
	BFS mybfs = new BFS();
	
	@Before
	public void setUp() throws Exception {
		mybfs = new BFS();
	}
	
	// Test wrong row, column inputs
	@Test(expected=InputMismatchException.class)
	public void testRC()
	{
		mybfs.loadFile("maze-1");
	}
	
	//Test wrong cell inputs
	@Test(expected=InputMismatchException.class)
	public void testTooManyRow()
	{
		mybfs.loadFile("maze-2");
	}
	
	@Test(expected=InputMismatchException.class)
	public void testTooFewRow()
	{
		mybfs.loadFile("maze-3");
	}

	@Test(expected=StringIndexOutOfBoundsException.class)
	public void testTooFewCol()
	{
		mybfs.loadFile("maze-5");
	}
	
	// test the solve()
	@Test
	public void testSolve(){
		mybfs.loadFile("maze-0");
		assertFalse(mybfs.solve());
		mybfs.loadFile("maze-4");
		assertTrue(mybfs.solve());
	}
}
