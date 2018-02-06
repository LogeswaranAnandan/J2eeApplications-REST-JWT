package com.bikeapplication.bean;

public class ApplicationBeanClass {
	private BikeBeanClass bikeBean;
	private RentBeanClass rentBean;
	private RentCalculatorBeanClass rentCalculatorBean;
	private UserBeanClass userBean;
	
	
	public BikeBeanClass getBikeBean() {
		return bikeBean;
	}
	public void setBikeBean(BikeBeanClass bikeBean) {
		this.bikeBean = bikeBean;
	}
	public RentBeanClass getRentBean() {
		return rentBean;
	}
	public void setRentBean(RentBeanClass rentBean) {
		this.rentBean = rentBean;
	}
	public RentCalculatorBeanClass getRentCalculatorBean() {
		return rentCalculatorBean;
	}
	public void setRentCalculatorBean(RentCalculatorBeanClass rentCalculatorBean) {
		this.rentCalculatorBean = rentCalculatorBean;
	}
	public UserBeanClass getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBeanClass userBean) {
		this.userBean = userBean;
	}
}