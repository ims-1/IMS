package com.ims.service.computerunitsinventory;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface ComputerUnitsInventoryService {
	void getComputerUnits() throws SQLException;

	void insertComputerUnits(HttpServletRequest request) throws SQLException;
}
