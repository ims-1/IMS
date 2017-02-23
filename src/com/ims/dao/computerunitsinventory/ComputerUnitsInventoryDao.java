package com.ims.dao.computerunitsinventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ims.entity.computerunitsinventory.ComputerUnits;

public interface ComputerUnitsInventoryDao {
	List<ComputerUnits> getComputerUnits() throws SQLException;
	
	List<ComputerUnits> getComputerUnitsByUnitNo(Integer unitNo) throws SQLException;
	
	void insertComputerUnits(Map<String, Object> params) throws SQLException;

	void deleteComputerUnit(Map<String, Object> params) throws SQLException;

	void updateComputerUnit(Map<String, Object> params) throws SQLException;
	
}
