package com.ims.service.usermaintenance;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ims.model.usermaintenance.Users;

public interface UserMaintenanceService {

	List<Users> getUsers() throws SQLException;
	void insertNewUser(HttpServletRequest request) throws SQLException;
	String getUserId(String id) throws SQLException;
	List<Users> getUser(String uId) throws SQLException;
	void updateUser(HttpServletRequest request) throws SQLException; 
	String getPassword(String username) throws SQLException;
	String getUserAccess(String username) throws SQLException;
	void populateUserFields(HttpServletRequest request) throws SQLException;
	void confirmPassword(HttpServletRequest request) throws SQLException;
	void updatePassword(HttpServletRequest request) throws SQLException; 
}
