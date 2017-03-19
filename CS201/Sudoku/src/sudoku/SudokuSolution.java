package sudoku;
// import packages for reading files
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
//import packages for reading files
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.lang.Exception;

import javax.swing.JTextField;

public class SudokuSolution {
	// Sudoku object
	//private Sudoku sdk;
	// 9by9 matrix storing solution
	private int[][] solution;
	// Constants for the number of rows and columns in the solution.
    private final static int NUM_GRID_ROWS = 9;
    private final static int NUM_GRID_COLUMNS = 9;
	// a hint array
	private int[][] hints;
	//private Scanner scan;
	// Constructor read the input file
	//private Sudoku sdk;
	public SudokuSolution(String fileName) throws IOException
	{
		//sdk = new Sudoku();
		solution = new int[NUM_GRID_ROWS][NUM_GRID_COLUMNS];
		hints = new int[NUM_GRID_ROWS][NUM_GRID_COLUMNS];
		System.out.println("Here I am in the constructor");
		try{
			System.out.println("Here I am in the try block");
			Scanner scan = new Scanner(new FileReader(fileName));
		// read in the first 9 lines of the input file
		// check if their is a next integer
		// put into the solution array
	     for(int i=0; i<=8;i++)
			{	
	    	 //System.out.println("Here I am in the i loop");
				for(int j =0; j<=8; j++)
				{
					//System.out.println("Here I am in the j loop");
					// how to catch an exception?
					if(scan.hasNextInt()){
					solution[i][j]=scan.nextInt();
					System.out.println("solution["+i+"]["+j+"]="+solution[i][j]);
					}
					else{
						System.out.println("Input file has non integer entry");
						break;
					}
					}
				}	
	     
	     System.out.println("Here I am after the for loop");

			
		 // check if the first nine line are actually int
		 while(scan.hasNextInt()){
			 System.out.println("I found extra integers");
		     System.out.println(scan.nextInt());
		 }
		 
		 System.out.println("Here I am after the while loop check");
		 
		 // scan the rest and put into 2 vars row, col
		 // right after that call the setValue in the Sudoku object
		 // which currently is not here
		 // right now put it in an array list
		 
		 if(scan.next().equals("@"))
		 {
			 System.out.println("Here I am after reading the @");
			 int row, col; // store the position of the hint
			 while(scan.hasNextInt())
			 {
			 	 row = scan.nextInt();
				 col = scan.nextInt();
				 hints[row][col]=solution[row][col];
				 
				 /**
				 sdk.setValue(row, col, solution[row][col]);
				 sdk.setEditable(row,col,false);
				 sdk.setTextDisplay(row, col, new Font("Monaco", Font.BOLD, 12), Color.BLUE,JTextField.CENTER );
				  */
			 /**ArrayList<String> posList = new ArrayList<String>();
			 while(scan.hasNext())
			 {
			 posList.add(scan.next());
			 }
		 
			 // trim the size of the array list
			 
			 posList.trimToSize();
			 hints = new int[posList.size()];
			 for (int i=0; i<posList.size();i++)
			 		hints[i]= ;
			 	 
		 }
		 /**for (int i=0; i<posList.size();i++)
			 System.out.println("position "+i+" is " + posList.get(i));*/
			 }
		 System.out.println("Here I am after reading the @");
		 scan.close();
		}
		}catch(FileNotFoundException e){
			 System.out.println("Here I am in the catch block");
			 System.out.println("FileNotFound"+e.getMessage());
		 }
	}
	
	/**
	public void setSudoku(Sudoku sod)
	{
		sdk = sod;
	}*/
	
	public int getCorrectValue(int row, int col)
	{
		return solution[row][col];
	}
	
	public void printArray()
	{
		for(int i=0; i<8;i++)
			for(int j =0; j<8; j++)
				System.out.println("Solution is "+ solution[i][j]
				                                               +"Hint is "+ hints[i][j]);		
	}
	/**
	 * getSolution return the solution array
	 */
	public int[][] getSolution()
	{
		return solution;
	}
	
	/**
	 * getSolution return the solution array
	 */
	public int[][] getHints()
	{
		return hints;
	}
	
	
	// create a new SudokuSolution and print out the array
	// create a new SudokuSolution and print out the array
	/**
	public static void main( String[] args ) throws IOException
	    {
	        // Create a new big grid instance.
		 	System.out.println("Here I am in the main");
	        SudokuSolution ssln = new SudokuSolution("solution1IO.txt");
	        //System.out.println("Here I am before printing array");
	        ssln.printArray();
	    }		
	    */
	
	
	
}
	