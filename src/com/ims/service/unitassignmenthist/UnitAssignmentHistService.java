package com.ims.service.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;

public interface UnitAssignmentHistService {

	List<UnitAssignmentHist> getUnitAssignmentHist() throws SQLException;
}
