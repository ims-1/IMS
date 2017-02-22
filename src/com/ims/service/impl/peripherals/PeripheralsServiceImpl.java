package com.ims.service.impl.peripherals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.dao.impl.peripherals.DaoPeripheralsImpl;
import com.ims.model.peripherals.Peripherals;
import com.ims.service.peripherals.PeripheralsService;

public class PeripheralsServiceImpl implements PeripheralsService{
private DaoPeripheralsImpl dao;
	
	public DaoPeripheralsImpl getDao() {
		return dao;
	}

	public void setDao(DaoPeripheralsImpl dao) {
		this.dao = dao;
	}	
	
	@Override
	public List<Peripherals> getPeripherals() throws SQLException {
		return this.getDao().getPeripherals();
	}

	@Override
	public void insertNewPeripherals(HttpServletRequest request) throws SQLException {
		String unitNo = request.getParameter("unitNo");
		String peripheralNo = request.getParameter("peripheralNo");
		String serialNo = request.getParameter("serialNo");
		String peripheralType = request.getParameter("peripheralType");
		String brand = request.getParameter("brand");
		String tagNumber = request.getParameter("tagNumber");
		String model = request.getParameter("model");
		String acquiredDate = request.getParameter("acquiredDate");
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		String userId = request.getParameter("userId");
		String remarks = request.getParameter("remarks");
		
		Map<String, Object> params = new HashMap<>();
		params.put("peripheralNo", peripheralNo);
		params.put("serialNo", serialNo);
		params.put("peripheralType", peripheralType);
		params.put("brand", brand);
		params.put("tagNumber", tagNumber);
		params.put("model", model);
		params.put("acquiredDate", acquiredDate);
		params.put("color", color);
		params.put("description", description);
		params.put("userId", userId);
		params.put("remarks", remarks);
		
		this.getDao().insertNewPeripherals(params);
	}

	@Override
	public List<Peripherals> getPeripheralRecord(HttpServletRequest request) throws SQLException {
		
		int id = Integer.parseInt(request.getParameter("peripheralId"));
		
		return this.getDao().getPeripheralRecord(id);
	}
}
 