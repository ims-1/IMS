package com.ims.service.impl.computerunitsinventory;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.dao.impl.computerunitsinventory.ComputerUnitsInventoryImpl;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;

public class ComputerUnitsInventoryServiceImpl implements ComputerUnitsInventoryService {

	private ComputerUnitsInventoryImpl dao;

	public ComputerUnitsInventoryImpl getDao() {
		return dao;
	}

	public void setDao(ComputerUnitsInventoryImpl dao) {
		this.dao = dao;
	}

	@Override
	public void getComputerUnits() throws SQLException {
		List<ComputerUnits> computerUnitsList = this.getDao().getComputerUnits();

		for (ComputerUnits x : computerUnitsList) {
			System.out.println(x.getRemarks());
		}
	}

	@Override
	public void insertComputerUnits(HttpServletRequest request) throws SQLException {
		System.out.println("begin");
		Integer unitNo = Integer.parseInt(request.getParameter("unitNo"));
		String unitName = request.getParameter("unitName");
		String tagNumber = request.getParameter("tagNumber");
		String ipAddress = request.getParameter("ipAddress");
		String type = request.getParameter("type");
		String acquiredDate = request.getParameter("acquiredDate");
		String description = request.getParameter("description");
		String serialNo = request.getParameter("serialNo");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String color = request.getParameter("color");
		String userId = request.getParameter("userId");
		String lastUpdate = request.getParameter("lastUpdate");
		String remarks = request.getParameter("remarks");
		System.out.println("med");
		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitNo);
		params.put("unitName", unitName);
		params.put("tagNumber", tagNumber);
		params.put("ipAddress", ipAddress);
		params.put("type", type);
		params.put("acquiredDate", acquiredDate);
		params.put("description", description);
		params.put("serialNo", serialNo);
		params.put("brand", brand);
		params.put("model", model);
		params.put("color", color);
		params.put("userId", userId);
		params.put("lastUpdate", lastUpdate);
		params.put("remarks", remarks);
		System.out.println("end");
		this.getDao().insertComputerUnits(params);
		System.out.println("end2s");

	}

	/*
	 * Integer employeeId =
	 * Integer.parseInt(request.getParameter("employee_id")); String firstName =
	 * request.getParameter("first_name");
	 * 
	 * Map<String, Object> params = new HashMap<>(); params.put("employeeId",
	 * employeeId); params.put("firstName", firstName);
	 * 
	 * this.getDao().insertNewEmp(params);
	 */

}
