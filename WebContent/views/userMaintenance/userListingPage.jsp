
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ims-css.css" type="text/css"> 
	<title>User Listing Page</title>
</head>
<body>
<div id="mainContents">
<jsp:include page="../header.jsp"></jsp:include>
	<h1 align="center" style="color: yellow;">User Listing Page</h1>
			<div id="usersMainTable" align=center>
				<table id="usersMainBodyList" class="table table-striped table-bordered">
					<thead>
					  <tr>
					    <th colspan="2"><input id="txtSearch" type="text" value="Enter keyword to search..." class="form-control"/></th>
					    <th></th>
					    <th></th>
					    <th></th>
					    <th><input id="btnAddUser" type="button" value="ADD USER" class="btn btn-primary"/></th>
					  </tr>
					  <tr>
					    <th>USER ID</th>
					    <th>USER NAME</th>
					    <th>USER ACCESS</th>
					    <th>ACTIVE TAG</th>
					    <th>ENTRY DATE</th>
					    <th>ACTIONS</th>
					  </tr>
					</thead>
					<tbody align="center" id="body">
					</tbody>
				</table>
				<div id="pagination"></div>
			</div>
		</div>
</body>
<script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userListingPage.js"></script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userMaintenanceScreen.js"></script>
</html>