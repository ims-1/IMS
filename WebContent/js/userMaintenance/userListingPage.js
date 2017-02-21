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