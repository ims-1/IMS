package com.ims.dao.impl.usermaintenance;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.usermaintenance.UserMaintenanceDao;
import com.ims.model.usermaintenance.Users;
import com.ims.utilities.SystemStatus;

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
	public SystemStatus updateUser(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();

			this.getSqlMapClient().update("updateUser", params);
			this.getSqlMapClient().executeBatch();

			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			System.out.println("in catch");
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}
	
	@Override
	public SystemStatus updatePassword(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();

			this.getSqlMapClient().update("updatePassword", params);
			this.getSqlMapClient().executeBatch();

			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			System.out.println("in catch");
			e.printStackTrace();
			status= SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUser(String uId) throws SQLException {
		return this.getSqlMapClient().queryForList("getUser", uId);
	}

	@Override
	public SystemStatus insertNewUser(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();

			this.getSqlMapClient().insert("insertNewUser", params);
			this.getSqlMapClient().executeBatch();

			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			System.out.println("in catch");
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public String getPassword(String username) throws SQLException {
	return (String) this.getSqlMapClient().queryForObject("getPassword", username);
	}

	@Override
	public String getUserAccess(String username) throws SQLException {
		return (String) this.getSqlMapClient().queryForObject("getUserAccess", username);
	}

	

}
