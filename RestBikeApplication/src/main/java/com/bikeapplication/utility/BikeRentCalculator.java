package com.bikeapplication.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;

public class BikeRentCalculator {
	Logger logger = Logger.getLogger(BikeRentCalculator.class.getName());

	public RentCalculatorBeanClass CalculateAmount(RentBeanClass rentBean, int charge) {
		RentCalculatorBeanClass rentCalculatorBean = new RentCalculatorBeanClass();
		int hoursRented, penaltyHours = 0, totalCharge, penaltyCharge, actualCharge;
		int advancePaid = rentBean.getAdvancePaid();
		hoursRented = calculateHours(rentBean.getRentedDateTime());
		if (hoursRented > rentBean.getDuration()) {
			penaltyHours = hoursRented - rentBean.getDuration();
		}
		actualCharge = hoursRented * charge;
		penaltyCharge = penaltyHours * charge / 2;
		totalCharge = actualCharge + penaltyCharge;
		if (penaltyHours > 0) {
			int remainingPayment = totalCharge - rentBean.getAdvancePaid();
			rentCalculatorBean.setHoursRented(hoursRented);
			rentCalculatorBean.setActualCharge(actualCharge);
			rentCalculatorBean.setPenaltyHours(penaltyHours);
			rentCalculatorBean.setPenaltyCharge(penaltyCharge);
			rentCalculatorBean.setTotalCharge(totalCharge);
			rentCalculatorBean.setAdvancePaid(advancePaid);
			rentCalculatorBean.setRemainingPayment(remainingPayment);
		} else {			
			int remainingRentTime = rentBean.getDuration() - hoursRented;
			int remainingPayment = actualCharge - advancePaid;
			rentCalculatorBean.setHoursRented(hoursRented);
			rentCalculatorBean.setActualCharge(actualCharge);
			rentCalculatorBean.setPenaltyHours(0);
			rentCalculatorBean.setPenaltyCharge(0);
			rentCalculatorBean.setTotalCharge(actualCharge);
			rentCalculatorBean.setRemainingRentTime(remainingRentTime);
			rentCalculatorBean.setAdvancePaid(advancePaid);
			rentCalculatorBean.setRemainingPayment(remainingPayment);
		}
		rentCalculatorBean.setRegistrationNumber(rentBean.getRegistrationNumber());
		rentCalculatorBean.setTransactionId(rentBean.getTransactionId());
		return rentCalculatorBean;
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
