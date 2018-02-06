package com.bikeapplication.delegate;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;

public class AdminDelegate {
	Logger logger = Logger.getLogger(AdminDelegate.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();
	
	public void viewAllBikes(HttpServletRequest request, HttpServletResponse response) {
		List<BikeBeanClass> bikeBeanList = dao.viewAllBikes();
		request.setAttribute("bikeBeanList", bikeBeanList);
	}
	
	public void viewAllRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		List<RentBeanClass> rentBeanList = dao.viewAllRentedBikes();
		request.setAttribute("rentBeanList", rentBeanList);
	}
	
	public void viewAllUsers(HttpServletRequest request, HttpServletResponse response) {
		List<UserBeanClass> userBeanList = dao.viewAllUsers();
		request.setAttribute("userBeanList", userBeanList);
	}
	
	public void addNewRegistrationNumber(HttpServletRequest request, HttpServletResponse response) {
		int bikeId = Integer.parseInt(request.getParameter("bikename"));
		String registrationNumber = request.getParameter("registration-number");
		dao.addNewRegistrationNumber(bikeId, registrationNumber);
	}
	
	
}
