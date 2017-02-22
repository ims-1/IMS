package com.ims.service.usermaintenance;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ims.model.usermaintenance.Users;

public interface UserMaintenanceService {

	List<Users> getUsers() throws SQLException;
	void insertNewUser(HttpServletRequest request) throws SQLException;
	String getUserId(String id) throws SQLException;
}
