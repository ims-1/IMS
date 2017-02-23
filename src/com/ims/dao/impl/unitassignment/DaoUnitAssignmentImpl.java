package com.ims.dao.impl.unitassignment;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.unitassignment.DaoUnitAssignment;
import com.ims.dao.unitassignmenthist.DaoUnitAssignmentHist;
import com.ims.model.unitassignment.UnitAssignment;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;

public class DaoUnitAssignmentImpl implements DaoUnitAssignment {

	private SqlMapClient sqlMapClient;
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	} 
	
	@SuppressWarnings("unchecked")
	public List<UnitAssignment> getUnitAssignment() throws SQLException {
		return this.getSqlMapClient().queryForList("getUnitAssignment");
	}

	
}
