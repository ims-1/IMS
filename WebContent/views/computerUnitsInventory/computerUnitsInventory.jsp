<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ims-css.css"
	type="text/css">
<title>Computer Units Inventory</title>
</head>
<body>
	<div id="mainContents">
		<jsp:include page="/views/header.jsp"></jsp:include>
		<input type="text" id="txtSearchBox" onkeyup="filterTable()"
			placeholder="Enter Words to Search"> <br>
		<table id="tblComputerUnits"
			class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="label-right">NO.</th>
					<th>UNIT NAME</th>
					<th>TAG NUMBER</th>
					<th>IP ADDRESS</th>
					<th>TYPE</th>
					<th>BRAND/MODEL</th>
					<th>SERIAL NUMBER</th>
					<th>ACQUIRED DATE</th>
				</tr>
			</thead>
			<tbody id="body">
				<%-- <c:forEach var="compUnitList" items="${compUnitList}">
					<tr class="record" onclick="selectedRow(this)">
						<td>${compUnitList.unitNo}</td>
						<td>${compUnitList.unitName}</td>
						<td>${compUnitList.tagNumber}</td>
						<td>${compUnitList.ipAddress}</td>
						<td>${compUnitList.type}</td>
						<td>${compUnitList.brand}</td>
						<td>${compUnitList.serialNo}</td>
						<td>${compUnitList.acquiredDate}</td>
					</tr>
				</c:forEach>
				<tr class="record" onclick="selectedRow(this)">
					<td>0001</td>
					<td>TRNG-201</td>
					<td>2017/02/21:007</td>
					<td>192.12.12.155</td>
					<td>Sample type 1</td>
					<td>Sample brand 1</td>
					<td>swer98we923423</td>
					<td>Feb. 21, 2017</td>
				</tr> --%>
			</tbody>
		</table>
		<div id="pagination" align="right"></div>
		<table id="inputFields" class="table borderless">
			<tr>
				<td class="label-right">UNIT NO.</td>
				<td><input type="text" id="txtUnitNo"
					class="number form-control" disabled="disabled"></td>

				<td class="label-right">SERIAL NO.</td>
				<td><input type="text" id="txtSerialNo"
					class="required form-control"></td>
			</tr>
			<tr>
				<td class="label-right">UNIT NAME</td>
				<td><input type="text" id="txtUnitName"
					class="required form-control"></td>

				<td class="label-right">BRAND</td>
				<td><input type="text" id="txtBrand"
					class="required form-control"></td>
			</tr>
			<tr>
				<td class="label-right">TAG NUMBER</td>
				<td><input type="text" id="txtTagNumber"
					class="required form-control"></td>
				<td class="label-right">MODEL</td>
				<td><input type="text" id="txtModel" class="form-control"></td>
			</tr>
			<tr>
				<td class="label-right">IP ADDRESS</td>
				<td><input type="text" id="txtIpAddress" class="form-control"></td>
				<td class="label-right">COLOR</td>
				<td><input type="text" id="txtColor" class="form-control"></td>
			</tr>

			<tr>
				<td class="label-right">TYPE</td>
				<td><div id="divSelectType"></div></td>
				<td class="label-right">USER ID</td>
				<td><input type="text" id="txtUserId"
					class=" required form-control"></td>
			</tr>
			<tr>
				<td class="label-right">ACQUIRED DATE</td>
				<td><input type="datetime-local" id="txtAcquiredDate"
					class="form-control" placeholder="dd-MMM-yy"></td>
				<td class="label-right">LAST UPDATE</td>

				<td><input type="datetime-local" id="txtLastUpdate"
					class="form-control" placeholder="dd-MMM-yy" disabled="disabled"></td>
			</tr>

			<tr>
				<td class="label-right">DESCRIPTION</td>
				<td><input type="text" id="txtDescription" class="form-control"></td>
				<td class="label-right">REMARKS</td>
				<td><input type="text" id="txtRemarks" class="form-control"></td>
			</tr>
		</table>
		<div align="center">
			<table class="tblButtons">
				<tr>
					<td><input type="button" id="btnAddUpdate" value="ADD"
						class="btn btn-primary ADD"></td>
					<td><input type="button" id="btnDelete" value="DELETE"
						class="btn btn-primary"></td>
				</tr>
				<tr>
					<td><input type="button" id="btnUnitAssignment"
						value="UNIT ASSIGNMENT" class="btn btn-primary"></td>
					<td><input type="button" id="btnOSandSI"
						value="Operating System and Software Installed"
						class="btn btn-primary"></td>
					<td><input type="button" id="btnPeripherals"
						value="PERIPHERALS" class="btn btn-primary"></td>
					<td><input type="button" id="btnSave" value="SAVE"
						class="btn btn-primary"></td>
					<td><input type="button" id="btnCancel" value="CANCEL"
						class="btn btn-primary"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	var context = "${pageContext.request.contextPath}";

	function getRecord(record) {
		new Ajax.Request(context + "/ComputerUnitsInventoryController", {
			method : "get",
			parameters : {
				action : "getComputerUnitByUnitNo",
				unitNo : record.down(0).innerHTML
			},
			onSuccess : function(response) {
				var p = response.responseText.evalJSON();
				p.each(function(compUnit) {
					$('txtUnitNo').value = compUnit.unitNo;
					$('txtUnitName').value = compUnit.unitName;
					$('txtTagNumber').value = compUnit.tagNumber;
					$('txtIpAddress').value = compUnit.ipAddress;
					$('txtType').value = compUnit.type == "LT" ? "Laptop"
							: "Desktop";
					$('txtBrand').value = compUnit.brand;
					$('txtSerialNo').value = compUnit.serialNo;
					$('txtAcquiredDate').value = compUnit.acquiredDate;
					$('txtLastUpdate').value = compUnit.lastUpdate;
					$('txtUserId').value = compUnit.userId;
					$('txtDescription').value = compUnit.description;
					$('txtColor').value = compUnit.color;
					$('txtModel').value = compUnit.model;
					$('txtRemarks').value = compUnit.remarks;
					$('btnAddUpdate').value = "UPDATE";
				})
			}
		})
	};

	function clearFields() {
		$('txtUnitNo').value = "";
		$('txtUnitName').value = "";
		$('txtTagNumber').value = "";
		$('txtIpAddress').value = "";
		$('txtType').value = "";
		$('txtBrand').value = "";
		$('txtSerialNo').value = " ";
		$('txtAcquiredDate').value = " ";
		$('txtLastUpdate').value = " ";
		$('txtUserId').value = " ";
		$('txtDescription').value = " ";
		$('txtColor').value = "";
		$('txtModel').value = "";
		$('txtRemarks').value = "";
		$('btnAddUpdate').value = "ADD";
	};
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/computerUnitsInventory/computerUnitsInventory.js">
	
</script>
</html>