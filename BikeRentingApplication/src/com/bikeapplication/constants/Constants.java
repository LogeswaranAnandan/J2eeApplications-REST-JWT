package com.bikeapplication.constants;

public class Constants {
	public static int USERID;
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/bikerentingapplication";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "zilkeradmin";
	public static final String CHOOSE_ROLE = "Choose your role\n 1.User\n 2.Admin\n Enter your choice : ";
	public static final String TYPE_OF_USER = "\n 1.Existing user\n 2.New User\n Enter your choice : ";
	public static final String INVALID_CHOICE = "Invalid choice. Please try again with a valid choice";
	public static final String INVALID_DATA = "Invalid data. Please try again with valid data";
	public static final String AVAILABLE_BIKES = "select * from bikedetails where availability > 0";
	public static final String ALL_BIKES = "select * from bikedetails";
	public static final String USERMENU = "Menu\n 1. View all bikes\n 2. View available bikes\n 3. Rent available bike\n "
			+ "4. Return a rented bike\n 5. View remaining rent timing\n 6. Exit\n Enter your choice : ";
	public static final String ADMINMENU = "\nMenu\n 1.View all Bikes\n 2.View all rented bikes\n 3.Add a new bike\n"
			+ " 4.View all customers\n 5.Exit\n Enter you choice : ";
	public static final String VIEW_USER_DETAILS = "select * from userdetails inner join addressdetails where "
			+ "userdetails.addressid = addressdetails.addressid AND userdetails.userrole = 'Customer'";
	public static final String GET_USER_NAME = "select username from userdetails where userid = ?;";
	public static final String GET_AVAILABILITY = "select availability from bikedetails where bikeid = ?";
	public static final String GET_REGISTRATION_NUMBER = "select registrationnumber from bikeregistrationdetails where bikeid = ? and status = 'Available' limit 1";
	public static final String RENT_BIKE = "insert into rentdetails(`userid`, `bikeid`,`registrationnumber`, `rented_datetime`, `duration`, `estimatedamount`, `advancepaid`, `status`)"
			+ " values(?,?,?,current_timestamp(),?,? * (select charge from bikedetails where bikeid = ?),?,'Rented');";
	public static final String BIKE_STATUS_RENTED = "update bikeregistrationdetails set status = 'Rented' where registrationnumber = ?";
	public static final String BIKE_STATUS_RETURNED = "update bikeregistrationdetails set status = 'Available' where registrationnumber = ?";
	public static final String RETURN_BIKE = "Select * from rentdetails where userid = ? AND registrationnumber = ? AND status = 'Rented'";

	public static final String UPDATE_RENT_STATUS = "Update rentdetails SET status = 'Returned' where transactionid = ?";
	public static final String USER_LOGIN_PHONENUMBER = "select userid from userdetails where phonenumber = ? "
			+ "AND userpassword = ? AND userrole = 'Customer';";
	public static final String USER_LOGIN_USERNAME = "select userid from userdetails where username = ? AND "
			+ "userpassword = ? AND userrole = 'Customer';";
	public static final String USER_SIGNUP = "insert into userdetails values(?,?,?,?,?,?,'Customer')";
	public static final String USER_SIGNUP_ADDRESS = "insert into addressdetails values(?,?,?,?,?)";
	public static final String VIEW_RENTED_BIKES = "select * from rentdetails";
	public static final String GET_BIKE_DETAILS = "select * from bikedetails where bikeid = ?";
	public static final String ADD_NEW_BIKE = "insert into bikedetails values(?,?,?,?,?)";
	public static final String ADMIN_LOGIN_PHONENUMBER = "select userid from userdetails where phonenumber = ? AND"
			+ " userpassword = ? AND userrole = 'Admin';";
	public static final String ADMIN_LOGIN_USERNAME = "select userid from userdetails where username = ? AND"
			+ " userpassword = ? AND userrole = 'Admin';";
	public static final String ALTER_AVAILABILITY = "update bikedetails set availability = ?  where bikeid = ?";

	public static void setUSERID(int USERID) {
		Constants.USERID = USERID;
	}

	public static int getUSERID() {
		return Constants.USERID;
	}
	
	public static final String USER_RENTED_BIKE = "select registrationnumber,manufacturer,bikename,charge "
			+ "from bikedetails inner join rentdetails where "
			+ "bikedetails.bikeid = rentdetails.bikeid AND userid = ? AND "
			+ "rentdetails.status = 'Rented';";
	
	public static final String GET_BIKEID = "select bikeid from bikeregistrationdetails where registrationnumber = ?";

}
