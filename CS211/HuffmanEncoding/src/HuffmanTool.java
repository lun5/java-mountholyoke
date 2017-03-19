import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * HuffmanTool is the decoder
 * Methods include: endoe, decode from String and Files
 * @author nguye28l
 * @version Nov 15
 */
public class HuffmanTool {
	/**
	 * property: a map of the seed file
	 * containing the code
	 */
	private Map<String,String> _codemap;
	/**
	 * The Huffman's tree
	 */
	private BinaryTree<String> _mytree;
	/**
	 * Constructor
	 * @param a seed file
	 * @throws FileNotFoundException 
	 */
	public HuffmanTool(File seed) throws FileNotFoundException{
		Map<String,Integer>	_map = new BSTMap<String,Integer>();
		Scanner sc = new Scanner(seed).useDelimiter("");
		// read the seed file
		while(sc.hasNext()){
			String str = sc.next();
			if(_map.containskey(str)){
				_map.put(str, _map.get(str)+1);
			} else{
				_map.put(str, 1);
			}
		}// complete building the map
		// put the characters into the priority queue
		// the first one will have the lowest frequency
		PriorityQueue<WrapperNode> _queue = new PriorityQueue<WrapperNode>();
		for(String mykey:_map.keySet()){
			// make a tree out of the key and put it into the wrapper node with its freq
			WrapperNode wnode = new WrapperNode(_map.get(mykey), new BinaryTree<String>(null, mykey, null));
			// put the node into the queue
			_queue.add(wnode);
		}
		// build the Huffman's tree
		// take the 2 lowest frequency trees, glue them into a tree, 
		//sum the frequency and put back to the queue
		while(_queue.size()>1){
			WrapperNode node1 = _queue.remove();
			WrapperNode node2 = _queue.remove();
			//System.out.println(node1.get_tree().getValue()+ " " + node1.getFreq() + " " + node2.get_tree().getValue()+ " "+ node2.getFreq());
			// the new wrapper node
			WrapperNode newWnode = new WrapperNode(node1.getFreq()+node2.getFreq(), new BinaryTree<String>(node1.get_tree(), "", node2.get_tree()));
					//new BinaryTree<String>(node1.get_tree(),node1.get_tree().getValue() + node2.get_tree().getValue(),node2.get_tree()));
			_queue.add(newWnode);
		}// finish building huffman's tree. There's only 1 tree in the queue now.
		_mytree = new BinaryTree<String>(null, null, null);
		_mytree = _queue.getFirst().get_tree();
		// Build the final map of the code by calling in order traversal
		// final code map
		_codemap = new BSTMap<String,String>();
		inOrderTraversal(_mytree,"");
	}
	
	/**
	 * inOrderTraversal
	 * @param a binary tree and a string
	 * get the binary code by traversing the tree and appending the string
	 * put the final code into the code map if we reach a leaf
	 */
	private void inOrderTraversal(BinaryTree<String> tree, String str){
		// base case
		if(tree.isLeaf()){
			_codemap.put(tree.getValue(), str);
			return;
		}	
		inOrderTraversal(tree.getLeftChild(), str+"0");	
		inOrderTraversal(tree.getRightChild(), str+"1");
	}
	
	/**
	 * encode
	 * @param String
	 * @return encoded version as string of 1's and 0'
	 */
	public String encode(String text){
		String str="";
		Scanner sc = new Scanner(text).useDelimiter("");
		while(sc.hasNext()){
			str = str + _codemap.get(sc.next());
		}
		return str;
	}
	
	/**
	 * decode
	 * @param String
	 * @return the decoded version from the binary code
	 */
	public String decode(String text){
		String str = ""; // return string
		// read in the string character by character
		// follow the tree until reach a leaf and print out the character at the leaf
		Scanner sc = new Scanner(text).useDelimiter("");
		BinaryTree<String> child = _mytree; 
		while(sc.hasNext()) {
			try{	
					int c = Integer.parseInt(sc.next());
					if(c==0) child = child.getLeftChild();
					else if(c==1) child = child.getRightChild();
					else throw new InputMismatchException();
					// if reach leaf then concartenate the string
					if(child.isLeaf()){
						str = str + child.getValue();
						child = _mytree;	
					}				
			}catch(InputMismatchException e){
				System.out.println("Wrong input.");
			}			
		}
		return str;
	}
		
	/**
	 * printMap
	 * print the code map of the seed file
	 * just for checking at the moment
	 
	public void printMap(){
		for(String key: _codemap.keySet()){
			System.out.println(key + " " + _codemap.get(key));
		}
	}*/		
}
