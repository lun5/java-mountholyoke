package main;

/**
* MineSweeperSquare.java
* CSC 101 Final Project
* Luong Nguyen
**/

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import java.lang.Integer;

/**
* MineSweeperSquare represents a square in the MineSweeper game.
* @author Luong Nguyen
**/
public class MineSweeperSquare extends JComponent
{
	/** 
	* Hold mine information: -1 if there is a mine. 
	* 0-8 as a number of surrounding mines. 
	**/
	public int mineRisk; 
	
	/**
	* square is revealed if true.
	**/
	public boolean revealed;
	
	/**
	* Constructor. Creates a new MineSweeperSquare instance with
	* the passed mineRisk, initially unrevealed.
	**/
	public MineSweeperSquare( int mineRisk)
	{
		this.mineRisk = mineRisk;
		
		// set square face down
		revealed = false;
	}
	
	/** 
	* Get mineRisk for this square.
	* @return int describing the square's mine status.
	**/
	public int getMineRisk()
	{
		return mineRisk;
	}
	
	/**
	* Test if square is revealed.
	* @return true if square is revealed.
	**/
	public boolean isRevealed()
	{
		return revealed;
	}
	
	/**
	* Flip square over.
	**/
	public void flip()
	{
		revealed = true; // switch to other boolean value
	}
	
	/**
	* Special method as specified in JComponent (parent)
	* for drawing on this button.
	* We *override* the method here to customize the display.
	**/
	public void paint( Graphics g )
	{
		//System.out.println( "painting, revealed: "+  revealed );
		// if square is face up
		if ( revealed )
		{
			//System.out.println("im in painting if revealed");
			//if there is a bomb
			if (mineRisk == -1){
				// call utility method to draw the image
				// of the bomb onto the square
				/**
				* image used
				**/
				//System.out.println("im painting bomb");
				BufferedImage img = null;
				//Image img = null;
				try {
					img = ImageIO.read(new File("bombPicture.jpg"));
				} catch (IOException e) {
				}
				
				//System.out.println("Have placed tile");
				//paintBackground( g, Color.red );
				g.drawImage(img, 0, 0,img.getWidth(),img.getHeight(),null);
			}
			//if there is no bomb
			else
			{
			    //System.out.println("im in painting number");
				// call utility method to paint background of square pink
				paintBackground( g, Color.pink );
				// call utility method to paint mineRisk value onto card
				paintDescription( g );
			}
			
		}
		// otherwise, square is not revealed
		else
		{
			// simply paint the back of a square
			paintBackground( g, Color.pink );
		}
	}
	
	/**
	* Utility method to paint the background of the square
	* with the specified color.
	**/
	public void paintBackground( Graphics g, Color c )
	{
		// set the color of the "brush"
		g.setColor( c );
		
		// fill a rectangle (using the square's color)
		// arguments are x, y, width, height
		// note: we use methods getWidth() and getHeight(),
		// which we inherited from JComponent
		g.fillRoundRect( 0, 0, getWidth() - 1, getHeight() - 1, 10, 10 );
		
		// add a rounded rectangular border in black
		g.setColor( Color.black );
		// arguments are x, y, width, height, arcWidth, arcHeight
		g.drawRoundRect( 0, 0, getWidth() - 1, getHeight() - 1, 10, 10 ); 
	}
	
	/**
	* Utility method to paint the description onto the square.
	**/
	public void paintDescription( Graphics g )
	{
		// set "brush" color to white
		g.setColor( Color.white );
		
		// set font
		g.setFont( new Font( "Palatino", Font.BOLD, 36 ) );
		
		// draw text
		g.drawString( Integer.toString(mineRisk), 20, getHeight() / 2 + 15 );
	}
}
