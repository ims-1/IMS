<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<title>User Maintenance Screen</title>
</head>
<body>
<h1>User Maintenance Screen</h1>
	<div>
		<table id="usersMaintenanceBody">
		  <tr>
		    <td>USER ID</td>
		    <td><input id="txtUserId" type="text"/></td>
		  </tr>
		  <tr>
		    <td>FIRST NAME</td>
		    <td><input id="txtFirstName" type="text"/></td>
		  </tr>
		  <tr>
		    <td>MIDDLE INITIAL</td>
		    <td><input id="txtMiddleInitial" type="text"/></td>
		  </tr>
		  <tr>
		    <td>LAST NAME</td>
		    <td><input id="txtLastName" type="text"/></td>
		  </tr>
		  <tr>
		    <td>EMAIL</td>
		    <td><input id="txtEmail" type="text"/></td>
		  </tr>
		  <tr>
		    <td>STATUS</td>
		    <td>ACTIVE<input id="rdActive" name="status" type="radio"/>INACTIVE<input id="rdInactive" name="status" type="radio"/></td>
		  </tr>
		  <tr>
		    <td>USER ACCESS</td>
		    <td><select id="selUserAccess">
		    		<option value=""></option>
		    	</select>
		    </td>
		  </tr>
		   <tr>
		    <td>ENTRY DATE</td>
		    <td><input id="txtEntryDate" type="text"/></td>
		  </tr>
		   <tr>
		    <td>REMARKS</td>
		    <td><input id="txtRemarks" type="text"/></td>
		  </tr>
		   <tr>
		    <td>LAST UPDATED BY</td>
		    <td><input id="txtLastUpdatedBy" type="text"/></td>
		  </tr>
		   <tr>
		    <td>LAST UPDATE</td>
		    <td><input id="txtLastUpdate" type="text"/></td>
		  </tr>
		   <tr>
		    <td><input id="btnBackToUserListingPage" type="button" value="BACK"/>
		    	<input id="btnSaveUser" type="button" value="SAVE"/>
		    </td>
		    <td><input id="btnUserChangePassword" type="button" value="CHANGE PASSWORD"/></td>
		  </tr>
		</table>
	</div>
</body>
<script type="text/javascript">var contextPath = "${pageContext.request.contextPath}"</script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userMaintenanceScreen.js"></script>
</html>