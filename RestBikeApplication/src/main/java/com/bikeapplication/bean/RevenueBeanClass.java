package com.bikeapplication.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RevenueBeanClass {
	private RentBeanClass rentBean;
	private RentCalculatorBeanClass rentCalculatorBean;
	
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
	
}
