package com.bikeapplication.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikeapplication.delegate.FactoryDelegate;

@WebServlet("/FactoryServlet")
public class FactoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FactoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FactoryDelegate factoryDelegate = new FactoryDelegate();
		factoryDelegate.viewRentedBikes(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getAttribute("path").toString());
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
