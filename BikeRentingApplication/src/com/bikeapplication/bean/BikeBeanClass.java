package com.bikeapplication.bean;

public class BikeBeanClass {
	private int bikeid;
	private String Manufacturer;
	private String bikename;
	private int charge;
	private int availability;

	public int getBikeid() {
		return bikeid;
	}

	public void setBikeid(int bikeid) {
		this.bikeid = bikeid;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getBikename() {
		return bikename;
	}

	public void setBikename(String bikename) {
		this.bikename = bikename;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}
	
	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String toString() {
		return "Bikeid : " + this.getBikeid() + "\nManufacturer : " + this.getManufacturer() + "\nBike Name : "
				+ this.getBikename() + "\nCharge(per hour) : " + this.getCharge() + "\nAvailability : " + this.getAvailability();
	}
}
