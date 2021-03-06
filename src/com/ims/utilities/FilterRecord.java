package com.ims.utilities;

import java.util.LinkedList;
import java.util.List;

import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;
import com.ims.model.usermaintenance.Users;

public class FilterRecord {
	public static List<ComputerUnits> getFilterCompUnits(List<ComputerUnits> computerUnits, String words) {

		List<ComputerUnits> filterList = new LinkedList<>();
		for (int x = 0; x < computerUnits.size(); x++) {
			String type = computerUnits.get(x).getType().equals("DT") ? "Desktop" : "Laptop";
			if (computerUnits.get(x).getUnitName().toUpperCase().contains(words)
					|| computerUnits.get(x).getAcquiredDate().toString().toUpperCase().contains(words)
					|| computerUnits.get(x).getBrand().toUpperCase().contains(words)
					|| computerUnits.get(x).getIpAddress().toUpperCase().contains(words)
					|| computerUnits.get(x).getModel().toUpperCase().contains(words)
					|| computerUnits.get(x).getUnitNo().toString().toUpperCase().contains(words)
					|| computerUnits.get(x).getTagNumber().toUpperCase().contains(words)
					|| computerUnits.get(x).getSerialNo().toUpperCase().contains(words)
					|| type.toUpperCase().contains(words)) {
				filterList.add(computerUnits.get(x));
			}
		}
		return filterList;
	}

	public static List<UnitAssignmentHist> getFilterUnitAssignmentHist(List<UnitAssignmentHist> unitAssignmentHist, String words) {
		List<UnitAssignmentHist> filterList = new LinkedList<>();
		for (int x = 0; x < unitAssignmentHist.size(); x++) {
			if (unitAssignmentHist.get(x).getHistNo().toString().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getUnitNo().toString().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getUnitName().toString().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getAssigneeNo().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getLocation().toUpperCase().contains(words) 
					|| unitAssignmentHist.get(x).getAssignedBy().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getIpAddress().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getStatus().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getAssignedDate().toString().toUpperCase().contains(words)
					|| unitAssignmentHist.get(x).getReturnDate().toString().toUpperCase().contains(words)){
				// TODO-Phyllis do the rest of the getters
				filterList.add(unitAssignmentHist.get(x));
			}
		}
		return filterList;
	}

	public static List<Users> getFilterUsers(List<Users> users, String words) {
		List<Users> filterList = new LinkedList<>();
		for (int x = 0; x < users.size(); x++) {
			if (users.get(x).getFirstName().toUpperCase().contains(words)
					|| users.get(x).getMiddleInitial().toUpperCase().contains(words)
					|| users.get(x).getLastName().toString().toUpperCase().contains(words)
					|| users.get(x).getEmail().toUpperCase().contains(words)
					|| users.get(x).getUserId().toUpperCase().contains(words)
					|| users.get(x).getUserAccess().toUpperCase().contains(words)
					|| users.get(x).getActiveTag().toUpperCase().contains(words)){
				//TODO-Sharlene do the rest of the getters
				filterList.add(users.get(x));
			}
		}
		return filterList;
	}
}
