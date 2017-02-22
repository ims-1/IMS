package com.ims.dao.usermaintenance;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ims.model.usermaintenance.Users;

public interface UserMaintenanceDao {

	List<Users> getUsers() throws SQLException;
	void insertNewUser(Map<String, Object> params) throws SQLException;
	String getUserId(String id) throws SQLException;
}
