package com.bikeapplication.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.bikeapplication.constants.Constants;

public class DatabaseConnection {
	Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

	public Connection getConnection() {
		try {
			Class.forName(Constants.JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.warning("Problem in the jdbc driver");
		}
		try {
			Connection connection = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
			return connection;
		} catch (SQLException e) {
			logger.warning("Problem while creating a connection");
		}
		return null;
	}

	public void closeConnection(ResultSet result, Statement statement, Connection connection) {
		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.warning("Problem while closing database connection");
		}
	}
}
