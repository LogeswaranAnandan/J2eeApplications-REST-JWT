package com.bikeapplication.interfacepackage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewRentedBikeInterface {

	public void viewRentedBikes(HttpServletRequest request, HttpServletResponse response);
	public String getDispatcherPath();
}
