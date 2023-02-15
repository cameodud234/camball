package org.openjfx.physics;

public class VelocityException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VelocityException() {
		super();       
	}

	public VelocityException(String message) {
		super(message);
	}
	
	public VelocityException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public VelocityException(Throwable cause) {
		super(cause);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
