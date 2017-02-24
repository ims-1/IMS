package com.ims.dao.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ims.model.unitassignmenthist.UnitAssignmentHist;

public interface DaoUnitAssignmentHist {
	
	public List<UnitAssignmentHist> getUnitAssignmentHist() throws SQLException;
	List<UnitAssignmentHist> getUnitHist(String unitId) throws SQLException;
	void insertNewAssigneeHist(Map<String, Object> params) throws SQLException;
	void updateUnit(Map<String, Object> params) throws SQLException;
}
