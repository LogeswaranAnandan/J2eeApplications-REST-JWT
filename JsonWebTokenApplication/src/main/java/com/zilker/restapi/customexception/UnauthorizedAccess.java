package com.zilker.restapi.customexception;

import java.util.logging.Logger;

public class UnauthorizedAccess extends Exception {

	public UnauthorizedAccess() {
		Logger.getLogger(UnauthorizedAccess.class.getName()).warning("User is not Authorized for this resource");
	}
}
