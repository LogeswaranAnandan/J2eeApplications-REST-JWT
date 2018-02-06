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
import com.bikeapplication.dao.BikeApplicationDao;

public class CustomerDelegate {
	Logger logger = Logger.getLogger(CustomerDelegate.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();

	public List<BikeBeanClass> viewAllBikes() {
		List<BikeBeanClass> bikeBeanList = dao.viewAllBikes();
		return bikeBeanList;
	}
	
	public List<BikeBeanClass> viewAvailableBikes() {
		List<BikeBeanClass> bikeBeanList = dao.viewAvailableBikes();
		return bikeBeanList;
	}
	
	public void rentBike(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		RentBeanClass rentBean = new RentBeanClass();
		HttpSession session = request.getSession();
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
			boolean isSuccessful = dao.rentBike(rentBean);
			if (isSuccessful == true) {
				out.println("<script>alert('Bike is rented successfully')</script>");
			} else {
				out.println(
						"<script>alert('Some problem occurred while renting the bike. Please try again..!')</script>");
			}
		} catch (Exception e) {
			logger.info("problem while creating printwriter in rentbike method");
		}
	}
	
	public void viewUserRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		List<BikeBeanClass> bikeBeanList = dao.userRentedBike(userId);
		List<RentCalculatorBeanClass> rentCalculatorBeanList = new ArrayList<>();
		for (BikeBeanClass bikeBean : bikeBeanList) {
			int bikeId = bikeBean.getBikeId();
			String registrationNumber = bikeBean.getRegistrationNumber();
			RentBeanClass rentBean = dao.getRentDetails(userId, bikeId, registrationNumber);
			RentCalculatorBeanClass rentCalculatorBean = dao.rentCalculator(rentBean, bikeId);
			rentCalculatorBeanList.add(rentCalculatorBean);
		}
		request.setAttribute("bikeBeanList", bikeBeanList);
		request.setAttribute("rentCalculatorBeanList", rentCalculatorBeanList);
	}
	
	public void returnBike(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		int bikeId = Integer.parseInt(request.getParameter("bike-id"));
		String registrationNumber = request.getParameter("registration-number");
		RentCalculatorBeanClass rentCalculatorBean = dao.returnBike(userId, bikeId, registrationNumber);
		request.setAttribute("rentCalculatorBean", rentCalculatorBean);
		request.setAttribute("status", "Return this Bike");
	}
	
	public void viewRemainingDuration(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		int bikeId = Integer.parseInt(request.getParameter("bike-id"));
		String registrationNumber = request.getParameter("registration-number");
		RentBeanClass rentBean = dao.getRentDetails(userId, bikeId, registrationNumber);
		RentCalculatorBeanClass rentCalculatorBean = dao.rentCalculator(rentBean, bikeId);
		request.setAttribute("rentCalculatorBean", rentCalculatorBean);
	}
	
	public void viewRentHistory(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		List<ApplicationBeanClass> appBeanList = dao.viewRentHistory(userId);
		request.setAttribute("appBeanList", appBeanList);
	}
}
