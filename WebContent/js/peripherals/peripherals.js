/**
 * 
 */
var pageSize = 0;
$('txtUnitNo').observe("change", function() {
	getPeripherals();
});

function addPeripherals() {
	if (validate()) {
		delete Array.prototype.toJSON;

		var content = [];
		var object = {};
		object.unitNo = $F('txtUnitNo');
		object.peripheralNo = $F('txtPeripheralNo') == '' ? null : $F('txtPeripheralNo');
		object.serialNo = $F('txtSerialNo');
		object.peripheralType = $F('txtPeripheralType');
		object.brand = $F('txtBrand');
		object.tagNumber = $F('txtTagNumber');
		object.model = $F('txtModel');
		object.acquiredDate = $F('dtAcquiredDate');
		object.color = $F('txtColor');
		object.description = $F('txtDescription');
		object.remarks = $F('txtRemarks');
		content.push(object);
		var json = JSON.stringify(content);

		new Ajax.Request(context + "/PeripheralsController", {
			method : "post",
			encoding : "UTF-8",
			parameters : {
				content : json,
				action : "add",
				status : $F(btnAdd)
			},
			onSuccess : function(response) {
				var p = response.responseText.evalJSON();
				var parent = $('body');
				p.each(function(peripheral) {
					var content = "";
					content += "<td>" + peripheral.peripheralNo + "</td>";
					content += "<td>" + peripheral.peripheralType + "</td>";
					content += "<td>" + peripheral.tagNumber + "</td>";
					content += "<td>" + peripheral.brand + " "
							+ peripheral.model + " </td>";
					content += "<td>" + peripheral.serialNo + "</td>";
					content += "<td>" + peripheral.acquiredDate + "</td>";
					content += "<td>" + peripheral.description + "</td>";

					var newTr = new Element('tr');
					newTr.setAttribute("class", "record");
					newTr.update(content);
					parent.insert({
						bottom : newTr
					});
				});
			}
		});
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}

function getPeripherals() {
	new Ajax.Request(context + "/PeripheralsController", {
		method : "get",
		contentType : "application/json",
		parameters : {
			action : "pagination",
			page : "1",
			num : $F('txtUnitNo')
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			$('body').hide();

			p.each(function(peripherals) {
				var content = "";
				content += "<td>" + peripherals.peripheralNo + "</td>";
				content += "<td>" + peripherals.peripheralType + "</td>";
				content += "<td>" + peripherals.tagNumber + "</td>";
				content += "<td>" + peripherals.brand + " " + peripherals.model
						+ " </td>";
				content += "<td>" + peripherals.serialNo + "</td>";
				content += "<td>" + peripherals.acquiredDate + "</td>";
				content += "<td>" + peripherals.description + "</td>";

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
			console.log("There is something wrong. Please check connection");
			alert("There is something wrong. Please check connection");
		}
	});
	function getSize() {
		new Ajax.Request(context + "/PeripheralsController", {
			method : "get",
			contentType : "application/json",
			parameters : {
				action : "getSize"
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
					newBtn.setAttribute("class", "btn-nav");
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

}

function getRecordPage(a) {
	new Ajax.Request(context + "/PeripheralsController", {
		method : "get",
		contentType : "application/json",
		parameters : {
			action : "getRecordPage",
			page : a
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');

			$$('.record').each(function(record) {
				$(record).remove();
			});

			p.each(function(peripherals) {
				var content = "";
				content += "<td>" + peripherals.peripheralNo + "</td>";
				content += "<td>" + peripherals.peripheralType + "</td>";
				content += "<td>" + peripherals.tagNumber + "</td>";
				content += "<td>" + peripherals.brand + " " + peripherals.model
						+ " </td>";
				content += "<td>" + peripherals.serialNo + "</td>";
				content += "<td>" + peripherals.acquiredDate + "</td>";
				content += "<td>" + peripherals.description + "</td>";

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

getPeripherals();

function getRecord(record) {
	new Ajax.Request(context + "/PeripheralsController", {
		method : "post",
		parameters : {
			peripheralId : $(record).down('td', 0).innerHTML,
			action : "getPeripheralRecord"
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			p.each(function(peripheral) {
				$('txtPeripheralNo').value = peripheral.peripheralNo;
				$('txtSerialNo').value = peripheral.serialNo;
				$('txtPeripheralType').value = peripheral.peripheralType;
				$('txtBrand').value = peripheral.brand;
				$('txtTagNumber').value = peripheral.tagNumber;
				$('txtModel').value = peripheral.model;
				$('dtAcquiredDate').value = peripheral.acquiredDate;
				$('txtColor').value = peripheral.color;
				$('txtDescription').value = peripheral.description;
				$('txtUserId').value = peripheral.userId;
				$('txtRemarks').value = peripheral.remarks;
				$('dtLastUpdate').value = peripheral.lastUpdate;
			});
			$('btnAdd').value = "Update";
		},
		onFailed : function(response) {

		}
	});
}

function insertPeripherals() {
	new Ajax.Request(context + "/PeripheralsController", {
		method : "post",
		parameters : {
			action : "saveRecord"
		},
		onSuccess : function(response) {

		},
		onFailed : function(response) {

		}
	});
}

function clearFields(record) {
	$('btnAdd').value = "Add";
	$$('.form-control').each(function(field) {
		$(field).value = "";
	})
}
