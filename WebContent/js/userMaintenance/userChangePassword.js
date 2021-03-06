function confirmPassword() {
	if ($F("txtCurrentPassword") == "" && $F("txtNewPassword") == "") {
		alert("Please fill in all fields.");
	} else {
		new Ajax.Request(contextPath + "/UserMaintenanceController", {
			method : "post",
			parameters : {
				action : "confirmPassword",
				userId : $F("txtUserId"),
				currentPassword : $F("txtCurrentPassword"),
				newPassword : $F("txtNewPassword")
			},
			onSuccess : function(response) {
				if (response.status == 202) {
					alert("Incorrect password.");
				}
				if (response.status == 200) {
					alert("Password changed.");
				}
			}
		});
	}
}