package com.ims.dao.homeandlogin;

import java.sql.SQLException;

public interface LoginDao {
	
	String getLoginId(String id) throws SQLException;
	
}
