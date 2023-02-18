package org.openjfx.physics;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class VelocityException extends Exception {

	private static final long serialVersionUID = 1L;
	
	String messageKey = null;
	Object[] messageArgs = null;
	
	public VelocityException() {
		
	}
	
	public VelocityException(String messageKey) {
		this.messageKey = messageKey;
	}

	public VelocityException(String messageKey, Object... messageArgs) {
		this.messageKey = messageKey;
		this.messageArgs = messageArgs;
	}
	
	public VelocityException(String messageKey, Throwable cause, Object... messageArgs) {
		super(cause);
		this.messageKey = messageKey;
		this.messageArgs = messageArgs;
	}
	
	public VelocityException(Throwable cause) {
		super(cause);
	}
	
	public VelocityException(Throwable cause, Object... messageArgs) {
		super(cause);
		this.messageArgs = messageArgs;
	}
	
	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder();
		String retrievedMessage = "";
		String formattedMsg = "";
		
		if(messageKey != null){
			retrievedMessage = ResourceBundle.getBundle("message")
					.getString(messageKey);
			MessageFormat messageFormatter = new MessageFormat(retrievedMessage);
			
			if(messageArgs == null) {
				formattedMsg = messageFormatter.toString();
			}
			else {
				formattedMsg = messageFormatter.format(messageArgs);
			}
			
			message.append(formattedMsg);	
		}
		
		if(super.getCause() != null) {
			message.append(": ");
			message.append(super.getCause());
		}
		
		
		return message.toString();
		
	}
	
}