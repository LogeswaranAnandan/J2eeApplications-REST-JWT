package com.bikeapplication.concreteclass;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;
import com.bikeapplication.interfacepackage.ViewRentedBikeInterface;

public class Admin implements ViewRentedBikeInterface {

	@Override
	public void viewRentedBikes(HttpServletRequest request, HttpServletResponse response) {
		BikeApplicationDao dao = new BikeApplicationDao();
		List<RentBeanClass> rentBeanList = dao.viewAllRentedBikes();
		request.setAttribute("rentBeanList", rentBeanList);
	}
	
	@Override
	public String getDispatcherPath() {
		return "jsp/admin/viewrentedbikes.jsp";
	}
}
