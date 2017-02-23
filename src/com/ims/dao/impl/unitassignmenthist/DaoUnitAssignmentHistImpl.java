package com.ims.dao.impl.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.unitassignmenthist.DaoUnitAssignmentHist;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;


public class DaoUnitAssignmentHistImpl implements DaoUnitAssignmentHist {

	
	private SqlMapClient sqlMapClient;
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	} 
	
	@SuppressWarnings("unchecked")
	public List<UnitAssignmentHist> getUnitAssignmentHist() throws SQLException {
		return this.getSqlMapClient().queryForList("getUnitAssignmentHist");
	}
}
