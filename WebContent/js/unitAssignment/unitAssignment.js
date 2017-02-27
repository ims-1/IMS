//Populate Computer Units textfields in Unit Assignment page
function populateCompUnits() {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "populate",
			selectUnits : $F("txtUnitNo")
		},
		onSuccess : function(response) {
			var compUnit = response.responseText.evalJSON();
			compUnit.each(function(unit) {
				$("txtSerialNo").value = unit.serialNo;
				$("txtUnitName").value = unit.unitName;
				$("txtBrand").value = unit.brand;
				$("txtTagNumber").value = unit.tagNumber;
				$("txtUnitModel").value = unit.model;
				$("txtUnitType").value = unit.type;
				$("txtUnitColor").value = unit.color;
			});
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// Display the history of the Selected Computer Unit
function getUnitHist() {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "fetch",
			selectUnits : $F("txtUnitNo"),
			page : "1"
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			u.each(function(unit) {
				var units = "";

				units += "<td>" + unit.unitNo + "</td>";
				units += "<td>" + unit.unitName + "</td>";
				units += "<td>" + unit.location + "</td>";
				units += "<td>" + unit.ipAddress + "</td>";
				units += "<td>" + unit.status + "</td>";
				units += "<td>" + unit.assignedBy + "</td>";
				units += "<td>" + unit.assignedDate + "</td>";
				units += "<td>" + unit.returnDate + "</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(units);
				parent.insert({
					bottom : newTr
				});
			});
			getSize();
			recordEvents();
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// Condition if the User came from the Computer Unit Inventory PAge
if ($F("txtUnitNo") != "") {
	populateCompUnits();
	getUnitHist();

	$("btnAssigneeSearch").observe('click', function() {
		fetchAssignees();
	})

	$("btnYes").observe('click', function() {
		if(validate())
		{
			var json = addRecord();
			new Ajax.Request(context + "/UnitAssignmentController", {
				method : "post",
				parameters : {
					action : "assignToDatabase",
					actionTwo : "assignToHistData",
					unitassignment : json,
					unitassignmenthist : json,
					unitId : $F("txtUnitNo")
				},
				onComplete : function(response) {
					// $("mainContents").update(response.responseText);
				}
			});
		}
	}
	)

}
// Fetching the computer units data from the computer units database
function fetchCompUnits() {

	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "fetchUnits",
			selectUnits : $F("txtUnitNo")
		},
		onSuccess : function(response) {
			var compUnit = response.responseText.evalJSON();
			var parent = $('modalContent');

			$$('.modalRecord').each(function(record) {
				$(record).remove();
			});

			compUnit.each(function(unit) {
				var units = "";

				units += '<td>' + unit.unitNo + '</td>';
				units += '<td>' + unit.unitName + '</td>';

				var newTr = new Element('tr');
				newTr.setAttribute("class", "modalRecord");
				newTr.setAttribute("onclick", "getUnit(this)");

				newTr.update(units);
				parent.insert({
					bottom : newTr
				});
			});
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// Function for selecting computer unit through the LOV
function getUnit(compUnitNo) {
	$("txtUnitNo").value = compUnitNo.down(0).innerHTML;
	populateCompUnits();

	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "fetch",
			selectUnits : $F("txtUnitNo"),
			page : "1"
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('body');


			$$('.record').each(function(record) {
				$(record).remove();
			});

			u.each(function(unit) {
				var units = "";

				units += "<td>" + unit.unitNo + "</td>";
				units += "<td>" + unit.unitName + "</td>";
				units += "<td>" + unit.location + "</td>";
				units += "<td>" + unit.ipAddress + "</td>";
				units += "<td>" + unit.status + "</td>";
				units += "<td>" + unit.assignedBy + "</td>";
				units += "<td>" + unit.assignedDate + "</td>";
				units += "<td>" + unit.returnDate + "</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(units);
				parent.insert({
					bottom : newTr
				});
			});
			getSize();
			recordEvents();
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// FUNCTION if the search button is clicked
$("btnUnitSearch").observe('click', function() {
	fetchCompUnits();

})

// FUNCTION if the search button is clicked
$("btnAssigneeSearch").observe('click', function() {
	fetchAssignees();
})

// Fetching the datas from the ims_assignee_1 table
function fetchAssignees() {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "findName",
		},
		onSuccess : function(response) {

			var assigneeList = response.responseText.evalJSON();
			var parent = $('modalContentAssignee');

			$$('.modalRecord').each(function(record) {
				$(record).remove();
			});

			assigneeList.each(function(assignee) {
				var assigneeContent = "";

				assigneeContent += '<td>' + assignee.assigneeNo + '</td>';
				assigneeContent += '<td>' + assignee.assigneeName + '</td>';

				var newTr = new Element('tr');
				newTr.setAttribute("class", "modalRecord");
				newTr.setAttribute("onclick", "getAssigneeName(this)");
				newTr.update(assigneeContent);
				parent.insert({
					bottom : newTr
				});
			});

		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// Function to populate the Assignee TextField
function populateTxtAssignee() {

	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "populateAssignee",
			selectAssignee : $F("hiddenAssignNo")
		},
		onSuccess : function(response) {
			var assignee = response.responseText.evalJSON();
			assignee.each(function(assign) {
				$("txtAssignee").value = assign.assigneeName;
			});
		}
	});
}

// Function to get the records from the Unit Assignment Page
function addRecord() {
	delete Array.prototype.toJSON;
	var obj = [];
	var addObj = {};

	addObj.unitNo = $F("txtUnitNo");
	addObj.unitName = $F("txtUnitName");
	addObj.assigneeNo = $F("hiddenAssignNo");
	addObj.location = $F("txtAssigneeLocation");
	addObj.ipAddress = $F("txtIpAdd");
	addObj.status = $F("txtAssigneeStatus");
	addObj.assignedBy = $F("txtAssignedBy");
	addObj.returnDate = $F("txtReturnDate");
	obj.push(addObj);
	var json = JSON.stringify(obj);
	return json;
}

/*
 * In the Modal. if the 'Yes' Button is Clicked, it will save all the Records
 * from the UNit assignment Page to the ims_unit_assignment_1 and
 * ims_assignment_hist_1 tables
 */
$("btnYes").observe('click', function() {
	if(validate())
	{
		var json = addRecord();

		new Ajax.Request(context + "/UnitAssignmentController", {
			method : "post",
			parameters : {
				action : "assignToDatabase",
				actionTwo : "assignToHistData",
				unitassignment : json,
				unitassignmenthist : json,
				unitId : $F("txtUnitNo")
			},
			onComplete : function(response) {
				// $("mainContents").update(response.responseText);
			},
			onFailure : function(response) {
				alert("There is something wrong!");
			}
		});
	}
})

// Function to get the values of the selected row from the Assignee LOV
function getAssigneeName(fetchName) {
	$("hiddenAssignNo").value = fetchName.down(0).innerHTML;
	$("txtAssignee").value = fetchName.down(1).innerHTML;
	populateTxtAssignee();
}

// Function to display all the Unit assignment History
function getUnitAssignmentHist() {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "pagination",
			page : "1"
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('body');

			u.each(function(unit) {
				var content = "";

				content += "<td>" + unit.unitNo + "</td>";
				content += "<td>" + unit.unitName + "</td>";
				content += "<td>" + unit.location + "</td>";
				content += "<td>" + unit.ipAddress + "</td>";
				content += "<td>" + unit.status + "</td>";
				content += "<td>" + unit.assignedBy + "</td>";
				content += "<td>" + unit.assignedDate + "</td>";
				content += "<td>" + unit.returnDate + "</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});
			getSize();
			recordEvents();
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});

}

// Function to get the size of all rows in the ims_assignment_hist_1 table. This
// is for Pagination
function getSize() {
	new Ajax.Request(
			context + "/UnitAssignmentController",
			{
				method : "get",
				parameters : {
					action : "getSize"
				},
				onSuccess : function(response) {
					var u = response.responseText.evalJSON();
					var parent = $('pagination');

					u.each(function(sizes) {
						pageSize = sizes.listSize;
					});
					var btnCount = parseInt(pageSize / 5);
					if (pageSize % 5 != 0) {
						btnCount += 1;
					}
					for (var a = 1; a <= btnCount; a++) {
						var newBtn = new Element('button');
						newBtn.setAttribute("class", "btn-nav btn btn-primary");
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
			});
}

// Fetching and Displaying of Records from the ims_assignment_hist_1 table
function getRecordPage(a) {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "getRecordPage",
			page : a
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			u.each(function(unit) {
				var content = "";

				content += "<td>" + unit.unitNo + "</td>";
				content += "<td>" + unit.unitName + "</td>";
				content += "<td>" + unit.location + "</td>";
				content += "<td>" + unit.ipAddress + "</td>";
				content += "<td>" + unit.status + "</td>";
				content += "<td>" + unit.assignedBy + "</td>";
				content += "<td>" + unit.assignedDate + "</td>";
				content += "<td>" + unit.returnDate + "</td>";

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
	});
}

// getUnitAssignmentHist() function is called. It will Display all the
// assignment history.
getUnitAssignmentHist();

function filteredUnitAssignmentsHist() {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "getFilteredRecord",
			filterText : $F('searchBox')
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});
			u.each(function(unit) {
				var content = "";
				content += "<td>" + unit.unitNo + "</td>";
				content += "<td>" + unit.unitName + "</td>";
				content += "<td>" + unit.location + "</td>";
				content += "<td>" + unit.ipAddress + "</td>";
				content += "<td>" + unit.status + "</td>";
				content += "<td>" + unit.assignedBy + "</td>";
				content += "<td>" + unit.assignedDate + "</td>";
				content += "<td>" + unit.returnDate + "</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});
			$$('.btn-nav').each(function(record) {
				$(record).remove();
			});
			getFilteredUnitAssignmentSize();
			recordEvents();
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// For the search box. It will get the size of all searched rows
function getFilteredUnitAssignmentSize() {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "getFilteredUnitAssignmentSize"
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('pagination');

			u.each(function(sizes) {
				pageSize = sizes.listSize;
			});
			var btnCount = parseInt(pageSize / 5);
			if (pageSize % 5 != 0) {
				btnCount += 1;
			}
			for (var a = 1; a <= btnCount; a++) {
				var newBtn = new Element('button');
				newBtn.setAttribute("class", "btn-nav");
				newBtn.update(a);
				newBtn.setAttribute("onclick",
						"getFilteredUnitAssignmentPage(this.innerHTML)");
				parent.insert({
					bottom : newBtn
				});
			}
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

// Function to display all the searched rows from the search textbox
function getFilteredUnitAssignmentPage(a) {
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			action : "getFilteredUnitAssignmentPage",
			page : a
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $('body');
			$$('.btn-nav').each(function(record) {
				$(record).remove();
			});
			$$('.record').each(function(record) {
				$(record).remove();
			});
			u.each(function(unit) {
				var content = "";

				content += "<td>" + unit.unitNo + "</td>";
				content += "<td>" + unit.unitName + "</td>";
				content += "<td>" + unit.location + "</td>";
				content += "<td>" + unit.ipAddress + "</td>";
				content += "<td>" + unit.status + "</td>";
				content += "<td>" + unit.assignedBy + "</td>";
				content += "<td>" + unit.assignedDate + "</td>";
				content += "<td>" + unit.returnDate + "</td>";

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
	});
}
