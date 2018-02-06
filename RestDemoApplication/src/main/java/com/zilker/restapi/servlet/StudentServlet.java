package com.zilker.restapi.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.restapi.bean.StudentBean;
import com.zilker.restapi.delegate.StudentDelegate;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDelegate delegate = new StudentDelegate();
		List<StudentBean> beanList = delegate.getStudentDetails();
		request.setAttribute("beanList", beanList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/display.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDelegate delegate = new StudentDelegate();
		StudentBean bean = delegate.insertStudentDetail(request, response);
		request.setAttribute("bean", bean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/display.jsp");
		dispatcher.forward(request, response);
	}

}
