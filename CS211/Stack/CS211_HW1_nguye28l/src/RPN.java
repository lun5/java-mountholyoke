/**
 * Reverse Polish Notation (RPN) calculator
 * implement a stack calculator
 * Description of this calculator can be found on google
 * 
 * Assignment 1. Due 09/24/11
 * @author Luong Nguyen <nguye28l@mtholyoke.edu>
 *  @version 22 september 2011
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class RPN {
	// stack of string storing the numbers 
	private Stack<Integer> _rpn;
	// stack of all the operands/operators pushed in
	private Stack<String> _history;
	//Constructors
	public RPN(){
		_rpn = new Stack<Integer>(10);
		 _history = new Stack<String>(10);
	}
	
	/**
	 * calString takes in a 
	 * @param string of operands and operators
	 * implement the RPN
	 * 
	 * @return the value
	 */
	public int calString(String str){
		// spacePos keeps track of space " " position
		int spacePos = 0;
		// token keep track of tokens separated by spaces
		String token = new String();
		// we shorten the string each time see the space
		// when the string is empty, we finish reading the string and calculating the output
		while(str.length()>0){
			spacePos = str.indexOf(" ");
			// if there is a space in the string
			if(spacePos != -1){
				//parse the string by that space
				token = str.substring(0, spacePos);
			} else{
				// otherwise take the string as the token
				token = str;
			}
			// process the token
			process(token);
			// old is the string just finish process
			String old = str;
			// cut the token from the input string
			str = str.substring(spacePos+1,str.length());
			// if the string just process and the new string are the same
			// that means we finish reading in the string
			if(old.equals(str)){
				str="";
			}				
		}
		// result if the input is correct
		int result;
		result = _rpn.pop();
		// if the stack is empty then the input is correct
		// otherwise throw a InputMismatchException
		if(_rpn.isEmpty()){
			return result;
		}else{
			throw new InputMismatchException();
		}	
	}
	
	/**
	 * process
	 * @param a string input with spaces between numbers and operators
	 * 
	 * and perform operations based on what the input is
	 */
	private void process(String token){
		// the 2 numbers popped out of the stack
		Integer num1, num2;
		// if the token is one of the operators: +, - * /
		// then pop 2 numbers from the stack and operate on them
		if(token.equals("+")){
			num1 = _rpn.pop();
			num2 = _rpn.pop();
			// add them
			_rpn.push(num2 + num1);
		} else if(token.equals("-")){
			num1 = _rpn.pop();
			num2 = _rpn.pop();
			// substract them
			_rpn.push(num2 - num1);
		} else if(token.equals("*")){
			num1 = _rpn.pop();
			num2 = _rpn.pop();
			// multiply them
			_rpn.push(num2 * num1);
		} else if(token.equals("/")){
			num1 = _rpn.pop();
			num2 = _rpn.pop();
			// divide them
			_rpn.push(num2 / num1);
		// if not an operator then try to read in a number
		} else {
			try{
				int num = Integer.valueOf(token);
				_rpn.push(num);				
			} catch (InputMismatchException e){
					// if the token is none of the operators
					System.out.println("Wrong output format " + token);
					throw new InputMismatchException();
			}				
		}
	}
	
	/**
	 * calInteractive
	 * allow user to input the number and operators
	 * and give the result
	 * @param A scanner take in the stander inputs
	 * if user input Q then say Goodbye and quit
	 * otherwise, call the processInteract on input
	 */
	public void calInteractive(Scanner in) {
		String str = "";
		//System.out.print(">");
		while(1>0){
			System.out.print(">");
			str = in.nextLine();
			// if the user type anything but "Q" for quit
			if(!str.equals("Q")){
				// process the string with processInteract
				processInteract(str);
			}else{
				// exit the program
				System.out.println("Good bye!");
				System.exit(1);
			}
			
		}
	}
	
	
	/**
	 * processInteract take in
	 * @param token
	 * and process it in the interactive version 
	 * 
	 */
	private void processInteract(String token){
		//"S" shows the content of current stack
		if(token.equals("S")){
			// make a new stack
			// pop each of stack element into the stack
			// and print out at the same time
			Stack<Integer> temp = new Stack<Integer>(10);
			while(!_rpn.isEmpty()){
				System.out.println(_rpn.peek());
				temp.push(_rpn.pop());
			}
			while(!temp.isEmpty()){
				// then pop back to _rpn 
				_rpn.push(temp.pop());
			}
		// "H" shows the history of all input
		} else if(token.equals("H")){
			// print out the content of stack _history
			// the temporary stack tmpHist store the history
			// pop everything from _history to tmpHist
			Stack<String> tmpHist = new Stack<String>(10);
			while(!_history.isEmpty()){
				//System.out.println(_history.peek());
				tmpHist.push(_history.pop());
			}
			// then print out tmpHist and pop back into _history
			while(!tmpHist.isEmpty()){
				// then pop back to _rpn 
				System.out.println(tmpHist.peek());
				_history.push(tmpHist.pop());
			}
		} else {		
			process(token);
			// push on the stack of history
			_history.push(token);
			// and see the top of the stack
			System.out.println(_rpn.peek());
		}		
	}
		
	public static void main(String[] args) {
		String s = "15 1 22 + 4 * + 3 -";
		// declare the calculator
		RPN calculator = new RPN();
		// the string version
		System.out.println("The result is "+calculator.calString(s));
		// the interactive version
		System.out.println("Enter your calculation");
		Scanner sc = new Scanner(System.in);
		calculator.calInteractive(sc);
	}
}
