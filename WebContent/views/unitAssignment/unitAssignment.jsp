<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ims-css.css"
	type="text/css">
</head>
<body>
	<jsp:include page="/views/header.jsp"></jsp:include>
	<div id="mainContents">
		<table>
			<tr>
				<td>
					<table class="table borderless">
						<tr>
							<td><label class="label-right">UNIT NO.</label></td>
							<td>
								<div class="input-group">
									<input type="text" class="form-control" id="txtUnitNo">
									<span class="input-group-btn">
										<button class="btn btn-secondary" type="button"
											data-toggle="modal" data-target="#myUnitModal" id="btnUnitSearch">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div>
							</td>
							<td><label class="label-right">SERIAL NO. </label></td>
							<td><input type="text" id="txtSerialNo" /></td>
						</tr>
						<tr>
							<td><label class="label-right">UNIT NAME </label></td>
							<td><input type="text" id="txtUnitName" /></td>
							<td><label class="label-right">BRAND</label></td>
							<td><input type="text" id="txtBrand" /></td>
						</tr>
						<tr>
							<td><label class="label-right">TAG NUMBER </label></td>
							<td><input type="text" id="txtTagNumber" /></td>
							<td><label class="label-right">MODEL </label></td>
							<td><input type="text" id="txtUnitModel" /></td>
						</tr>
						<tr>
							<td><label class="label-right">TYPE </label></td>
							<td><input type="text" id="txtUnitType" /></td>
							<td><label class="label-right">COLOR </label></td>
							<td><input type="text" id="txtUnitColor" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="table borderless">
						<tr>
							<td><label class="label-right">ASSIGNEE</label></td>
							<td>
								<div class="input-group">
									<input type="text" class="form-control" id="txtAssignee">
									<span class="input-group-btn">
										<button class="btn btn-secondary" type="button"
											data-toggle="modal" data-target="#myAssigneeModal"
											id="btnAssigneeSearch">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div> <input type="hidden" id="hiddenAssignNo">
							</td>
							<td><label class="label-right">STATUS </label></td>
							<td><input type="text" id="txtAssigneeStatus" /></td>
						</tr>
						<tr>
							<td><label class="label-right">LOCATION </label></td>
							<td><input type="text" id="txtAssigneeLocation" /></td>
							<td><label class="label-right">ASSIGNED BY </label></td>
							<td><input type="text" id="txtAssignedBy" /></td>
						</tr>
						<tr>
							<td><label class="label-right">IP ADDRESS </label></td>
							<td><input type="text" id="txtIpAdd" /></td>
							<td><label class="label-right">DATE ASSIGNED </label></td>
							<td><input type="text" id="txtDateAssigned" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<input type="button" value="Assign Unit" id="btnAssign"
			class="btn btn-primary" /><br> <br> <input type="text"
			id="searchBox" placeholder="Enter Keyword to search.." /><br> <br>

	</div>

	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>NO.</th>
				<th>Unit Name</th>
				<th>Location</th>
				<th>IP Address</th>
				<th>Status</th>
				<th>Assigned By</th>
				<th>Date Assigned</th>
				<th>Return date</th>
			</tr>
		</thead>
		<tbody id="body">
			<tr class="record"></tr>
	</table>
	<div id="pagination" class="btn btn-primary"></div>
	<jsp:include page="/views/modals/unitModal.jsp"></jsp:include>
	<jsp:include page="/views/modals/assigneeModal.jsp"></jsp:include>
</body>

<script>
	var context = "${pageContext.request.contextPath}";
</script>
<script
	src="${pageContext.request.contextPath}/js/prototype.js"></script>
<script type="text/javascript" src="js/require.js"></script>
<script type="text/javascript" src="js/resolveConflict.js"></script>
<script
	src="${pageContext.request.contextPath}/js/unitAssignment/unitAssignment.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js"></script>

</html>