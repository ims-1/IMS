package com.ims.service.impl.unitassignmenthist;

import java.sql.SQLException;
import java.util.List;

import com.ims.dao.impl.unitassignmenthist.DaoUnitAssignmentHistImpl;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;
import com.ims.service.unitassignmenthist.UnitAssignmentHistService;

public class UnitAssignmentHistServiceImpl implements UnitAssignmentHistService{

private DaoUnitAssignmentHistImpl dao;
	
	public DaoUnitAssignmentHistImpl getDao() {
		return dao;
	}

	public void setDao(DaoUnitAssignmentHistImpl dao) {
		this.dao = dao;
	}

	@Override
	public List<UnitAssignmentHist> getUnitAssignmentHist() throws SQLException {
		return this.getDao().getUnitAssignmentHist();
	}	

}
