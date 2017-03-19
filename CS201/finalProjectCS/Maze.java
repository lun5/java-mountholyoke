import java.io.*;
//import java.lang.Exception;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;
/**
 * This is the the internal representation 
 * of the maze
 * it contains a 2D array of Squares
 * row, width of the maze
 * a start and end Square
 * Maze extends JPanel for GUI
 * @author Luong Nguyen
 * @date 4/19/11
 */

public class Maze extends JPanel{
	// 2 Dimensional array of squares
	private Square[][] sqMatrix;
	// row, column of the maze
	private int row, column;
	// the start and finish square
	private Square start, end;
	private static int HEIGHT = 20;
	private static int WIDTH = 20;
	
	/**
	 * Constructor 
	 */
	public Maze()
	{
		
	}
	
	/**
	 * loadMaze load the maze contained in the file named fname
	 * @param String fname
	 * @return void
	 * throws FileBadFormatException,FileNotFoundException
	 * call 2 private methods readRC and readMaze
	 */
	public void loadMaze(String fname) throws FileNotFoundException, FileBadFormatException
	{
		//  use Scanner used to read the file
		try{
			//System.out.println("Here I am in the try block");
			Scanner scan = new Scanner(
					new BufferedReader(
							new FileReader(fname)));
			// read in the number of row and column
			readRC(scan);
			// read in the maze
			System.out.println("Number of rows and colums are"+row +" "+column);
			readMaze(scan);
			//close the scanner
			scan.close();								
		}catch(FileNotFoundException e){
			//catch the FileNotFound 
			JOptionPane.showMessageDialog(null, "There is no such file", "There is no such file", JOptionPane.ERROR_MESSAGE);
			System.out.println("FileNotFound"+e.getMessage());
		}
	}
	
	/**
	 * readRC take in a scanner
	 * read in the row and columns in the input file
	 */
	private void readRC(Scanner scan) throws FileNotFoundException, FileBadFormatException
	{
		//read in number of row and column in the first line
		String line = scan.nextLine();
		// read in number of rows and colums
		// declare a new scanner on the line. 
		Scanner scanLine = new Scanner(line);
		// try reading in the number of row
		if (scanLine.hasNextInt())  
			// scan that integer into row number or row
			row=scanLine.nextInt();
		else throw new FileBadFormatException("The solution input file does not have number of rows");
		// try reading in the number of cols
		if (scanLine.hasNextInt())  
		// scan that integer into row number or row
			column=scanLine.nextInt(); 
		else throw new FileBadFormatException("The solution input file does not have number of columns");
		// if there are more than 2 entries then throw an exception
		if (scanLine.hasNext()) throw new FileBadFormatException("The solution input file has wrong format of the number of rows and columns");
	}
	
	/**
	 * readMaze take in a scanner
	 * and read the maze info from the input file
	 */
	private void readMaze(Scanner scan) throws FileNotFoundException, FileBadFormatException
	{
		// initialize the square matrix
		sqMatrix = new Square[row][column];
		// read in the matrix for square
		// loop through # of rows
		for(int i =0; i< row; i++)
		{
			// check if there is a next line then read 
			if(scan.hasNextLine())
			{
				String line = scan.nextLine();						
				// make a scanner for that line
				Scanner scanLine = new Scanner(line);
				//System.out.println("Here I am in the i loop if part is true i= " + i);
				// read in each line
				for(int j =0; j<column; j++)
				{
				//check if there is a next integer
				if (scanLine.hasNextInt())
					{
						//System.out.println("Here I am in the j loop if part is true j= " + j);
						int t = scanLine.nextInt(); // store the type of the square
						// make new square and assign type to the square in the matrix
						sqMatrix[i][j] = new Square();
						// check if the type is 0,1,2,3
						if (t ==0|t==1)
							// assign the type
							sqMatrix[i][j].setType(t);
						else if(t==2)
						{
							// assign the type
							sqMatrix[i][j].setType(t);
							// the start square
							start = sqMatrix[i][j];
						}else if(t==3)
						{
							// assign the type
							sqMatrix[i][j].setType(t);
							// the end square
							end = sqMatrix[i][j];
						}else{
							// if something else then wrong format
							throw new FileBadFormatException("input file has wrong format of the type of square");
						}					
						sqMatrix[i][j].setPoint(i, j);
					}
					// if there is no integer
					else{	
						//System.out.println("There is a non integer entry in the input file");
						throw new FileBadFormatException("There is a non integer entry in the input file or not enough " +
								"entry in a row");
					}									
				}
				// if the line still have more entries then there are too many on each line
				if(scanLine.hasNext())
					//System.out.println("Is there anything else in that line "+ scanLine.hasNext() );
					throw new FileBadFormatException("There are too many entries in a rows of the input file");				
			}else
				// if there is not enough row. The for loop for i does not run to row-1
				throw new FileBadFormatException("There are too few rows in the input file");
		}
		// if there are more row to read
		if(scan.hasNextLine()) throw new FileBadFormatException("There are too many rows in the input file");
	}
	
