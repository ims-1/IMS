<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ims-css.css" type="text/css">  
	<title>Change Password</title>
</head>
<body>
<div id="mainContents">
<jsp:include page="/views/header.jsp"></jsp:include>
	<table>
		<tr class="record">
			<td class="label-right">CURRENT PASSWORD</td>
			<td><input id="txtCurrentPassword" type="password" class="form-control" maxlength="100"/></td>
		</tr>
		<tr class="record">
			<td class="label-right">NEW PASSWORD</td>
			<td><input id="txtNewPassword" type="password" class="form-control" maxlength="100"/></td>
		</tr>
		<tr class="record">
			<td><input id="btnConfirm" type="button" value="CONFIRM" onclick="confirmPassword()"/></td>
		</tr>
	</table>
	<input id="hiddenBtn" type="hidden" value="${userId}"/>
</div>
</body>
<script type="text/javascript">var contextPath = "${pageContext.request.contextPath}";</script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userChangePassword.js"></script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/users.js"></script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userMaintenance.js"></script>
</html>