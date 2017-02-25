package com.ims.service.impl.usermaintenance;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ims.dao.impl.usermaintenance.UserMaintenanceDaoImpl;
import com.ims.model.usermaintenance.Users;
import com.ims.service.usermaintenance.UserMaintenanceService;
import com.ims.utilities.Password;
import com.ims.utilities.SystemStatus;

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
	public List<Users> getUser(String uId) throws SQLException {
		return this.getDao().getUser(uId);
	}
	
	@Override
	public List<Users> populateUserFields(HttpServletRequest request) throws SQLException {
		System.out.println("Populating fields...");
		String uId = request.getParameter("userId");
		return this.getDao().getUser(uId);
	}
	
	@Override
	public SystemStatus updateUser(HttpServletRequest request) throws SQLException {
	
		HttpSession session = request.getSession();
		
		String loggedInId = (String) session.getAttribute("user_auth");
		String userId = request.getParameter("userId");
		String firstName = request.getParameter("firstName");
		String middleInitial = request.getParameter("middleInitial");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String remarks = request.getParameter("remarks");
		String activeTag = request.getParameter("activeTag");
		
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("firstName", firstName);
		params.put("middleInitial", middleInitial);
		params.put("lastName", lastName);
		params.put("email", email);
		params.put("activeTag", activeTag);
		params.put("remarks", remarks);
		params.put("lastUserId", loggedInId);
		
		return this.getDao().updateUser(params);
	}
	
	@Override
	public SystemStatus updatePassword(HttpServletRequest request) throws SQLException {
		String userId = request.getParameter("userId");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		HttpSession session = request.getSession();
		String loggedInId = (String) session.getAttribute("user_auth");
		
		String defaultPassword = "";
		String userID = "";
		
		List<Users> users = this.getUser(userId);
		
		for (Users u : users) {	
			userID = u.getUserId();
			defaultPassword = u.getPassword();
		}		
		
		if (Password.checkPassword(currentPassword,defaultPassword)) {
			System.out.println("Change successful.");
			
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userID);
			params.put("password", Password.hashPassword(newPassword));
			params.put("lastUserId", loggedInId);
			
			return this.getDao().updatePassword(params);
		} else {
			System.out.println("Current password is incorrect.");
			return SystemStatus.notmatched;
		}
	}
	
	@Override
	public SystemStatus insertNewUser(HttpServletRequest request) throws SQLException {

		HttpSession session = request.getSession();
		String loggedInId = (String) session.getAttribute("user_auth");
		
		String userId = request.getParameter("userId");
		String firstName = request.getParameter("firstName");
		String middleInitial = request.getParameter("middleInitial");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String activeTag = request.getParameter("activeTag");
		String userAccess = request.getParameter("userAccess");
		String remarks = request.getParameter("remarks");

		String generatedPassword = Password.hashPassword(userId);
		
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
		params.put("lastUserId", loggedInId);
		
		System.out.println("Record added successfully.");
		return this.getDao().insertNewUser(params);
	}

	public UserMaintenanceDaoImpl getDao() {
		return dao;
	}

	public void setDao(UserMaintenanceDaoImpl dao) {
		this.dao = dao;
	}

	@Override
	public String getPassword(String username) throws SQLException {
		return this.getDao().getPassword(username);
	}

	@Override
	public String getUserAccess(String username) throws SQLException {
		return this.getDao().getUserAccess(username);
	}

}
