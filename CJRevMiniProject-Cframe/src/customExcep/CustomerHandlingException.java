package customExcep;

@SuppressWarnings("serial")
public class CustomerHandlingException extends Exception { // Extending superClass Exception
	// to make our exception checked exception
	
	public CustomerHandlingException(String msg) {
		super(msg);
	}
}
