package com.ims.dao.impl.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<UnitAssignmentHist> getUnitHist(String unitId) throws SQLException {
		return this.getSqlMapClient().queryForList("getUnitHist", unitId);
	}

	@Override
	public void insertNewAssigneeHist(Map<String, Object> params) throws SQLException {

		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().insert("insertUnitAssignmentHist", params);
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

	@Override
	public void updateUnit(Map<String, Object> params) throws SQLException {
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().update("updateUnitHist", params);
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
}
