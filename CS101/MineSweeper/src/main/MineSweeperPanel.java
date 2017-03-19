package main;

/**
* MineSweeperPanel.java
* CSC 101 Final Project
* Luong Nguyen
**/
import java.lang.String;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
/**
* MineSweeperPanel is the main GUI panel for the MineSweeper game.
**/
public class MineSweeperPanel extends JPanel implements MouseListener
{
	/**
	* Currently clicked square.
	**/
	private MineSweeperSquare clickedSquare;
	//number of dimensions of grid
	private int dim;
	//array size dim*dim, holding MineSweeperSquare
	private MineSweeperSquare [][] mineGrid;
	//number of mines in the grid
	private int numberOfMines;
	//number of squares have been revealed
	private int numberOfRevealed;
	/**
	* Constructor. Create a MineSweeper game with dim*dim squares. 
	**/
	

	public MineSweeperPanel()
	{
		// use BorderLayout
		setLayout( new BorderLayout() );		
		// initialize dimension of the grid, numberOfMines and revealed
		dim = 7;
		numberOfMines =15;
		numberOfRevealed = 0;			
		// initialize the mineGrid array	
		mineGrid = new MineSweeperSquare[dim][dim];
		//initialize the frame
		//frame = new JOptionPane();
		// put mineRisk=0 for all entries of mineGrid
		for (int i=0; i<dim; i++)
			for (int j=0; j<dim; j++)
				mineGrid[i][j] = new MineSweeperSquare( 0 );
		// call function assignMine and Assign Grid to fill the array
		assignMine();
		assignGrid();
		// add a grid layout dimxdim in center
		add( gridLayout(), BorderLayout.CENTER );
	}
	
	//***************** ADDITIONAL METHODS *****************
	
	// add the mine Grid to the center of the border layout
	public JPanel gridLayout()
	{
		//initialize mineField
		JPanel mineField = new JPanel();
		mineField.setLayout(new GridLayout(dim,dim));
		//add array to the grid
		for (int i=0; i<dim; i++)
			for (int j=0; j<dim; j++)
		{
				// add a MineSweeperSquare on the field
				mineField.add(mineGrid[i][j]);
				// add MouseListener to each square on the grid
				mineGrid[i][j].addMouseListener( this );
		}
		return mineField;
	}
	
	/**
	* Create and add a MineSweeperSquare
	**/
	/*public MineSweeperSquare makeSquare( int mineRisk )
	{
		// create a new square
		//MineSweeperSquare square = new MineSweeperSquare( mineRisk );
		
		// add this object as square's mouse listener
		// note that MineSweeperPanel implements MouseListener interface
		square.addMouseListener( this );
		
		// add square to this panel
		return square;
	}*/
	
	//This function assigns numberOfMines randomly to the Grid
	public void assignMine()
	{
		int rowIndex; 
		int colIndex;
		//loop through numberOfMines to assign mines to the grid
		for (int i = 0; i<numberOfMines; i++){
			//choose rowIndex (0 to rowNumber-1) and colIndex randomly (0 to colNumber-1)
			rowIndex = (int)Math.round(Math.random()*(dim -1));
			colIndex = (int)Math.round(Math.random()*(dim -1));
			//no mine at mineGrid[rowIndex][colIndex] then
			if (!(mineGrid[rowIndex][colIndex].getMineRisk()==-1)){
				//assign a mine (put -1 to mineRisk)
				mineGrid[rowIndex][colIndex].mineRisk = -1;		
			}
			//otherwise, decrease run var i so it go through the loop again
			else i = i-1;
		}
	}
	
    //This function assign mineRisk to the rest of the grid
	public void assignGrid()
	{
		for (int i=0; i<dim; i++)
			for (int j=0; j<dim; j++)
			    // call function assignMineRisk for each elt of the grid
				mineGrid[i][j].mineRisk = assignMineRisk(i,j);
	}
	
    //take in row and col index and return corresponding number of surrounding mines
	public int assignMineRisk(int row, int col)
	{
		// cells in the center, call corresponding method
		if (row>0 && (row <dim -1) && col >0 && (col <dim-1))
			return assignCenter(row,col);
		// 4 corners
		else if (row == 0 && col ==0)
			return assignTopLeft(row,col);
		else if (row == 0 && (col == dim -1))
			return assignTopRight(row,col);
		else if ((row == dim -1) && col == 0) 
			return assignBottomLeft(row,col);
		else if ((row == dim -1) && (col == dim -1))
			return assignBottomRight(row,col);
		// top and bottom row excluding corners
		else if (row == 0 && col > 0 && (col <dim-1))
			return assignTopRow(row,col);
		else if ((row == dim-1) && col > 0 && (col <dim-1))
			return assignBottomRow(row,col);
		// left and right cols excluding corners
		else if (row>0 && (row <dim -1) && col ==0)
			return assignLeftCol(row,col);
		else 
			//if (row>0 && (row <dim -1) && (col ==dim-1))
			return assignRightCol(row,col);
		// this should never happen
		//else System.out.println( "Something has gone wrong!" );
	}
	
