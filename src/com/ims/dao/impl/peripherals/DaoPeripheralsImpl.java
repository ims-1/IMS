package com.ims.dao.impl.peripherals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ims.dao.peripherals.DaoPeripherals;
import com.ims.entity.computerunitsinventory.ComputerUnits;
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
		return this.getSqlMapClient().queryForList("getComputerAssigneeData", unitNo);
	}

	@Override
	public SystemStatus deletePeripheral(Integer no) throws SQLException {
		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			this.getSqlMapClient().delete("deletePeripheral", no);
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

	@Override
	public SystemStatus savePeripherals(List<Peripherals> sessionPeripherals) throws SQLException {

		SystemStatus status = SystemStatus.ok;
		try {
			this.getSqlMapClient().startTransaction();
			this.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			this.getSqlMapClient().startBatch();
			for (Peripherals p : sessionPeripherals) {
				if (p.getStatus().equals("Add")) {
					Map<String, Object> params = new HashMap<>();
					params.put("unitNo", p.getUnitNo());
					params.put("peripheralNo", p.getPeripheralNo());
					params.put("serialNo", p.getSerialNo());
					params.put("peripheralType", p.getPeripheralType());
					params.put("brand", p.getBrand());
					params.put("tagNumber", p.getTagNumber());
					params.put("model", p.getModel());
					params.put("acquiredDate", p.getAcquiredDate());
					params.put("color", p.getColor());
					params.put("description", p.getColor());
					params.put("userId", p.getUserId());
					params.put("remarks", p.getRemarks());
					this.insertNewPeripherals(params);
				}
				else{
					Map<String, Object> params = new HashMap<>();
					params.put("peripheralNo", p.getPeripheralNo());
					params.put("peripheralType", p.getPeripheralType());
					params.put("tagNumber", p.getTagNumber());
					params.put("acquiredDate", p.getAcquiredDate());
					params.put("description", p.getDescription());
					params.put("serialNo", p.getSerialNo());
					params.put("brand", p.getBrand());
					params.put("model", p.getModel());
					params.put("color", p.getColor());
					params.put("remarks", p.getRemarks());
					params.put("userId", p.getUserId());
					this.updatePeripheral(params);
				}
			}
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
}
