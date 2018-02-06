package com.bikeapplication.bean;

public class RentCalculatorBeanClass {
	private int transactionId;
	private int hoursRented;
	private int actualCharge;
	private int penaltyHours;
	private int penaltyCharge;
	private int totalCharge;
	private int advancePaid;
	private int remainingPayment;
	private int remainingRentTime;
	private String registrationNumber;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public int getRemainingRentTime() {
		return remainingRentTime;
	}
	public void setRemainingRentTime(int remainingRentTime) {
		this.remainingRentTime = remainingRentTime;
	}
	public int getHoursRented() {
		return hoursRented;
	}
	public void setHoursRented(int hoursRented) {
		this.hoursRented = hoursRented;
	}
	public int getActualCharge() {
		return actualCharge;
	}
	public void setActualCharge(int actualCharge) {
		this.actualCharge = actualCharge;
	}
	public int getPenaltyHours() {
		return penaltyHours;
	}
	public void setPenaltyHours(int penaltyHours) {
		this.penaltyHours = penaltyHours;
	}
	public int getPenaltyCharge() {
		return penaltyCharge;
	}
	public void setPenaltyCharge(int penaltyCharge) {
		this.penaltyCharge = penaltyCharge;
	}
	public int getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(int totalCharge) {
		this.totalCharge = totalCharge;
	}
	public int getAdvancePaid() {
		return advancePaid;
	}
	public void setAdvancePaid(int advancePaid) {
		this.advancePaid = advancePaid;
	}
	public int getRemainingPayment() {
		return remainingPayment;
	}
	public void setRemainingPayment(int remainingPayment) {
		this.remainingPayment = remainingPayment;
	}
	
	public String toString() {
		if(this.getPenaltyHours() > 0) {
			return "Charge for <b>" + hoursRented + "hours</b> is : <b>" + actualCharge+
					"</b><br/>Penalty for extra time of " + penaltyHours + "hour is : " + penaltyCharge
					+ "\nPenaltycharge is 1.5times of normal charge for exceeding hours."
					+ "The total charge is :" + totalCharge + "\nAdvance paid is : " + advancePaid
					+ "\nThe amount to be paid is : " + remainingPayment ;
		} else {
			return "Charge for " + hoursRented + "hours is : " + actualCharge + "\nYou still have "
					+ this.getRemainingRentTime() + "hour left to return the bike.";
		}
	}
}
