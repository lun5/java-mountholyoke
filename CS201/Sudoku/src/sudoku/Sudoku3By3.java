package sudoku;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.Integer;

	/**
	 * Displays a grid of text cells. The rows and columns are
	 * determined by the NUM_GRID_* constants below.
	 * 
	 * @author hfeild with modifications by Lisa Ballesteros
	 *
	 */
	public class Sudoku3By3
	{
	    // Constants for the number of rows and columns.
	    private final static int NUM_GRID_ROWS = 3;
	    private final static int NUM_GRID_COLUMNS = 3;
	    
	    // This keeps track of the cells that make up the grid.
	    private JTextField[][] cells;
	    
	    // The main grid panel.
	    JPanel sudoku3By3;
	    
	    /**
	     * Initializes the little grid.
	     */
	    public Sudoku3By3()
	    {
	        // Initialize the cells of the grid.
	        cells = new JTextField[NUM_GRID_ROWS][NUM_GRID_COLUMNS];
	        
	        // Create a new text field to be associated with each cell
	        // of the grid.
	        for( int i = 0; i < NUM_GRID_ROWS; i++ )
	            for( int j = 0; j < NUM_GRID_COLUMNS; j++ )
	                cells[i][j] = new JTextField();
	    }
	    
	    /**
	     * Creates a panel of text field cells.
	     * 
	     * @return A panel of text field cells.
	     */
	    public JPanel createPanel()
	    {
	        // Create a new panel.
	    	sudoku3By3 = new JPanel();
	        
	        // Set the panel to be set like a grid -- each thing we add below
	        // will be placed in a cell, starting with the upper left and moving
	        // left-to-right, top-to-bottom.
	    	sudoku3By3.setLayout( new GridLayout(NUM_GRID_ROWS, NUM_GRID_COLUMNS) );
	        
	        // place a border around this panel
	    	sudoku3By3.setBorder(BorderFactory.createLineBorder(Color.RED,3));
	        
	        // Add the cells to the grid.
	        for( int i = 0; i < NUM_GRID_ROWS; i++ )
	            for( int j = 0; j < NUM_GRID_COLUMNS; j++ )
	            	sudoku3By3.add( cells[i][j] );
	        
	        
	        // Return the new panel.
	        return sudoku3By3;
	    }
	    
	    /**
	     * setValue take in a row number and column number between 0 and 2
	     * and an int value between 1 and 9
	     * and call setText method to put the value in the right position
	     */
	    public void setValue(int i, int j, int val)
	    {
	    	String s = new String();
	    	// convert the int val into a string s
	    	s = ""+val;
	    	// set the display of the cell at position i, j to be s
	    	cells[i][j].setText(s);
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
	    	cells[i][j].setFont(f);
	    	cells[i][j].setForeground(c);
	    	cells[i][j].setHorizontalAlignment(align);
	    }
	    
	       
	    
	    /**
	     * setEditable takes in a boolean 
	     * and will make the textfield editable/not
	     */
	    public void setEditable(int i, int j, boolean b)
	    {
	    	cells[i][j].setEditable(b);
	    }
	    /**
	     * getText takes in row and col number 
	     * @return the text in the JTextField
	     */
	    public int getText(int i, int j) throws NumberFormatException
	    {
	    	String myString = cells[i][j].getText();
	    	/** call the getTex method in Sudoku3By3
	    	try{
	    		 n = Integer.parseInt(myString);
	    		 return n;
	    	}catch(Exception e)
	        {
	    		e.printStackTrace();
	    	}*/

	    	if (myString.equals("")) return 0;
	    	else{
	    		try{
	    			// try parsing the string
	    			int n= Integer.parseInt(myString);
	    			return n;
	    		}catch(NumberFormatException e){
	    			// if not successful
	    			return -1;	    			
	    		}
	    	}
	    }	    
}
