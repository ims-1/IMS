package com.ims.dao.impl.peripherals;

import java.sql.SQLException;
import java.util.List;

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
	public List<Peripherals> getPeripherals(int rowStart) throws SQLException {
		return this.getSqlMapClient().queryForList("getPeripherals", rowStart);
	}

}
