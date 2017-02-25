package com.ims.dao.computerunitsinventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ims.entity.computerunitsinventory.ComputerType;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.utilities.SystemStatus;

public interface ComputerUnitsInventoryDao {
	List<ComputerUnits> getComputerUnits() throws SQLException;

	List<ComputerUnits> getMaxUnitNumber() throws SQLException;

	List<ComputerUnits> getComputerUnitsByUnitNo(Integer unitNo) throws SQLException;

	List<ComputerType> getComputerType() throws SQLException;

	SystemStatus insertComputerUnits(Map<String, Object> params) throws SQLException;

	SystemStatus deleteComputerUnit(Map<String, Object> params) throws SQLException;

	SystemStatus updateComputerUnit(Map<String, Object> params) throws SQLException;
	
}
