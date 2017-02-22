/**
 * 
 */
var pageSize = 0;

function addPeripherals() {
	if (validate()) {
		delete Array.prototype.toJSON;
		new Ajax.Request(context + "/PeripheralsController", {
			method : "post",
			parameters : {},
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
				alert("hey");
				p.each(function(sizes) {
					pageSize = sizes.listSize;
				});
				$('pagination').innerHTML = (pageSize);
			}
		});
	}

}

getPeripherals();
