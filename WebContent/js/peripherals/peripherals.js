/**
 * 
 */
var unitNo = "";
var fetched = "";

function searchUnit() {
	if ($('modalContent').innerHTML.trim() == '') {
		new Ajax.Request(context + "/PeripheralsController", {
			method : "get",
			parameters : {
				action : "getUnits"
			},
			onSuccess : function(response) {
				if (response.status == 200) {
					var u = response.responseText.evalJSON();
					var parent = $('modalContent');
					u.each(function(unit) {
						content = "";
						content += "<td>" + unit.unitNo + "</td>";
						content += "<td>" + unit.unitName + "</td>";

						var newTr = new Element('tr');
						newTr.setAttribute("class", "modalRecord");
						newTr.setAttribute("onclick", "getUnitRecord(this)");
						newTr.update(content);
						parent.insert({
							bottom : newTr
						});
					});
				}
			},
			onFailure : function(response) {
				alert("There is something wrong!");
			}
		});
	}
}

function getUnitRecord(record) {
	if (unitNo != $(record).down('td', 0).innerHTML) {
		unitNo = $(record).down('td', 0).innerHTML;
		$('txtUnitNo').value = $(record).down('td', 0).innerHTML;
	} else {
		unitNo = "";
		$('txtUnitNo').value = "";
	}
}

function setUnit() {
	if (unitNo != "") {
		$('txtUnitNo').value = unitNo;
		unitNo = "";
		getComputerUnitRecord();
	}
}

var pageSize = 0;
// $('txtUnitNo').observe("change", function() {
// if ($F('txtUnitNo').trim() != '') {
// $('btnAdd').setAttribute("disabled", "disabled");
// fetched = "";
// getComputerUnitRecord();
// } else {
// getPeripherals();
// }
//
// });

function getComputerUnitRecord() {
	$('btnDelete').disabled = true;
	new Ajax.Request(context + "/PeripheralsController", {
		method : "post",
		parameters : {
			unitNo : $F('txtUnitNo'),
			status : '',
			action : "getComputerAssignee"
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		},
		onSuccess : function(response) {
			if (response.status == 200) {
				var unit = response.responseText.evalJSON();
				unit.each(function(u) {
					$('txtSerialNo').value = u.serialNo;
					$('txtUnitName').value = u.unitName;
					$('txtBrand').value = u.brand;
					$('txtTagNumber').value = u.tagNumber;
					$('txtModel').value = u.model;
					$('txtType').value = u.type;
					$('txtColor').value = u.color;
					$('txtIpAddress').value = u.ipAddress;
					$('txtAssignee').value = u.assigneeName;
					$('txtStatus').value = u.status;
					fetched = u.unitNo;
				});

				$('btnAdd').disabled = false;
			} else if (response.status == 202) {
				$('btnAdd').disabled = true;
				$('btnDelete').disabled = true;
				alert("User id is deleted!");
			} else if (response.status == 201) {
				$('btnAdd').disabled = true;
				$('btnDelete').disabled = true;
				alert("No user found!");
			}
		},
		onComplete : function(response) {
			getPeripherals();
		}
	})
}

