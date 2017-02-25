package com.ims.service.impl.unitassignmenthist;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<UnitAssignmentHist> getUnitHist(String unitId) throws SQLException {
		return this.getDao().getUnitHist(unitId);
	}

	@Override
	public void insertNewAssigneeHist(UnitAssignmentHist unitassignmentHist) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitassignmentHist.getUnitNo());
		params.put("unitName", unitassignmentHist.getUnitName());
		params.put("assigneeNo", unitassignmentHist.getAssigneeNo());
		params.put("location", unitassignmentHist.getLocation());
		params.put("status", unitassignmentHist.getStatus());
		params.put("ipAddress", unitassignmentHist.getIpAddress());
		params.put("assignedBy", unitassignmentHist.getAssignedBy());
		params.put("returnDate", unitassignmentHist.getReturnDate());
		params.put("userId", "ydt");

		this.getDao().insertNewAssigneeHist(params);
	}

	@Override
	public void updateUnit(UnitAssignmentHist unitassignmentHist) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitassignmentHist.getUnitNo());
		params.put("unitName", unitassignmentHist.getUnitName());
		params.put("assigneeNo", unitassignmentHist.getAssigneeNo());
		params.put("location", unitassignmentHist.getLocation());
		params.put("status", unitassignmentHist.getStatus());
		params.put("ipAddress", unitassignmentHist.getIpAddress());
		params.put("assignedBy", unitassignmentHist.getAssignedBy());

		this.getDao().updateUnit(params);
		
	}	

}
