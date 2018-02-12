package com.bikeapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bikeapplication.bean.ApplicationBeanClass;
import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.RentCalculatorBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.utility.BikeRentCalculator;
import com.bikeapplication.utility.DatabaseConnection;

public class BikeApplicationDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	DatabaseConnection dbconnection = new DatabaseConnection();
	Logger logger = Logger.getLogger(BikeApplicationDao.class.getName());
	
	// Executes a SELECT QUERY a returns a ResultSet
	public PreparedStatement selectQueryExecutor(String query) {
		try {
			connection = dbconnection.getConnection();
			statement = connection.prepareStatement(query);
			return statement;
		} catch (Exception e) {
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
	
	// Method to get the availability of bike
	public int fetchAvailability(int bikeid) {
		int availability = 0;
		String query = Constants.GET_AVAILABILITY;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, bikeid);
			result = statement.executeQuery();
			if (result.next()) {
				availability = result.getInt("availability");
			}
		} catch (SQLException e) {
			logger.warning("Problem while fetching the availability");
		} finally {
			closeConnection(result, statement, connection);
		}
		return availability;
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
		} finally {
			closeConnection(result, statement, connection);
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
		} finally {
			closeConnection(result, statement, connection);
		}
	}
	
	public void setBikeStatusRented(String registrationNumber) {
		String query = Constants.BIKE_STATUS_RENTED;
		PreparedStatement statement = queryExecutor(query);
		try {
			statement.setString(1, registrationNumber);
			statement.executeUpdate();
		} catch (Exception e) {
			logger.warning("updating status failed");
		} finally {
			closeConnection(result, statement, connection);
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
		} finally {
			closeConnection(result, statement, connection);
		}
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
				bikebean.setBikeId(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikeName(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setAvailability(result.getInt(5));
			}
		} catch (SQLException e) {
			logger.warning("Problem occurred while fetching the bike details");
		} finally {
			closeConnection(result, statement, connection);
		}
		return bikebean;
	}
	
	public Long userSignUp(UserBeanClass userbean) {
		String query = Constants.USER_SIGNUP;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		boolean flag = false;
		Long userId = System.currentTimeMillis() % 1000;
		userbean.setAddressId(System.currentTimeMillis() / 1000 % 1000);
		try {
			statement.setLong(1, userId);
			statement.setString(2, userbean.getUserName());
			statement.setLong(3, userbean.getAddressId());
			statement.setLong(4, userbean.getPhoneNumber());
			statement.setString(5, userbean.getLicenseNumber());
			statement.setString(6, userbean.getUserPassword());
			rowsaffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while registering the user details");
		} finally {
			closeConnection(result, statement, connection);
		}
		flag = userSignUpAddress(userbean);
		if ((rowsaffected > 0) && (flag == true)) {
			return userId;
		}
		return (long) 0;
	}
	
	public boolean userSignUpAddress(UserBeanClass userbean) {
		String query = Constants.USER_SIGNUP_ADDRESS;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setLong(1, userbean.getAddressId());
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

	public UserBeanClass userLogin(String username, String password) {
		UserBeanClass userBean = new UserBeanClass();
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
				int userId = result.getInt(1);
				String userName = result.getString(2);
				String userRole = result.getString(3);
				userBean.setUserId(userId);
				userBean.setUserName(userName);
				userBean.setUserRole(userRole);
			} else {
				logger.warning("Incorrect username and password. Please try again");
				return null;
			}
		} catch (SQLException e) {
			logger.warning("Please enter a valid username and password");
		} finally {
			closeConnection(result, statement, connection);
		}
		return userBean;
	}

	public List<BikeBeanClass> viewAllBikes() {
		String query = Constants.ALL_BIKES;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<BikeBeanClass> bikeBeanList = new ArrayList<>();
		try {
			result = statement.executeQuery();
			while (result.next()) {
				BikeBeanClass bikeBean = new BikeBeanClass();
				bikeBean.setBikeId(result.getInt(1));
				bikeBean.setManufacturer(result.getString(2));
				bikeBean.setBikeName(result.getString(3));
				bikeBean.setCharge(result.getInt(4));
				bikeBean.setAvailability(result.getInt(5));
				bikeBeanList.add(bikeBean);
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return bikeBeanList;
	}
	
	public List<BikeBeanClass> viewAvailableBikes() {
		String query = Constants.AVAILABLE_BIKES;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<BikeBeanClass> bikebeanlist = new ArrayList<>();
		try {
			result = statement.executeQuery();
			while (result.next()) {
				BikeBeanClass bikebean = new BikeBeanClass();
				bikebean.setBikeId(result.getInt(1));
				bikebean.setManufacturer(result.getString(2));
				bikebean.setBikeName(result.getString(3));
				bikebean.setCharge(result.getInt(4));
				bikebean.setAvailability(result.getInt(5));
				bikebeanlist.add(bikebean);
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return bikebeanlist;
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
		} finally {
			closeConnection(result, statement, connection);
		}
		return registrationNumber;
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
		} finally {
			closeConnection(result, statement, connection);
		}
		return bikeid;
	}
	
	public boolean rentBike(RentBeanClass rentBean) {
		String registrationNumber = fetchRegistrationNumber(rentBean.getBikeId());
		rentBean.setRegistrationNumber(registrationNumber);
		String query = Constants.RENT_BIKE;
		boolean isSuccessful = false;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setInt(1, rentBean.getUserId());
			statement.setInt(2, rentBean.getBikeId());
			statement.setString(3, rentBean.getRegistrationNumber());
			statement.setInt(4, rentBean.getDuration());
			statement.setInt(5, rentBean.getDuration());
			statement.setInt(6, rentBean.getBikeId());
			statement.setInt(7, rentBean.getAdvancePaid());
			rowsaffected = statement.executeUpdate();
			if (rowsaffected > 0) {
				isSuccessful = true;
			} else {
				logger.warning("Please enter valid credentials and try again");
				isSuccessful = false;
			}
		} catch (SQLException e) {
			logger.warning("Problem while inserting the rented bike");
		} finally {
			closeConnection(result, statement, connection);
		}
		decreaseAvailability(rentBean.getBikeId());
		setBikeStatusRented(rentBean.getRegistrationNumber());
		return isSuccessful;
	}
	
	public List<ApplicationBeanClass> userRentedBike(int userid) {
		String query = Constants.USER_RENTED_BIKE;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<ApplicationBeanClass> appBeanList = new ArrayList<>();
		try {
			statement.setInt(1, userid);
			result = statement.executeQuery();
			while (result.next()) {
				ApplicationBeanClass appBean = new ApplicationBeanClass();
				BikeBeanClass bikeBean = new BikeBeanClass();
				bikeBean.setRegistrationNumber(result.getString(1));
				bikeBean.setManufacturer(result.getString(2));
				bikeBean.setBikeName(result.getString(3));
				bikeBean.setCharge(result.getInt(4));
				bikeBean.setBikeId(result.getInt(5));				
				int bikeId = bikeBean.getBikeId();
				String registrationNumber = bikeBean.getRegistrationNumber();
				RentBeanClass rentBean = getRentDetails(userid, bikeId, registrationNumber);
				RentCalculatorBeanClass rentCalculatorBean = rentCalculator(rentBean, bikeId);
				appBean.setBikeBean(bikeBean);
				appBean.setRentCalculatorBean(rentCalculatorBean);
				appBeanList.add(appBean);				
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes rented by this user");
		} finally {
			closeConnection(result, statement, connection);
		}
		return appBeanList;
	}
	
	public RentBeanClass getRentDetails(int userId, int bikeId, String registrationNumber) {
		String query = Constants.RETURN_BIKE;
		RentBeanClass rentBean = new RentBeanClass();
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		try {
			statement.setInt(1, userId);
			statement.setString(2, registrationNumber);
			result = statement.executeQuery();
			if (result.next()) {
				rentBean.setTransactionId(result.getInt(1));
				rentBean.setUserId(result.getInt(2));
				rentBean.setBikeId(result.getInt(3));
				rentBean.setRegistrationNumber(result.getString(4));
				rentBean.setRentedDateTime(result.getString(5));
				rentBean.setDuration(result.getInt(6));
				rentBean.setEstimatedAmount(result.getInt(7));
				rentBean.setAdvancePaid(result.getInt(8));
				rentBean.setStatus(result.getString(9));
			}
		} catch (SQLException e) {
			logger.warning("Problem while returning the bike");
		} finally {
			closeConnection(result, statement, connection);
		}
		return rentBean;
	}
	
	public RentCalculatorBeanClass rentCalculator(RentBeanClass rentBean,int bikeId) {
		BikeRentCalculator rentCalculator = new BikeRentCalculator();
		RentCalculatorBeanClass rentCalculatorBean = new RentCalculatorBeanClass();
		rentCalculatorBean = rentCalculator.CalculateAmount(rentBean, getBikeDetails(bikeId).getCharge());
		return rentCalculatorBean;
	}

	public RentCalculatorBeanClass returnBike(int userId, int bikeId, String registrationNumber) {
		RentCalculatorBeanClass rentCalculatorBean = null;
		try {
		RentBeanClass rentBean = getRentDetails(userId, bikeId, registrationNumber);
		rentCalculatorBean = rentCalculator(rentBean, bikeId);
		updateRentStatus(rentBean.getTransactionId(), rentBean.getBikeId());
		insertRevenueDetails(rentCalculatorBean);
		setBikeStatusReturned(registrationNumber);
		} catch(Exception e) {
			logger.warning("Problem while returning the bike");
		} finally {
			closeConnection(result, statement, connection);
		}
		return rentCalculatorBean;
	}

	public void updateRentStatus(int transactionid, int bikeid) {
		String query = Constants.UPDATE_RENT_STATUS;
		PreparedStatement statement = queryExecutor(query);
		int rowsaffected = 0;
		try {
			statement.setInt(1, transactionid);
			rowsaffected = statement.executeUpdate();
			if (rowsaffected > 0) {
				logger.info("Bike is returned successfully");
				increaseAvailability(bikeid);
			} else {
				logger.warning("Please enter valid credentials and try again");
			}
		} catch (SQLException e) {
			logger.warning("Problem while updating status in rentdetails table");
		} finally {
			closeConnection(result, statement, connection);
		}
	}
	
	public void insertRevenueDetails(RentCalculatorBeanClass rentCalculatorBean) {
		String query = Constants.INSERT_REVENUE_DETAILS;
		PreparedStatement statement = queryExecutor(query);
		int rowsAffected = 0;
		try {
			statement.setInt(1, rentCalculatorBean.getTransactionId());
			statement.setInt(2, rentCalculatorBean.getHoursRented());
			statement.setInt(3, rentCalculatorBean.getActualCharge());
			statement.setInt(4, rentCalculatorBean.getPenaltyHours());
			statement.setInt(5, rentCalculatorBean.getPenaltyCharge());
			statement.setInt(6, rentCalculatorBean.getTotalCharge());
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			logger.warning("Problem while inserting value into revenue details table");
		} finally {
			closeConnection(result, statement, connection);
		}
	}
	
	public List<ApplicationBeanClass> viewRentHistory(int userId) {
		String query = Constants.VIEW_RENT_HISTORY;
		PreparedStatement statement = selectQueryExecutor(query);
		List<ApplicationBeanClass> appBeanList = new ArrayList<>();
		try {
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				RentBeanClass rentBean = new RentBeanClass();
				RentCalculatorBeanClass rentCalculatorBean = new RentCalculatorBeanClass();
				BikeBeanClass bikeBean = new BikeBeanClass();
				ApplicationBeanClass appBean = new ApplicationBeanClass();
				rentBean.setTransactionId(result.getInt(1));
				rentBean.setBikeId(result.getInt(3));
				rentBean.setRegistrationNumber(result.getString(4));
				rentBean.setRentedDateTime(result.getString(5));
				rentCalculatorBean.setTransactionId(result.getInt(10));
				rentCalculatorBean.setHoursRented(result.getInt(11));
				rentCalculatorBean.setActualCharge(result.getInt(12));
				rentCalculatorBean.setPenaltyHours(result.getInt(13));
				rentCalculatorBean.setPenaltyCharge(result.getInt(14));
				rentCalculatorBean.setTotalCharge(result.getInt(15));
				bikeBean.setBikeId(result.getInt(16));
				bikeBean.setManufacturer(result.getString(17));
				bikeBean.setBikeName(result.getString(18));
				bikeBean.setRegistrationNumber(result.getString(19));
				appBean.setRentBean(rentBean);
				appBean.setRentCalculatorBean(rentCalculatorBean);
				appBean.setBikeBean(bikeBean);
				appBeanList.add(appBean);
			}
		} catch (SQLException e) {
			logger.warning("Problem while fetching user rent history");
		} finally {
			closeConnection(result, statement, connection);
		}
		return appBeanList;
	}
	
	
	///////////////////////////////ADMIN CONSOLE/////////////////////////
	
	public List<RentBeanClass> viewAllRentedBikes() {
		String query = Constants.ALL_RENTED_BIKES;
		PreparedStatement statement = selectQueryExecutor(query);
		ResultSet result = null;
		List<RentBeanClass> rentBeanList = new ArrayList<>();
		try {
			result = statement.executeQuery();
			while (result.next()) {
				RentBeanClass rentBean = new RentBeanClass();
				rentBean.setTransactionId(result.getInt(1));
				rentBean.setUserId(result.getInt(2));
				rentBean.setBikeId(result.getInt(3));
				rentBean.setRegistrationNumber(result.getString(4));
				rentBean.setRentedDateTime(result.getString(5));
				rentBean.setDuration(result.getInt(6));
				rentBean.setEstimatedAmount(result.getInt(7));
				rentBean.setAdvancePaid(result.getInt(8));
				rentBeanList.add(rentBean);
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return rentBeanList;
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
				userbean.setUserId(result.getInt(1));
				userbean.setUserName(result.getString(2));
				userbean.setPhoneNumber(result.getLong(4));
				userbean.setLicenseNumber(result.getString(5));
				userbean.setStreet(result.getString(9));
				userbean.setArea(result.getString(10));
				userbean.setCity(result.getString(11));
				userbean.setPincode(result.getInt(12));
				userbeanlist.add(userbean);
			}
		} catch (SQLException e) {
			logger.warning("Problem while displaying the bikes");
		} finally {
			closeConnection(result, statement, connection);
		}
		return userbeanlist;
	}
	
	public boolean addNewRegistrationNumber(int bikeId, String registrationNumber) {
		boolean isSuccessful = false;
		String query = Constants.ADD_REGISTRATION_NUMBER;
		PreparedStatement statement = queryExecutor(query);
		int rowsAffected = 0;
		try {
			statement.setInt(1, bikeId);
			statement.setString(2, registrationNumber);
			rowsAffected = statement.executeUpdate();
			if(rowsAffected > 0) {
				logger.info("Bike is added");
				increaseAvailability(bikeId);
				isSuccessful = true;
			}
		} catch (SQLException e) {
			logger.warning("problem while inserting new bike in existing list");
		} finally {
			closeConnection(result, statement, connection);
		}
		return isSuccessful;
	}

}
