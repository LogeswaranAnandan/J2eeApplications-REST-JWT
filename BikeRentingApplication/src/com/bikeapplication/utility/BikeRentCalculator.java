package com.bikeapplication.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.bikeapplication.bean.RentBeanClass;

public class BikeRentCalculator {
	Logger logger = Logger.getLogger(BikeRentCalculator.class.getName());

	public void CalculateAmount(RentBeanClass rentbean, int charge) {
		int hours, penaltyhours = 0, totalcharge, penaltycharge, actualcharge;
		hours = calculateHours(rentbean.getRented_datetime());
		if (hours > rentbean.getDuration()) {
			penaltyhours = hours - rentbean.getDuration();
		}
		actualcharge = hours * charge;
		penaltycharge = penaltyhours * charge / 2;
		totalcharge = actualcharge + penaltycharge;
		if (penaltyhours > 0) {
			logger.info("Charge for " + hours + "hours is : " + actualcharge);
			logger.info("Penalty for extra time of " + penaltyhours + "hour is : " + penaltycharge
					+ "\nPenaltycharge is 1.5times of normal charge for exceeding hours.");
			logger.info("The total charge is :" + totalcharge + "\nAdvance paid is : " + rentbean.getAdvancepaid()
					+ "\nThe amount to be paid is : " + (totalcharge - rentbean.getAdvancepaid()));
		} else {
			logger.info("Charge for " + hours + "hours is : " + actualcharge + "\nYou still have "
					+ (rentbean.getDuration() - hours) + "hour left to return the bike.");
		}
	}

	public int calculateHours(String date) {
		Date renteddate = null;
		Date returneddate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			renteddate = dateformat.parse(date);
		} catch (ParseException e) {
			logger.warning("Problem while parsing the date.");
		}
		long diff = returneddate.getTime() - renteddate.getTime();
		int hours = (int) (diff / (60 * 60 * 1000));
		return (hours + 1);
	}
}
