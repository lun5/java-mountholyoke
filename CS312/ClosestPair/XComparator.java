import java.awt.Point;
import java.util.Comparator;

/**
 * A comparator that can be used to order points from low to high based on their x values.
 * @author Barbara Lerner
 * @version March 24, 2011
 *
 */
public class XComparator implements Comparator<Point> {

	public int compare(Point p1, Point p2) {
		return (int)(p1.getX() - p2.getX());
	}

}
