package com.bikeapplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.utility.BikeRentCalculator;
import com.bikeapplication.utility.DatabaseConnection;

public class BikeApplicationDao {
	DatabaseConnection dbconnection = new DatabaseConnection();
	Logger logger = Logger.getLogger(BikeApplicationDao.class.getName());
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	// Executes a SELECT QUERY a returns a ResultSet
	public PreparedStatement selectQueryExecutor(String query) {
		try {
			connection = dbconnection.getConnection();
			statement = connection.prepareStatement(query);
			return statement;
		} catch (SQLException e) {
			logger.warning("Problem while accessing the database");
		}
		return statement;
	}

	// Executes a INSERT or UPDATE or DELETE QUERY and returns rows affected
	public PreparedStatement queryExecutor(String query) {
		try {
			connection = dbconnection.getConnection();
			statement = connection.prepareStatement(query);
			return statement;
		} catch (SQLException e) {
			logger.warning("Problem while inserting the values");
		}
		return statement;
	}

	// Method to close a connection after user exit
	public void closeConnection(ResultSet result, Statement statement, Connection connection) {
		dbconnection.closeConnection(result, statement, connection);
	}

	// Method to fetch customer name using customer id
	public String getUserName(int userid) {
		String query = Constants.GET_USER_NAME;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, userid);
			result = statement.executeQuery();
			if (result.next()) {
				return result.getString(1);
			}
		} catch (SQLException e) {
			logger.warning("Problem while retrieving user name");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	// Method to get the availability of bike
	public int fetchAvailability(int bikeid) {
		String query = Constants.GET_AVAILABILITY;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, bikeid);
			result = statement.executeQuery();
			if (result.next()) {
				return result.getInt("availability");
			}
		} catch (SQLException e) {
			logger.warning("Problem while fetching the availability");
		} finally {
			closeConnection(result, statement, connection);
		}
		return 0;
	}

	public void decreaseAvailability(int bikeid) {
		String query = Constants.ALTER_AVAILABILITY;
		int temp = fetchAvailability(bikeid) - 1;
		PreparedStatement statement = queryExecutor(query);
		try {
			statement.setInt(1, temp);
			statement.setInt(2, bikeid);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while decreasing availability");
		}
	}

	public void increaseAvailability(int bikeid) {
		String query = Constants.ALTER_AVAILABILITY;
		int temp = fetchAvailability(bikeid) + 1;
		PreparedStatement statement = queryExecutor(query);
		try {
			statement.setInt(1, temp);
			statement.setInt(2, bikeid);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while decreasing availability");
		}
	}

	// Method to display all bikes in bikedetails table
	public List<BikeBeanClass> viewAllBike() {
		String query = Constants.ALL_BIKES;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<BikeBeanClass> bikebeanlist = new ArrayList<>();
		try {
			result = statement.executeQuery();
			while (result.next()) {
				BikeBeanClass bikebean = new BikeBeanClass();
				bikebean.setBikeid(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikename(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setAvailability(result.getInt(5));
				bikebeanlist.add(bikebean);
			}
			return bikebeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	// Method to display only the available bikes in bikedetails table
	public List<BikeBeanClass> viewAvailableBikes() {
		String query = Constants.AVAILABLE_BIKES;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<BikeBeanClass> bikebeanlist = new ArrayList<>();
		try {
			result = statement.executeQuery();
			while (result.next()) {
				BikeBeanClass bikebean = new BikeBeanClass();
				bikebean.setBikeid(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikename(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setAvailability(result.getInt(5));
				bikebeanlist.add(bikebean);
			}
			return bikebeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	// Method to rent a bike and insert the value into rentdetails table
	public void rentBike(RentBeanClass rentbean) {
		String registrationNumber = fetchRegistrationNumber(rentbean.getBikeid());
		rentbean.setRegistrationNumber(registrationNumber);
		String query = Constants.RENT_BIKE;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setInt(1, Constants.USERID);
			statement.setInt(2, rentbean.getBikeid());
			statement.setString(3, rentbean.getRegistrationNumber());
			statement.setInt(4, rentbean.getDuration());
			statement.setInt(5, rentbean.getDuration());
			statement.setInt(6, rentbean.getBikeid());
			statement.setInt(7, rentbean.getAdvancepaid());
			rowsaffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while inserting the rented bike");
		}
		if (rowsaffected > 0) {
			logger.info("Bike is rented successfully");
		} else {
			logger.warning("Please enter valid credentials and try again");
			return;
		}
		decreaseAvailability(rentbean.getBikeid());
		setBikeStatusRented(rentbean.getRegistrationNumber());
	}

	public String fetchRegistrationNumber(int bikeid) {
		String query = Constants.GET_REGISTRATION_NUMBER;
		String registrationNumber = null;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, bikeid);
			result = statement.executeQuery();
			if (result.next()) {
				registrationNumber = result.getString(1);
			}
		} catch (Exception e) {
			logger.warning("problem while fetching registration number");
		}
		return registrationNumber;
	}

	public void setBikeStatusRented(String registrationNumber) {
		String query = Constants.BIKE_STATUS_RENTED;
		PreparedStatement statement = queryExecutor(query);
		try {
			statement.setString(1, registrationNumber);
			statement.executeUpdate();
		} catch (Exception e) {
			logger.warning("updating status failed");
		}
	}
	
	public void setBikeStatusReturned(String registrationNumber) {
		String query = Constants.BIKE_STATUS_RETURNED;
		PreparedStatement statement = queryExecutor(query);
		try {
			statement.setString(1, registrationNumber);
			statement.executeUpdate();
		} catch (Exception e) {
			logger.warning("updating status as available failed");
		}
	}
	
	public void userRentedBike(int userid) {
		String query = Constants.USER_RENTED_BIKE;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, userid);
			result = statement.executeQuery();
			while (result.next()) {
				logger.info("\nRegistration Number : " + result.getString(1) + "\nManufacturer : " + result.getString(2)
						+ "\nBike Name : " + result.getString(3) + "\nCharge : " + result.getInt(4));
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes rented by this user");
		} finally {
			closeConnection(result, statement, connection);
		}
	}
	
	public int fetchBikeId(String registrationNumber) {
		String query = Constants.GET_BIKEID;
		int bikeid = 0;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setString(1, registrationNumber);
			result = statement.executeQuery();
			if(result.next()) {
				bikeid = result.getInt(1);
			}
		} catch (Exception e) {
			logger.warning("problem occurred while fetching the bike id");
		}
		return bikeid;
	}

	public RentBeanClass calculateRentAmount(String registrationNumber) {
		int bikeid = fetchBikeId(registrationNumber);
		String query = Constants.RETURN_BIKE;
		RentBeanClass rentbean = new RentBeanClass();
		BikeRentCalculator rentCalculator = new BikeRentCalculator();
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, Constants.USERID);
			statement.setString(2, registrationNumber);
			result = statement.executeQuery();
			if (result.next()) {
				rentbean.setTransacationid(result.getInt(1));
				rentbean.setUserid(result.getInt(2));
				rentbean.setBikeid(result.getInt(3));
				rentbean.setRegistrationNumber(result.getString(4));
				rentbean.setRented_datetime(result.getString(5));
				rentbean.setDuration(result.getInt(6));
				rentbean.setEstimatedamount(result.getInt(7));
				rentbean.setAdvancepaid(result.getInt(8));
				rentbean.setStatus(result.getString(9));
			}
		} catch (SQLException e) {
			logger.warning("Problem while returning the bike");
		} finally {
			closeConnection(result, statement, connection);
		}
		rentCalculator.CalculateAmount(rentbean, getBikeDetails(bikeid).getCharge());
		return rentbean;
	}

	public void returnBike(String registrationNumber) {
		try {
			RentBeanClass rentbean = calculateRentAmount(registrationNumber);
			updateRentStatus(rentbean.getTransacationid(), rentbean.getBikeid());
			setBikeStatusReturned(registrationNumber);
		} catch (Exception e) {
			logger.warning("Problem while returning the bike");
		}
	}

	public void updateRentStatus(int transactionid, int bikeid) {
		String query = Constants.UPDATE_RENT_STATUS;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setInt(1, transactionid);
			rowsaffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while updating status in rentdetails table");
		}
		if (rowsaffected > 0) {
			logger.info("Bike is returned successfully");
			increaseAvailability(bikeid);
		} else {
			logger.warning("Please enter valid credentials and try again");
			return;
		}
	}

	public boolean userLogin(String username, String password) {
		String query = "";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			long phonenumber = Long.parseLong(username);
			query = Constants.USER_LOGIN_PHONENUMBER;
			statement = selectQueryExecutor(query);
			statement.setLong(1, phonenumber);
			statement.setString(2, password);
		} catch (Exception e) {
			query = Constants.USER_LOGIN_USERNAME;
			try {
				statement = selectQueryExecutor(query);
				statement.setString(1, username);
				statement.setString(2, password);
			} catch (SQLException ex) {
				logger.warning("Problem during user login");
			}
		}
		try {
			result = statement.executeQuery();
			if (result.next()) {
				int userid = result.getInt(1);
				Constants.setUSERID(userid);
				return true;
			} else {
				logger.warning("Incorrect username and password. Please try again");
				return false;
			}
		} catch (SQLException e) {
			logger.warning("Please enter a valid username and password");
		} finally {
			closeConnection(result, statement, connection);
		}
		return false;
	}

	public boolean userSignup(UserBeanClass userbean) {
		String query = Constants.USER_SIGNUP;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		boolean flag = false;
		userbean.setAddressid(System.currentTimeMillis() / 1000 % 1000);
		try {
			statement.setLong(1, (System.currentTimeMillis() % 1000));
			statement.setString(2, userbean.getUsername());
			statement.setLong(3, userbean.getAddressid());
			statement.setLong(4, userbean.getPhonenumber());
			statement.setString(5, userbean.getLicenseno());
			statement.setString(6, userbean.getUserpassword());
			rowsaffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while registering the user details");
		} finally {
			closeConnection(result, statement, connection);
		}
		flag = userSignupAddress(userbean);
		if ((rowsaffected > 0) && (flag == true)) {
			return true;
		}
		return false;
	}

	public boolean userSignupAddress(UserBeanClass userbean) {
		String query = Constants.USER_SIGNUP_ADDRESS;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setLong(1, userbean.getAddressid());
			statement.setString(2, userbean.getStreet());
			statement.setString(3, userbean.getArea());
			statement.setString(4, userbean.getCity());
			statement.setInt(5, userbean.getPincode());
			rowsaffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while registering the user details");
		} finally {
			closeConnection(result, statement, connection);
		}
		if (rowsaffected > 0) {
			return true;
		}
		return false;
	}

	public List<RentBeanClass> viewRentedBikes() {
		String query = Constants.VIEW_RENTED_BIKES;
		ResultSet result = null;
		List<RentBeanClass> rentbeanlist = new ArrayList<>();
		try {
			PreparedStatement statement = selectQueryExecutor(query);
			result = statement.executeQuery();
			while (result.next()) {
				RentBeanClass rentbean = new RentBeanClass();
				rentbean.setUserid(result.getInt(1));
				rentbean.setBikeid(result.getInt(2));
				rentbean.setRented_datetime(result.getString(3));
				rentbean.setDuration(result.getInt(4));
				rentbean.setEstimatedamount(result.getInt(5));
				rentbean.setAdvancepaid(result.getInt(6));
				rentbeanlist.add(rentbean);
			}
			return rentbeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	public BikeBeanClass getBikeDetails(int bikeid) {
		BikeBeanClass bikebean = new BikeBeanClass();
		String query = Constants.GET_BIKE_DETAILS;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, bikeid);
			result = statement.executeQuery();
			if (result.next()) {
				bikebean.setBikeid(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikename(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setAvailability(result.getInt(5));
				return bikebean;
			}
		} catch (SQLException e) {
			logger.warning("Problem occurred while fetching the bike details");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;
	}

	public boolean addNewBike(BikeBeanClass bikebean) {
		String query = Constants.ADD_NEW_BIKE;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setInt(1, bikebean.getBikeid());
			statement.setString(2, bikebean.getManufacturer());
			statement.setString(3, bikebean.getBikename());
			statement.setInt(4, bikebean.getCharge());
			statement.setInt(5, bikebean.getAvailability());
			rowsaffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while adding new bike");
		} finally {
			closeConnection(result, statement, connection);
		}
		if (rowsaffected > 0) {
			return true;
		}
		return false;
	}

	public boolean adminLogin(String username, String password) {
		String query = "";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			long phonenumber = Long.parseLong(username);
			query = Constants.ADMIN_LOGIN_PHONENUMBER;
			statement = selectQueryExecutor(query);
			statement.setLong(1, phonenumber);
			statement.setString(2, password);

		} catch (Exception e) {
			try {
				query = Constants.ADMIN_LOGIN_USERNAME;
				statement = selectQueryExecutor(query);
				statement.setString(1, username);
				statement.setString(2, password);
			} catch (SQLException ex) {
				logger.warning("Problem during admin login");
			}
		}
		try {
			result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.warning("Problem while validating the admin");
		} finally {
			closeConnection(result, statement, connection);
		}
		return false;
	}

	public List<UserBeanClass> viewAllUsers() {
		String query = Constants.VIEW_USER_DETAILS;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<UserBeanClass> userbeanlist = new ArrayList<>();
		try {
			result = statement.executeQuery();
			while (result.next()) {
				UserBeanClass userbean = new UserBeanClass();
				userbean.setUserid(result.getInt(1));
				userbean.setUsername(result.getString(2));
				userbean.setAddressid(result.getInt(3));
				userbean.setPhonenumber(result.getLong(4));
				userbean.setLicenseno(result.getString(5));
				userbean.setStreet(result.getString(9));
				userbean.setArea(result.getString(10));
				userbean.setCity(result.getString(11));
				userbean.setPincode(result.getInt(12));
				userbeanlist.add(userbean);
			}
			return userbeanlist;
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return null;

	}
}
