package redalpha.exception;

/**
 * 
 * @author anastasia
 *
 * Custom exception for call entity validation.
 */
public class CallValidationException extends Exception {

	public CallValidationException() {}
	
	public CallValidationException(String message) {
		super(message);
	}
}
