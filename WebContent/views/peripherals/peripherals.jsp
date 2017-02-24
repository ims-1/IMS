<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Peripherals</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ims-css.css" type="text/css"/> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
</head>
<body>
<jsp:include page="/views/header.jsp"></jsp:include>
<div align=center>
<table class="table borderless" id="tableCompUnit">
	<tr>
		<td class="label-right">Unit No.</td>
		<td><input type="text" id="txtUnitNo" class="required"/></td>
		<td class="label-right">Serial No.</td>
		<td><input type="text" id="txtSerialNo" disabled="disabled"/></td>
	</tr>
	<tr>
		<td class="label-right">Unit Name</td>
		<td><input type="text" id="txtUnitName" disabled="disabled"/></td>
		<td class="label-right">Brand</td>
		<td><input type="text" id="txtBrand" disabled="disabled"/></td>
	</tr>
	<tr>
		<td class="label-right">Tag Number</td>
		<td><input type="text" id="txtTagNumber" disabled="disabled"/></td>
		<td class="label-right">Model</td>
		<td><input type="text" id="txtModel" disabled="disabled"/></td>
	</tr>
	<tr>
		<td class="label-right">Type</td>
		<td><input type="text" id="txtType" disabled="disabled"/></td>
		<td class="label-right">Color</td>
		<td><input type="text" id="txtColor" disabled="disabled"/></td>
	</tr>
	<tr>
		<td class="label-right">IP Address</td>
		<td><input type="text" id="txtIpAddress" disabled="disabled"/></td>
		<td class="label-right">Location</td>
		<td><input type="text" id="txtLocation" disabled="disabled"/></td>
	</tr>
	<tr>
		<td class="label-right">Assignee</td>
		<td><input type="text" id="txtAssignee" disabled="disabled"/></td>
		<td class="label-right">Status</td>
		<td><input type="text" id="txtStatus" disabled="disabled"/></td>		
	</tr>
</table>
<table class="table table-striped table-bordered" id="tableRecord">
	<thead>
		<tr>
			<th>No</th>
			<th>Peripheral Type</th>
			<th>Tag Number</th>
			<th>Brand / Model</th>
			<th>Serial No.</th>
			<th>Acquired Date</th>
			<th>Description</th>
		</tr>
	</thead>
	<tbody id="body"></tbody>
</table>
<div class="btn-group border" id="pagination" align="right"> 
	<button type="button" class="btn">1</button>
	<button type="button" class="btn">2</button> 
	<button type="button" class="btn">3</button> 
	<button type="button" class="btn">4</button>
</div>
<table class="table borderless" id="tableInput">
	<tr>
		<td class="label-right">Peripheral No.</td>
		<td><input type="text" id="txtPeripheralNo" class="form-control" readonly="readonly"></td>
		<td class="label-right">Serial No.</td>
		<td><input type="text" id="txtSerialNo" class="form-control"></td>
	</tr>
	<tr>
		<td class="label-right">Peripheral Type</td>
		<td><input type="text" id="txtPeripheralType" class="required form-control"></td>
		<td class="label-right">Brand</td>
		<td><input type="text" id="txtBrand" class="form-control"></td>
	</tr>
	<tr>
		<td class="label-right">Tag Number</td>
		<td><input type="text" id="txtTagNumber" class="form-control"></td>
		<td class="label-right">Model</td>
		<td><input type="text" id="txtModel" class="form-control"></td>
	</tr>
	<tr>
		<td class="label-right">Acquired Date</td>
		<td><input type="datetime-local" id="dtAcquiredDate" class="form-control" placeholder="MM/DD/YYYY"></td>
		<td class="label-right">Color</td>
		<td><input type="text" id="txtColor" class="form-control"></td>
	</tr>
	<tr>
		<td class="label-right">Description</td>
		<td><input type="text" id="txtDescription" class="form-control"></td>
		<td class="label-right">User ID</td>
		<td><input type="text" id="txtUserId" class="form-control" disabled="disabled"></td>
	</tr>
	<tr>
		<td class="label-right">Remarks</td>
		<td><input type="text" id="txtRemarks" class="form-control"></td>
		<td class="label-right">Last Update</td>
		<td><input type="datetime-local" id="dtLastUpdate" class="date form-control" placeholder="MM/DD/YYYY"  disabled="disabled"></td>
	</tr>
	</tbody>
</table>
<table class="table borderless">
	<tr>
		<td align="right"><input type="button" id="btnAdd" value="Add" class="btn btn-primary" onclick="addPeripherals()"></td>
		<td><input type="button" id="btnDelete" value="Delete" class="btn btn-primary"></td>
	</tr>
	<tr>
		<td align="right"><input type="button" id="btnSave" value="Save" class="btn btn-primary"onclick="insertPeripherals()"></td>
		<td><input type="button" id="btnCancel" value="Cancel" class="btn btn-primary"></td>
	</tr>
</table>
</div>
	</body>
	<script>
		var context = "${pageContext.request.contextPath}";
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/peripherals/peripherals.js"></script>
</html>