package com.ims.service.impl.usermaintenance;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.dao.impl.usermaintenance.UserMaintenanceDaoImpl;
import com.ims.model.usermaintenance.Users;
import com.ims.service.usermaintenance.UserMaintenanceService;

public class UserMaintenanceServiceImpl implements UserMaintenanceService{

	private UserMaintenanceDaoImpl dao;
	
	@Override
	public List<Users> getUsers() throws SQLException {
		return this.getDao().getUsers();
	}
	
	@Override
	public String getUserId(String id) throws SQLException {
		return this.getDao().getUserId(id);
	}
	
	@Override
	public void insertNewUser(HttpServletRequest request) throws SQLException {
		System.out.println("START INSERT");
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String middleInitial = request.getParameter("middleInitial");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String activeTag = request.getParameter("activeTag");
		String userAccess = request.getParameter("userAccess");
		String remarks = request.getParameter("remarks");

		String generatedPassword = (new Integer(userId)).toString();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("firstName", firstName);
		params.put("middleInitial", middleInitial);
		params.put("lastName", lastName);
		params.put("email", email);
		params.put("activeTag", activeTag);
		params.put("userAccess", userAccess);
		params.put("remarks", remarks);
		params.put("password", generatedPassword);

		System.out.println("added2");
		this.getDao().insertNewUser(params);
		
		System.out.println("added");
	}

	public UserMaintenanceDaoImpl getDao() {
		return dao;
	}

	public void setDao(UserMaintenanceDaoImpl dao) {
		this.dao = dao;
	}

}
