import java.util.ArrayList;

/**
 * extends the abstract class MazeSolver
 * define the add and remove abstract methods in MazeSolver by stub blocks
 * @author Luong Nguyen
 * @date 4/27/11
 */

public class MazeSolverRecursive extends MazeSolver{

	public MazeSolverRecursive(Maze m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	/**
	 * solve() @return true if a path is found
	 */
	public boolean solve()
	{
		// start square
		Square stSq = maze.getStart();
		// exit square
		Square endSq = maze.getEnd();
		//return solveIt(stSq,endSq);
		return solveIt(stSq);
	}
	
	/**
	 * helper function for the solve()
	 * solveIt
	 * @param start square
	 * @return true if start square is end square
	 * false otherwise
	 */
	private boolean solveIt(Square stSq)
	{
		// make the solver to go to sleep for 500ms
		do{
			try{
				Thread.sleep(500);					
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		while(!isAnimated);
		
		if(stSq.equals(maze.getEnd()))
		{
			// the maze is solve
			return true;
		}
		else
		{
			// mark the start square
			stSq.setOnlist(true);
			// identify the neighbors of this start square
			ArrayList<Square> neighbor = maze.getNeighbors(stSq);
			// for each unmarked square S adjacent to current square
			// walk through the list
			//System.out.println("I'm animated? " + isAnimated);
				for (int i =0; i< neighbor.size(); i++)
				{
					//System.out.println("I'm in the for loop");
					// store the current neighbor considered in the loop
					Square currentN = neighbor.get(i);
					// if the neighbor are not walls
					// not previously explored
					if((currentN.getType()!=1) && (!currentN.getOnlist()))// && (!currentN.getDis()))
					{
						//System.out.println("Im in the if now");
						// mark the neighbor
						currentN.setOnlist(true);
						//currentN.setDis(true);
						// repaint the maze
						//System.out.println("I'm repainting now");
						maze.repaint();
						currentN.setPrev(stSq);
						// try to find a path from that neighbor to the exit square
						if(solveIt(currentN))
						{
							// set the previous of the current neighbor to the start input 
							return true;
						}
						else
						{
							currentN.setOnlist(false);
							maze.repaint();
						}
					}
				}	
			
		}
		return false;
	}
	
	@Override
	public void add(Square sq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Square remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
			//System.out.println("I'm in the first while loop of the retrace path method");
			// add the currentSquare to the stack
			backPath.push(currentSq);
			
			// change the current square to its previous
			currentSq = currentSq.getPrev();
		}
		
		// pop each element of backPath
		// concart the string representation to the string s
		while(!backPath.isThisEmpty())
		{
			//System.out.println("I'm in the second while loop of the retrace path method");
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

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

}
