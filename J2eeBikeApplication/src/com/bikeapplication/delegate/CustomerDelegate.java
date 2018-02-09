package com.bikeapplication.delegate;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.bean.ApplicationBeanClass;
import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.dao.BikeApplicationDao;
import com.bikeapplication.utility.HttpRequest;
import com.bikeapplication.utility.ParserClass;

public class CustomerDelegate {
	Logger logger = Logger.getLogger(CustomerDelegate.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();
	HttpRequest httpRequest = new HttpRequest();

	public List<BikeBeanClass> viewAllBikes() {
		ParserClass<BikeBeanClass> parser = new ParserClass<>();
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/bikes");
		List<BikeBeanClass> beanList = parser.toJavaList(responseBody, BikeBeanClass[].class);
		return beanList;
	}
	
	
	public void rentBike(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		RentBeanClass rentBean = new RentBeanClass();
		ParserClass<RentBeanClass> parser = new ParserClass<>();
		HttpSession session = request.getSession();
		String requestUrl = "http://localhost:8080/RestBikeApplication/rest/bikes/rent";
		try {
			PrintWriter out = response.getWriter();
			int userId = Integer.parseInt(session.getAttribute("userId").toString());
			int bikeId = Integer.parseInt(request.getParameter("bike-id"));
			int rentDuration = Integer.parseInt(request.getParameter("rent-duration"));
			int advancePaid = Integer.parseInt(request.getParameter("advance-paid"));
			rentBean.setUserId(userId);
			rentBean.setBikeId(bikeId);
			rentBean.setDuration(rentDuration);
			rentBean.setAdvancePaid(advancePaid);
			String jsonString = parser.toJson(rentBean);
			String bufferContent = httpRequest.doPost(requestUrl, jsonString, Constants.PLAIN_TEXT);
			if (bufferContent.equals("true")) {
				out.println("<script>alert('Bike is rented successfully')</script>");
			} else {
				out.println("<script>alert('Some problem occurred while renting the bike. Please try again..!')</script>");
			}	
		} catch (Exception e) {
			logger.info("problem while creating printwriter in rentbike method");
		}
	}
	
	public void viewUserRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/rented/user/" + userId);
		ParserClass<ApplicationBeanClass> parser = new ParserClass<>();
		List<ApplicationBeanClass> appBeanList = parser.toJavaList(responseBody, ApplicationBeanClass[].class);
		request.setAttribute("appBeanList", appBeanList);
	}
	
	public void returnBike(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		int bikeId = Integer.parseInt(request.getParameter("bike-id"));
		
		ParserClass<RentCalculatorBeanClass> parser = new ParserClass<>();
		String registrationNumber = request.getParameter("registration-number");
		registrationNumber = registrationNumber.replaceAll(" ", "%20");
		String requestUrl = "http://localhost:8080/RestBikeApplication/rest/bikes/return/" + registrationNumber +"?bikeid="
				+ bikeId + "&userid=" + userId;
		String requestBody = "";
		String acceptType = "application/json";
		String responseBody = httpRequest.doPost(requestUrl, requestBody, acceptType);
		RentCalculatorBeanClass rentCalculatorBean = parser.toJava(responseBody, RentCalculatorBeanClass.class); 
		System.out.println("Rent calculator bean = " + rentCalculatorBean.toString());
		request.setAttribute("rentCalculatorBean", rentCalculatorBean);
		request.setAttribute("status", "Return this Bike");
	}
	
	public void viewRentHistory(HttpServletRequest request, HttpServletResponse response) {
		ParserClass<ApplicationBeanClass> parser = new ParserClass<>();
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/rented/history/" + userId);
		List<ApplicationBeanClass> appBeanList = parser.toJavaList(responseBody, ApplicationBeanClass[].class);
		request.setAttribute("appBeanList", appBeanList);
		
		/*
		ParserClass<BikeBeanClass> parser = new ParserClass<>();
		String responseBody = httpRequest.doGet("http://localhost:8080/RestBikeApplication/rest/bikes");
		List<BikeBeanClass> beanList = parser.toJavaList(responseBody, BikeBeanClass[].class);
		return beanList;
		*/
	}
}
