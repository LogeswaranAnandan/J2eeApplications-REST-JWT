package com.bikeapplication.bean;

public class RentBeanClass {
	private int transactionId;
	private int userId;
	private int bikeId;
	private String registrationNumber;
	private String rentedDateTime;
	private int duration;
	private int estimatedAmount;
	private int advancePaid;
	private String status;
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public String getRentedDateTime() {
		return rentedDateTime;
	}

	public void setRentedDateTime(String rentedDateTime) {
		this.rentedDateTime = rentedDateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(int estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public int getAdvancePaid() {
		return advancePaid;
	}

	public void setAdvancePaid(int advancePaid) {
		this.advancePaid = advancePaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "TransactionId : " + this.getTransactionId() + "\nUserId : " + this.getUserId() + "\nBikeId : "
				+ this.getBikeId() + "\nRented datetime : " + this.getRentedDateTime() + "\nDuration : "
				+ this.getDuration() + "\nEstimated Payment : " + this.getEstimatedAmount() + "\nAdvance Paid : "
				+ this.getAdvancePaid() + "\nPending amount : " + (this.getEstimatedAmount() - this.getAdvancePaid())
				+ "\nStatus : " + this.getStatus();
	}

}
