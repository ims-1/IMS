$('btnSubmit').observe("click", function() {
	if (validate()) {
		new Ajax.Request(context + "/login", {
			method : "post",
			parameters : {
				username : $F('txtUserName'),
				password : $F('txtPassword')
			},
			onSuccess : function(response) {
				window.location.href = "/home";
			},
			onFailure : function(response) {
				alert("Please provide correct password and username");
			}
		});
	}
});