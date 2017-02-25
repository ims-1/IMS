function confirmPassword() {
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "post",
		parameters : {
			action : "confirmPassword",
			userId : $F("hiddenBtn"),
			currentPassword : $F("txtCurrentPassword"),
			newPassword : $F("txtNewPassword")
		},
		onComplete : function(response) {
		
		}
	});
}