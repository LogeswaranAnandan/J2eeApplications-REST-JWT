package com.bikeapplication.delegate;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.utility.HttpRequest;
import com.bikeapplication.utility.ParserClass;

public class AdminDelegate {
	Logger logger = Logger.getLogger(AdminDelegate.class.getName());
	HttpRequest httpRequest = new HttpRequest();
	
	public void viewAllBikes(HttpServletRequest request, HttpServletResponse response) {
		ParserClass<BikeBeanClass> parser = new ParserClass<>();
		HttpSession session = request.getSession();
		String jwt = session.getAttribute("jwt").toString();
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/bikes", jwt);
		if (responseBody == null) {
			request.setAttribute("Authorization", "Unauthorized");
			return;
		}
		List<BikeBeanClass> beanList = parser.toJavaList(responseBody, BikeBeanClass[].class);
		request.setAttribute("bikeBeanList", beanList);
		
		/*String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/bikes", jwt);
		if(responseBody == null) {
			request.setAttribute("Authorization", "Unauthorized");
			return null;
		}
		List<BikeBeanClass> beanList = parser.toJavaList(responseBody, BikeBeanClass[].class);*/
	}
	
	public void viewAllRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		ParserClass<RentBeanClass> parser = new ParserClass<>();
		HttpSession session = request.getSession();
		String jwt = session.getAttribute("jwt").toString();
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/rented/allbikes", jwt);
		if (responseBody == null) {
			request.setAttribute("Authorization", "Unauthorized");
			return;
		}
		List<RentBeanClass> rentBeanList = parser.toJavaList(responseBody, RentBeanClass[].class);
		request.setAttribute("rentBeanList", rentBeanList);
	}
	
	public void viewAllUsers(HttpServletRequest request, HttpServletResponse response) {
		ParserClass<UserBeanClass> parser = new ParserClass<>();
		HttpSession session = request.getSession();
		String jwt = session.getAttribute("jwt").toString();
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/users", jwt);
		if (responseBody == null) {
			request.setAttribute("Authorization", "Unauthorized");
			return;
		}
		List<UserBeanClass> userBeanList = parser.toJavaList(responseBody, UserBeanClass[].class);
		request.setAttribute("userBeanList", userBeanList);
	}
	
	public void addNewRegistrationNumber(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String jwt = session.getAttribute("jwt").toString();
		int bikeId = Integer.parseInt(request.getParameter("bikename"));
		String registrationNumber = request.getParameter("registration-number");
		registrationNumber = registrationNumber.replaceAll(" ", "%20");
		String requestUrl = "http://localhost:8080/RestBikeApplication/rest/bikes/addbike/" + bikeId + "/" + registrationNumber;
		logger.info("url = " + requestUrl);
		String requestBody = "";
		String acceptType = "text/plain";
		String responseBody = httpRequest.doPost(requestUrl, requestBody, acceptType, jwt);
		logger.info("Response body = " + responseBody);
		if (responseBody == null) {
			request.setAttribute("Authorization", "Unauthorized");
			return;
		} else if (responseBody.equals("true")) {
			logger.info("Bike is added successfully");
		} else {
			logger.warning("Problem occurred while adding the bike");
		}
	}
	
	
}
