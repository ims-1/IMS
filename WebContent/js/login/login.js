$('btnSubmit').observe("click", function() {
	if (validate()) {
		$('spinner').style.display = "block";
		new Ajax.Request(context + "/login", {
			method : "post",
			parameters : {
				username : $F('txtUserName'),
				password : $F('txtPassword'),
				action : ""
			},
			onSuccess : function(response) {
				if (response.status == 200) {
					window.location.href = context + "/home";
				} else if (response.status == 201) {
					alert("Please provide correct password and username");
				} else if (response.status == 202) {
					alert("User inactive");
				}
				$('spinner').style.display = "none";
			},
			onFailure : function(response) {
				alert('There is something wrong with the connection');
				$('spinner').style.display = "none";
			}
		});
	}
});

onLoad();
function onLoad() {
	new Ajax.Request(context + "/login", {
		method : "post",
		parameters : {
			action : "auth"
		},
		onSuccess : function(response) {
			if (response.status == 204) {
				window.location.href = context + "/home";
			}
		}

	});
}