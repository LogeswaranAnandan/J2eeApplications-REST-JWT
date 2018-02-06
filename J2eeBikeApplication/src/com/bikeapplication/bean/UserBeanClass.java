package com.bikeapplication.bean;

public class UserBeanClass {

	private long userId;
	private String userName;
	private long addressId;
	private long phoneNumber;
	private String licenseNumber;
	private String userPassword;
	private String userRole;
	private String street;
	private String area;
	private String city;
	private int pincode;



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public long getAddressId() {
		return addressId;
	}



	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}



	public long getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getLicenseNumber() {
		return licenseNumber;
	}



	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getUserRole() {
		return userRole;
	}



	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public int getPincode() {
		return pincode;
	}



	public void setPincode(int pincode) {
		this.pincode = pincode;
	}



	public String toString() {
		return "\nUser id : " + this.getUserId() + "\nUser Name : " + this.getUserName() + "\nAddress : "
				+ "\n\tStreet : " + this.getStreet() + "\n\tArea : " + this.getArea() + "\n\tCity : " + this.getCity()
				+ "\n\tPincode : " + this.getPincode() + "\nPhone number : " + this.getPhoneNumber()
				+ "\nLicense number : " + this.getLicenseNumber();
	}

}
