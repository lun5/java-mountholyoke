/**
 * extends the abstract class MazeSolver
 * define the add and remove abstract methods in MazeSolver
 * @author Luong Nguyen
 * @date 4/22/11
 */

public class MazeSolverStack extends MazeSolver{
	// pathL stores the length of the path. It has the start square in it
	private int pathL=1;
	// the start square of the maze
	//private Square stSq;
	
	/**
	 * Constructor @param the Maze to be solved
	 * stored in maze var
	 */
	public MazeSolverStack(Maze m)
	{
		// call the super class constructor
		super(m);
		//super.makeEmpty();
		//stSq = maze.getStart();
	}
	
	/**
	 * implement the abstract method add declared in the MazeSolver abstract class
	 */
	 
	public void add(Square sq)
	{
		//System.out.println("I am now in the add method of the MazeSolverStack");
		// add the square to the end of the worklist.
		//System.out.println("What is my worklist now? "+ worklist);
		worklist.add(sq);
		//System.out.println("What is my worklist now? "+ worklist);
	}
	
	/**
	 * implement the abstract method remove()
	 * @return a square
	 * remove and return a square from the worklist
	 */
	public Square remove()
	{
		return worklist.removeLast();
	}
	
		
	/**
	 * getLength @return the path length
	*/ 
	public int getLength()
	{
		return pathL;
	}
	
	/**
	 * retracePath
	 * @param Square sq
	 * print out the path found from the start to the Square sq
	 * use a stack to store the squares encountered as following the path backward
	 * pop the square from the stack to print in forward direction
	 */
	public String retracePath(Square sq)
	{
		// the return string
		String s="";
		// the start square
		Square stSq = maze.getStart();
		// the first square in the path is the start square
		s = s+ "["+ stSq.getX() +", "+ stSq.getY() +"]-->";
		// the current square considered
		// starting from the input square
		Square currentSq = sq;
		// the stack used to store the backward path
		MyStack<Square> backPath = new MyStack<Square>();
		
		// if the current square is not the start
		// i.e. its previous is not null
		while(currentSq.getPrev()!=null)
		{
			// add the currentSquare to the stack
			backPath.push(currentSq);
			
			// increase the pathlength
			pathL = pathL+1;
			// change the current square to its previous
			currentSq = currentSq.getPrev();
		}
		
		// pop each element of backPath
		// concart the string representation to the string s
		while(!backPath.isThisEmpty())
		{
			// the pop square
			Square popSq = backPath.pop();
			// change the type of the square to exit
			// so it is painted red
			popSq.setType(3);
			// if the stack is not empty
			if(!backPath.isThisEmpty())
			s = s + "["+ popSq.getX() +", "+ popSq.getY() +"] -->";
			else s = s + "["+ popSq.getX() +", "+ popSq.getY() +"]";
		}		
				
		// return the path
		return s;
	}
	
	
	 
}
