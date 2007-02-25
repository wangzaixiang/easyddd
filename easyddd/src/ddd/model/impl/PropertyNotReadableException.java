package ddd.model.impl;

public class PropertyNotReadableException extends Exception {

	public PropertyNotReadableException() {
		super();
	}

	public PropertyNotReadableException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyNotReadableException(String message) {
		super(message);
	}

	public PropertyNotReadableException(Throwable cause) {
		super(cause);
	}

}
