import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Uses the closest pair algorithm described in Kleinberg and Tardos to find the closest
 * pair of points in 2D.
 */
public class ClosestPairCalculator {
	// Where everything is displayed
	private Display disp;
	
	// A comparator to sort by x-values
	private static final Comparator<Point> xComparator = new XComparator();

	// A comparator to sort by y-values
	private static final Comparator<Point> yComparator = new YComparator();

	/**
	 * Create an object to compute the segmented least squares
	 * @param disp where to display the lines created
	 */
	public ClosestPairCalculator (Display disp) {
		this.disp = disp;
	}

	/**
	 * Finds the closest pair of points in a list
	 * @param points the list of points
	 * @return the closest pair
	 */
	public PointPair findClosestPair(List<Point> points) {
		return null;
	}
}
