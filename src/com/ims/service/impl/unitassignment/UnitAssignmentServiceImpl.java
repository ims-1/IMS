package com.ims.service.impl.unitassignment;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.dao.impl.unitassignment.DaoUnitAssignmentImpl;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignment.Assignee;
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

	public List<Assignee> getAssignee(String assigneeId) throws SQLException {
		return this.getDao().getAssignee(assigneeId);
	}

	@Override
	public void insertNewAssignee(UnitAssignment unitassignment) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitassignment.getUnitNo());
		params.put("assigneeNo", unitassignment.getAssigneeNo());
		params.put("location", unitassignment.getLocation());
		params.put("status", unitassignment.getStatus());
		params.put("ipAddress", unitassignment.getIpAddress());
		params.put("assignedBy", unitassignment.getAssignedBy());
		params.put("userId", "ydt");

		this.getDao().insertNewAssignee(params);
	}
	

	@Override
	public List<ComputerUnits> getUnit(String unitId) throws SQLException {
		return this.getDao().getUnit(unitId);
	}

	@Override
	public void deleteUnit(UnitAssignment unitassignment) throws SQLException {
		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitassignment.getUnitNo());
		params.put("assigneeNo", unitassignment.getAssigneeNo());
		params.put("location", unitassignment.getLocation());
		params.put("status", unitassignment.getStatus());
		params.put("ipAddress", unitassignment.getIpAddress());
		params.put("assignedBy", unitassignment.getAssignedBy());
		
		this.getDao().deleteUnit(params);
	}

	@Override
	public List<Assignee> getAssigneeList() throws SQLException {
		return this.getDao().getAssigneeList();
	}

}