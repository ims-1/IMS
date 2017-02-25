package com.ims.service.impl.usermaintenance;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.dao.impl.usermaintenance.UserMaintenanceDaoImpl;
import com.ims.model.usermaintenance.Users;
import com.ims.service.usermaintenance.UserMaintenanceService;
import com.ims.utilities.Password;

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
	public void populateUserFields(HttpServletRequest request) throws SQLException {
		//populate fields
		String uId = request.getParameter("userId");
		request.setAttribute("hidden", "edit");
		//disable uid button
		request.setAttribute("disableUserId", "disabled='disabled'");
		List<Users> users = this.getUser(uId);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		for (Users u : users) {
			request.setAttribute("userId", 			u.getUserId());
			request.setAttribute("firstName", 		u.getFirstName());
			request.setAttribute("middleInitial", 	u.getMiddleInitial());
			request.setAttribute("lastName", 		u.getLastName());
			request.setAttribute("email", 			u.getEmail());
			request.setAttribute("activeTag", 		u.getActiveTag());
			request.setAttribute("userAccess", 		u.getUserAccess());
			request.setAttribute("entryDate", 		formatter.format(u.getEntryDate()));
			request.setAttribute("remarks", 		u.getRemarks());
			request.setAttribute("lastUserId", 		u.getLastUserId());
			request.setAttribute("lastUpdate", 		formatter.format(u.getLastUpdate()));	
			request.setAttribute("password", 		u.getPassword());	
		}				
	}
	
	@Override
	public void updateUser(HttpServletRequest request) throws SQLException {
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
		
		this.getDao().updateUser(params);
	}
	
	@Override
	public void updatePassword(HttpServletRequest request) throws SQLException {
		String userId = request.getParameter("userId");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		String defaultPassword = "";
		String userID = "";
		
		List<Users> users = this.getUser(userId);

		System.out.println("CURRENT PASSWORD "+ currentPassword);
		
		for (Users u : users) {	
			userID = u.getUserId();
			defaultPassword = u.getPassword();
		}		
		System.out.println("DEFAULT PASSWORD "+defaultPassword);
		
		if (Password.checkPassword(currentPassword,defaultPassword)) {
			System.out.println("Change successful.");
			
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userID);
			params.put("password", Password.hashPassword(newPassword));
			
			this.getDao().updatePassword(params);
		} else {
			System.out.println("Current password is incorrect.");
		}
	}
	
	@Override
	public void confirmPassword(HttpServletRequest request) throws SQLException {
		
	}
	
	@Override
	public void insertNewUser(HttpServletRequest request) throws SQLException {

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
		
		this.getDao().insertNewUser(params);
		
		System.out.println("Record added successfully.");
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
