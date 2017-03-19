package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class Sudoku extends JComponent implements ActionListener
{
    // Constants for the number of rows and columns in the big grid.
    private final static int NUM_GRID_ROWS = 3;
    private final static int NUM_GRID_COLUMNS = 3;
    
    // Constants for the number of rows and columns in the frame.
    //private final static int NUM_FRAME_ROWS = 1;
    //private final static int NUM_FRAME_COLUMNS = 1;
    
    // This keeps track of the cells that make up the grid.
    private Sudoku3By3[][] sudoku3By3;
    // the game frame
    private JFrame frame;
    // Keeps track of the grid panel.
    private JPanel gridPanel;
    // Keeps track of the button panel.
    private JPanel buttonPanel;
    // 3 Button on the frame
	private JButton checkB, nextgameB, revealB;
	// SudokuSolution object
	private SudokuSolution sol;
	// array storing the solution
	private int[][] sdkSol; 
	private String[] solSet={"solution1IO.txt","solution2IO.txt","solution3IO.txt"};
    // the game Number
	int gameNum;
    
    /**
     * Initializes the grid.
     * @throws IOException 
     */
    public Sudoku(int gameN) throws IOException
    {
    	System.out.println("I'm in a constructor");
    	this.gameNum = gameN;
    	sol = new SudokuSolution(solSet[gameNum]);
    	sdkSol = sol.getSolution();
    	//sol.setSudoku(this);
        // Initializes the double LittleGrid array, but does not
        // initialize any of the cells.
        sudoku3By3 = new Sudoku3By3[NUM_GRID_ROWS][NUM_GRID_COLUMNS];        
        // Set up the frame that contains the grid.
        setupFrame();
    }
    
    /**
     * Creates a frame that will display the grid of little grids.
     */
    private void setupFrame()
    {
        // Create the frame and set its size.
        frame = new JFrame();
        frame.setSize(new Dimension(300, 350));

        // Exit the application when the user closes the frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout to be a grid.
        frame.setLayout(new BorderLayout());
        //frame.add(new JButton ("Press me!"));
        // Set the layout to be a grid.
        //frame.setLayout( 
          //      new GridLayout(NUM_FRAME_ROWS,NUM_FRAME_COLUMNS) );
        // Create the button panel and add it.
        frame.add( createButtonPanel(),BorderLayout.NORTH);
        // Create the grid panel and add it.
        frame.add( createGridPanel(),BorderLayout.CENTER); 
        frame.pack();
        // Display the frame.
        frame.setVisible(true);

    }
    
    /**
     * Creates the grid of Sudoku3By3.
     * 
     * @return A panel containing a grid of sudoku3By3.
     */
    private JPanel createGridPanel()
    {
        // Create a new panel.
        gridPanel = new JPanel();
        
        // Set the panel to be set like a grid -- each thing we add below
        // will be placed in a cell, starting with the upper left and moving
        // left-to-right, top-to-bottom.
        gridPanel.setLayout( new GridLayout(NUM_GRID_ROWS, NUM_GRID_COLUMNS) );
        
        // Iterate through each row and column of the big grid.
        for( int i = 0; i < NUM_GRID_ROWS; i++ )
        {
            for( int j = 0; j < NUM_GRID_COLUMNS; j++ )
            {
                // Track a new little grid.
                sudoku3By3[i][j] = new Sudoku3By3();
                // Add it to the big grid panel.
                gridPanel.add(sudoku3By3[i][j].createPanel() );
            }
        }
        
        // Return the newly made big grid panel.
        return gridPanel;
    }
    
    /**
     * Creates the button Panel
     * 
     * @return A panel containing 3 button
     */
    private JPanel createButtonPanel()
    {
        // Create a new panel.
        buttonPanel = new JPanel();
        
        // Set the panel to be set like a box-- each thing we add below
        // will be placed in a cell, placing from left-to-right
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        // define the three buttons on the buttonPanel
		checkB = new JButton("Check");
		nextgameB = new JButton("Next game");
		revealB = new JButton("Reveal solution");
		
		/**				// add action listener to each of the button
		checkB.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					// this method is activated when Check is clicked
					{
						//Pop up window
						//JOptionPane.showMessageDialog(buttonPanel,"You clicked Check");
						 System.out.println("clicked Check");
					}// method actionPerformed
				});
		
		nextgameB.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					// this method is activated when Check is clicked
					{
						//Pop up window
						//JOptionPane.showMessageDialog(buttonPanel,"You clicked next game");
						 System.out.println("clicked nextgame");
					}// method actionPerformed
				});
		
		revealB.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					// this method is activated when Check is clicked
					{
						//Pop up window
						//JOptionPane.showMessageDialog(buttonPanel,"You clicked reveal");
						 System.out.println("clicked reveal");
					}// method actionPerformed
				});*/
		
		
		// add action listener to each of the button
		checkB.addActionListener(this);
		nextgameB.addActionListener(this);
		revealB.addActionListener(this);
		
		// add buttons to the panel
		buttonPanel.add(checkB);
		buttonPanel.add(nextgameB);
		buttonPanel.add(revealB);
		
        // Return the newly made big grid panel.
        return buttonPanel;
    }
    
    /**
     * setTextDisplay will set the font, color, and alignment of entries 
     * hints are blue Monaco
     * user solutions are black Menlo
     * reveal solutions are blue Time New Romans
     * everything is center alignment
     */
    public void setTextDisplay(int i, int j,Font f, Color c, int align)
    {
    	// identify the indices of Sudoku3By3 object in the position
    	int index1, index2;
    	index1 = (int)(i/3);
    	index2 = (int)(j/3);
    	
    	// position of interest in the sudoku3By3 object
    	int row, col;
    	row = i%3;
    	col = j%3;
    	    	
    	// call the setValue method in Sudoku3By3
    		sudoku3By3[index1][index2].setTextDisplay(row, col , f, c, align); 
    }
    
    /* This method is activited automatically by an event occurring*/
    public void actionPerformed(ActionEvent e)
    {
	Object theEvent = e.getSource();
	
	if (theEvent == checkB){  
	    // print out my name when Ok is clicked
		System.out.println("clicked Check");
		this.checkAns();
		//JOptionPane.showMessageDialog(frame, "You clicked Check");
	}
	if (theEvent == nextgameB){ 
	    // print out "Close clicked"
	    System.out.println("clicked nextgame");
	    JOptionPane.showMessageDialog(frame, "You clicked Next game");
	    try {
			this.nextGame();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	if (theEvent == revealB){ 
	    System.out.println("clicked reveal");
	    revealSolution();
	   //JOptionPane.showMessageDialog(frame, "You clicked Reveal");
	}
	}// method actionPerformed
    
    /**
     * setValue take in a row number and column number between 0 and 8
     * and an int value between 1 and 9
     * and call setValue method in Sudoku3By3 to put the value in the right position
     */
    public void setValue(int i, int j, int val)
    {
    	// identify the indices of Sudoku3By3 object in the position
    	int index1, index2;
    	index1 = (int)(i/3);
    	index2 = (int)(j/3);
    	
    	// position of interest in the sudoku3By3 object
    	int row, col;
    	row = i%3;
    	col = j%3;
    	    	
    	// call the setValue method in Sudoku3By3
    	sudoku3By3[index1][index2].setValue(row, col, val);    	
    }
    
    
    /**
     * setEditable takes in a boolean and position of cell in the big grid
     * and will make the corresponding textfield editable/not
     */
    public void setEditable(int i, int j, boolean b)
    {
    	// identify the indices of Sudoku3By3 object in the position
    	int index1, index2;
    	index1 = (int)(i/3);
    	index2 = (int)(j/3);
    	
    	// position of interest in the sudoku3By3 object
    	int row, col;
    	row = i%3;
    	col = j%3;
    	// call the setEditable method in Sudoku3By3
    	sudoku3By3[index1][index2].setEditable(row, col, b);
    }
    
    
    
    /**
     * initGame initialize the display of the game
     */
    public void initGame()
    {
    	//revealB.setEnabled(false);
    	//nextgameB.setEnabled(false);
    	displayHints();
    }
    
    /**
     * setEnabled takes in a boolean
     * and free the button if false
     * and activate the button if true
     */
    
    /**
     * userAnswer take in the user
     */
    
    
    /**
     * displayGame will reset the display 
     * of Sudoku based on what have been clicked
     */
    public void displayGame()
    {
    	
    }
    
    /**
     * displayHint will the display the hints
     */
    public void displayHints()
    {
    	int[][] sdkhint = sol.getHints();
    	for (int i=0; i<9; i++)
    		for (int j=0; j<9; j++)
    		{
    			if (sdkhint[i][j]!=0)
    			{
    			this.setValue(i, j, sdkhint[i][j]);
    			this.setEditable(i,j,false);
   				this.setTextDisplay(i, j, new Font("Monaco", Font.BOLD, 12), Color.BLUE,JTextField.CENTER );
    			}
    			else{
    			this.setTextDisplay(i, j, new Font("Menlo", Font.BOLD, 12), Color.BLACK, JTextField.CENTER);
    			}
    		}
    }
    
    /**
     * revealSolution will reset the display 
     * and reveal all of the solution
     */
    public void revealSolution()
    {
    	for (int i=0; i<9; i++)
    		for (int j=0; j<9; j++)
    		{
    			this.setValue(i, j, sdkSol[i][j]);
    			this.setEditable(i,j,false);
   				this.setTextDisplay(i, j, new Font("Monaco", Font.BOLD, 12), Color.BLUE,JTextField.CENTER );
    		}
    }
    
    /**
     * checkAns checks the user answer
     * if everything is good, Congratulation will pop out
     * otherwise it will be Try again
     */
    public void checkAns() //throws SolutionBadException
    {
    	//System.out.println("I'm in check. Screw me");
    	boolean win;
    	win = true;
    	for (int i=0; i<9; i++)
    		for (int j=0; j<9; j++)
    		{
    			//System.out.println("I'm in check, for loop, before if. Screw me");
    			System.out.println(this.getText(i,j));
    			if(this.getText(i,j)==-1) {JOptionPane.showMessageDialog(frame, "You have to enter a value from 1 to 9"); /**System.exit(1);*/}
    			else if(this.getText(i, j)==0) win = false;
    			else if(this.getText(i,j)!=sdkSol[i][j]) win = false;
    		}
    	System.out.println("I'm in check, after forloop. Screw me");
    	if(win) JOptionPane.showMessageDialog(frame, "Congratulation");
    	else JOptionPane.showMessageDialog(frame, "Try again");
    }   
    
    /**
     * getText takes in row and col number 
     * @return the text in the JTextField
     */
    public int getText(int i, int j)
    {
    	// identify the indices of Sudoku3By3 object in the position
    	int index1, index2;
    	index1 = (int)(i/3);
    	index2 = (int)(j/3);
    	
    	// position of interest in the sudoku3By3 object
    	int row, col;
    	row = i%3;
    	col = j%3;
    	// call the getTex method in Sudoku3By3
    	return sudoku3By3[index1][index2].getText(row, col);
    }
    
    /**
     * nextGame let the user continue to anotherGame
     */
    public void nextGame() throws IOException//throws SolutionBadException
    {
    	Sudoku sdk = new Sudoku((this.getGameNum()+1)%3);
        sdk.initGame();
    }  
    
    public int getGameNum()
    {
    	return gameNum;
    }
    /**
     * Creates a new Sudoku
     * 
     * @param args Command line arguments (not used).
     * @throws IOException 
     */
    public static void main( String[] args ) throws IOException
    {
        // Create a new big grid instance.
        Sudoku sdk = new Sudoku(0);
        sdk.initGame();
        /**for (int i=0; i<9; i++)
    		for (int j=0; j<9; j++)
    		{
    			System.out.println("Input at "+i+" and "+j+" position is"+sdk.getText(i,j));
    		}*/
        //SudokuSolution ssln = new SudokuSolution("solution1IO.txt",sdk);
        //ssln.printArray();
        // try setting the value
        //sdk.setValue(8, 5, 7);
    }
    
}

