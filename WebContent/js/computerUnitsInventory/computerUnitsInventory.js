var tblComputerUnits = $('tblComputerUnits');
var tablerowCounter = 0;
var unitCounter = 16;
var pageSize = 0;
populateTable();
getComputerType();

$('btnAddUpdate').observe('click', function() {
	if ($F('btnAddUpdate') == "ADD") {
		addUnitsToTable();
		addUnitsToDatabase();
	} else if ($F('btnAddUpdate') == "UPDATE") {
		updateComputerUnit();
	}

});

$('btnDelete').observe('click', function() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "post",
		parameters : {
			action : "deleteComputerUnit",
			unitNo : $F('txtUnitNo')
		},
		onComplete : function(response) {
			clearFields();
		}
	})
});

function addUnitsToTable() {
	tablerowCounter = tablerowCounter + 1;
	unitCounter = unitCounter + 1;
	if (validate()) {
		// create row
		var row = tblComputerUnits.insertRow(tablerowCounter);
		var rwUnitNo = row.insertCell(0);
		var rwUnitName = row.insertCell(1);
		var rwTagNo = row.insertCell(2);
		var rwIp = row.insertCell(3);
		var rwType = row.insertCell(4);
		var rwBrandModel = row.insertCell(5);
		var rwSerialNo = row.insertCell(6);
		var rwAcquiredDate = row.insertCell(7);

		// set attributes
		row.setAttribute('class', 'tblUnitsRow');
		row.setAttribute('onclick', 'selectedRow(this)');
		row.addClassName('record');

		// table values
		rwUnitNo.innerHTML = unitCounter;
		rwUnitName.innerHTML = $F('txtUnitName');
		rwTagNo.innerHTML = $F('txtTagNumber');
		rwIp.innerHTML = $F('txtIpAddress');
		rwType.innerHTML = $F('txtType');
		rwBrandModel.innerHTML = $F('txtBrand') + ' ' + $F('txtModel');
		rwSerialNo.innerHTML = $F('txtSerialNo');
		rwAcquiredDate.innerHTML = $F('txtAcquiredDate');
	}
}

function addUnitsToDatabase() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "post",
		parameters : {
			action : "insertNewComputerUnit",
			unitNo : unitCounter,
			unitName : $F('txtUnitName'),
			tagNumber : $F('txtTagNumber'),
			ipAddress : $F('txtIpAddress'),
			type : $F('txtType'),
			acquiredDate : $F('txtAcquiredDate'),
			description : $F('txtDescription'),
			serialNo : $F('txtSerialNo'),
			brand : $F('txtBrand'),
			model : $F('txtModel'),
			color : $F('txtColor'),
			userId : $F('txtUserId'),
			lastUpdate : $F('txtLastUpdate'),
			remarks : $F('txtRemarks')
		},
		onComplete : function(response) {
		}
	})
};

function updateComputerUnit() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "post",
		parameters : {
			action : "updateComputerUnit",
			unitNo : $F('txtUnitNo'),
			unitName : $F('txtUnitName'),
			tagNumber : $F('txtTagNumber'),
			ipAddress : $F('txtIpAddress'),
			type : $F('txtType'),
			acquiredDate : $F('txtAcquiredDate'),
			description : $F('txtDescription'),
			serialNo : $F('txtSerialNo'),
			brand : $F('txtBrand'),
			model : $F('txtModel'),
			color : $F('txtColor'),
			userId : $F('txtUserId'),
			lastUpdate : $F('txtLastUpdate'),
			remarks : $F('txtRemarks')
		},
		onComplete : function(response) {
		}
	})
}

function selectedRow(row) {
	$$('.record').each(function(x) {
		if (row.rowIndex != r.rowIndex) {
			r.removeClassName('selected');
		}
	})

	row.toggleClassName('selected');

	if (row.hasClassName('selected')) {

	} else {

	}
}

