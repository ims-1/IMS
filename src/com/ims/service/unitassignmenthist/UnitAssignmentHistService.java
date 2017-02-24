package com.ims.service.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;

import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignment.UnitAssignment;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;

public interface UnitAssignmentHistService {

	List<UnitAssignmentHist> getUnitAssignmentHist() throws SQLException;
	List<UnitAssignmentHist> getUnitHist(String unitId) throws SQLException;
	void insertNewAssigneeHist(UnitAssignmentHist unitassignmentHist) throws SQLException; 
	void updateUnit(UnitAssignmentHist unitassignmentHist) throws SQLException;
}
