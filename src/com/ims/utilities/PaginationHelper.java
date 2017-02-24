package com.ims.utilities;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.peripherals.Peripherals;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;
import com.ims.model.usermaintenance.Users;

public class PaginationHelper {
	public static String getPagePeripherals(List<Peripherals> peripherals, int page, int pageLimit, int size) {
		List<Peripherals> returnPeripherals = new LinkedList<>();
		String json = null;
		if (!peripherals.isEmpty()) {

			int startRow = page == 0 ? 0 : (page * pageLimit) - pageLimit;

			int endRow = page == 0 ? pageLimit : (page * pageLimit);
			endRow = endRow > peripherals.size() ? peripherals.size() : endRow;

			for (int start = startRow; start < endRow; start++) {
				returnPeripherals.add(peripherals.get(start));
			}
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
			json = gson.toJson(returnPeripherals);

		}
		return json;
	}

	public static String getPageComputerUnit(List<ComputerUnits> computerUnits, int page, int pageLimit, int size) {
		List<ComputerUnits> returnComputerUnits = new LinkedList<>();
		String json = null;
		if (!computerUnits.isEmpty()) {

			int startRow = page == 0 ? 0 : (page * pageLimit) - pageLimit;

			int endRow = page == 0 ? pageLimit : (page * pageLimit);
			endRow = endRow > computerUnits.size() ? computerUnits.size() : endRow;

			for (int start = startRow; start < endRow; start++) {
				returnComputerUnits.add(computerUnits.get(start));
			}
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
			json = gson.toJson(returnComputerUnits);

		}
		return json;
	}

	public static String getPageUsers(List<Users> users, int page, int pageLimit, int size) {
		List<Users> returnUsers = new LinkedList<>();
		String json = null;
		if (!users.isEmpty()) {

			int startRow = page == 0 ? 0 : (page * pageLimit) - pageLimit;

			int endRow = page == 0 ? pageLimit : (page * pageLimit);
			endRow = endRow > users.size() ? users.size() : endRow;

			for (int start = startRow; start < endRow; start++) {
				returnUsers.add(users.get(start));
			}
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
			json = gson.toJson(returnUsers);

		}
		return json;
	}

	public static String getPageUnitAssignmentHist(List<UnitAssignmentHist> unitAssignmentHist, int page, int pageLimit,
			int size) {
		List<UnitAssignmentHist> returnUnitAssignment = new LinkedList<>();
		String json = null;
		if (!unitAssignmentHist.isEmpty()) {

			int startRow = page == 0 ? 0 : (page * pageLimit) - pageLimit;

			int endRow = page == 0 ? pageLimit : (page * pageLimit);
			endRow = endRow > unitAssignmentHist.size() ? unitAssignmentHist.size() : endRow;

			for (int start = startRow; start < endRow; start++) {
				returnUnitAssignment.add(unitAssignmentHist.get(start));
			}
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
			json = gson.toJson(returnUnitAssignment);

		}
		return json;
	}
}
