package ddd.model.impl;

public class PropertyNotWritableException extends Exception {

	public PropertyNotWritableException() {
		super();
	}

	public PropertyNotWritableException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyNotWritableException(String message) {
		super(message);
	}

	public PropertyNotWritableException(Throwable cause) {
		super(cause);
	}

}
