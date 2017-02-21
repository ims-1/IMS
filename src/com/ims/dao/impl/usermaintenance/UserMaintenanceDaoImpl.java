package com.ims.dao.impl.usermaintenance;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.usermaintenance.UserMaintenanceDao;
import com.ims.model.usermaintenance.Users;

public class UserMaintenanceDaoImpl implements UserMaintenanceDao{

	private SqlMapClient sqlMapClient;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsers() throws SQLException {
		return this.getSqlMapClient().queryForList("getUsers");
	}
	
	@Override
	public void insertNewUser(Map<String, Object> params) throws SQLException {
		this.getSqlMapClient().insert("insertNewUser", params);		
	}
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	

}
