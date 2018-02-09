package com.bikeapplication.bean;

public class UserBeanClass {

	private long userid;
	private String username;
	private long addressid;
	private long phonenumber;
	private String licenseno;
	private String userpassword;
	private String userrole;
	private String street;
	private String area;
	private String city;
	private int pincode;

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
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

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String toString() {
		return "\nUser id : " + this.getUserid() + "\nUser Name : " + this.getUsername() + "\nAddress : "
				+ "\n\tStreet : " + this.getStreet() + "\n\tArea : " + this.getArea() + "\n\tCity : " + this.getCity()
				+ "\n\tPincode : " + this.getPincode() + "\nPhone number : " + this.getPhonenumber()
				+ "\nLicense number : " + this.getLicenseno();
	}

}
