
function loadPage() {
	
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "get",
		parameters : {
			
			action : "loadData"
		
		},
		onComplete : function (response) {
			$("mainContents").update(response.responseText);
		}
	});
	
}

function addRecord() {
	delete Array.prototype.toJSON;
	var obj = [];
	var addObj = {};
	
	addObj.unitNo = $F("txtUnitNo");
	addObj.unitName = $F("txtUnitName");
	addObj.assignee = $F("txtUnitAssignee");
	addObj.Location = $F("txtAssigneeLocation");
	addObj.ip = $F("txtIpAdd");
	addObj.status = $F("txtAssigneeStatus");
	addObj.assignedBy = $F("txtAssignedBy");
	addObj.dateAssigned = $F("txtDateAssigned");
	
	obj.push(addObj);
	var json = JSON.stringify(obj);
	
//	prompt($("popUp"));
//	addObj.returnDate = $F("txtReturnDate");
//	
//	unitNumberValidation();
//	txtNullValidation();
//		
//	var content = '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.unitNo + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.unitName + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.assignee + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.Location + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.ip + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.status + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.assignedBy + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.dateAssigned + '</label>'
//				+ '<label style="width: 100px; font-size: 12px; text-align: center; float: left;">'+ addObj.returnDate + '</label>';
//	
//	var addTable = $("tableDiv");
//	var newDiv = new Element ("Div");
//
//	newDiv.addClassName("tableRow");
//	newDiv.update(content);
//	addTable.insert({bottom: newDiv});
	
	return json;
}

$("btnAssign").observe('click', function () {
	delete Array.prototype.JSON;
	var json = addRecord();
	alert(json);
	
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "post",
		parameters : {
			
			action  : "actions",
			unitassignment		: json		
		},
		onComplete : function (response) {
			$("mainContents").update(response.responseText);
		}
	});
})

 function unitNumberValidation() { 
	 if(isNaN($("txtUnitNo").value))
		 {
		 	alert("Invalid Unit Number");
		 }	
 }
 function txtNullValidation() {
	 if($("txtUnitNo") == "" || $F("txtUnitName") == ""
			 				 || $F("txtAssigneeLocation") == ""
			 				 || $F("txtUnitAssignee") == "" 
			 				 || $F("txtIpAdd") == "" 
			 				 || $F("txtAssigneeStatus") == "" 
			 				 || $F("txtAssignedBy") == "" 
			 				 || $F("txtDateAssigned") == "") 
		 {
		 	alert("Please fill all the text fields");
		 }
 }

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
			}
		});


	}
		function getSize() {
			new Ajax.Request(context + "/UnitAssignmentController", {
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
			}
		});
	}

	getUnitAssignmentHist();

