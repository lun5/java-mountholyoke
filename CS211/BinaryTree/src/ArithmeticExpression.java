import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * ArithmeticExpression
 * implement an arithmetic expression binary tree
 * the inner nodes are operators (+,-,*,/) and leaves are operands (constant/variables)
 * value of an expression tree is either the value stored in the node
 * or the result of applying the operator stored in the node to its left and right subtrees
 * 
 * Assignment 3. Due 10/08/11
 * @author nguye28l
 * @version 3 Oct 2011
 */

public class ArithmeticExpression {
	/**
	 * class properties
	 */
	Stack<BinaryTree<String>> _treeStack;// stack of tree
	Stack<String> _operatorStack; // stack of operator
	Map<String,Integer> _map; // map variable and the value user wants to set
	Boolean _contain; // whether the variable name is contained in the expression 
	//Stack<Integer> _parStack;// store the position of open parentheses "("      
	
	/**
	 * Constructor
	 * @param a String of expression, space delimited
	 */
	public ArithmeticExpression(String s){
		// initialize the variables
		_treeStack = new Stack<BinaryTree<String>>();
		_operatorStack = new Stack<String>();
		_map = new HashMap<String,Integer>();
		_contain = false;
		// process the input string with readString function
		readString(s);
	}
	
	/**
	 * readString
	 * @param s with or without space
	 * put operators and operands in a tree
	 */
	private void readString(String s){
		// split the given string by space
		String[] str = s.split(" ");
		// reset the string
		s = "";
		// put the string together without any space
		for(int i = 0; i<Array.getLength(str); i++) s = s+str[i];
		// length of the string without spaces
		int length = s.length();
		// positions of operators
		int pos = -1; // start of the string
		// string of operand
		String operand = new String();
		// parse the string
		int i = 0;
		while(i<length){
			// check if the character at position i is an operator
			if(isOperator(s.charAt(i)+"")){
				// get the operand between any 2 operators
				// & between the 1st char and the first operator
				operand = s.substring(pos+1,i);
				// process the operand
				parseOperand(operand);
				// process the operator
				parseOperator(s.charAt(i)+"");
				// update the operator position
				pos = i;
			}
			// if we get to the end of the string
			if(i== length-1){
				// update the operand
				// between the last operator and end of the string
				operand = s.substring(pos+1,i+1);
				parseOperand(operand);// process the operator
			}
			i ++; // update i
		}// end while loop
	
		// after the string is consumed, use while loop to clear out all the operators
		processOperator();
		// check if there is only 1 tree in the tree stack
		BinaryTree<String> remain = _treeStack.pop();
		// if there are more than 1 tree in the stack
		if(!_treeStack.isEmpty()){
			System.out.println("Your input has wrong format");
			throw new InputMismatchException();
		} else{
			// put the tree back to the stack
			_treeStack.push(remain);
		}
	}
	
	/**
	 * isOperator
	 * @param a string
	 * @return true if it's an operators
	 */
	private boolean isOperator(String s){
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}
	
	
	// parseOperator decides what to do with the operator
	// depending on what it is
	private void parseOperator(String s){
		// if the operator stack is empty
		// or the operator is "*" or "/"
		// push on the operatorStack
		if (_operatorStack.isEmpty()||s.equals("*")||s.equals("/")){
			_operatorStack.push(s);
		} else { // otherwise
			// call helper function processOperator 
			//to clear out the operator stack
			processOperator();
			// push the current operator on top of the operatorStack
			_operatorStack.push(s);
		}
	}
	
	/**
	 * processOperator 
	 * clear out the operator stack, make new trees and push on the treeStack
	 */
	private void processOperator(){
		while(!_operatorStack.isEmpty()){
			// pop an operator from the operator stack
			String op = _operatorStack.pop();
			// pop the top two trees from the tree stack
			try{
				BinaryTree<String> tree1 = _treeStack.pop();
				BinaryTree<String> tree2 = _treeStack.pop();
				// create a new tree with two tress and the operators
				// the first tree popped out is the right
				BinaryTree<String> newTree = new BinaryTree<String>(tree2, op, tree1);
				// push the new tree on the tree stack
				_treeStack.push(newTree);
			}catch (InputMismatchException e){
				// if we don't have enough 2 trees to pop
				System.out.println("Wrong input");
			}
		}
	}
	
	/**
	 * parseOperand
	 * @param string s
	 * make a new tree with value s at root
	 * then push the tree on the treeStack
	 */
	private void parseOperand(String s){
		BinaryTree<String> tree = new BinaryTree<String>(null,s,null);
		// make a tree out of the operand and push onto the stack
		_treeStack.push(tree);
	}
	
