package com.bikeapplication.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikeapplication.bean.BikeBeanClass;
import com.bikeapplication.delegate.CustomerDelegate;

@WebServlet({ "/CustomerServlet", "/CustomerRequestServlet" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CustomerServlet.class.getName());

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDelegate delegate = new CustomerDelegate();
		String functionality = request.getParameter("customer-functionality");

		if (functionality.equals("HOME")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer/customer.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View All Bikes") || functionality.equals("Rent a Bike")) {
			List<BikeBeanClass> bikeBeanList = delegate.viewAllBikes();
			request.setAttribute("bikeBeanList", bikeBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer/ViewAllBikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Return the Bike")) {
			delegate.viewUserRentedBikes(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer/viewrentedbikes.jsp");
			dispatcher.forward(request, response);
		}
		else if (functionality.equals("Rent this Bike")) {
			delegate.rentBike(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer/customer.jsp");
			dispatcher.include(request, response);
		} else if (functionality.equals("Return this Bike")) {
			delegate.returnBike(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer/viewrentamount.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View Rent History")) {
			delegate.viewRentHistory(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer/viewrenthistory.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
