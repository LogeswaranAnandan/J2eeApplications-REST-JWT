package com.bikeapplication.customexception;

import java.util.logging.Logger;

public class UnauthorizedAccessException extends Exception {

	public UnauthorizedAccessException() {
		Logger.getLogger(UnauthorizedAccessException.class.getName()).warning("User is not Authorized to access this resource");
	}
}
