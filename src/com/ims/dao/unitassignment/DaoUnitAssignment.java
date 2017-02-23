package com.ims.dao.unitassignment;

import java.sql.SQLException;
import java.util.List;

import com.ims.model.unitassignment.UnitAssignment;

public interface DaoUnitAssignment {
	
	public List<UnitAssignment> getUnitAssignment() throws SQLException;
}
