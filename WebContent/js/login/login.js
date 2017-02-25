$('btnSubmit').observe("click", function() {
	if (validate()) {
		$('spinner').style.display = "block";
		new Ajax.Request(context + "/login", {
			method : "post",
			parameters : {
				username : $F('txtUserName'),
				password : $F('txtPassword')
			},
			onSuccess : function(response) {
				if(response.status == 200){
					window.location.href = "/views/home";
				}
				else{
					alert("Please provide correct password and username");
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