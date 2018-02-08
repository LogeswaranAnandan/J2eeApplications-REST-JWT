package com.bikeapplication.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class HttpRequest {
	Logger logger = Logger.getLogger(HttpRequest.class.getName());

	public String doGet(String requestUrl) {
		URL url;
		HttpURLConnection connection;
		BufferedReader bufferedReader;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			url = new URL(requestUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output = null;
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}
		} catch (Exception e) {
			logger.warning("Problem while executing get request method");
		}
		return stringBuffer.toString();
	}

	public String doPost(String requestUrl, String requestBody, String acceptType) {
		URL url;
		HttpURLConnection connection;
		BufferedReader bufferedReader;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			url = new URL(requestUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", acceptType);
			connection.setRequestProperty("Content-type", "application/json");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(requestBody);
			out.close();

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output = null;
			while ((output = bufferedReader.readLine()) != null) {
				stringBuffer.append(output);
			}
			System.out.println("buffer: " + stringBuffer.toString());
		} catch (Exception e) {
			logger.warning("Problem while making a http post call");
		}
		return stringBuffer.toString();
	}
}
