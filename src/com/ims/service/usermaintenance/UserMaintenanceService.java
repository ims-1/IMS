package com.ims.service.usermaintenance;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ims.model.usermaintenance.Users;
import com.ims.utilities.SystemStatus;

public interface UserMaintenanceService {

	List<Users> getUsers() throws SQLException;
	SystemStatus insertNewUser(HttpServletRequest request) throws SQLException;
	String getUserId(String id) throws SQLException;
	List<Users> getUser(String uId) throws SQLException;
	SystemStatus updateUser(HttpServletRequest request) throws SQLException; 
	String getPassword(String username) throws SQLException;
	String getUserAccess(String username) throws SQLException;
	List<Users> populateUserFields(HttpServletRequest request) throws SQLException;
	SystemStatus updatePassword(HttpServletRequest request) throws SQLException; 
}
