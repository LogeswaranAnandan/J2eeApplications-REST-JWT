package com.bikeapplication.utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class ParserClass<T> {

	public List<T> toJavaList(String jsonString, Class<T[]> clazz) {
		List<T> beanList = new ArrayList<>();
		try {
		Gson gson = new Gson();
		T[] beanArray = gson.fromJson(jsonString, clazz);
		beanList = Arrays.asList(beanArray);
		} catch(Exception e) {
			Logger.getLogger(ParserClass.class.getName()).warning("Problem while converting response to Java Object");
		}
		return beanList;
	}

	public T toJava(String jsonString, Type type) {
		Gson gson = new Gson();
		T bean = gson.fromJson(jsonString, type);
		return bean;
	}

	public String toJson(T bean) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(bean);
		return jsonString;
	}
}
