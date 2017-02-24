package com.ims.service.homeandlogin;

import java.sql.SQLException;

public interface LoginService {

	String getLoginId(String id) throws SQLException;
}