function populateTable() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "pagination"
		},
		onComplete : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');

			p.each(function(computerUnits) {
				var content = "";
				content += "<td>" + computerUnits.unitNo + "</td>";
				content += "<td>" + computerUnits.unitName + "</td>";
				content += "<td>" + computerUnits.tagNumber + "</td>";
				content += "<td>" + computerUnits.ipAddress + "</td>";
				content += "<td>" + computerUnits.type + "</td>";
				content += "<td>" + computerUnits.brand + " "
						+ computerUnits.model
				"</td>";
				content += "<td>" + computerUnits.serialNo + "</td>";
				content += "<td>" + computerUnits.acquiredDate + "</td>";
				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});

			recordEvents();
			getSize();
		}
	})
}

function getSize() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getSize"
		},
		onComplete : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('pagination');
			p.each(function(sizes) {
				pageSize = sizes.listSize;
			});
			var btnCount = parseInt(pageSize / 10);
			if (pageSize % 10 != 0) {
				btnCount += 1;
				for (var a = 1; a <= btnCount; a++) {
					var newBtn = new Element('button');
					newBtn.setAttribute("class", "btn-nav");
					newBtn.update(a);
					newBtn.setAttribute("onclick",
							"getRecordPage(this.innerHTML)");
					parent.insert({
						bottom : newBtn
					});
				}
			}
		}
	})
}

function getRecordPage(x) {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getRecordPage",
			page : x
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			p.each(function(computerUnits) {
				var content = "";
				content += "<td>" + computerUnits.unitNo + "</td>";
				content += "<td>" + computerUnits.unitName + "</td>";
				content += "<td>" + computerUnits.tagNumber + "</td>";
				content += "<td>" + computerUnits.ipAddress + "</td>";
				content += "<td>" + computerUnits.type + "</td>";
				content += "<td>" + computerUnits.brand + " "
						+ computerUnits.model
				"</td>";
				content += "<td>" + computerUnits.serialNo + "</td>";
				content += "<td>" + computerUnits.acquiredDate + "</td>";
				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});

			recordEvents();
		}
	})

}

function filterTable() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getFilteredRecord",
			filterText : $F('txtSearchBox')
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');
			$$('.record').each(function(record) {
				$(record).remove();
			});
			p.each(function(computerUnits) {
				var content = "";
				content += "<td>" + computerUnits.unitNo + "</td>";
				content += "<td>" + computerUnits.unitName + "</td>";
				content += "<td>" + computerUnits.tagNumber + "</td>";
				content += "<td>" + computerUnits.ipAddress + "</td>";
				content += "<td>" + computerUnits.type + "</td>";
				content += "<td>" + computerUnits.brand + " "
						+ computerUnits.model
				"</td>";
				content += "<td>" + computerUnits.serialNo + "</td>";
				content += "<td>" + computerUnits.acquiredDate + "</td>";
				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});

			recordEvents();
			$$('.btn-nav').each(function(record) {
				$(record).remove();
			});
			getFilteredSize();

		}
	})
}

function getFilteredSize() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getFilteredSize"
		},
		onComplete : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('pagination');
			p.each(function(sizes) {
				pageSize = sizes.listSize;
			});
			if (pageSize < 10) {

			} else {

				var btnCount = parseInt(pageSize / 10);
				if (pageSize % 10 != 0) {
					btnCount += 1;
					for (var a = 1; a <= btnCount; a++) {
						var newBtn = new Element('button');
						newBtn.setAttribute("class", "btn-nav");
						newBtn.update(a);
						newBtn.setAttribute("onclick",
								"getFilteredRecordPage(this.innerHTML)");
						parent.insert({
							bottom : newBtn
						});
					}
				}
			}
		}
	})
}

function getFilteredRecordPage(x) {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getFilteredRecordPage",
			page : x
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			p.each(function(computerUnits) {
				var content = "";
				content += "<td>" + computerUnits.unitNo + "</td>";
				content += "<td>" + computerUnits.unitName + "</td>";
				content += "<td>" + computerUnits.tagNumber + "</td>";
				content += "<td>" + computerUnits.ipAddress + "</td>";
				content += "<td>" + computerUnits.type + "</td>";
				content += "<td>" + computerUnits.brand + " "
						+ computerUnits.model
				"</td>";
				content += "<td>" + computerUnits.serialNo + "</td>";
				content += "<td>" + computerUnits.acquiredDate + "</td>";
				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});

			recordEvents();
		}
	})
}

function getComputerType() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getComputerType"
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			p.each(function(TypeList) {
				alert(TypeList.girValue);
			})
		}
	})
}