package com.ims.dao.impl.peripherals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.peripherals.DaoPeripherals;
import com.ims.model.peripherals.ComputerAssigneeData;
import com.ims.model.peripherals.Peripherals;
import com.ims.utilities.SystemStatus;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class DaoPeripheralsImpl implements DaoPeripherals {
	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peripherals> getPeripherals() throws SQLException {
		return this.getSqlMapClient().queryForList("getPeripherals");
	}

	@Override
	public SystemStatus insertNewPeripherals(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().insert("insertNewPeripheral", params);
			this.getSqlMapClient().executeBatch();
			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;

		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<Peripherals> getPeripheralRecord(int id) throws SQLException {
		return this.getSqlMapClient().queryForList("getPeripheralRecord", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peripherals> getPeripherals(String no) throws SQLException {
		System.out.println(no);
		return this.getSqlMapClient().queryForList("getPeripheralForUnit", no);
	}

	@Override
	public Integer getPeripheralNo() throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject("getPeripheralNo");
	}

	@Override
	public SystemStatus updatePeripheral(Map<String, Object> params) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().update("updatePeripheral", params);
			this.getSqlMapClient().executeBatch();
			this.getSqlMapClient().getCurrentConnection().commit();
			status = SystemStatus.committed;
		} catch (SQLException e) {
			this.getSqlMapClient().getCurrentConnection().rollback();
			e.printStackTrace();
			status = SystemStatus.exception;
		} finally {
			this.getSqlMapClient().endTransaction();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerAssigneeData> getComputerAssigneeData(Integer unitNo) throws SQLException {
		List<ComputerAssigneeData> returnList = new LinkedList<>();
		ComputerAssigneeData returnResultData = new ComputerAssigneeData();
		// select computerassignee
		List<ComputerAssigneeData> computerAssignee = this.getSqlMapClient().queryForList("getComputerAssigneeData", unitNo);
		System.out.println("error here");
		if (!computerAssignee.isEmpty()) {
			returnResultData.setUnitNo(computerAssignee.get(0).getUnitNo());
			returnResultData.setSerialNo(computerAssignee.get(0).getSerialNo());
			returnResultData.setUnitName(computerAssignee.get(0).getUnitName());
			returnResultData.setBrand(computerAssignee.get(0).getBrand());
			returnResultData.setTagNumber(computerAssignee.get(0).getTagNumber());
			returnResultData.setModel(computerAssignee.get(0).getModel());
			returnResultData.setType(computerAssignee.get(0).getType());
			returnResultData.setColor(computerAssignee.get(0).getColor());
			returnResultData.setDeleteTag(computerAssignee.get(0).getDeleteTag());
			returnResultData.setIpAddress(computerAssignee.get(0).getIpAddress());

			System.out.println(computerAssignee.get(0).getUnitNo());
			System.out.println(computerAssignee.get(0).getSerialNo());
			System.out.println(computerAssignee.get(0).getUnitName());
			System.out.println(computerAssignee.get(0).getBrand());
			System.out.println(computerAssignee.get(0).getTagNumber());
			System.out.println(computerAssignee.get(0).getModel());
			System.out.println(computerAssignee.get(0).getType());
			System.out.println(computerAssignee.get(0).getColor());
			System.out.println(computerAssignee.get(0).getDeleteTag());
			System.out.println(computerAssignee.get(0).getIpAddress());

			List<ComputerAssigneeData> assignmentHist = this.getSqlMapClient().queryForList("getAssignmentHistData", unitNo);

			// select assignee
			if (!assignmentHist.isEmpty()) {
				returnResultData.setStatus(assignmentHist.get(0).getStatus());
				returnResultData.setAssigneeNo(assignmentHist.get(0).getAssigneeNo());

				List<ComputerAssigneeData> assignee = this.getSqlMapClient().queryForList("getAssigneeData", assignmentHist.get(0).getAssigneeNo());

				if (!assignee.isEmpty()) {
					returnResultData.setAssigneeName(assignee.get(0).getAssigneeName());
				}
			}
		}
		
		else{
			return null;
		}
		returnList.add(returnResultData);
		return returnList;
	}
}
