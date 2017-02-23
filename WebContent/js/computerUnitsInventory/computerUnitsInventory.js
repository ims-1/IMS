var today = new Date().toLocaleDateString();

$('txtLastUpdate').value = today;
var tblComputerUnits = $('tblComputerUnits');
var tablerowCounter = 0;
var pageSize = 0;
getComputerType();
populateTable();

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
			populateTable();
			clearFields();
		}
	})
});

function addUnitsToTable() {
	if (validate()) {
		tablerowCounter = tablerowCounter + 1;
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

			populateTable();

		}
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}

function addUnitsToDatabase() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "post",
		parameters : {
			action : "insertNewComputerUnit",
			unitName : $F('txtUnitName'),
			tagNumber : $F('txtTagNumber'),
			ipAddress : $F('txtIpAddress'),
			type : $F('txtType') == "Desktop" ? "DT" : "LT",
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
			type : $F('txtType') == "Desktop" ? "DT" : "LT",
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
	populateTable();
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
			$$('.btn-nav').each(function(record) {
				$(record).remove();
			});
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
			var parent = $('divSelectType');
			var content = "";
			content += "<option> </option>";
			p.each(function(TypeList) {
				content += "<option>" + TypeList.girMeaning + "</option>";
			})

			var newSelect = new Element("select");
			newSelect.setAttribute("id", "txtType");
			newSelect.setAttribute("class", "form-control");
			newSelect.update(content);
			parent.insert({
				bottom : newSelect
			})
		}
	})
}

$('btnSave').observe('click', function() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "saveCompList"
		},
		onSuccess : function(response) {

		}
	})
})