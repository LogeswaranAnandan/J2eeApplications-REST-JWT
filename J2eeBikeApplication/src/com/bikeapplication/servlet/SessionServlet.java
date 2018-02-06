package com.bikeapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.delegate.BikeApplicationDelegate;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SessionServlet.class.getName());
	BikeApplicationDelegate delegate = new BikeApplicationDelegate();

	public SessionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userRequest = request.getParameter("submit-button");
		if (userRequest.equals("LOGIN")) {
			RequestDispatcher requestDispatcher;
			UserBeanClass userBean = new UserBeanClass();
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				userBean = delegate.validateLogin(userName, password);
				if (userBean != null) {
					if (userBean.getUserRole().equals("Customer")) {
						requestDispatcher = request.getRequestDispatcher("jsp/customer/customer.jsp");
					} else {
						requestDispatcher = request.getRequestDispatcher("jsp/admin/admin.jsp");
					}
					HttpSession session = request.getSession();
					session.setAttribute("userId", userBean.getUserId());
					session.setAttribute("userName", userBean.getUserName());
					session.setAttribute("userRole", userBean.getUserRole());
					requestDispatcher.forward(request, response);
				} else {
					request.setAttribute("loginStatus", "invalid");
					requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (Exception e) {
				response.sendRedirect("index.jsp");
				logger.warning("in login exception");
			}
		} else if (userRequest.equals("SIGN UP")) {
			RequestDispatcher dispatcher;
			UserBeanClass userBean = new UserBeanClass();
			userBean.setUserName(request.getParameter("username"));
			userBean.setStreet(request.getParameter("street"));
			userBean.setArea(request.getParameter("area"));
			userBean.setCity(request.getParameter("city"));
			userBean.setPincode(Integer.parseInt(request.getParameter("pincode")));
			userBean.setPhoneNumber(Long.parseLong(request.getParameter("phonenumber")));
			userBean.setLicenseNumber(request.getParameter("licensenumber"));
			userBean.setUserPassword(request.getParameter("userpassword"));
			long userId = delegate.userSignUp(userBean);
			if (userId != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				session.setAttribute("userName", request.getParameter("username"));
				session.setAttribute("userRole", "Customer");
				dispatcher = request.getRequestDispatcher("jsp/customer/customer.jsp");
			} else {
				out.println("<script>alert('some problem occurred while registration')</script>");
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			dispatcher.include(request, response);
		} else if (userRequest.equals("Logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
