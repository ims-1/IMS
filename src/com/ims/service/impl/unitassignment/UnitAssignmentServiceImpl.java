package com.ims.service.impl.unitassignment;

import java.sql.SQLException;
import java.util.List;


import com.ims.dao.impl.unitassignment.DaoUnitAssignmentImpl;
import com.ims.model.unitassignment.UnitAssignment;
import com.ims.service.unitassignment.UnitAssignmentService;

public class UnitAssignmentServiceImpl implements UnitAssignmentService {
	private DaoUnitAssignmentImpl dao;
	
	public DaoUnitAssignmentImpl getDao() {
		return dao;
	}

	public void setDao(DaoUnitAssignmentImpl dao) {
		this.dao = dao;
	}

	@Override
	public List<UnitAssignment> getUnitAssignment() throws SQLException {
		return this.getDao().getUnitAssignment();
	}	

	
}