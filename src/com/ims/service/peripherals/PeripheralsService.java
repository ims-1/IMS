package com.ims.service.peripherals;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ims.model.peripherals.Peripherals;

public interface PeripheralsService {
	List<Peripherals> getPeripherals() throws SQLException;
	void insertNewPeripherals(Peripherals peripheral) throws SQLException;
	List<Peripherals> getPeripheralRecord(HttpServletRequest request) throws SQLException;
	List<Peripherals> getPeripherals(HttpServletRequest request) throws SQLException;
	Integer getPeripheralNo() throws SQLException;
	void updatePeripheral(Peripherals peripheral) throws SQLException;
}
