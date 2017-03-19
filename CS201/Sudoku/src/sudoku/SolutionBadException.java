package sudoku;
//Import statement
import java.lang.Exception;

public class SolutionBadException extends Exception
{
	public SolutionBadException(String str){
		super(str);
	}
}
