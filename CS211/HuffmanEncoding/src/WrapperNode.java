/**
 * wrapper node is the node class for elts to put on the priority queue
 * properties: frequencies of the letter, and a binary tree
 * @author nguye28l
 * @param <K>
 *
 */
public class WrapperNode implements Comparable<WrapperNode> {
	private int _freq;
	private BinaryTree<String> _tree;
		
	public WrapperNode(int freq, BinaryTree<String> tree){
		_freq = freq;
		_tree = tree;
	}
	public int getFreq() {
		return _freq;
	}



	public void setFreq(int freq) {
		this._freq = freq;
	}



	public BinaryTree<String> get_tree() {
		return _tree;
	}



	public void set_tree(BinaryTree<String> _tree) {
		this._tree = _tree;
	}



	@Override
	public int compareTo(WrapperNode o) {
		// TODO Auto-generated method stub
		return o.getFreq() - this.getFreq();
	}
}
