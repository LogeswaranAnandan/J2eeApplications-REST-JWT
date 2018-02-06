package com.bikeapplication.delegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.factory.ViewRentedBikeFactory;
import com.bikeapplication.interfacepackage.ViewRentedBikeInterface;

public class FactoryDelegate {

	public void viewRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		ViewRentedBikeFactory viewRentedBikeFactory = new ViewRentedBikeFactory();
		HttpSession session = request.getSession();
		Object userRole = session.getAttribute("userRole");
		ViewRentedBikeInterface object = viewRentedBikeFactory.getInstance(userRole);
		object.viewRentedBikes(request, response);
		String path = object.getDispatcherPath();
		request.setAttribute("path", path);
	}
}
