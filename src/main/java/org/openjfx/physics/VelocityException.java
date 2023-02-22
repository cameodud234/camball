package org.openjfx.physics;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.openjfx.messages.Messages;

public class VelocityException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(Velocity.class);
	
	private Object[] messageArgs = null;
	
	public VelocityException() {}
	
	public VelocityException(String message) {
		super(message);
	}
	
	public VelocityException(String message, Object... messageArgs) {
		super(message);
		this.messageArgs = messageArgs;
	}
	
	public VelocityException(Throwable cause) {
		super(cause);
	}
	
	public VelocityException(String message, Throwable cause, Object... messageArgs) {
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