	// assign mineRisk to cells in the center
	public int assignCenter( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 8 cells around it
			if (mineGrid[row-1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col+1].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to 0,0 entry
	public int assignTopLeft( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check position [1,0]
			if (mineGrid[row+1][col].getMineRisk() == -1) countMine ++;
			//check position [0,1]
			if (mineGrid[row][col+1].getMineRisk() == -1) countMine ++;
			//check [1,1]
			if (mineGrid[row+1][col+1].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to 0,dim -1  entry
	public int assignTopRight( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check position [0,dim-2]
			if (mineGrid[row][col-1].getMineRisk() == -1) countMine ++;
			//check position [0,1]
			if (mineGrid[row+1][col-1].getMineRisk() == -1) countMine ++;
			//check [1,1]
			if (mineGrid[row+1][col].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to bottom left  entry
	public int assignBottomLeft( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 3 positions around it
			if (mineGrid[row-1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col+1].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to bottom right  entry
	public int assignBottomRight( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 3 positions around it
			if (mineGrid[row-1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col-1].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	
	// assign mineRisk to top row, excluding corners
	public int assignTopRow( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 5 positions around it
			if (mineGrid[row][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col+1].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to bot row, excluding corners
	public int assignBottomRow( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 5 positions around it
			if (mineGrid[row][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col+1].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to left col, excluding corners
	public int assignLeftCol( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 5 positions around it
			if (mineGrid[row-1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col+1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
	
	// assign mineRisk to right col, excluding corners
	public int assignRightCol( int row, int col)
	{
		int countMine = 0;
		if (mineGrid[row][col].getMineRisk() == -1) return -1;
		else{
			//check 5 positions around it
			if (mineGrid[row-1][col].getMineRisk() == -1) countMine ++;
			if (mineGrid[row-1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col-1].getMineRisk() == -1) countMine ++;
			if (mineGrid[row+1][col].getMineRisk() == -1) countMine ++;
			return countMine;
		}
	}
    // This return true if game is over, false if game is still on	
	public boolean gameOver()
	{
		// if the clicked square has mine the game is over
		if (clickedSquare.getMineRisk() == -1) return true;
		// if all the non mined cells are revealed
		else if(numberOfRevealed == dim*dim - numberOfMines) return true;
		// otherwise, the game is on
		else return false;
	}
    // return true if user wins, false if user loses or the game is still on
	public boolean gameWon()
	{
	    // if the game is over
		if (gameOver()){
		    //and all the non-mined squares are faced up, user wins
			if (numberOfRevealed == dim*dim - numberOfMines) return true;
			//otherwise, s/he loses
			else return false;
		}
		else return false;		
	}
	
    //This refresh the display of the game
	public void refreshDisplay()
	{
	    //System.out.println("Im in refresh display");
		// pop up congratulation if user win
		if (gameWon()){
			JOptionPane.showMessageDialog(this, "Congratulation. You won!! ^__^");
			// freeze the panel
			for (int i=0; i<dim; i++)
				for (int j=0; j<dim; j++)
					mineGrid[i][j].removeMouseListener( this );
			
		}
		// revealed every cell if the user lose
		else if (gameOver() && !gameWon()){
			for (int i=0; i<dim; i++){
				for (int j=0; j<dim; j++){
				    //System.out.println("Im in lost loop");
				    //flip every square up
					mineGrid[i][j].flip();
					// update display
					//System.out.println("Im repainting");
					//System.out.println(mineGrid[i][j].isRevealed());
					mineGrid[i][j].repaint();
					//repaint();
					//System.out.println("I did not do anything");
				}
			}
			//System.out.println( "Lost" );
			// pop up sorry pane
			JOptionPane.showMessageDialog(this, "Sorry. You lost! T___T");
		}
	}
		
	//***************** REQUIRED MOUSELISTENER METHODS *****************
	/**
	* Invoked when the mouse button has been clicked 
	* (pressed and released) on a component. 
	**/
	public void mouseClicked( MouseEvent e ) 
	{
		// determine which square generated this event
		// NOTE: this is an example of *casting*
		// we know that all objects using this as a MouseListener
		// are MineSweeperSquares, and thus the source of the event must
		// be a MineSweeperSquare
		// Since the getSource() method returns an Object 
		// (superclass of MineSweeperSquare), we force the type to be
		// a MineSweeperSquare by casting: (MineSweeperSquare) 
		clickedSquare = (MineSweeperSquare)e.getSource();
		
		// as long as clicked square was not already face up
		if ( !clickedSquare.isRevealed() )
		{    
			// flip square
			clickedSquare.flip();
			// increased the number of revealed squares
			numberOfRevealed ++;
			//System.out.println("im in clicked, after flip before repaint");
			// update display
			clickedSquare.repaint( 0 );
			//System.out.println("im in clicked, after repaint");
			// refresh display
			refreshDisplay();
		}
	}
	
	/**
	* Invoked when the mouse enters a component. 
	**/
	public void mouseEntered( MouseEvent e ) {}
	
	/**
	* Invoked when the mouse exits a component. 
	**/
	public void mouseExited( MouseEvent e ) {} 
	
	/**
	* Invoked when a mouse button has been pressed on a component. 
	**/
	public void mousePressed( MouseEvent e ) {} 
	
	/**
	* Invoked when a mouse button has been released on a component. 
	**/
	public void mouseReleased( MouseEvent e ) {} 
	
}
