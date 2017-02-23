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
	public void insertComputerUnits(HttpServletRequest request) throws SQLException {
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
		params.put("deleteTag", "N");
		this.getDao().insertComputerUnits(params);

	}

	@Override
	public void deleteComputerUnit(HttpServletRequest request) throws SQLException {
		Integer unitNo = Integer.parseInt(request.getParameter("unitNo"));

		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitNo);

		this.getDao().deleteComputerUnit(params);
	}

	@Override
	public void updateComputerUnit(HttpServletRequest request) throws SQLException {
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
		this.getDao().updateComputerUnit(params);

	}

	@Override
	public List<ComputerUnits> getComputerUnits() throws SQLException {
		return this.getDao().getComputerUnits();
	}

	@Override
	public List<ComputerUnits> getComputerUnitByUnitNo(Integer unitNo) throws SQLException {
		return this.getDao().getComputerUnitsByUnitNo(unitNo);
	}

}
