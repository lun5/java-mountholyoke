import java.io.FileNotFoundException;

/**
 * extends the class MazeSolverTester
 * test the MazeSolver class
 * @author Luong Nguyen
 * @date 4/22/11
 */

public class MazeSolverTester {
	/**
	 * Test the Maze by main
	 */
	public static void main(String[] args) throws FileNotFoundException, FileBadFormatException{
		// create a maze from file
		Maze maze = new Maze();
		maze.loadMaze("maze-2");
		// print the maze to the console to confirm correct maze
		System.out.print(maze.toString());
		// create a MazeSolverStack
		MazeSolver msol = new MazeSolverStack(maze);
		System.out.println("Before I ask if the maze can be solved");
		// if there is no solution
		//System.out.println("##########Can I solve the maze " + msol.solve()+"##########");
		boolean solvable = msol.solve();
		System.out.println("##########Can I solve the maze? " + solvable +"##########");
		if(solvable)
		{
			System.out.println("########## Can I solve the maze " + msol.solve()+"##########");
			// if found a path, print the length of the path using str
			String str="";
			// the end square is
			Square endSq = maze.getEnd();
			// call the trace path on the end square
			str = str + msol.retracePath(endSq);
			//System.out.println("Length of path is "+ msol.getLength() + str);
			System.out.println("Found a path of length "+ msol.getLength());
			System.out.println(str);
			
		}
		else
		{
			// print a msg indicating that no path exists
			System.out.println("#######There is no path through the maze###########");
			
		}
		
	}
}
