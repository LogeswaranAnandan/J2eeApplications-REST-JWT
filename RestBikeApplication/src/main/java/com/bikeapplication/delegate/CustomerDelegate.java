package com.bikeapplication.delegate;

import java.util.List;
import java.util.logging.Logger;

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
	
	public boolean rentBike(RentBeanClass rentBean) {
		return dao.rentBike(rentBean);
	}
	
	public List<ApplicationBeanClass> viewUserRentedBikes(int userId) {
		return dao.userRentedBike(userId);
	}
	
	public RentCalculatorBeanClass returnBike(int userId, int bikeId, String registrationNumber) {
		return dao.returnBike(userId, bikeId, registrationNumber);
	}

	public List<ApplicationBeanClass> viewRentHistory(int userId) {
		return dao.viewRentHistory(userId);
	}

	public List<RentBeanClass> viewAllRentedBikes() {
		return dao.viewAllRentedBikes();
	}
}
