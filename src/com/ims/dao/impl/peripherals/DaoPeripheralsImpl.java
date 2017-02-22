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
	public List<Peripherals> getPeripherals(int page, int pageLimit) throws SQLException {

		Map<String, Object> params = new HashMap<>();
		params.put("rowEnd", page * pageLimit);
		params.put("rowStart", (page * pageLimit) - (pageLimit - 1));

		return this.getSqlMapClient().queryForList("getPeripherals", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getTotalPeripherals() throws SQLException {
		return this.getSqlMapClient().queryForList("getTotalPeripherals");
	}

}
