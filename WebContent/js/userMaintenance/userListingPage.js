onLoadUserListPage(); 

function onLoadUserListPage() {
	alert("hello usreload");
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "get",
		parameters : {
			action : "pagination"
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $("body");
			
			u.each(function(users) {
				var content = "";
				var uAccess = "";
				
				if (users.userAccess == "A") {
					uAccess = "Admin";
				}
				if (users.userAccess == "U") {
					uAccess = "Regular User";
				}
				
				content = "<td>"+ users.userId +"</td>" 
							+ "<td>"+ users.firstName + " " + users.middleInitial + ". " + users.lastName +"</td>"
							+ "<td>"+ uAccess +"</td>"
							+ "<td>"+ users.activeTag +"</td>"
							+ "<td>"+ users.entryDate +"</td>"
							+ '<td><button onclick="editUser()" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-edit"></span></td>';
				
				var newTr = new Element("tr");
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

var pageSize = 0;
function getSize() {
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "get",
		parameters : {
			action : "getSize"
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $("pagination");
			
			
			
			u.each(function(sizes){
				pageSize = sizes.listSize;
			});
			
			var btnCount = parseInt(pageSize/10);
			for (var a = 1; a <= btnCount; a++) {
				var newBtn = new Element("button");
				newBtn.setAttribute("class", "btn-nav");
				newBtn.update(a);
				newBtn.setAttribute("onclick", "getRecordPage(this.innerHTML)");
				parent.insert({
					bottom : newBtn
				});
			}
		}
	});
}

function getRecordPage(a) {
	alert(a);
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "get",
		parameters : {
			action : "getRecordPage",
			page : a
		},
		onSuccess : function(response) {
			var u = response.responseText.evalJSON();
			var parent = $("body");
			
			$$(".record").each(function(record) {
				$(record).remove();
			});
			
			u.each(function(users) {
				var content = "";
				var uAccess = "";
				
				if (users.userAccess == "A") {
					uAccess = "Admin";
				}
				if (users.userAccess == "U") {
					uAccess = "Regular User";
				}
				
				content = "<td>"+ users.userId +"</td>" 
							+ "<td>"+ users.firstName + " " + users.middleInitial + ". " + users.lastName +"</td>"
							+ "<td>"+ uAccess +"</td>"
							+ "<td>"+ users.activeTag +"</td>"
							+ "<td>"+ users.entryDate +"</td>"
							+ '<td><button onclick="editUser()" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-edit"></span></td>';
				
				var newTr = new Element("tr");
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

//add new user
$("btnAddUser").observe("click", function() {
	addUser();
});

function addUser(){
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "post",
		parameters : {
			action : "goToUserMaintenanceScreen"
		},
		onComplete : function(response) {
			$("mainContents").update(response.responseText);
		}
	});
}

//EDIT USER

function editUser(){
	new Ajax.Request(contextPath +"/UserMaintenanceController", {
		method : "post",
		parameters : {
			action 			: "editUser"
		},
		onComplete : function(response) {
			$("mainContents").update(response.responseText);
		}
	});
}