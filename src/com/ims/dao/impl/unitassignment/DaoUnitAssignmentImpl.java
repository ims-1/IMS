package com.ims.dao.impl.unitassignment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.unitassignment.DaoUnitAssignment;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignment.Assignee;
import com.ims.model.unitassignment.UnitAssignment;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Assignee> getAssignee(String assigneeId) throws SQLException {
		return this.getSqlMapClient().queryForList("getAssigneeNo", assigneeId);
	}

	@Override
	public void insertNewAssignee(Map<String, Object> params) throws SQLException {

		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().insert("insertUnitAssignment", params);
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
	@Override
	public List<ComputerUnits> getUnit(String unitId) throws SQLException {
		return this.getSqlMapClient().queryForList("getUnitAssignment", unitId);
	}

	@Override
	public void deleteUnit(Map<String, Object> params) throws SQLException {
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().delete("deleteUnit", params);
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
	@Override
	public List<Assignee> getAssigneeList() throws SQLException {
		return this.getSqlMapClient().queryForList("getAssigneeList");
	}


	
}
