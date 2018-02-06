package com.bikeapplication.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikeapplication.delegate.AdminDelegate;
import com.bikeapplication.delegate.FactoryDelegate;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(AdminServlet.class.getName());
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegate delegate = new AdminDelegate();
		String functionality = request.getParameter("admin-functionality");
		if (functionality.equals("HOME")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/admin/admin.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View All Bikes")) {
			delegate.viewAllBikes(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/admin/viewallbikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View All Rented Bikes")) {
			FactoryDelegate factoryDelegate = new FactoryDelegate();
			factoryDelegate.viewRentedBikes(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/admin/viewrentedbikes.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("View Customer Details")) {
			delegate.viewAllUsers(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/admin/viewallusers.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Add New Bike")) {
			delegate.viewAllBikes(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/admin/addnewbike.jsp");
			dispatcher.forward(request, response);
		} else if (functionality.equals("Add Bike")) {
			delegate.addNewRegistrationNumber(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
