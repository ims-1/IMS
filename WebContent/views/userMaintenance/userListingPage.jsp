<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<title>User Listing Page</title>
</head>
<body>
	<h1>User Listing Page</h1>
		<div id="mainContents">
			<div id="usersMainTable">
				<table id="usersMainBodyList" border="1">
				  <tr>
				    <th>USER ID</th>
				    <th>USER NAME</th>
				    <th>USER ACCESS</th>
				    <th>ACTIVE TAG</th>
				    <th>ENTRY DATE</th>
				    <th>ACTIONS</th>
				  </tr>
				</table>
			</div>
			<br>
			<div>
				<input id="btnAddUser" type="button" value="ADD USER"/>
			</div>
			<br>
			<div>
				<input id="txtSearch" type="text" value="Enter keyword to search..."/>
			</div> 
		</div>
</body>
<script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userListingPage.js"></script>
</html>