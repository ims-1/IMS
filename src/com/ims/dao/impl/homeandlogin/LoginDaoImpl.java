package com.ims.dao.impl.homeandlogin;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.homeandlogin.LoginDao;

public class LoginDaoImpl implements LoginDao {
	
	private SqlMapClient sqlMapClient;

	@Override
	public String getLoginId(String id) throws SQLException {
		return (String) this.getSqlMapClient().queryForObject("getLoginId", id);
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
