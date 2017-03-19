package main;

/**
* MinesweeperApplet.java
* CSC 101 Final Project
* Luong Nguyen
**/

// awt
import javax.swing.JApplet;

/**
* MineSweeperApplet is a wrapper for the MineSweeperPanel class.
**/ 
public class MineSweeperApplet extends JApplet
{
	
	/**
	* Invoked when applet is initialized.
	**/
	public void init()
	{
		// add MineSweeperPanel
		getContentPane().add( new MineSweeperPanel() ); 
	}
	
	public void destroy()
	{
	}
	
	public void start()
	{
	}
	
	public void stop()
	{
	}
	
}
