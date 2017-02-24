<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ims-css.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/prototype.js"></script>
</head>
<body>
	<jsp:include page="/views/header.jsp"></jsp:include>
	<table id="tableLogin" class="table table-bordered table-striped">
		<tr>
			<td class="label-right">Username:</td>
			<td><input type="text" id="txtUserName" placeholder="username"
				class="required form-control"></td>
		</tr>
		<tr>
			<td class="label-right">Password:</td>
			<td><input type="password" id="txtPassword"
				placeholder="password" class="required form-control"></td>
		</tr>
		<tr align="right">
			<td><input type="button" id="btnSubmit" value="Login"
				class="btn btn-primary"></td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	var context = "${pageContext.request.contextPath}"
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/login/login.js"></script>
</html>