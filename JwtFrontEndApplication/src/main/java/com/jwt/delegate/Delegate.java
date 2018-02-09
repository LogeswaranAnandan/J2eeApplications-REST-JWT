package com.jwt.delegate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.utility.HttpRequest;

public class Delegate {
	Logger logger = Logger.getLogger(Delegate.class.getName());
	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		URL url;
		HttpURLConnection connection;
		BufferedReader bufferedReader;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			url = new URL("http://localhost:8080/JsonWebTokenApplication/rest/main/" + userName + "/" + password);
			System.out.println("url = " + url);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			/*String output = null;
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}*/
			logger.info("buffer = " + stringBuffer.toString());
			String jwt = connection.getHeaderField("jwt");
			session.setAttribute("jwt", jwt);
			System.out.println("jwt = " + session.getAttribute("jwt").toString());
		} catch (Exception e) {
			logger.warning("Problem while executing get request method");
			session.setAttribute("jwt", null);
		}
		
	}
	public void viewData(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String jwt = session.getAttribute("jwt").toString();
		String token = "Bearer " + jwt;
		logger.info("token = " + token);
		URL url;
		HttpURLConnection connection;
		BufferedReader bufferedReader;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			url = new URL("http://localhost:8080/JsonWebTokenApplication/rest/test");
			System.out.println("url = " + url);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", token);
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output = null;
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}
			logger.info("buffer = " + stringBuffer.toString());
			request.setAttribute("content", stringBuffer.toString());
		} catch (Exception e) {
			logger.warning("Problem while executing get request method");
			e.printStackTrace();
			session.setAttribute("jwt", null);
		}
		
	}

}
