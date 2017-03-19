import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.Point;
import java.lang.StringIndexOutOfBoundsException;

/**
 * Breadth first search (BFS)
 * gives the fastest path from the start to the destination
 * with obstacles in the middle
 * 
 * Assignment 1. Due 09/24/11
 * @author Luong Nguyen <nguye28l@mtholyoke.edu>
 *  @version 20 september 2011
 */

/**
 * PLAN
 * a 2x2 array to store the topology and the scores
 * a queue to process the path
 * 
 * a function that read in the file and put the topology into a 2d matrix of int
 * a function that get the neighbor and assign scores to them
 * a back tracking function
 * 
 *
 */

public class BFS {
	// array for storing the topology
	private int[][] topology;
	// number of row and col
	private int row, col;
	// a queue of Points to store the explored cells in BFS
	private Queue<Point> qCells;	
	// stack of the shortest path
	private Stack<Point> sPath;
	
	//Constructors
	public BFS(){
		qCells = new Queue<Point>(10);	
		sPath = new Stack<Point>(10);
	}
	
	/**
	 * loadFile load the maze contained in the file named fname
	 * @param String fname
	 * @return void
	 * throws FileNotFoundException
	 * call 2 private methods readRC and readMaze
	 */
	public void loadFile(String fname) 
	{
		//  use Scanner used to read the file
		try{
			Scanner scan = new Scanner(new FileReader(fname));
			//read in number of row and column in the first line
			readRC(scan);
			readMaze(scan);
			//close the scanner
			scan.close();								
		}catch(FileNotFoundException e){
			System.out.println("FileNotFound"+e.getMessage());
		}
	}
	
	/**
	 * readRC read in number of rows and columns in the inputfile
	 * @param a scanner
	 * throw InputMismatchException
	 * 
	 */
	private void readRC(Scanner scan){
		//read in number of row and column in the first line
				String line = scan.nextLine();
				// read in number of rows and colums
				// declare a new scanner on the line. 
				Scanner scanLine = new Scanner(line);
				// assume col comes before row (the hw example)
				// try reading in the number of row
				if (scanLine.hasNextInt())  
					// scan that integer into row number or row
					col=scanLine.nextInt(); 
				else throw new InputMismatchException("The input file does not have number of columns");
				if (scanLine.hasNextInt())  
					// scan that integer into row number or row
					row=scanLine.nextInt();
				else throw new InputMismatchException("The input file does not have number of rows");
				// try reading in the number of cols
				// if there are more than 2 entries then throw an exception
				if (scanLine.hasNext()) throw new InputMismatchException("The " +
						"input file has wrong format of the number of rows and columns");
	}
	
	/**
	 * readMaze take in a scanner
	 * and read the maze info from the input file
	 * . is open space, value = -1; 
	 * # wall val = -2, 
	 * S start val=0, D destination val = -3
	 * suppose there are no spaces in between cells 
	 */
	private void readMaze(Scanner scan) //throws FileNotFoundException
	{
		// initialize the square matrix
		topology = new int[row][col];
		// str stores temporary character read in
		String str = "";
		// read in the matrix for square
		// loop through # of rows
		for(int i =0; i< row; i++)
		{
			// check if there is a next line then read 
			if(scan.hasNextLine())
			{
				String line = scan.nextLine();						
				// make a scanner for that line
				//Scanner scanLine = new Scanner(line);
				// read in each line
				for(int j =0; j<col; j++){
					// make sure there are col # of entries in a row
					try{
					str = line.charAt(j)+"";
					// open space
					if(str.equals(".")) topology[i][j]= -1;
					//wall
					else if(str.equals("#")) topology[i][j]=-2;
					//start
					else if(str.equals("S")){
						topology[i][j] = 0;
						// enqueue this element to the qCells
						qCells.enQueue(new Point(i,j));
					}
					//stop
					else if(str.equals("D")){
						topology[i][j] = -3;
						// push this on the path stack
						sPath.push(new Point(i,j));
					}
					// otherwise wrong format
					else{
						System.out.println("This is wrong "+str);
						throw new InputMismatchException("Illegal input");
					}
					}catch(StringIndexOutOfBoundsException e){
						throw new StringIndexOutOfBoundsException("Not enough inputs in a row");
					}
				} // end of j loop
				//too many inputs on a row
				if(line.length()>col) throw new InputMismatchException("Extra input in row "+ i+ " is " + str);
						
			}// end of if has next line
			else{
				//not enough line
				throw new InputMismatchException("Two few rows.");
			}
		}// end of i loop
		// check if there are too many rows
		if(scan.hasNextLine()) throw new InputMismatchException("Two many rows.");
	}
	
