<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ims-css.css" type="text/css"> 
	<title>User Maintenance Screen</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1 align="center" style="color: yellow;">User Maintenance Screen</h1>
	<div>
		<table class="table borderless">
		  <tr class="record">
		    <td class="label-right">USER ID</td>
		    <td><input id="txtUserId" type="text" class="required form-control" value="${userId}" ${disableUserId}/></td>
		  </tr>
		  <tr class="record">
		    <td class="label-right">FIRST NAME</td>
		    <td><input id="txtFirstName" type="text" class="required form-control" value="${firstName}"/></td>
		  </tr>
		  <tr class="record">
		    <td class="label-right">MIDDLE INITIAL</td>
		    <td><input id="txtMiddleInitial" type="text" class="required form-control" value="${middleInitial}"/></td>
		  </tr>
		  <tr class="record">
		    <td class="label-right">LAST NAME</td>
		    <td><input id="txtLastName" type="text" class="required form-control" value="${lastName}"/></td>
		  </tr>
		  <tr class="record">
		    <td class="label-right">EMAIL</td>
		    <td><input id="txtEmail" type="text" class="required form-control" value="${email}"/></td>
		  </tr>
		  <tr class="record">
		    <td class="label-right">STATUS</td>		    
		    <td style="color: white;">ACTIVE<input id="rdActive" name="status" type="radio" />
		    	INACTIVE<input id="rdInactive" name="status" type="radio"/></td>
		  </tr>
		  <tr class="record">
		    <td class="label-right">USER ACCESS</td>
		    <td><select id="selUserAccess" class="required form-control" >
		    		<option value="A" selected>ADMIN</option>
		    		<option value="U">REGULAR USER</option>
		    	</select>
		    </td>
		  </tr>
		   <tr class="record">
		    <td class="label-right">ENTRY DATE</td>
		    <td><input id="txtEntryDate" type="text" disabled="disabled" value="${entryDate}"/></td>
		  </tr>
		   <tr class="record">
		    <td class="label-right">REMARKS</td>
		    <td><input id="txtRemarks" type="text" class="form-control" value="${remarks}"/></td>
		  </tr>
		   <tr class="record">
		    <td class="label-right">LAST UPDATED BY</td>
		    <td><input id="txtLastUpdatedBy" type="text" disabled="disabled" value="${lastUserId}"/></td>
		  </tr>
		   <tr class="record">
		    <td class="label-right">LAST UPDATE</td>
		    <td><input id="txtLastUpdate" type="text" disabled="disabled" value="${lastUpdate}"/></td>
		  </tr>	  
		</table>
			<input id="btnBackToUserListingPage" type="button" value="BACK" class="btn btn-primary"/><br>
		    <input id="btnSaveUser" type="button" value="SAVE" class="btn btn-primary" onclick="saveUser()"/><br>
		    <input id="btnUserChangePassword" type="button" value="CHANGE PASSWORD" class="btn btn-primary"/>
	<input type="hidden" id="txtHidden" value="${hidden}"/>
	</div>
</body>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
//radio btn
if ('${activeTag}' == 'Y') {
	$("rdActive").checked = "checked";
}
if ('${activeTag}' == 'N') {
	$("rdInactive").checked = "checked";
}
//dropdown
if ('${userAccess}' == 'A') {
	$('selUserAccess').value = '${userAccess}';
}
if ('${userAccess}' == 'U') {
	$('selUserAccess').value = '${userAccess}';
}
</script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userMaintenanceScreen.js"></script>
<script src="${pageContext.request.contextPath}/js/userMaintenance/userListingPage.js"></script>
</html>