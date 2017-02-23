package com.ims.service.unitassignment;

import java.sql.SQLException;
import java.util.List;


import com.ims.model.unitassignment.UnitAssignment;



public interface UnitAssignmentService {

	List<UnitAssignment> getUnitAssignment() throws SQLException;

	
}
