package org.openjfx.physics;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PositionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getLogger(Position.class);
	
	private Object[] messageArgs = null;
	
	public PositionException() {}
	
	public PositionException(String message) {
		super(message);
	}
	
	public PositionException(String message, Object... messageArgs) {
		super(message);
		this.messageArgs = messageArgs;
	}
	
	public PositionException(Throwable cause) {
		super(cause);
	}
	
	public PositionException(String message, Throwable cause, Object... messageArgs) {
		super(message, cause);
		this.messageArgs = messageArgs;
	}
	
	@Override
	public String getMessage() {
		
	    StringBuilder message = new StringBuilder();
	    
	    message.append(super.getMessage());
	    
	    if(super.getCause() != null) {
	    	message.append(": ");
	    	message.append(super.getCause());
	    }
	    
	    if(messageArgs == null) {  
	    	return message.toString();
	    }
	    
	    String messageString = message.toString();
	    
	    MessageFormat messageFormatter = new MessageFormat(messageString);
	    
	    String formattedMessage = messageFormatter.format(messageArgs);
	    
	    return formattedMessage;

	}
	
}