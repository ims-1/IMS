/**
 * 
 */
var pageSize = 0;

function addPeripherals() {
	if (validate()) {
		new Ajax.Request(context + "/PeripheralsController", {
			method : "post",
			parameters : {
				action : "insert",
				unitNo : $F('txtUnitNo'),
				peripheralNo : $F('txtPeripheralNo'),
				serialNo : $F('txtSerialNo'),
				peripheralType : $F('txtPeripheralType'),
				brand : $F('txtBrand'),
				tagNumber : $F('txtTagNumber'),
				model : $F('txtModel'),
				acquiredDate : $F('dtAcquiredDate'),
				color : $F('txtColor'),
				description : $F('txtDescription'),
				userId : $F('txtUserId'),
				remarks : $F('txtRemarks')
			},
			onSuccess : function(response) {

			}
		});
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}

function getPeripherals() {
	new Ajax.Request(context + "/PeripheralsController", {
		method : "get",
		parameters : {
			action : "pagination",
			page : "1"
		},
		onSuccess : function(response) {
			var p = response.responseText.evalJSON();
			var parent = $('body');
			$('body').hide();

			p.each(function(peripherals) {
				var content = "";
				content += "<td>" + peripherals.unitNo + "</td>";
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
			parameters : {
				action : "getSize"
			},
			onSuccess : function(response) {
				var p = response.responseText.evalJSON();
				var parent = $('pagination');

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
				content += "<td>" + peripherals.unitNo + "</td>";
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
			p.each(function(peripheral){
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
			});		
		},
		onFailed : function(response) {

		}
	});
}
