/**
 * 
 */
var pageSize = 0;

function addPeripherals() {
	if (validate()) {
		delete Array.prototype.toJSON;
		var content = [];
		var obj = {};

		obj.peripheralNo = $F('txtPeripheralNo');
		obj.serialNo = $F('txtSerialNo');
		obj.peripheralType = $F('txtPeripheralType');
		obj.brand = $F('txtBrand');
		obj.tagNumber = $F('txtTagNumber');
		obj.model = $F('txtModel');
		obj.acquiredDate = $F('dtAcquiredDate');
		obj.color = $F('txtColor');
		obj.description = $F('txtDescription');
		obj.userId = $F('txtUserId');
		obj.remarks = $F('txtRemarks');
		obj.lastUpdate = $F('dtLastUpdate');

		content.push(obj);
		var json = JSON.stringify(content);

		new Ajax.Request(context + "/PeripheralsController", {
			method : "post",
			parameters : {
				action : "insert",
				record : json
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
			getSize();
			recordEvents();
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
				var btnCount = parseInt(pageSize / 5);
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
