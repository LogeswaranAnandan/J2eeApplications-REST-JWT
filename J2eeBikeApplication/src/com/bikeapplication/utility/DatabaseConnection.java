package com.bikeapplication.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {
	Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

	public Connection getConnection() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/bikerentingapplication");
			Connection connection = dataSource.getConnection();
			return connection;
		} catch (NamingException | SQLException e) {
			logger.warning("Problem while creating a connection in application");
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
