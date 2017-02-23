package com.ims.service.impl.homeandlogin;

import java.sql.SQLException;

import com.ims.dao.impl.homeandlogin.LoginDaoImpl;
import com.ims.service.homeandlogin.LoginService;

public class LoginServiceImpl implements LoginService{

	private LoginDaoImpl dao;
	
	@Override
	public String getLoginId(String id) throws SQLException {
		return this.getDao().getLoginId(id);
	}

	public LoginDaoImpl getDao() {
		return dao;
	}

	public void setDao(LoginDaoImpl dao) {
		this.dao = dao;
	}

}
