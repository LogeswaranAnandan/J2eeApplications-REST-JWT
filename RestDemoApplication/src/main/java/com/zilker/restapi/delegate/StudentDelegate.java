package com.zilker.restapi.delegate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zilker.restapi.bean.StudentBean;

public class StudentDelegate {
	Logger logger = Logger.getLogger(StudentDelegate.class.getName());
	String UrlPrefix = "http://localhost:8080/RestDemoApplication/rest/";

	public List<StudentBean> getStudentDetails() {
		URL url;
		HttpURLConnection connection;
		BufferedReader bufferedReader;
		StringBuffer stringBuffer = new StringBuffer();
		List<StudentBean> beanList = new ArrayList<>();
		try {
			url = new URL(UrlPrefix + "students");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output = null;
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}
			logger.info("The string is");
			logger.info(stringBuffer.toString());
			/*
			 * JSONParser jsonParser = new JSONParser(); String responseBody =
			 * stringBuffer.toString(); logger.info("response body is : " + responseBody);
			 * JSONArray jsonArray = (JSONArray) jsonParser.parse(responseBody);
			 * logger.info("Json array is : " + jsonArray.toString());
			 * for (int i = 0; i < jsonArray.size(); i++) { 
				 * JSONObject temp = (JSONObject) jsonArray.get(i); 
				 * StudentBean bean = new StudentBean();
				 * bean.setStudentName(temp.get("studentName").toString());
				 * bean.setRollNumber(Integer.parseInt(temp.get("rollNumber").toString()));
				 * beanList.add(bean); 
				 * logger.info("Bean_" + i + " = " + bean.toString()); 
			 * }
			 */
			Gson gson = new Gson();
			String responseBody = stringBuffer.toString();
			beanList = gson.fromJson(responseBody, new TypeToken<List<StudentBean>>() {}.getType());
			logger.info("In beanlist");
			logger.info(beanList.toString());
		} catch (Exception e) {
			logger.warning("Problem occured while sending the http request");
		}

		return beanList;
	}

	public StudentBean insertStudentDetail(HttpServletRequest request, HttpServletResponse response) {
		URL url;
		HttpURLConnection connection;
		StudentBean bean = new StudentBean();
		bean.setRollNumber(Integer.parseInt(request.getParameter("roll-number").toString()));
		bean.setStudentName(request.getParameter("student-name").toString());
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(bean);
			logger.info("json string is = " + jsonString);
			url = new URL("http://localhost:8080/RestDemoApplication/rest/students");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-type", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonString);
			System.out.println("content of out = " + out.toString());
			out.close();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output = null;
			StringBuffer stringBuffer = new StringBuffer();
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}

			System.out.println("buffer: " + stringBuffer.toString());

		} catch (Exception e) {
			logger.warning("Problem while inserting the data");
			e.printStackTrace();
		}
		return null;
	}
}
