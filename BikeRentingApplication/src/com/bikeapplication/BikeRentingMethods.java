package com.bikeapplication;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.bean.RentBeanClass;
import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.constants.Constants;
import com.bikeapplication.dao.BikeApplicationDao;

public class BikeRentingMethods {
	Logger logger = Logger.getLogger(BikeRentingMethods.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();
	Scanner scanner = new Scanner(System.in);

	public void userLogin() {
		try {
			String username = "";
			String password = "";
			boolean success = false;
			logger.info("Enter your username or phonenumber");
			username = scanner.nextLine();
			logger.info("Enter the password:");
			password = scanner.nextLine();
			success = dao.userLogin(username, password);
			if (success) {
				logger.info("Welcome, " + dao.getUserName(Constants.getUSERID()));
				userMenu();
			} else {
				userLogin();
			}
		} catch (Exception e) {
			logger.info(Constants.INVALID_DATA);
		}
	}

	public void userSignup() {
		try {
			boolean success = false;
			UserBeanClass userbean = new UserBeanClass();
			logger.info("Enter your name : ");
			userbean.setUsername(scanner.nextLine());
			logger.info("Enter address details as following");
			logger.info("\tEnter the street : ");
			userbean.setStreet(scanner.nextLine());
			logger.info("\tEnter the area : ");
			userbean.setArea(scanner.nextLine());
			logger.info("\tEnter the city : ");
			userbean.setCity(scanner.nextLine());
			logger.info("\tEnter the pincode : ");
			userbean.setPincode(Integer.parseInt(scanner.nextLine()));
			logger.info("Enter phone number : ");
			userbean.setPhonenumber(Long.parseLong(scanner.nextLine()));
			logger.info("Enter License number : ");
			userbean.setLicenseno(scanner.nextLine());
			logger.info("Enter user password : ");
			userbean.setUserpassword(scanner.nextLine());
			success = dao.userSignup(userbean);
			if (success) {
				logger.info("User is Registered successfully. \nPlease verify again by logging in.....");
				userLogin();
			} else {
				logger.info("Please try again with valid credentials");
				userSignup();
			}
		} catch (Exception e) {
			logger.warning(Constants.INVALID_DATA);
		}
	}

	public void userMenu() {
		try {
			do {
				logger.info(Constants.USERMENU);
				switch (Integer.parseInt(scanner.nextLine())) {
				case 1:
					viewAllBikes();
					break;
				case 2:
					viewAvailableBikes();
					break;
				case 3:
					viewAndRentBike();
					break;
				case 4:
					returnBike();
					break;
				case 5:
					calculateBikeRent();
					break;
				case 6:
					logger.info("Thank you, " + dao.getUserName(Constants.USERID));
					Constants.setUSERID(0);
					closeScanner();
					System.exit(0);
					break;
				default:
					logger.warning("Please enter a valid choice");
					break;
				}
			} while (true);
		} catch (Exception e) {
			logger.warning(Constants.INVALID_CHOICE);
			userMenu();
		}
	}

	public void viewAllBikes() {
		List<BikeBeanClass> bikebean = dao.viewAllBike();
		for(BikeBeanClass temp : bikebean) {
			logger.info(temp.toString());
		}
		
	}

	public void viewAvailableBikes() {
		List<BikeBeanClass> bikebean = dao.viewAvailableBikes();
		for (BikeBeanClass temp : bikebean) {
			logger.info(temp.toString());
		}
		logger.info("Do you want to rent a bike?(y/n)");
		if (scanner.nextLine().equals("y")) {
			rentBike();
		}

	}

	public void viewAndRentBike() {
		List<BikeBeanClass> bikebean = dao.viewAvailableBikes();
		for (BikeBeanClass temp : bikebean) {
			logger.info(temp.toString());
		}
		rentBike();
	}

	public void rentBike() {
		try {
			RentBeanClass rentbean = new RentBeanClass();
			logger.info("Enter the bike id : ");
			rentbean.setBikeid(Integer.parseInt(scanner.nextLine()));
			logger.info("Enter the Duration of renting(in hours) : ");
			rentbean.setDuration(Integer.parseInt(scanner.nextLine()));
			logger.info("Enter the advance payment amount : ");
			rentbean.setAdvancepaid(Integer.parseInt(scanner.nextLine()));
			dao.rentBike(rentbean);
		} catch (Exception e) {
			logger.warning(Constants.INVALID_DATA);
		}
	}

	public void returnBike() {
		try {
			dao.userRentedBike(Constants.USERID);
			logger.info("Enter the registration number of bike to be returned : ");
			String registrationNumber = scanner.nextLine();
			logger.info("Registration Number : " + registrationNumber);
			dao.returnBike(registrationNumber);
		} catch (Exception e) {
			logger.warning(Constants.INVALID_DATA);
		}
	}

	public void calculateBikeRent() {
		String input;
		do {
			dao.userRentedBike(Constants.USERID);
			logger.info("Enter the registration number of bike to be viewed : ");
			String registrationNumber = scanner.nextLine();
			logger.info("Registration Number : " + registrationNumber);
			dao.calculateRentAmount(registrationNumber);
			logger.info("Do you want to view rent for more bikes (y/n)?");
			input = scanner.nextLine();
		} while (input.equals("y"));
	}

	///////////////////////////////// ADMIN FUNCTIONS/////////////////

	public void adminLogin() {
		try {
			boolean success;
			String username = "";
			String password = "";
			logger.info("Enter the username : ");
			username = scanner.nextLine();
			logger.info("Enter the password : ");
			password = scanner.nextLine();
			success = dao.adminLogin(username, password);
			if (success) {
				adminMenu();
			} else {
				logger.info("Invalid login. Please try again");
				adminLogin();
			}
		} catch (Exception e) {
			logger.warning(Constants.INVALID_DATA);
		}
	}

	public void adminMenu() {
		try {
			do {
				logger.info(Constants.ADMINMENU);
				switch (Integer.parseInt(scanner.nextLine())) {
				case 1:
					viewAllBikes();
					break;
				case 2:
					viewRentedBikes();
					break;
				case 3:
					addNewBike();
					break;
				case 4:
					viewAllUsers();
					break;
				case 5:
					logger.info("Exiting....Done");
					closeScanner();
					System.exit(0);
					break;
				default:
					logger.warning(Constants.INVALID_CHOICE);
					break;
				}
			} while (true);
		} catch (Exception e) {
			logger.warning(Constants.INVALID_CHOICE);
			adminMenu();
		}
	}

	public void viewRentedBikes() {
		List<RentBeanClass> rentbeanlist = dao.viewRentedBikes();
		for (RentBeanClass temp : rentbeanlist) {
			logger.info(temp.toString());
			logger.info("Do you want to view the bike details of this bike?(y/n)");
			if ((scanner.nextLine()).equals("y")) {
				BikeBeanClass bikebean = dao.getBikeDetails(temp.getBikeid());
				logger.info(bikebean.toString());
			}
		}
	}

	public void addNewBike() {
		try {
			boolean success = false;
			BikeBeanClass bikebean = new BikeBeanClass();
			logger.info("Enter the bike id : ");
			bikebean.setBikeid(Integer.parseInt(scanner.nextLine()));
			logger.info("Enter the manufacturer name : ");
			bikebean.setManufacturer(scanner.nextLine());
			logger.info("Enter the bike name : ");
			bikebean.setBikename(scanner.nextLine());
			logger.info("Enter the charge for renting(per hour)");
			bikebean.setCharge(Integer.parseInt(scanner.nextLine()));
			logger.info("Enter the number of bikes available : ");
			bikebean.setAvailability(Integer.parseInt(scanner.nextLine()));
			success = dao.addNewBike(bikebean);
			if (success) {
				logger.info("New bike is added successfully");
			} else {
				logger.warning("Please enter valid credentials");
			}
		} catch (Exception e) {
			logger.warning(Constants.INVALID_DATA);
		}
	}

	public void viewAllUsers() {
		List<UserBeanClass> userbean = dao.viewAllUsers();
		for (UserBeanClass temp : userbean) {
			logger.info(temp.toString());
		}
	}

	public void closeScanner() {
		scanner.close();
	}
}
