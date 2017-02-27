<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Unauthenticated</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ims-css.css"
	type="text/css">
<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
</head>
<body>
	<jsp:include page="/views/header.jsp"></jsp:include>
	<p class="error-message">User not authenticated exception
	<p>
		<button class="btn btn-primary" id="btnLogin">Login</button>
</body>
<script>
	$('btnLogin').observe("click", function() {
		window.location.href = "${pageContext.request.contextPath}";
	});
</script>
</html>