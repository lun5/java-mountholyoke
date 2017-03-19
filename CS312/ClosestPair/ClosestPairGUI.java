import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Given a set of points, find the closest pair of points.
 * 
 * To use this program, the user clicks in the window to add points.  Clicking the Closest Pair button
 * will find the closest pair and draw a line between them.
 * 
 * @author Barbara Lerner
 * March 20011
 *
 */
public class ClosestPairGUI {
	// The number of points generated by the "Generate random points" button.
	private static final int NUM_RANDOM_POINTS = 25;

	// The points
	private List<Point> points = new LinkedList<Point>();
	
	// The display for the points
	private Display display = new Display(points);
	
	// The object that finds the closest pair of points
	private ClosestPairCalculator calculator = new ClosestPairCalculator(display);
	
	// The buttons for the user to click on
	private JButton closestPairButton = new JButton ("Find closest pair");
	private JButton clearButton = new JButton ("Clear data");
	private JButton randomButton = new JButton ("Generate random points");
	
	/**
	 * Creates the GUI for the program and attaches the listeners
	 */
	public ClosestPairGUI() {
		createDisplay();
		attachListeners();
	}

	/**
	 * Attaches the listeners.  The user can click in the display to create a point or use the 
	 * "Generate random points" button to get points randomly distributed on the display.  
	 * "Clear data" removes all the points so that the user can start again on a new set.
	 * "Find closest pair" will find the closest pair of points and draw a red line between them.
	 * The console will display a text message indicating the distance between the closest pair.
	 */
	private void attachListeners() {
		display.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// Add a point where the user clicks.
				points.add (new Point (e.getX(), e.getY()));
				closestPairButton.setEnabled(true);
				display.clearLines();
				display.repaint();
			}

		});
		
		closestPairButton.addActionListener (new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PointPair closest = calculator.findClosestPair(points);
				display.highlightLine(closest.getP1(), closest.getP2());
				display.repaint();
				System.out.println("Closest = " + closest.getDistance());
			}

			
		});
		
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				display.clear();
				closestPairButton.setEnabled(false);
			}
			
		});
		
		randomButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				display.clear();
				Random generator = new Random();
				for (int i = 0; i < NUM_RANDOM_POINTS; i++) {
					points.add(new Point(generator.nextInt(Display.DISPLAY_WIDTH), 
							generator.nextInt(Display.DISPLAY_HEIGHT)));
				}
				closestPairButton.setEnabled(true);
			}
			
		});
	}

	/**
	 * Create the GUI components and display the window.
	 */
	private void createDisplay() {
		JFrame f = new JFrame();
		
		Container contentPane = f.getContentPane();
		contentPane.add(display, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		closestPairButton.setEnabled(false);
		buttonPanel.add (closestPairButton);
		
		buttonPanel.add(clearButton);
		buttonPanel.add(randomButton);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		f.setSize(Display.DISPLAY_WIDTH, Display.DISPLAY_HEIGHT);
		f.setVisible(true);
	}
	
	/**
	 * Create the user interface.
	 * @param args Not used
	 */
	public static void main (String[] args) {
		new ClosestPairGUI();
	}
	

}
