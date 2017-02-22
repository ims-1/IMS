package com.ims.dao.peripherals;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ims.model.peripherals.Peripherals;

public interface DaoPeripherals {
	List<Peripherals> getPeripherals() throws SQLException;
	void insertNewPeripherals(Map<String, Object> params) throws SQLException;
	List<Peripherals> getPeripheralRecord(int id) throws SQLException;
}
