
$("btnUnitSearch").observe('click', function () {
	new Ajax.Request(context + "/UnitAssignmentController" , {
		method : "get",
		parameters : {
			action : "populate",
			selectUnits : $F("txtUnitNo")
		},		
		onSuccess : function(response) {
			var compUnit = response.responseText.evalJSON();
			compUnit.each(function(unit) {
				
				$("txtSerialNo").value = unit.unitNo;
				$("txtUnitName").value =  unit.unitName;
				$("txtBrand").value = unit.brand;
				$("txtTagNumber").value =  unit.tagNumber;
				$("txtUnitModel").value =  unit.model;
				$("txtUnitType").value = unit.type;
				$("txtUnitColor").value = unit.color;		
			});
		}
	});
})

$("btnUnitSearch").observe('click', function() {
	alert($F("txtUnitNo"));
	new Ajax.Request(context + "/UnitAssignmentController" , {
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
		}
	});
})


$("btnAssigneeSearch").observe('click', function () {
	new Ajax.Request(context + "/UnitAssignmentController" , {
		method : "get",
		parameters : {
			action : "findName",
			findAssigneeNo : $F("txtAssignee")
		},		
		onSuccess : function(response) {
			var assigneeList = response.responseText.evalJSON();
			assigneeList.each(function(assignee) {			
				$("txtAssignee").value = assignee.assigneeName;		
				$("hiddenAssignNo").value = assignee.assigneeNo;	
			});
		}
	});
})


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
	addObj.unitName =$F("txtUnitName");
	addObj.assigneeNo = $F("hiddenAssignNo");
	addObj.location = $F("txtAssigneeLocation");
	addObj.ipAddress = $F("txtIpAdd");
	addObj.status = $F("txtAssigneeStatus");
	addObj.assignedBy = $F("txtAssignedBy");
	
	
	obj.push(addObj);
	var json = JSON.stringify(obj);
	return json;
}

$("btnAssign").observe('click', function () {
	alert("Assign");
	var json = addRecord();
	alert(json);
	new Ajax.Request(context + "/UnitAssignmentController", {
		method : "post",
		parameters : {			
			action  : "assignToDatabase",
			actionTwo : "assignToHistData",
			unitassignment		: json,	
			unitassignmenthist : json,
			unitId : $F("txtUnitNo")
		},
		onComplete : function (response) {
			//$("mainContents").update(response.responseText);
		}
	});
})


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

