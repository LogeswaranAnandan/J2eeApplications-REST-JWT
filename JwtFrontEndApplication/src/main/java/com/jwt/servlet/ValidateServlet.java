package com.jwt.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.delegate.Delegate;

public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValidateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Delegate delegate = new Delegate();
		delegate.viewData(request,response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/viewdata.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		Delegate delegate = new Delegate();
		delegate.userLogin(request, response);
		if(session.getAttribute("jwt") != null) {
			dispatcher = request.getRequestDispatcher("/success.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().write("Invalid username or password");
			dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.include(request, response);
		}
	}

}
