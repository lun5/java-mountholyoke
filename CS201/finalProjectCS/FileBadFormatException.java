//Import statement
import java.lang.Exception;

import javax.swing.JOptionPane;


/** 
 * extends Exception help deal with problems arising 
 * if the solution file has the wrong format
 * extends the Exception class
 * @author Luong Nguyen
 * 19 April, 2011
 **/

public class FileBadFormatException extends Exception{
	public FileBadFormatException(String str){
		super(str);
		JOptionPane.showMessageDialog(null, str,str, JOptionPane.ERROR_MESSAGE);
	}
}
