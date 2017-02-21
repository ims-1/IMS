package com.ims.dao.peripherals;

import java.sql.SQLException;
import java.util.List;

import com.ims.model.peripherals.Peripherals;

public interface DaoPeripherals {
	List<Peripherals> getPeripherals(int rowStart)throws SQLException ;
}
 