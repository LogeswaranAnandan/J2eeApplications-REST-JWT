package com.bikeapplication;

import java.util.Scanner;
import java.util.logging.Logger;

import com.bikeapplication.constants.Constants;

public class BikeRentingApplication {
	public static void main(String[] args) {
		BikeRentingMethods bikerentingmethod = new BikeRentingMethods();
		Scanner scanner = new Scanner(System.in);
		Logger logger = Logger.getLogger(BikeRentingMethods.class.getName());
		try {
			logger.info(Constants.CHOOSE_ROLE);
			switch (Integer.parseInt(scanner.nextLine())) {
			case 1:
				logger.info(Constants.TYPE_OF_USER);
				switch (Integer.parseInt(scanner.nextLine())) {
				case 1:
					bikerentingmethod.userLogin();
					break;
				case 2:
					bikerentingmethod.userSignup();
					break;
				default:
					logger.warning(Constants.INVALID_CHOICE);
					break;
				}
				break;
			case 2:
				bikerentingmethod.adminLogin();
				break;
			default:
				logger.warning(Constants.INVALID_CHOICE);
			}
		} catch (Exception e) {
			logger.warning(Constants.INVALID_CHOICE);
			main(args);
		} finally {
			scanner.close();
		}
	}
}
