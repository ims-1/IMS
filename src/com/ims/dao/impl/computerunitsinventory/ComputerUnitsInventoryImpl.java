package com.ims.dao.impl.computerunitsinventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.computerunitsinventory.ComputerUnitsInventoryDao;
import com.ims.entity.computerunitsinventory.ComputerType;
import com.ims.entity.computerunitsinventory.ComputerUnits;

public class ComputerUnitsInventoryImpl implements ComputerUnitsInventoryDao {
	private SqlMapClient sqlMapClient;

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

	@Override
	public void deleteComputerUnit(Map<String, Object> params) throws SQLException {
		this.getSqlMapClient().update("deleteComputerUnit", params);

	}

	@Override
	public void updateComputerUnit(Map<String, Object> params) throws SQLException {
		this.getSqlMapClient().update("updateComputerUnit", params);
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

}
