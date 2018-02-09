package com.bikeapplication.bean;

public class RentBeanClass {
	private int transacationid;
	private int userid;
	private int bikeid;
	private String registrationNumber;
	private String rented_datetime;
	private int duration;
	private int estimatedamount;
	private int advancepaid;
	private String status;
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRented_datetime() {
		return rented_datetime;
	}

	public void setRented_datetime(String rented_datetime) {
		this.rented_datetime = rented_datetime;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getEstimatedamount() {
		return estimatedamount;
	}

	public void setEstimatedamount(int estimatedamount) {
		this.estimatedamount = estimatedamount;
	}

	public int getBikeid() {
		return bikeid;
	}

	public void setBikeid(int bikeid) {
		this.bikeid = bikeid;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAdvancepaid() {
		return advancepaid;
	}

	public void setAdvancepaid(int advancepaid) {
		this.advancepaid = advancepaid;
	}

	public int getTransacationid() {
		return transacationid;
	}

	public void setTransacationid(int transacationid) {
		this.transacationid = transacationid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "TransactionId : " + this.getTransacationid() + "\nUserId : " + this.getUserid() + "\nBikeId : " + this.getBikeid() + "\nRented datetime : "
				+ this.getRented_datetime() + "\nDuration : " + this.getDuration() + "\nEstimated Payment : "
				+ this.getEstimatedamount() + "\nAdvance Paid : " + this.getAdvancepaid() + "\nPending amount : "
				+ (this.getEstimatedamount() - this.getAdvancepaid()) + "\nStatus : " + this.getStatus();
	}

}
