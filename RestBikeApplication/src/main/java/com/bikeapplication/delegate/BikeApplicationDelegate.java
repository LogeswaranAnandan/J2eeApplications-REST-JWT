package com.bikeapplication.delegate;

import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;

public class BikeApplicationDelegate {
	BikeApplicationDao dao = new BikeApplicationDao();
	UserBeanClass userBean = new UserBeanClass();
	public UserBeanClass validateLogin(String userName,String password) {
			userBean = dao.userLogin(userName, password);
			return userBean;
	}
	
	public long userSignUp(UserBeanClass userBean) {
		return dao.userSignUp(userBean);
	}
}
