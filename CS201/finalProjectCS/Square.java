import java.awt.Point;

import javax.swing.JOptionPane;

/**
 * This is the the internal representation 
 * of square in the maze
 * it contains type, position and previous square
 * @author Luong Nguyen
 * @date 4/19/11
 * 
 */
public class Square {
	//type 0 is empty, 1 is wall, 2 is start, 3 is end
	private int type;
	// Point store the position of the square
	private Point point;
	// prev stores the previous square of this
	private Square prev;
	// discover keep track whether a square is discovered or not
	private boolean discover;
	// keep track if the square is on the worklist
	private boolean onlist;
	
	/**
	 * Constructor takes in type, positions
	 */
	public Square(int type, int x, int y)
	{
		this.type = type;
		this.point= new Point(x,y); 
		discover = false;
		onlist = false;
		prev = null;
	}
	
	/**
	 * Constructor takes in nothing
	 */
	public Square()
	{
		//this.type = 0;
		//this.point= null; 
		discover = false;
		prev = null;
	}
	
	
	/**
	 * toString
	 * @return characters corresponding to the Square
	 * - empty, # wall, S start, E exit
	 */
	public String toString()
	{
		String sqStr="";// the return string
		switch(type){
		// if empty
		case 0: sqStr = "-"; break;
		// if wall
		case 1: sqStr = "#"; break;
		// if start
		case 2: sqStr = "S"; break;
		// if exit
		case 3: sqStr = "E"; break;
		// otherwise
		//default: JOptionPane.showMessageDialog(null, "wrong format", "wrong", JOptionPane.ERROR_MESSAGE);
		}
		return sqStr;
	}
	
	/**
	 * getType() @return the type of square
	 */
	public int getType()
	{
		return type;
	}
	
	/**
	 * setType @param int t
	 * set type to be t
	 */
	public void setType(int t)
	{
		type = t;
	}
	
	/**
	 * getX() @return the X position
	 */
	public int getX()
	{
		return (int)point.getX();
	}
	
	/**
	 * getY() @return the Y position
	 */
	public int getY()
	{
		return (int)point.getY();
	}
	
	/**
	 * setPoint(Point p)
	 * set point to be p
	 */
	public void setPoint(Point p)
	{
		point = p;
	}
	
	/**
	 * setPoint(int x, int y)
	 * @param x, y positions
	 * @return void
	 */
	public void setPoint(int x, int y)
	{
		point = new Point(x,y);
	}
	
	/**
	 * getPrev 
	 * @return the square previous to this square
	 */
	public Square getPrev()
	{
		return prev;
	}
	
	/**
	 * setPrev(Square sq)
	 * @param a square sq
	 * @return void
	 */
	public void setPrev(Square sq)
	{
		prev = sq;
	}
	
	/**
	 * getDis()
	 * @return discover
	 */
	public boolean getDis()
	{
		return discover;
	}
	
	/**
	 * setDis @param boolean dis
	 * set discover to dis
	 */
	public void setDis(boolean dis)
	{
		discover = dis;
	}
	
	/**
	 * getOnlist()
	 * @return onlist
	 */
	public boolean getOnlist()
	{
		return onlist;
	}
	
	/**
	 * setDis @param boolean dis
	 * set discover to dis
	 */
	public void setOnlist(boolean ol)
	{
		onlist = ol;
	}
		
}
