/**
 * 
 */
function addPeripherals() {
	if (validate()) {
		alert("pass validation");
		new Ajax.Request(context + "/PeripheralsController", {
			method : "post",
			parameters : {

			},
			onSuccess : function(response) {

			}
		});
	} else {
		alert("Please check fields. Red fields are required while blue fields only accepts integer.");
	}
}
