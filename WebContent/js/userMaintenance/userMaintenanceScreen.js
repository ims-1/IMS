//BACK BUTTON - go back to USER LISTING PAGE
$("btnBackToUserListingPage").observe("click", function() {
	backToUserListingPage();
});

function backToUserListingPage(){
	new Ajax.Request(contextPath + "/UserMaintenanceController", {
		method : "post",
		parameters : {
			action : "backToUserListingPage"
		},
		onComplete : function(response) {
			$("mainContents").update(response.responseText);
		}
	});
}

//SAVE BUTTON - save user
function saveUser(){
	if(validate()){
		var activeUser = "Y";

		if (rdActive.checked) {
			activeUser = 'Y';
		} 
		if (rdInactive.checked) {
			activeUser = 'N';
		}

		new Ajax.Request(contextPath +"/UserMaintenanceController", {
			method : "post",
			parameters : {
				action 			: "saveUser",
				userId 			: $F("txtUserId"),	
				firstName 		: $F("txtFirstName"),
				middleInitial 	: $F("txtMiddleInitial"),
				lastName 		: $F("txtLastName"),
				email 			: $F("txtEmail"),
				activeTag 		: activeUser,
				userAccess 		: $F("selUserAccess"),
				remarks 		: $F("txtRemarks"),
			},
			onComplete : function(response) {
				$("mainContents").update(response.responseText);
			},
			onSuccess : function(response) {
				alert("Saved successfully!");
			},
			onFailure : function(response) {
				alert("User ID already in use.");
			}
		});
	}
}

//CHANGE PASSWORD BUTTON - change password of user
$("btnUserChangePassword").observe("click", function() {
	userChangePassword();
});

function userChangePassword(){
	new Ajax.Request("${pageContext.request.contextPath}/UserMaintenanceController", {
		method : "post",
		parameters : {
			action : "userChangePassword"
		},
		onComplete : function(response) {
			$("mainContents").update(response.responseText);
		}
	});
}
