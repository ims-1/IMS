package com.ims.dao.impl.usermaintenance;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.usermaintenance.UserMaintenanceDao;
import com.ims.model.usermaintenance.Users;

public class UserMaintenanceDaoImpl implements UserMaintenanceDao {

	private SqlMapClient sqlMapClient;

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsers() throws SQLException {
		return this.getSqlMapClient().queryForList("getUsers");
	}

	@Override
	public String getUserId(String id) throws SQLException {
		return (String) this.getSqlMapClient().queryForObject("getUserId", id);
	}
	
	@Override
	public void updateUser(Map<String, Object> params) throws SQLException {
		this.getSqlMapClient().update("updateUser", params);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUser(String uId) throws SQLException {
		return this.getSqlMapClient().queryForList("getUser", uId);
	}

	@Override
	public void insertNewUser(Map<String, Object> params) throws SQLException {

		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();

			this.getSqlMapClient().insert("insertNewUser", params);
			this.getSqlMapClient().executeBatch();

			this.getSqlMapClient().getCurrentConnection().commit();
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			System.out.println("in catch");
			e.printStackTrace();
		} finally {
			this.getSqlMapClient().endTransaction();
		}

	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
