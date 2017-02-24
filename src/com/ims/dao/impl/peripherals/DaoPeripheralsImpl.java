package com.ims.dao.impl.peripherals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.peripherals.DaoPeripherals;
import com.ims.model.peripherals.Peripherals;
import com.ims.utilities.SystemStatus;

public class DaoPeripheralsImpl implements DaoPeripherals {
	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peripherals> getPeripherals() throws SQLException {
		return this.getSqlMapClient().queryForList("getPeripherals");
	}

	@Override
	public SystemStatus insertNewPeripherals(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().insert("insertNewPeripheral", params);
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
	public List<Peripherals> getPeripheralRecord(int id) throws SQLException {
		return this.getSqlMapClient().queryForList("getPeripheralRecord", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peripherals> getPeripherals(String no) throws SQLException {
		System.out.println(no);
		return this.getSqlMapClient().queryForList("getPeripheralForUnit", no);
	}

	@Override
	public Integer getPeripheralNo() throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject("getPeripheralNo");
	}

	@Override
	public SystemStatus updatePeripheral(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().update("updatePeripheral", params);
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
}
