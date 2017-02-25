package com.ims.dao.unitassignment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignment.Assignee;
import com.ims.model.unitassignment.UnitAssignment;


public interface DaoUnitAssignment {
	
	public List<UnitAssignment> getUnitAssignment() throws SQLException;
	List<Assignee> getAssignee(String assigneeId) throws SQLException;
	void insertNewAssignee(Map<String, Object> params) throws SQLException;
	List<ComputerUnits> getUnit(String unitId) throws SQLException;
	void deleteUnit(Map<String, Object> params) throws SQLException;
	List<Assignee> getAssigneeList() throws SQLException;
}
