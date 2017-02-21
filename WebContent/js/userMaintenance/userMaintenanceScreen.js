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
$("btnSaveUser").observe("click", function() {
	if (nullFieldValidation()) {
		alert("Please fill out all fields.");
	} else if (validCharFieldValidation()) {
		alert("Invalid character.");
	}
	else {
		alert("User added.");
		saveUser();
	}
});

function nullFieldValidation() {
	if ($F("txtFirstName") == '' || $F("txtMiddleInitial") == ''
		|| $F("txtLastName") == '' || $F("txtEmail") == '' || $F("txtRemarks") == '') {
		return true;
	}
}

function validCharFieldValidation() {
	if (!isNaN($("txtFirstName").value) || !isNaN($("txtMiddleInitial").value) 
			|| !isNaN($("txtLastName").value) || !isNaN($("txtEmail").value) || !isNaN($("txtRemarks").value)) {
		return true;
	}
}

function saveUser(){
	new Ajax.Request(contextPath +"/UserMaintenanceController", {
		method : "post",
		parameters : {
			action : "saveUser",
			userId : $F("txtUserId"),
			firstName : $F("txtFirstName"),
			middleInitial : $F("txtMiddleInitial"),
			lastName : $F("txtLastName"),
			email : $F("txtEmail"),
			activeTag : 'A',
			userAccess : "Hello",
			remarks : $F("txtRemarks"),
		},
		onComplete : function(response) {
			$("mainContents").update(response.responseText);
		}
	});
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
