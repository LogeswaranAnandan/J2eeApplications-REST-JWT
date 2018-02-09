package com.jwt.utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class ParserClass<T> {

	public List<T> toJavaList(String jsonString, Class<T[]> clazz) {
		Gson gson = new Gson();
		List<T> beanList = new ArrayList<T>();
		T[] beanArray = gson.fromJson(jsonString, clazz);
		return Arrays.asList(beanArray);
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
