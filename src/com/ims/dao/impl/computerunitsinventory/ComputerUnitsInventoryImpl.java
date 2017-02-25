package com.ims.dao.impl.computerunitsinventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.computerunitsinventory.ComputerUnitsInventoryDao;
import com.ims.entity.computerunitsinventory.ComputerType;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.utilities.SystemStatus;

public class ComputerUnitsInventoryImpl implements ComputerUnitsInventoryDao {
	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public SystemStatus insertComputerUnits(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().insert("insertComputerUnits", params);
			this.getSqlMapClient().executeBatch();
			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}

	@Override
	public SystemStatus deleteComputerUnit(Map<String, Object> params) throws SQLException {

		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().update("deleteComputerUnit", params);
			this.getSqlMapClient().executeBatch();
			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;

	}

	@Override
	public SystemStatus updateComputerUnit(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().update("updateComputerUnit", params);
			this.getSqlMapClient().executeBatch();
			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerUnits> getComputerUnits() throws SQLException {
		return this.getSqlMapClient().queryForList("getComputerUnits");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerUnits> getComputerUnitsByUnitNo(Integer unitNo) throws SQLException {
		return this.getSqlMapClient().queryForList("getComputerUnitByUnitNo", unitNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerType> getComputerType() throws SQLException {
		return this.getSqlMapClient().queryForList("getComputerType");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerUnits> getMaxUnitNumber() throws SQLException {
		return this.getSqlMapClient().queryForList("getMaxUnitNo");
	}

}
