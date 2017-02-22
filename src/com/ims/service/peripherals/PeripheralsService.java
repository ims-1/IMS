package com.ims.service.peripherals;

import java.sql.SQLException;
import java.util.List;

import com.ims.model.peripherals.Peripherals;

public interface PeripheralsService {
	List<Peripherals> getPeripherals(int page, int pageLimit) throws SQLException;
	List<Integer> getTotalPeripherals() throws SQLException;
}
