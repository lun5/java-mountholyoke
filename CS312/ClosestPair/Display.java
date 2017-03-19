import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

/**
 * Manages a display consisting of points, line segments and highlighted line segments.
 * 
 * @author Barbara Lerner
 * March 2007 Created
 * March 2011 Adapted for the closest pair of points problem
 *
 */
public class Display extends JPanel {
	/**
	 * The width of the display
	 */
	public static final int DISPLAY_WIDTH = 600;
	
	/**
	 * The height of the display
	 */
	public static final int DISPLAY_HEIGHT= 600;
	
	/**
	 * The size of a point
	 */
	private static final int POINT_SIZE = 6;
	
	// The points to display
	private List<Point> points;
	
	// The line segments to display
	private ArrayList<Line2D> segments = new ArrayList<Line2D>();
	
	// The line segments to highlight
	private ArrayList<Line2D> highlights = new ArrayList<Line2D>();
	
	/**
	 * Creates a display for a set of points
	 * @param points the points to display
	 */
	public Display(List<Point> points) {
		this.points = points;
	}

	/**
	 * Draws the display consisting of the x-y axes, the points and the line segments.
	 */
	public void paintComponent (Graphics g) {
		// Erase the previous display
		g.clearRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		
		g.setColor(Color.BLACK);
		
		// Draw the points
		Iterator<Point> iter = points.iterator(); 
		while (iter.hasNext()) {
			Point p = iter.next();
			g.fillOval((int)(p.getX()-(POINT_SIZE/2)), (int)(p.getY()-(POINT_SIZE/2)), POINT_SIZE, POINT_SIZE);
		}
		
		// Draw the line segments
		Iterator<Line2D> lineIter = segments.iterator();
		while (lineIter.hasNext()) {
			Line2D segment = lineIter.next();
			g.drawLine((int)segment.getX1(), (int)segment.getY1(), (int)segment.getX2(), (int)segment.getY2());
		}

	
		// Draw the highlighted line segments
		g.setColor(Color.RED);
		Iterator<Line2D> highlightIter = highlights.iterator();
		while (highlightIter.hasNext()) {
			Line2D segment = highlightIter.next();
			g.drawLine((int)segment.getX1(), (int)segment.getY1(), (int)segment.getX2(), (int)segment.getY2());
		}
}

	/**
	 * Add a line segment and refresh the display
	 * @param segment the line segment to add.
	 */
	public void addSegment(Line2D segment) {
		segments.add(segment);
		repaint();
	}

	/**
	 * Remove all exisitng line segments.
	 *
	 */
	public void clearLines() {
		segments.clear();	
		highlights.clear();
	}

	/**
	 * Remove the points and the lines from the display.
	 */
	public void clear() {
		clearLines();
		points.clear();
		repaint();
	}

	/**
	 * Add a vertical line at an x value that extends the full height of the display.
	 * @param x
	 */
	public void addVerticalLine(int x) {
		segments.add(new Line2D.Double(new Point(x, 0), new Point(x, DISPLAY_HEIGHT)));
	}

	/**
	 * Add a highlighted line between 2 points
	 * @param p1 the first end point
	 * @param p2 the second end point
	 */
	public void highlightLine(Point p1, Point p2) {
		if (p1 == null || p2 == null) {
			return;
		}
		highlights.add(new Line2D.Double(p1, p2));		
	}
}
