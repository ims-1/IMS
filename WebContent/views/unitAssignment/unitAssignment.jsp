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
		<table class="table borderless" id="tableInput">
			<tr>
				<td class="label-right">UNIT NO.</td>
				<td>
					<!-- <div class="input-group">
									<input type="text" class="form-control" id="txtUnitNo">
									<span class="input-group-btn">
										<button class="btn btn-secondary" type="button"
												data-toggle="modal" data-target="#myUnitModal" id="btnUnitSearch">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div> --> <% 
									String unitNo = request.getParameter("unitNo");
									 	if (unitNo == null || unitNo == "") {
									 		out.print("<div class=input-group>");
									 		out.print("<input type=text class=form-control id=txtUnitNo readonly='readonly'>");
									 		out.print("<span class=\"input-group-btn\">");
									 		out.print("	<button class=\"btn btn-secondary\" type=button");
									 		out.print("		data-toggle=modal data-target=#myUnitModal");
									 		out.print("		id=btnUnitSearch onclick=\"fetchCompUnits()\">");
									 		out.print("		<span class=\"glyphicon glyphicon-search\"></span>");
									 		out.print("	</button>");
									 		out.print("</span></div>");
									 	} else {
									 		out.print("<input type=text class=form-control id=txtUnitNo value=" + unitNo + " readonly='readonly'>");
									 	}
								 %>

				</td>
				<td class="label-right">SERIAL NO.</td>
				<td><input type="text" id="txtSerialNo" class="form-control"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label-right">UNIT NAME</td>
				<td><input type="text" id="txtUnitName" readonly="readonly"
					class="form-control" /></td>
				<td class="label-right">BRAND</td>
				<td><input type="text" id="txtBrand" readonly="readonly"
					class="form-control" /></td>
			</tr>
			<tr>
				<td class="label-right">TAG NUMBER</td>
				<td><input type="text" id="txtTagNumber" readonly="readonly"
					class="form-control" /></td>
				<td class="label-right">MODEL</td>
				<td><input type="text" id="txtUnitModel" class="form-control"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label-right">TYPE</td>
				<td><input type="text" id="txtUnitType" readonly="readonly"
					class="form-control" /></td>
				<td class="label-right">COLOR</td>
				<td><input type="text" id="txtUnitColor" readonly="readonly"
					class="form-control" /></td>
			</tr>
		</table>
		<table class="table borderless" id="tableInput">
			<tr>
				<td class="label-right">ASSIGNEE</td>
				<td>
					<div class="input-group">
						<input type="text" class="form-control" id="txtAssignee"
							readonly="readonly"> <span class="input-group-btn">
							<button class="btn btn-secondary" type="button"
								data-toggle="modal" data-target="#myAssigneeModal"
								id="btnAssigneeSearch">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div> <input type="hidden" id="hiddenAssignNo">
				</td>
				<td class="label-right">STATUS</td>
				<td><select id="txtAssigneeStatus" class="form-control">
						<option value="P">PERMANENT</option>
						<option value="T">TEMPORARY</option>
						<option value="V">VACANT</option>
				</select></td>
			</tr>
			<tr>
				<td class="label-right">LOCATION</td>
				<td><input type="text" id="txtAssigneeLocation"
					class="form-control" /></td>
				<td class="label-right">ASSIGNED BY</td>
				<td><input type="text" id="txtAssignedBy" class="form-control"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="label-right">IP ADDRESS</td>
				<td><input type="text" id="txtIpAdd" class="form-control" /></td>
				<td class="label-right">DATE ASSIGNED</td>
				<td><input type="text" id="txtDateAssigned"
					class="form-control" readonly="readonly" /></td>
			</tr>
		</table>

		<button style="margin-left: 5.5in;" class="btn btn-secondary"
			type="button" data-toggle="modal" data-target="#modalReturnDate"
			id="btnAssign">Assign Unit</button>
		<br>
		<br> <input type="text" id="searchBox"
			onkeyup="filteredUnitAssignmentsHist()"
			placeholder="Enter Keyword to search.." class="form-control" /> <br>
		<br>


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
	<div id="pagination"></div>
	<jsp:include page="/views/modals/unitModal.jsp"></jsp:include>
	<jsp:include page="/views/modals/assigneeModal.jsp"></jsp:include>
	<jsp:include page="/views/modals/returnDateModal.jsp"></jsp:include>
</body>
<script>
	var context = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
<script type="text/javascript" src="js/require.js"></script>
<script type="text/javascript" src="js/resolveConflict.js"></script>

<script
	src="${pageContext.request.contextPath}/js/unitAssignment/unitAssignment.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js"></script>
</html>