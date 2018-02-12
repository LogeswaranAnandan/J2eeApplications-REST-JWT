package com.bikeapplication.delegate;

import javax.ws.rs.core.Response;

import com.bikeapplication.bean.UserBeanClass;
import com.bikeapplication.dao.BikeApplicationDao;
import com.bikeapplication.utility.JwtTokenGenerator;

public class BikeApplicationDelegate {
	BikeApplicationDao dao = new BikeApplicationDao();
	public Response validateLogin(String userName,String password) {
		UserBeanClass userBean = new UserBeanClass();
		JwtTokenGenerator jwtTokenGenerator = new JwtTokenGenerator();
		Response response = null;
		userBean = dao.userLogin(userName, password);
		if (userBean != null) {
			long userId = userBean.getUserId();
			String subject = userBean.getUserName();
			String userRole = userBean.getUserRole();
			response = Response.ok(userBean).header("jwt", jwtTokenGenerator.generateJwt(userId, subject, userRole)).build();
		} else {
			response = Response.status(Response.Status.NOT_FOUND).build();
		}
		return response;
	}
	
	public long userSignUp(UserBeanClass userBean) {
		return dao.userSignUp(userBean);
	}
}
