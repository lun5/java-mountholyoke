import java.awt.Point;

/**
 * A class that holds 2 points and the distance between them.
 * @author Barbara Lerner
 * @version March 24, 2011
 *
 */
public class PointPair {
	private final Point p1;
	private final Point p2;
	private final double distance;
	
	public PointPair(Point p1, Point p2, double distance) {
		assert p1.distance(p2) == distance;
		
		this.p1 = p1;
		this.p2 = p2;
		this.distance = distance;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public double getDistance() {
		return distance;
	}

	/**
	 * @param pair1
	 * @param pair2
	 * @return the point pair that has the least distance between the points
	 */
	public static PointPair closest(PointPair pair1, PointPair pair2) {
		if (pair1.distance < pair2.distance) {
			return pair1;
		}
		return pair2;
	}
}
