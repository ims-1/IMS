package com.ims.service.impl.peripherals;

import java.sql.SQLException;
import java.util.List;


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
}
 