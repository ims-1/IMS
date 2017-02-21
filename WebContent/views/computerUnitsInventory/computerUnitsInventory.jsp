<html>
<head>
<script src="${pageContext.request.contextPath}/js/prototype.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/computerUnitsInventoryStyle.css">
<title>Computer Units Inventory</title>
</head>
<body>
	<div id="mainContents">
		<input type="text" id="txtSearchBox" value="Enter Words to Search"
			onfocus=" "> <br>
		<table id="tblComputerUnits" border="1">
			<tr>
				<th>NO.</th>
				<th>UNIT NAME</th>
				<th>TAG NUMBER</th>
				<th>IP ADDRESS</th>
				<th>TYPE</th>
				<th>BRAND/MODEL</th>
				<th>SERIAL NUMBER</th>
				<th>ACQUIRED DATE</th>
			</tr>
			<tr>
				<td>0001</td>
				<td>TRNG-201</td>
				<td>2017/02/21:007</td>
				<td>192.12.12.155</td>
				<td>Sample type 1</td>
				<td>Sample brand 1</td>
				<td>swer98we923423</td>
				<td>Feb. 21, 2017</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<table id="inputFields">
						<tr>
							<td>UNIT NO.</td>
							<td><input type="text" id="txtUnitNo"></td>
						</tr>
						<tr>
							<td>UNIT NAME</td>
							<td><input type="text" id="txtUnitName"></td>
						</tr>
						<tr>
							<td>TAG NUMBER</td>
							<td><input type="text" id="txtTagNumber"></td>
						</tr>
						<tr>
							<td>IP ADDRESS</td>
							<td><input type="text" id="txtIpAddress"></td>
						</tr>

						<tr>
							<td>TYPE</td>
							<td><input type="text" id="txtType"></td>
						</tr>
						<tr>
							<td>ACQUIRED DATE</td>
							<td><input type="text" id="txtAcquiredDate"></td>
						</tr>

						<tr>
							<td>DESCRIPTION</td>
							<td><input type="text" id="txtDescription"></td>
						</tr>
					</table>
				</td>
				<td>
					<table>
						<tr>
							<td>SERIAL NO.</td>
							<td><input type="text" id="txtSerialNo"></td>
						</tr>
						<tr>
							<td>BRAND</td>
							<td><input type="text" id="txtBrand"></td>
						</tr>

						<tr>
							<td>MODEL</td>
							<td><input type="text" id="txtModel"></td>
						</tr>

						<tr>
							<td>COLOR</td>
							<td><input type="text" id="txtColor"></td>
						</tr>

						<tr>
							<td>USER ID</td>
							<td><input type="text" id="txtUserId"></td>
						</tr>

						<tr>
							<td>LAST UPDATE</td>
							<td><input type="text" id="txtLastUpdate"></td>
						</tr>

						<tr>
							<td>REMARKS</td>
							<td><input type="text" id="txtRemarks"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br> <input type="button" id="btnAddUpdate" value="ADD">
		<input type="button" id="btnDelete" value="DELETE"> <br>
		<input type="button" id="btnUnitAssignment" value="UNIT ASSIGNMENT">
		<input type="button" id="btnOSandSI"
			value="Operating System and Software Installed"> <input
			type="button" id="btnPeripherals" value="PERIPHERALS"> <input
			type="button" id="btnSave" value="SAVE"> <input type="button"
			id="btnCancel" value="CANCEL">
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/computerUnitsInventory/computerUnitsInventory.js">
</script>
</html>