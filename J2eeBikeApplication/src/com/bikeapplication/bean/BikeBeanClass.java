package com.bikeapplication.bean;

public class BikeBeanClass {
	private int bikeId;
	private String Manufacturer;
	private String bikeName;
	private int charge;
	private int availability;
	private String registrationNumber;

	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
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
		return "bikeId : " + this.getBikeId() + "\nManufacturer : " + this.getManufacturer() + "\nBike Name : "
				+ this.getBikeName() + "\nCharge(per hour) : " + this.getCharge() + "\nAvailability : " + this.getAvailability();
	}
}