function addPeripherals() {
	if (validate()) {
		//alert("hey");
		delete Array.prototype.toJSON;

		var content = [];
		var object = {};
		object.unitNo = $F('txtUnitNo') == '' ? null : $F('txtUnitNo');
		object.peripheralNo = $F('ptxtPeripheralNo') == '' ? null
				: $F('ptxtPeripheralNo');
		object.serialNo = $F('ptxtSerialNo') == '' ? null : $F('ptxtSerialNo');
		object.peripheralType = $F('ptxtPeripheralType') == '' ? null
				: $F('ptxtPeripheralType');
		object.brand = $F('ptxtBrand') == '' ? null : $F('ptxtBrand');
		object.tagNumber = $F('ptxtTagNumber') == '' ? null
				: $F('ptxtTagNumber');
		object.model = $F('ptxtModel') == '' ? null : $F('ptxtModel');
		object.acquiredDate = $F('pdtAcquiredDate') == '' ? null
				: $F('pdtAcquiredDate');
		object.color = $F('ptxtColor') == '' ? null : $F('ptxtColor');
		object.description = $F('ptxtDescription') == '' ? null
				: $F('ptxtDescription');
		object.remarks = $F('ptxtRemarks') == '' ? null : $F('ptxtRemarks');
		content.push(object);
		var json = JSON.stringify(content);

		new Ajax.Request(
				context + "/PeripheralsController",
				{
					method : "post",
					encoding : "UTF-8",
					parameters : {
						content : json,
						action : "add",
						status : $F(btnAdd)
					},
					onFailure : function(response) {
						alert("There is something wrong!");
					},
					onSuccess : function(response) {
						var p = response.responseText.evalJSON();
						var parent = $('body');

						$$('.record').each(function(record) {
							$(record).remove();
						});
						//alert("here");
						p
								.each(function(peripheral) {
									var content = "";
									content += "<td>"
											+ (peripheral.peripheralNo == null ? ''
													: peripheral.peripheralNo)
											+ "</td>";
									content += "<td>"
											+ (peripheral.peripheralType == null ? ''
													: peripheral.peripheralType)
											+ "</td>";
									content += "<td>"
											+ (peripheral.tagNumber == null ? ''
													: peripheral.tagNumber)
									"</td>";
									content += "<td>"
											+ (peripheral.brand == null ? ''
													: peripheral.brand)
											+ " "
											+ (peripheral.model == null ? ''
													: peripheral.model)
											+ " </td>";
									content += "<td>"
											+ (peripheral.serialNo == null ? ''
													: peripheral.serialNo)
											+ "</td>";
									content += "<td>"
											+ (peripheral.acquiredDate == null ? ''
													: peripheral.acquiredDate)
											+ "</td>";
									content += "<td>"
											+ (peripheral.description == null ? ''
													: peripheral.description)
											+ "</td>";

									var newTr = new Element('tr');
									newTr.setAttribute("class", "record");
									newTr.update(content);
									parent.insert({
										bottom : newTr
									});
								});
						recordEvents();
					}
				});
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}

function getPeripherals() {
	$('spinner').style.display = "block";
	new Ajax.Request(
			context + "/PeripheralsController",
			{
				method : "get",
				parameters : {
					action : "pagination",
					num : $('txtUnitNo').value
				},
				onSuccess : function(response) {
					if (response.status == 200) {
						var p = response.responseText.evalJSON();
						var parent = $('body');

						$$('.record').each(function(record) {
							$(record).remove();
						});

						$('body').hide();

						p
								.each(function(peripheral) {
									var content = "";
									content += "<td>"
											+ (peripheral.peripheralNo == null ? ''
													: peripheral.peripheralNo)
											+ "</td>";
									content += "<td>"
											+ (peripheral.peripheralType == null ? ''
													: peripheral.peripheralType)
											+ "</td>";
									content += "<td>"
											+ (peripheral.tagNumber == null ? ''
													: peripheral.tagNumber)
									"</td>";
									content += "<td>"
											+ (peripheral.brand == null ? ''
													: peripheral.brand)
											+ " "
											+ (peripheral.model == null ? ''
													: peripheral.model)
											+ " </td>";
									content += "<td>"
											+ (peripheral.serialNo == null ? ''
													: peripheral.serialNo)
											+ "</td>";
									content += "<td>"
											+ (peripheral.acquiredDate == null ? ''
													: peripheral.acquiredDate)
											+ "</td>";
									content += "<td>"
											+ (peripheral.description == null ? ''
													: peripheral.description)
											+ "</td>";

									var newTr = new Element('tr');
									newTr.setAttribute("class", "record");
									newTr.update(content);
									parent.insert({
										bottom : newTr
									});
								});
						$('body').show();
						getSize();
						recordEvents();
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
						alert("There is no peripheral record fetched");
					}

					$('spinner').style.display = "none";
				},
				onFailure : function(response) {
					console
							.log("There is something wrong. Please check connection");
					alert("There is something wrong. Please check connection");
					$('spinner').style.display = "none";
				}
			});

}
function getSize() {
	new Ajax.Request(
			context + "/PeripheralsController",
			{
				method : "get",
				contentType : "application/json",
				parameters : {
					action : "getSize"
				},
				onFailure : function(response) {
					alert("There is something wrong!");
				},
				onSuccess : function(response) {
					var p = response.responseText.evalJSON();
					var parent = $('pagination');
					$('pagination').innerHTML = '';

					p.each(function(sizes) {
						pageSize = sizes.listSize;
					});
					var btnCount = (pageSize / 5);
					btnCount = pageSize % 5 === 0 ? btnCount : btnCount + 1;
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
				}
			});
}

function getRecordPage(a) {
	new Ajax.Request(context + "/PeripheralsController", {
		method : "post",
		parameters : {
			action : "getRecordPage",
			page : a
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			p.each(function(peripheral) {
				content = "";
				content += "<td>"
						+ (peripheral.peripheralNo == null ? ''
								: peripheral.peripheralNo) + "</td>";
				content += "<td>"
						+ (peripheral.peripheralType == null ? ''
								: peripheral.peripheralType) + "</td>";
				content += "<td>"
						+ (peripheral.tagNumber == null ? ''
								: peripheral.tagNumber)
				"</td>";
				content += "<td>"
						+ (peripheral.brand == null ? '' : peripheral.brand)
						+ " "
						+ (peripheral.model == null ? '' : peripheral.model)
						+ " </td>";
				content += "<td>"
						+ (peripheral.serialNo == null ? ''
								: peripheral.serialNo) + "</td>";
				content += "<td>"
						+ (peripheral.acquiredDate == null ? ''
								: peripheral.acquiredDate) + "</td>";
				content += "<td>"
						+ (peripheral.description == null ? ''
								: peripheral.description) + "</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});
			recordEvents();
		}
	});
}
if ($F('txtUnitNo') == '') {
	getPeripherals();
} else {
	getComputerUnitRecord();
}

function getRecord(record) {
	//alert("here");
	new Ajax.Request(context + "/PeripheralsController", {
		method : "post",
		parameters : {
			peripheralId : $(record).down('td', 0).innerHTML,
			action : "getPeripheralRecord"
		},
		onSuccess : function(response) {
			if (response.status == 200) {

				var p = response.responseText.evalJSON();
				p.each(function(peripheral) {
					$('ptxtPeripheralNo').value = peripheral.peripheralNo;
					$('ptxtSerialNo').value = peripheral.serialNo;
					$('ptxtPeripheralType').value = peripheral.peripheralType;
					$('ptxtBrand').value = peripheral.brand;
					$('ptxtTagNumber').value = peripheral.tagNumber;
					$('ptxtModel').value = peripheral.model;
					$('pdtAcquiredDate').value = peripheral.acquiredDate;
					$('ptxtColor').value = peripheral.color;
					$('ptxtDescription').value = peripheral.description;
					$('ptxtUserId').value = peripheral.userId;
					$('ptxtRemarks').value = peripheral.remarks;
					$('pdtLastUpdate').value = peripheral.lastUpdate;
				});
				$('btnAdd').value = "Update";
				$('btnAdd').disabled = false;
				$('btnDelete').disabled = false;
			} else if (response.status == 201) {
				alert('The record has been just recently added. Need to save');
			}
		},
		onFailure : function(response) {
			alert("There is something wrong!");
		}
	});
}

function insertPeripherals() {
	alert($F('txtUnitNo'));
	new Ajax.Request(context + "/PeripheralsController", {
		method : "post",
		parameters : {
			action : "saveRecord",
			num : $F('txtUnitNo')
		},
		onSuccess : function(response) {
			alert("Record saved!");
			var p = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			$('body').hide();

			p.each(function(peripheral) {
				var content = "";
				content += "<td>"
						+ (peripheral.peripheralNo == null ? ''
								: peripheral.peripheralNo) + "</td>";
				content += "<td>"
						+ (peripheral.peripheralType == null ? ''
								: peripheral.peripheralType) + "</td>";
				content += "<td>"
						+ (peripheral.tagNumber == null ? ''
								: peripheral.tagNumber)
				"</td>";
				content += "<td>"
						+ (peripheral.brand == null ? '' : peripheral.brand)
						+ " "
						+ (peripheral.model == null ? '' : peripheral.model)
						+ " </td>";
				content += "<td>"
						+ (peripheral.serialNo == null ? ''
								: peripheral.serialNo) + "</td>";
				content += "<td>"
						+ (peripheral.acquiredDate == null ? ''
								: peripheral.acquiredDate) + "</td>";
				content += "<td>"
						+ (peripheral.description == null ? ''
								: peripheral.description) + "</td>";

				var newTr = new Element('tr');
				newTr.setAttribute("class", "record");
				newTr.update(content);
				parent.insert({
					bottom : newTr
				});
			});
			$('body').show();
			getSize();
			recordEvents();
		},
		onFailure : function(response) {
			alert("There is something wrong with saving the records.")
		}
	});
}

function deleteRecord() {
	//alert("delete");
	alert($F('txtUnitNo').trim());
	new Ajax.Request(
			context + "/PeripheralsController",
			{
				method : "post",
				parameters : {
					action : "deleteRecord",
					no : $F('ptxtPeripheralNo'),
					num : $F('txtUnitNo').trim()
				},
				onSuccess : function(response) {
					if (response.status == 200) {
						alert("Record was deleted.");
						var p = response.responseText.evalJSON();
						var parent = $('body');

						$$('.record').each(function(record) {
							$(record).remove();
						});
						//alert("here");
						p
								.each(function(peripheral) {
									var content = "";
									content += "<td>"
											+ (peripheral.peripheralNo == null ? ''
													: peripheral.peripheralNo)
											+ "</td>";
									content += "<td>"
											+ (peripheral.peripheralType == null ? ''
													: peripheral.peripheralType)
											+ "</td>";
									content += "<td>"
											+ (peripheral.tagNumber == null ? ''
													: peripheral.tagNumber)
									"</td>";
									content += "<td>"
											+ (peripheral.brand == null ? ''
													: peripheral.brand)
											+ " "
											+ (peripheral.model == null ? ''
													: peripheral.model)
											+ " </td>";
									content += "<td>"
											+ (peripheral.serialNo == null ? ''
													: peripheral.serialNo)
											+ "</td>";
									content += "<td>"
											+ (peripheral.acquiredDate == null ? ''
													: peripheral.acquiredDate)
											+ "</td>";
									content += "<td>"
											+ (peripheral.description == null ? ''
													: peripheral.description)
											+ "</td>";

									var newTr = new Element('tr');
									newTr.setAttribute("class", "record");
									if (peripheral.saved == "not") {
										newTr.setAttribute("class", "notsaved");
									}
									newTr.update(content);
									parent.insert({
										bottom : newTr
									});
								});
						getSize();
						recordEvents();
						clearFields();

					} else if (response.status == 204) {
						alert("The record you are trying to delete was a pending record. Please save the record first.");
					} else if (response.status == 205) {
						alert("There was an exception while deleteng the record");
					}

				},
				onFailure : function(response) {
					alert("There is some error while deleting record.");
				}
			});
}
function clearFields(record) {
	//alert("here");
	$('btnAdd').value = "Add";
	$('btnDelete').disabled = true;
	if ($('txtUnitNo').value.trim() == '') {
		$('btnAdd').disabled = true;
	}
	$('ptxtPeripheralNo').value = '';
	$('ptxtSerialNo').value = '';
	$('ptxtPeripheralType').value = '';
	$('ptxtBrand').value = '';
	$('ptxtTagNumber').value = '';
	$('ptxtModel').value = '';
	$('pdtAcquiredDate').value = '';
	$('ptxtColor').value = '';
	$('ptxtDescription').value = '';
	$('ptxtUserId').value = '';
	$('ptxtRemarks').value = '';
	$('pdtLastUpdate').value = '';
}

function clearFields() {
	//alert("here");
	$('btnAdd').value = "Add";
	$('btnDelete').disabled = true;
	if ($('txtUnitNo').value.trim() == '') {
		$('btnAdd').disabled = true;
	}
	$('ptxtPeripheralNo').value = '';
	$('ptxtSerialNo').value = '';
	$('ptxtPeripheralType').value = '';
	$('ptxtBrand').value = '';
	$('ptxtTagNumber').value = '';
	$('ptxtModel').value = '';
	$('pdtAcquiredDate').value = '';
	$('ptxtColor').value = '';
	$('ptxtDescription').value = '';
	$('ptxtUserId').value = '';
	$('ptxtRemarks').value = '';
	$('pdtLastUpdate').value = '';
}

function recordEvents() { // call on your own js file
	$$('.record').each(function(record) {
		$(record).observe("click", function() {
			// assign class to selected row
			if ($(record).hasClassName("hoverRow")) {
				$(record).toggleClassName("hoverRow");
				clearFields($(record));
				// clear fields
			} else {
				// clear selected row - remove class
				$$('.record').each(function(record) {
					$(record).removeClassName("hoverRow");
				});

				$(record).toggleClassName("hoverRow");
				getRecord($(record));// populate fields
			}
		});
	});
}