package com.ims.service.unitassignment;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignment.Assignee;
import com.ims.model.unitassignment.UnitAssignment;



public interface UnitAssignmentService {

	List<UnitAssignment> getUnitAssignment() throws SQLException;
	List<Assignee> getAssignee(String assigneeNo) throws SQLException;
	void insertNewAssignee(UnitAssignment unitassignment) throws SQLException; 
	List<ComputerUnits> getUnit(String unitId) throws SQLException;
	void deleteUnit(UnitAssignment unitassignment) throws SQLException;
}
