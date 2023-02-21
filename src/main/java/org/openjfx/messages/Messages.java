package org.openjfx.messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	private static final String BUNDLE_NAME = "messages"; // name of properties file
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	public Messages() {}
	
	public static boolean containsKey(String messageKey) {
		return RESOURCE_BUNDLE.containsKey(messageKey);
	}
	
	public static String getString(String messageKey, Object... args) {
		try {
			String message = RESOURCE_BUNDLE.getString(messageKey);
			return MessageFormat.format(message, args);
		} catch(MissingResourceException e) {
			return '|' + messageKey + '|';
		}
	}

}
