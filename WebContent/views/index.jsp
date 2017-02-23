<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Username:
	<input type="text" id="txtUserName" placeholder="username">
	<br> Password:
	<input type="password" id="txtPassword" placeholder="password">
	<br>
	<input type="button" id="btnSubmit" value="Login">
</body>
<script type="text/javascript">
	$('btnSubmit').observe("click", function() {
		new Ajax.Request("${pageContext.request.contextPath}" + "/login", {
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
	});
</script>
</html>