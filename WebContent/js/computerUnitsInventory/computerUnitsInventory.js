var today = new Date().toLocaleDateString();

$('txtLastUpdate').value = today;
var tblComputerUnits = $('tblComputerUnits');
var tablerowCounter = 0;
var pageSize = 0;
getComputerType();
populateTable();
getUserAuth();

function getUserAuth() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "getUserAuth"
		},
		onSuccess : function(response) {
			$('txtUserId').value = response.responseText;
		},
		onError : function(response) {
			alert("User error! Reload page.");
		}
	})
}

$('btnAddUpdate').observe('click', function() {
	if ($F('btnAddUpdate') == "ADD") {
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
			num : $F('txtUnitNo')
		},
		onComplete : function(response) {
			populateTable();
			clearFields();
			alert(response.responseText);
		},
		onFailure : function(response){
			alert("Failed to load page!")
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

		}
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}

function addUnitsToDatabase() {
	getUserAuth();
	if (validate()) {
		var x = 0;
		new Ajax.Request(context + "/ComputerUnitsInventoryController", {
			method : "post",
			parameters : {
				action : "insertNewComputerUnit",
				unitNo : x,
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
				// alert(response.responseText);
				var p = response.responseText.evalJSON();
				var parent = $('body');
				$$('.record').each(function(record) {
					$(record).remove();
				});

				p.each(function(computerUnits) {
					var content = "";
					content += "<td>" + "Temp" + "</td>";
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
			},
			onFailure: function(response){
				alert("Failed to load page!")
			}
		})
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
};

function updateComputerUnit() {
	getUserAuth();
	var userAuth = $('txtUserId').value;
	if (validate()) {
		new Ajax.Request(
				context + "/ComputerUnitsInventoryController",
				{
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
						if (response.status == 200) {

							var p = response.responseText.evalJSON();

							$$('.record')
									.each(
											function(record) {

												if (p.unitNo == record.down(0).innerHTML) {
													record.down(1).innerHTML = p.unitName;
													record.down(2).innerHTML = p.tagNumber;
													record.down(3).innerHTML = p.ipAddress;
													record.down(4).innerHTML = p.type == "DT" ? "Desktop"
															: "Laptop";
													record.down(5).innerHTML = p.brand
															+ " " + p.model;
													record.down(6).innerHTML = p.serialNo;
													record.down(7).innerHTML = p.acquiredDate;
												}

											});
						} else if (response.status == 210) {
							alert("No changes to update.");
						}
					}
				});
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}

function populateTable() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "get",
		parameters : {
			action : "pagination"
		},
		onComplete : function(response) {
			if (response.status == 200) {

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

			} else {
				$$('.record').each(function(record) {
					$(record).remove();
				});

				var parent = $('body');

				var content = "<td colspan=7>No record found.</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "no-record record");
				newTr.setAttribute("align", "center");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});

				$('pagination').innerHTML = '';
				alert("There is no Computer Units record fetched");
			}
		},
		onFailure : function(response) {
			console.log("There is something wrong. Please check connection");
			alert("There is something wrong. Please check connection");
		}
	})
}

function getSize() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController",
			{
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
					if (pageSize % 10 != 0 && pageSize > 10) {
						btnCount += 1;
					}
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
				},
				onFailure : function(response) {
					alert("There is something wrong!");
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
		},
		onFailure : function(response) {
			alert("There is something wrong!");
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
			if (response.status == 200) {

				$$('.btn-nav').each(function(record) {
					$(record).remove();
				});
				$$('.record').each(function(record) {
					$(record).remove();
				});
				$('body').clear;

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

				getFilteredSize();

			} else {
				$$('.record').each(function(record) {
					$(record).remove();
				});

				var parent = $('body');

				var content = "<td colspan=7>No record found.</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "no-record record");
				newTr.setAttribute("align", "center");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});

				$('pagination').innerHTML = '';
				alert("There is no Computer Units record fetched");
			}
		},
		onFailure : function(response) {
			console.log("There is something wrong. Please check connection");
			alert("There is something wrong. Please check connection");
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
		},
		onFailure : function(response) {
			alert("There is something wrong!");
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
		},
		onFailure : function(response) {
			alert("There is something wrong!");
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
			parent.innerHTML = "";
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
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	})
}

$('btnSave').observe('click', function() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "post",
		parameters : {
			action : "save"
		},
		onSuccess : function(response) {
			alert("Changes Saved!");
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	})
	populateTable();
})

$('btnCancel').observe('click', function() {
	new Ajax.Request(context + "/ComputerUnitsInventoryController", {
		method : "post",
		parameters : {
			action : "cancel"
		},
		onSuccess : function(response) {
			alert("Changes cancelled!");
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	})
	clearFields();
})

$('btnPeripherals').observe('click', function() {
	window.location.href = "/IMS/peripherals?unitNo=" + $F('txtUnitNo');
})

$('btnUnitAssignment').observe('click', function() {
	window.location.href = "/IMS/unitassignment?unitNo=" + $F('txtUnitNo');
});