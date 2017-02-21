var tblComputerUnits = $('tblComputerUnits');
var tablerowCounter = 0;
var unitCounter = 0;

$('btnAddUpdate').observe('click', function() {
	addUnitsToTable();
	addUnitsToDatabase();
});
$('btnDelete').observe('click', function() {
	alert('asda');
});

function addUnitsToTable() {
	tablerowCounter = tablerowCounter + 1;
	unitCounter = unitCounter + 1;

	// create row
	var row = tblComputerUnits.insertRow(tablerowCounter);
	var rwUnitNo = row.insertCell(0);
	var rwUnitName = row.insertCell(1);
	var rwTagNo = row.insertCell(2);
	var rwIp = row.insertCell(3);
	var rwType = row.insertCell(4);
	var rwBrandModel = row.insertCell(5);
	var rwSerialNo = row.insertCell(6);
	var rwAcquiredDate = row.insertCell(7);

	// set attributes
	row.setAttribute('class', 'tblUnitsRow');
	row.setAttribute('onclick', 'selectedRow(this)');

	// table values
	rwUnitNo.innerHTML = unitCounter;
	rwUnitName.innerHTML = $F('txtUnitName');
	rwTagNo.innerHTML = $F('txtTagNumber');
	rwIp.innerHTML = $F('txtIpAddress');
	rwType.innerHTML = $F('txtType');
	rwBrandModel.innerHTML = $F('txtBrand') + ' ' + $F('txtModel');
	rwSerialNo.innerHTML = $F('txtSerialNo');
	rwAcquiredDate.innerHTML = $F('txtAcquiredDate');
}

function addUnitsToDatabase() {
	new Ajax.Request(
			"${pageContext.request.contextPath}/ComputerUnitsInventoryController",
			{
				method : "post",
				parameters : {
					action : "insertNewComputerUnits",
					unitNo : unitCounter,
					unitName : $F('txtUnitName'),
					tagNumber : $F('txtTagNumber'),
					ipAddress : $F('txtIpAddress'),
					type : $F('txtType'),
					acquiredDate : $F('txtAcquiredDate'),
					description : $F('txtDescription'),
					serialNo : $F('txtSerialNo'),
					brand : $F('txtBrand'),
					model : $F('txtModel'),
					color : $F('txtColor'),
					userId : $F('txtUserId'),
					lastUpdate : $F('txtLastUpdate'),
					remarks : $F('txtRemarks')
				}
			})
}

function selectedRow(row) {
	row.toggleClassName('selected');
}