	/**
	 * getNeighbor 
	 * @param a point
	 * enqueue a point's neighbors that are not explored, not wall, not destination
	 * in order: North, South, East, West
	 */
	private void getNeighbors(Point sq)
	{
		int x = (int) sq.getX();
		int y = (int) sq.getY();
		int val = topology[x][y];
		// add neighbors to the array list
		// check if we can actually add the square
		if(x>0)	process(x-1,y,val);//North 
		if(x<row -1) process(x+1,y,val);//South
		if(y<col -1) process(x,y+1,val);//East
		if(y>0) process(x,y-1,val);//West	
	}
	
	/**
	 * Process decide whether to add a particular cell to the queue
	 * and change score of that cell if it's added
	 * @param x,y coordinates of the cell 
	 */
	private void process(int x, int y, int val){
		//if topology at position x,y is not explored
		// enqueue the cell
		// and give the cell a new score
		if(topology[x][y]==-1){
			qCells.enQueue(new Point(x,y));
			topology[x][y] = val +1;
		}
	}
	
	/**
	 * reachD
	 * @param a point
	 * @return true if a neighbor of that point is the destination
	 * false otherwise
	 */
	private boolean reachD(Point p)
	{
		int x = (int) p.getX();
		int y = (int) p.getY();
		// check north, south, east, west neighbor
		if(x>0	   && topology[x-1][y]==-3) return true;
		if(x<row-1 && topology[x+1][y]==-3) return true;
		if(y<col-1 && topology[x][y+1]==-3) return true;
		if(y>0     && topology[x][y-1]==-3) return true;
	    return false;
	}
	
	/**
	 * solve
	 * @param
	 * @return true if the maze is solvable; false otherwise
	 */
	public boolean solve()
	{
		// see the first elt in the queue
		Point temp = qCells.peek();
		// if neighbor of temp is not the destination
		while (!reachD(temp)){
			// dequeue that temp elt
			qCells.deQueue();
			// then process the not processed neighbors
			getNeighbors(temp);
			// if the queue is not empty
			// reset the temp
			if(!qCells.isEmpty()) temp = qCells.peek();
			//otherwise we cannot find the path
			else return false;
		}
		// if it ever gets out of while loop then we find a path
		return true;
	}
	
	/**
	 * retrace print out the shortest path
	 * by backtrack on cells with lowest scores
	 * using the sPath stack that we set up with the D in it
	 */
	public void retrace(){
		//look at the sPath stack
		Point temp = sPath.peek();
		// find neighbor with lowest score
		Point lNeighbor = backtrack(temp);
		// coordinates of lNeighbor
		int x = (int) lNeighbor.getX();
		int y = (int) lNeighbor.getY();
		// score of neighbor
		int score = topology[x][y];
		// if there is no more neighbor then lNeighbor = temp
		// then we reach the Start then
		while(score!=0){
			// push that neighbor on the stack
			sPath.push(lNeighbor);
			// update temp and lNeighbor
			temp = sPath.peek();
			lNeighbor = backtrack(temp);
			// coordinates of lNeighbor
			x = (int) lNeighbor.getX();
			y = (int) lNeighbor.getY();
			// score of neighbor
			score = topology[x][y];
		}
		// we have to do that until we see the start with score 0
		// the last lNeighbor is the S with score = 0, push it to the stack
		sPath.push(lNeighbor);
		while(!sPath.isEmpty()){
			Point track = sPath.pop();
			System.out.println("("+(int)track.getX()+","+(int)track.getY()+")");
		}
	}
	
	/**
	 * backtrack find the lowest scored neighbor of a point
	 * but not a wall & D
	 * @param a point
	 * @return its lowest scored neighbor
	 */
	private Point backtrack(Point p){
		// coordinates of the point
		int x = (int)p.getX();
		int y = (int)p.getY();
		// neigh hold the return value
		Point neigh= p;
		// the lowest score
		int score=10000;
		// look at all the neighbor and compare them
		if(x>0	   && topology[x-1][y]>=0){
			score = topology[x-1][y];
			neigh = new Point(x-1,y);
		}
		if(x<row-1 && topology[x+1][y]>=0 && score > topology[x+1][y]) {
			score = topology[x+1][y];
			neigh = new Point(x+1,y);
		}
		if(y<col-1 && topology[x][y+1]>=0 && score > topology[x][y+1]) {
			score = topology[x][y+1];
			neigh = new Point(x,y+1);
		}
		if(y>0     && topology[x][y-1]>=0 && score > topology[x][y-1]) {
			score = topology[x][y-1];
			neigh = new Point(x,y-1);
		}
		return neigh;
	}
	
	/**
	 * The main method has a test case
	 * 	
	 * @param args
	 */
	public static void main(String[] args) {
		//new BFS
		BFS mybfs = new BFS();
		mybfs.loadFile("maze-1");
		// need to retrace the path
		if(mybfs.solve()){
			System.out.println("The shortest path is:");
			mybfs.retrace();
		}
		else System.out.println("There are no paths to the destination.");
	}
	
}
