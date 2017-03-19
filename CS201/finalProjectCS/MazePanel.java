import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * This is the main GUI.
 * It contains the buttons and textboxes and such.
 * It also handles the action events (button clicks, etc.)
 *
 * You will only have to modify the actionPerformed method. 
 * 
 * @author Alexa Sharp
 * modified by jd 2010
 * 
 */

public class MazePanel extends JPanel implements ActionListener {
	// The maze itself
	private Maze	maze;
	// The height and width of the displayed maze. Feel free to change.
	private static final int MAZE_H = 300;
	private static final int MAZE_W = 300;
	// Whether or not the animation is currently going
	private boolean		isAnimated;
	// The MazeSolver
	private MazeSolver 	solver = null;

	// The following are the GUI components
	private JTextField	filenameTextField;
	private JButton		loadButton;
	private JButton		startStackButton;
	//private JButton		startQueueButton;
	private JButton		startRecursiveButton;
	private JButton		toggleButton;
	// No need to change the constructor
	public MazePanel() {
		makeTheObjects();	// creates the GUI components
		doTheLayout();	 	// place the components on the panel
		// The following makes sure that 
		// if any of the buttons are pressed, or the
		// enter key is pressed from the textbox,
		// that this class is notified through the 
		// actionPerformed method
		filenameTextField.addActionListener(this);
		loadButton.addActionListener(this);
		startStackButton.addActionListener(this);
		//startQueueButton.addActionListener(this);
		startRecursiveButton.addActionListener(this);
		toggleButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {		
		Object obj = e.getSource();

		if( obj.equals(filenameTextField) || obj.equals(loadButton) ) {
			// Load the file
			String fname = filenameTextField.getText();
			try {
				//TODO: load the maze contained in the file fname
				// use the private Maze variable "maze"
				// then repaint the maze
				maze.loadMaze(fname);
				maze.repaint();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileBadFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (obj.equals(startStackButton) ) {
			// TODO: Start the maze solver as a stack.
			// use the private MazeSolver variable "solver"
			//JOptionPane.showMessageDialog(this, "click the start button");
			solver = new MazeSolverStack(maze);
			// set the animation
			isAnimated = true;
			solver.setAnimated(isAnimated);
			// uncomment the following line
			new Thread(solver).start();
		} else if (obj.equals(startRecursiveButton)) {
			// TODO: Start the maze solver using the recursive algorithm.
			// use the private MazeSolver variable "solver"
			solver = new MazeSolverRecursive(maze);
			// set the animation
			isAnimated = true;
			solver.setAnimated(isAnimated);
			// uncomment the following line
			new Thread(solver).start();
		} else if (obj.equals(toggleButton) && solver!=null ) {
			//System.out.println("In maze panel before " + isAnimated);
			// toggle the animation.
			isAnimated = !isAnimated;
			//System.out.println("In maze panel after " + isAnimated);
			System.out.println("I clicked the toggle button.");
			// TODO: turn animation on or off in the solver
			solver.setAnimated(isAnimated);
			//new Thread(solver).start();
		}
	}

	public MazePanel(LayoutManager layout) {
		super(layout);
	}

	public MazePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public MazePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	private void makeTheObjects() {
		maze = new Maze();
		maze.setPreferredSize( new Dimension(MAZE_W,MAZE_H));
		filenameTextField = new JTextField(30);
		loadButton = new JButton( "Load" );
		startStackButton = new JButton( "Start (stack)");
		//startQueueButton = new JButton( "Start (queue)");
		startRecursiveButton = new JButton( "Start (recursive)");
		toggleButton = new JButton( "Toggle animation" );
	}
	/**
	 * Arranges the GUI components in the panel
	 * No need to modify.
	 */
	private void doTheLayout() {
		JPanel topHalf = new JPanel();
		JPanel middleHalf = new JPanel();
		JPanel bottomHalf = new JPanel();
		
		// lay out the top half
		topHalf.setLayout(new FlowLayout() );
		topHalf.add( new JLabel( "Filename"));
		topHalf.add(filenameTextField);
		topHalf.add(loadButton);
		topHalf.add(startStackButton);
		//topHalf.add(startQueueButton);
		topHalf.add(startRecursiveButton);
				
		middleHalf.setLayout(new FlowLayout() );
		middleHalf.add( toggleButton );
		
		bottomHalf.setLayout(new FlowLayout());
		bottomHalf.add( maze );
		
		setLayout( new BorderLayout() );
		add(topHalf, "North");
		add(middleHalf, "Center");
		add(bottomHalf, "South");	
	}
		
}