	/**
	 * parsedExpression
	 * @return a string containing the parsed expression with parentheses
	 */
	public String parsedExpression(){
		// our tree
		BinaryTree<String> tree = _treeStack.peek();
		// in order traverse the tree using helper function
		return inorderTraverse(tree);
	}
	
	/**
	 * inorderTraverse
	 * @param a tree
	 * @return string of parsed expression with parentheses
	 */
	
	private String inorderTraverse(BinaryTree<String> root){
		// if the root is a leaf
		if(root.isLeaf())
			return  root.getValue(); // the value of root
		// in order traversal: left, root, right
		// using recursion 
		String left = inorderTraverse(root.getLeftChild());
		String right = inorderTraverse(root.getRightChild());
		return "(" + left + root.getValue() + right + ")";
	}
	
	/**
	 * evaluate
	 * @return the integer result of evaluating the expression
	 * unset variables are 0
	 */
	public int evaluate(){
		// our tree
		BinaryTree<String> tree = _treeStack.peek();
		return evaluateTree(tree);// helper function
	}
	
	/**
	 * evaluateTree
	 * @param a tree
	 * @return the evaluation of the tree
	 * recursion with inorder traversal
	 */
	private int evaluateTree(BinaryTree<String> tree){
		// if the tree is just leave
		// return the value with function evalOperand
		if (tree.isLeaf()) return evalOperand(tree.getValue());
		// if the tree is not a single node
		// evaluate left and right subtree
		int left = evaluateTree(tree.getLeftChild());
		int right = evaluateTree(tree.getRightChild());
		// then evaluate the final operator at the root, using evalOperator
		return evalOperator(left,tree.getValue(),right);
	}
	
	/**
	 * evelOperand
	 * @param string
	 * @return number representation of the string
	 * a number if the string contain a number
	 * 0 if the string is a variable name not in the map
	 * corresponding value if the string is a variable in the map
	 */
	private int evalOperand(String s){
		Scanner scan = new Scanner(s);
		// if the node is an int, return the value
		if (scan.hasNextInt()) return scan.nextInt();
		// if not then see if it matches the (name, var) pair
		// return the int value of the pair in the map
		// use containsKey(Object key) and get(key)
		else if (_map.containsKey(s)){
			_contain = true; // reset the _contain boolean
			return _map.get(s);
		}		// otherwise return 0
		else return 0;
	}
	
	/**
	 * evalOperator
	 * @param int, a string of operator, and an int
	 * @return an int as the result of the operation
	 */
	private int evalOperator(int num1, String op, int num2){
		if(op.equals("+")) return num1+num2;
		else if(op.equals("-")) return num1-num2;
		else if(op.equals("*")) return num1*num2; 
		else if(op.equals("/")) return num1/num2;
		else throw new InputMismatchException();		
	}
	
	/**
	 * setVariable
	 * @param name, value
	 * @return void set value for a variable
	 * print a notification if there is no such variable
	 */
	public void setVariable(String name, int value){
		_map.put(name, value);
		// evaluate the expression to see if it contains the variable
		evaluate();
		// if the expression does not contain the variable
		// printout a notification to the user
		if (!_contain)	System.out.println("There is no such variable " + name);
	}
	
	/**
	 * main
	 * @param args
	 * Users will be asked to type an algebra expression of int 
	 * The expression could be with or without space 
	 * but multiplication must be explicit
	 * A parsed expression and a value will appear to the console
	 * Users will be asked to type variable name and the value they want to set
	 * Evaluation of the expression will be handed back
	 * if there is no such variable, a notification will be printed
	 * end of the program
	 */
	public static void main(String[] args) {
		System.out.println("Enter your algebra expression of integers (with/without spaces):");
		// example x+y*2+z*5-7* 9
		// scanner used to read the expression
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		ArithmeticExpression arth = new ArithmeticExpression(s);
		System.out.println("The corresponding parsed expression is:");
		System.out.println(arth.parsedExpression());
		System.out.println("Evaluation of the expression is:");
		System.out.println(arth.evaluate());
		System.out.println("Enter the variable name you want to set:");
		String varName = sc.next();
		System.out.println("Enter the value you want to set it to");
		int value;
		try{
			value = sc.nextInt(); // get the integer values
			arth.setVariable(varName, value); // set the variable
			System.out.println("Evaluation of the expression is:");
			System.out.println(arth.evaluate());
		}catch(InputMismatchException e){
			System.out.println("Wrong input format");// if user does not input an int for value
		}
	}// end main 
}