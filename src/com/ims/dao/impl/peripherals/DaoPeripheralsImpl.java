package com.ims.dao.impl.peripherals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.peripherals.DaoPeripherals;
import com.ims.model.peripherals.Peripherals;

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
	public void insertNewPeripherals(Map<String, Object> params) throws SQLException {
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().insert("insertNewPeripheral", params);
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

	@SuppressWarnings("unchecked")
	public List<Peripherals> getPeripheralRecord(int id) throws SQLException{
		return this.getSqlMapClient().queryForList("getPeripheralRecord", id);
	}
}
