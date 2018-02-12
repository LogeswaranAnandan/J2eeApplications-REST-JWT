package com.bikeapplication.delegate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;
import com.bikeapplication.utility.HttpRequest;
import com.bikeapplication.utility.ParserClass;

public class BikeApplicationDelegate {
	Logger logger = Logger.getLogger(BikeApplicationDelegate.class.getName());
	BikeApplicationDao dao = new BikeApplicationDao();
	
	public UserBeanClass validateLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserBeanClass userBean = new UserBeanClass();
		ParserClass<UserBeanClass> parser = new ParserClass<>();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String responseBody = "";
		
		//making a HTTP call
		try {
			URL url;
			HttpURLConnection connection;
			BufferedReader bufferedReader;
			StringBuffer stringBuffer = new StringBuffer();
			url = new URL("http://localhost:8080/RestBikeApplication/rest/login/" + userName + "/" + password);
			System.out.println("url = " + url);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output = null;
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}
			responseBody = stringBuffer.toString();
			String jwt = connection.getHeaderField("jwt").toString();
			session.setAttribute("jwt", jwt);
			System.out.println("jwt = " + session.getAttribute("jwt").toString());
		} catch (Exception e) {
			logger.warning("Problem while executing get request method");
		}
		
		//parsing the response
		userBean = parser.toJava(responseBody, UserBeanClass.class);
		if (userBean != null) {
			session.setAttribute("userId", userBean.getUserId());
			session.setAttribute("userName", userBean.getUserName());
			session.setAttribute("userRole", userBean.getUserRole());
		}
		return userBean;
	}

	public long userSignUp(UserBeanClass userBean) {
		return dao.userSignUp(userBean);
	}
}
