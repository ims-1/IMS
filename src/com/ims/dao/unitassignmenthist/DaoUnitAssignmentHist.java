package com.ims.dao.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;

import com.ims.model.unitassignmenthist.UnitAssignmentHist;

public interface DaoUnitAssignmentHist {
	
	public List<UnitAssignmentHist> getUnitAssignmentHist() throws SQLException;
}
