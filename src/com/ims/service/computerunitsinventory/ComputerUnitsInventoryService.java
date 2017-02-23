package com.ims.service.computerunitsinventory;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ims.entity.computerunitsinventory.ComputerType;
import com.ims.entity.computerunitsinventory.ComputerUnits;

public interface ComputerUnitsInventoryService {
	List<ComputerUnits> getComputerUnits() throws SQLException;

	List<ComputerUnits> getComputerUnitByUnitNo(Integer unitNo) throws SQLException;

	List<ComputerType> getComputerType() throws SQLException;

	void insertComputerUnits(HttpServletRequest request) throws SQLException;

	void deleteComputerUnit(HttpServletRequest request) throws SQLException;

	void updateComputerUnit(HttpServletRequest request) throws SQLException;
}
