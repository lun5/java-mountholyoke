import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
/**
 * The main class for the program
 * You should not change anything in this file.
 *
 * @author Alexa Sharp
 * (Fall 2009)
 * modified by jd 2010
 */


public class MazeApp extends JFrame {

	public MazeApp( ) {
		
		/*
		 * The following just guarantees that the
		 * program exits when the window is closed
		 */
		addWindowListener( new WindowAdapter() 
		{
			public void windowClosing( WindowEvent event )
			{System.exit(0); }
		}
		);
	}
	
	/**
	 * The main method of the Maze application
	 */ 
	

	public static void main(String[] args) {

		JFrame f = new MazeApp();
		f.setTitle( "A-Maze-ing!");
		
		Container contentPane = f.getContentPane();
		contentPane.add( new MazePanel() );
		f.pack();
		f.setVisible(true);
	}
	

}
