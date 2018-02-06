package com.bikeapplication.factory;

import java.util.logging.Logger;

import com.bikeapplication.interfacepackage.ViewRentedBikeInterface;

public class ViewRentedBikeFactory {
	Logger logger = Logger.getLogger(ViewRentedBikeFactory.class.getName());
	public ViewRentedBikeInterface getInstance(Object userRole) {
		try {
			String qualifiedClassName = "com.bikeapplication.concreteclass." + userRole.toString();
			Class cls = Class.forName(qualifiedClassName);
			return (ViewRentedBikeInterface) cls.newInstance();
		} catch (Exception e) {
			logger.info("Problem while executing factory class");
		}
		return null;
	}
}
