package com.bikeapplication.concreteclass;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.bean.ApplicationBeanClass;
import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;
import com.bikeapplication.interfacepackage.ViewRentedBikeInterface;

public class Customer implements ViewRentedBikeInterface {
	
	@Override
	public void viewRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BikeApplicationDao dao = new BikeApplicationDao();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		List<ApplicationBeanClass> appBeanList = dao.userRentedBike(userId);
		request.setAttribute("appBeanList", appBeanList);
	}
	
	@Override
	public String getDispatcherPath() {
		return "jsp/customer/viewrentedbikes.jsp";
	}

}