	/**
	 * getNeighbor 
	 * @param a square
	 * @return an ArrayLIst of Square neighbors of the parameter Square sq
	 * in order: North, South, East, West
	 */
	public ArrayList<Square> getNeighbors(Square sq)
	{
		// the arrayList to return, there are at most 4 neighbors
		ArrayList<Square> neighbors = new ArrayList<Square>(4); 
		// positions of the square
		int x = sq.getX();
		int y = sq.getY();
		// add neighbors to the array list
		// check if we can actually add the square
		if(x>0)	neighbors.add(sqMatrix[x-1][y]);//North 
		if(x<row -1) neighbors.add(sqMatrix[x+1][y]);//South
		if(y<column -1) neighbors.add(sqMatrix[x][y+1]);//East
		if(y>0) neighbors.add(sqMatrix[x][y-1]);//West
		return neighbors;		
	}
	
	/**
	 * toString
	 * @return a string representation of the maze
	 */
	public String toString()
	{
		// the return String
		String s="";
		// loop through the sqMatrix
		// get the string representation of each square
		for(int i= 0; i < row; i++)
		{
			for( int j = 0; j< column; j++)
			{
				s+= sqMatrix[i][j];
			}
			// new line after each row
			s+="\n";
		}
		return s;		
	}
	
	/**
	 * getSquare 
	 * @param position of the square needed
	 * @return the square
	 */
	public Square getSquare(int i, int j)
	{
		return sqMatrix[i][j];
	}
	
	/**
	 * getStart()
	 * @return the start square
	 */
	public Square getStart()
	{
		return start;
	}
	
	/**
	 * getEnd()
	 * @return the start square
	 */
	public Square getEnd()
	{
		return end;
	}	
	
	/**
	 * paintComponent
	 * @param Graphic g
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// loop through the square matrix
		for (int i = 0; i < row; i++)
		{
			for(int j = 0; j < column; j++)
			{
				//g.setColor(Color.BLACK);
				//g.setStroke(new BasicStroke(10F));
				// draw each square with width and row of 20
				//g.drawRect(j*WIDTH, i*HEIGHT, HEIGHT, WIDTH);
				// the Square is a wall
				if (sqMatrix[i][j].getType()==1) 
					g.setColor(Color.BLACK);				
				// the square is the start then paint it green
				else if(sqMatrix[i][j].getType()==2)
					g.setColor(Color.GREEN);							
				// if the square is the exit then paint it red
				else if(sqMatrix[i][j].getType()==3)
				
					g.setColor(Color.RED);
				// square on list but not explore light gray
				else if(sqMatrix[i][j].getOnlist())
					g.setColor(Color.lightGray);
				// explored squared is painted gray
				else if(sqMatrix[i][j].getDis())
					g.setColor(Color.gray);
				else
					// otherwise paint it white
					g.setColor(Color.WHITE);				
				// fill the square
				g.fillRect(j*WIDTH, i*HEIGHT, HEIGHT, WIDTH);
				// otherwise paint it white
				g.setColor(Color.BLACK);
				// draw each square with width and row of 20
				g.drawRect(j*WIDTH, i*HEIGHT, HEIGHT, WIDTH);
				
			}
		}
		// draw each square with width and height of 20		
	}	
}
