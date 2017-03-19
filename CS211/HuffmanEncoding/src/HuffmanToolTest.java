import static org.junit.Assert.*;
import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class HuffmanToolTest {
	HuffmanTool _tool;
	
	@Before
	public void setUp() throws Exception {
		_tool = new HuffmanTool(new File("seed.txt"));
	}
	
	// test the encode and decode of string
	@Test
	public void testEncodeDecodeString(){
		String strin = "The Brain-is wider than the Sky!\n\n" +
				"The Brain-is wider than the Sky\n" +
				"For-put them side by side-\n"+
				"The one the other will contain\n"+
				"With ease-and You-beside-\n\n"
				+"The Brain is deeper than the sea-\n"
				+"For-hold them-Blue to Blue-\n"
				+"The one the other will absord-\n" +
				"As Sponges-Buckets-do-\n\n" +
				"The Brain is just the weight of God-\n" +
				"For-Helf them-Pound to Pound-\n" +
				"And they will differ-if they do-\n" +
				"As Syllable from Sound-\n\n" +
				"Emily Dickinson";
		String inter = _tool.encode(strin);
		String strout = _tool.decode(inter);
		//System.out.println(strout);
		assertTrue(strin.equals(strout));
	}
}
