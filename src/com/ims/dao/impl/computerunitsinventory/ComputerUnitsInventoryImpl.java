package com.ims.dao.impl.computerunitsinventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.computerunitsinventory.ComputerUnitsInventoryDao;
import com.ims.entity.computerunitsinventory.ComputerUnits;

public class ComputerUnitsInventoryImpl implements ComputerUnitsInventoryDao {
	private SqlMapClient sqlMapClient;

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerUnits> getComputerUnits() throws SQLException {
		return this.getSqlMapClient().queryForList("getComputerUnits");
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public void insertComputerUnits(Map<String, Object> params) throws SQLException {
		 this.getSqlMapClient().insert("insertComputerUnits", params);
	}


}
