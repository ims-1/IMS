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
		<div id="pagination" align="center"></div>
		<table id="inputFields" class="table borderless">
			<tr>
				<td class="label-right">UNIT NO.</td>
				<td><input type="text" id="txtUnitNo"
					class="number form-control" disabled="disabled" maxlength="4"></td>

				<td class="label-right">SERIAL NO.</td>
				<td><input type="text" id="txtSerialNo"
					class="required form-control" maxlength="150"></td>
			</tr>
			<tr>
				<td class="label-right">UNIT NAME</td>
				<td><input type="text" id="txtUnitName"
					class="required form-control" maxlength="150"></td>

				<td class="label-right">BRAND</td>
				<td><input type="text" id="txtBrand"
					class="required form-control" maxlength="200"></td>
			</tr>
			<tr>
				<td class="label-right">TAG NUMBER</td>
				<td><input type="text" id="txtTagNumber"
					class="required form-control" maxlength="100"></td>
				<td class="label-right">MODEL</td>
				<td><input type="text" id="txtModel" class="form-control"
					maxlength="200"></td>
			</tr>
			<tr>
				<td class="label-right">IP ADDRESS</td>
				<td><input type="text" id="txtIpAddress" class="form-control"
					maxlength="250"></td>
				<td class="label-right">COLOR</td>
				<td><input type="text" id="txtColor" class="form-control"
					maxlength="100"></td>
			</tr>

			<tr>
				<td class="label-right">TYPE</td>
				<td><div id="divSelectType">
						<input type="text" id="txtType" class="form-control"
							maxlength="100">
					</div></td>
				<td class="label-right">USER ID</td>
				<td><input type="text" id="txtUserId"
					class=" required form-control" maxlength="100" disabled="disabled"></td>
			</tr>
			<tr>
				<td class="label-right">ACQUIRED DATE</td>
				<td><input type="text" id="txtAcquiredDate"
					class="required form-control" placeholder="MM/DD/YYYY"></td>
				<td class="label-right">LAST UPDATE</td>
				<td><input type="text" id="txtLastUpdate" class="form-control"
					placeholder="MM/DD/YYYY" disabled="disabled"></td>
			</tr>

			<tr>
				<td class="label-right">DESCRIPTION</td>
				<td><input type="text" id="txtDescription" class="form-control"
					maxlength="1000"></td>
				<td class="label-right">REMARKS</td>
				<td><input type="text" id="txtRemarks" class="form-control"
					maxlength="1000"></td>
			</tr>
		</table>
		<div align="center">

			<table class="tblButtons">
				<tr>
					<td><input type="button" id="btnAddUpdate" value="ADD"
						class="btn btn-primary ADD"></td>
					<td></td>
					<td><input type="button" id="btnDelete" value="DELETE"
						class="btn btn-primary"></td>
					<td></td>
					<td><input type="button" id="btnSave" value="SAVE"
						class="btn btn-primary"></td>
					<td></td>
					<td><input type="button" id="btnCancel" value="CANCEL"
						class="btn btn-primary"><br></td>
					<td></td>
					<td><input type="button" id="btnUnitAssignment"
						value="UNIT ASSIGNMENT" class="btn btn-primary"></td>
					<td></td>
					<td><input type="button" id="btnPeripherals"
						value="PERIPHERALS" class="btn btn-primary"></td>
					<td></td>
					<td><input type="button" id="btnOSandSI"
						value="OS and Software Installed" class="btn btn-primary"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('btnPeripherals').setAttribute("disabled", "disabled");
	$('btnUnitAssignment').setAttribute("disabled", "disabled");
	$('btnOSandSI').setAttribute("disabled", "disabled");
	$('btnDelete').setAttribute("disabled", "disabled");

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

					$('btnPeripherals').removeAttribute("disabled");
					$('btnUnitAssignment').removeAttribute("disabled");
					$('btnOSandSI').removeAttribute("disabled");
					$('btnDelete').removeAttribute("disabled");
				})
			}
		})
	};

	function clearFields() {
		getUserAuth();
		$('txtUnitNo').value = "";
		$('txtUnitName').value = "";
		$('txtTagNumber').value = "";
		$('txtIpAddress').value = "";
		$('txtType').value = "";
		$('txtBrand').value = "";
		$('txtSerialNo').value = " ";
		$('txtAcquiredDate').value = " ";
		$('txtLastUpdate').value = new Date().toLocaleDateString();
		$('txtUserId').value = " ";
		$('txtDescription').value = " ";
		$('txtColor').value = "";
		$('txtModel').value = "";
		$('txtRemarks').value = "";
		$('btnAddUpdate').value = "ADD";
		$('btnPeripherals').setAttribute("disabled", "disabled");
		$('btnUnitAssignment').setAttribute("disabled", "disabled");
		$('btnOSandSI').setAttribute("disabled", "disabled");
		$('btnDelete').setAttribute("disabled", "disabled");
	};
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validation.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/computerUnitsInventory/computerUnitsInventory.js">
	
</script>
</html>