//BACK BUTTON - go back to USER LISTING PAGE
function backToUserListingPage() {
	window.location.href = contextPath + "/users";
}

// SAVE BUTTON - save user
function saveUserAdd() {
	if (validate()) {
		var activeUser = "Y";

		if (rdActive.checked) {
			activeUser = 'Y';
		}
		if (rdInactive.checked) {
			activeUser = 'N';
		}
		new Ajax.Request(contextPath + "/UserMaintenanceController", {
			method : "post",
			parameters : {
				action : "saveUserAdd",
				userId : $F("txtUserId"),
				firstName : $F("txtFirstName"),
				middleInitial : $F("txtMiddleInitial"),
				lastName : $F("txtLastName"),
				email : $F("txtEmail"),
				activeTag : activeUser,
				userAccess : $F("selUserAccess"),
				remarks : $F("txtRemarks"),
			},
			onComplete : function(response) {
//				$("mainContents").update(response.responseText);
			},
			onSuccess : function(response) {
				if (response.status == 203) {
					alert("User ID already in use.");
				} else {
					alert("Saved successfully!");
				}
			}
		});
	}
}


function saveUserEdit() {
	if (validate()) {
		var activeUser = "Y";

		if (rdActive.checked) {
			activeUser = 'Y';
		}
		if (rdInactive.checked) {
			activeUser = 'N';
		}
		new Ajax.Request(contextPath + "/UserMaintenanceController", {
			method : "post",
			parameters : {
				action : "saveUserEdit",
				userId : $F("txtUserId"),
				firstName : $F("txtFirstName"),
				middleInitial : $F("txtMiddleInitial"),
				lastName : $F("txtLastName"),
				email : $F("txtEmail"),
				activeTag : activeUser,
				userAccess : $F("selUserAccess"),
				remarks : $F("txtRemarks"),
			},
			onComplete : function(response) {
//				$("mainContents").update(response.responseText);
			},
			onSuccess : function(response) {
				if (response.status == 203) {
					alert("User ID already in use.");
				} else {
					alert("User up to date.");
				}
			}
		});
	}
}

// CHANGE PASSWORD BUTTON - change password of user
function userChangePassword() {
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "post",
		parameters : {
			action : "userChangePassword",
			userId : $F("txtUserId")
		},
		onComplete : function(response) {
			$("mainContents").update(response.responseText);
		}
	});
}
