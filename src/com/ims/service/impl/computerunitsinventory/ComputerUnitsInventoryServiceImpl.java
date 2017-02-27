package com.ims.service.impl.computerunitsinventory;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.dao.impl.computerunitsinventory.ComputerUnitsInventoryImpl;
import com.ims.entity.computerunitsinventory.ComputerType;
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
	public void insertComputerUnits(ComputerUnits compUnitList) throws SQLException {
		SimpleDateFormat fDate = new SimpleDateFormat("dd-MMM-yy");

		String unitName = compUnitList.getUnitName();
		String tagNumber = compUnitList.getTagNumber();
		String ipAddress = compUnitList.getIpAddress();
		String type = compUnitList.getType();
		String acquiredDate = fDate.format(compUnitList.getAcquiredDate());
		String description = compUnitList.getDescription();
		String serialNo = compUnitList.getSerialNo();
		String brand = compUnitList.getBrand();
		String model = compUnitList.getModel();
		String color = compUnitList.getColor();
		String userId = compUnitList.getUserId();
		String lastUpdate = fDate.format(new Date());
		String remarks = compUnitList.getRemarks();

		Map<String, Object> params = new HashMap<>();
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
		Integer unitNo = Integer.parseInt(request.getParameter("num"));

		Map<String, Object> params = new HashMap<>();
		params.put("unitNo", unitNo);

		this.getDao().deleteComputerUnit(params);
	}

	@Override
	public List<ComputerUnits> getComputerUnits() throws SQLException {
		return this.getDao().getComputerUnits();
	}

	@Override
	public List<ComputerUnits> getComputerUnitByUnitNo(Integer unitNo) throws SQLException {
		return this.getDao().getComputerUnitsByUnitNo(unitNo);
	}

	@Override
	public List<ComputerType> getComputerType() throws SQLException {
		return this.getDao().getComputerType();
	}

	@Override
	public List<ComputerUnits> getMaxUnitNumber() throws SQLException {
		return this.getDao().getMaxUnitNumber();
	}

	@Override
	public ComputerUnits returnComputerUnits(HttpServletRequest request) {
		ComputerUnits compUnitModel = new ComputerUnits();
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
		@SuppressWarnings("deprecation")
		Date lastUpdateDate = new Date(lastUpdate);
		@SuppressWarnings("deprecation")
		Date acquiredDate2 = new Date(acquiredDate);
		System.out.println("unitNo");
		Integer unitNo = Integer.parseInt(request.getParameter("unitNo"));
		System.out.println(unitNo);
		if (unitNo != null) {
			System.out.println("ifnull");
			compUnitModel.setUnitNo(unitNo);
		}

		compUnitModel.setAcquiredDate(acquiredDate2);
		compUnitModel.setBrand(brand);
		compUnitModel.setColor(color);
		compUnitModel.setDescription(description);
		compUnitModel.setIpAddress(ipAddress);
		compUnitModel.setLastUpdate(lastUpdateDate);
		compUnitModel.setModel(model);
		compUnitModel.setRemarks(remarks);
		compUnitModel.setSerialNo(serialNo);
		compUnitModel.setTagNumber(tagNumber);
		compUnitModel.setType(type);
		compUnitModel.setUnitName(unitName);
		compUnitModel.setUserId(userId);

		return compUnitModel;
	}

	@Override
	public void updateComputerUnit(ComputerUnits compUnitList) throws SQLException {
		SimpleDateFormat fDate = new SimpleDateFormat("dd-MMM-yy");
		Integer unitNo = compUnitList.getUnitNo();
		String unitName = compUnitList.getUnitName();
		String tagNumber = compUnitList.getTagNumber();
		String ipAddress = compUnitList.getIpAddress();
		String type = compUnitList.getType();
		String acquiredDate = fDate.format(compUnitList.getAcquiredDate());
		String description = compUnitList.getDescription();
		String serialNo = compUnitList.getSerialNo();
		String brand = compUnitList.getBrand();
		String model = compUnitList.getModel();
		String color = compUnitList.getColor();
		String userId = compUnitList.getUserId();
		String lastUpdate = fDate.format(new Date());
		String remarks = compUnitList.getRemarks();

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
		
		System.out.println(userId);
		
		params.put("userId", userId);
		params.put("lastUpdate", lastUpdate);
		params.put("remarks", remarks);
		this.getDao().updateComputerUnit(params);

	}

}
