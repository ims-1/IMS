/**
 * 
 */
function validate() { // use on your own js file
	var hasError = false;

	$$('.form-control').each(function(field) {
		$(field).removeClassName("error-required");
		$(field).removeClassName("error-integer");
	});

	$$('.required').each(function(field) {
		if ($F(field).trim() == '') {
			$(field).addClassName("error-required");
			hasError = hasError || true;
		}
	});

	$$('.number').each(function(field) {
		if (isNaN($F(field).trim()) || $F(field).trim() % 1 !== 0) {
			$(field).addClassName("error-integer");
			hasError = hasError || true;
		}
	});

	return !hasError;
}

function recordEvents() { // call on your own js file
	$$('.record').each(function(record) {
		$(record).observe("click", function() {
			// assign class to selected row
			if ($(record).hasClassName("hoverRow")) {
				$(record).toggleClassName("hoverRow");
				clearFields();
				// clear fields
			} else {
				// clear selected row - remove class
				$$('.record').each(function(record) {
					$(record).removeClassName("hoverRow");
				});
				
				$(record).toggleClassName("hoverRow");
				getRecord($(record));// populate fields
			}
		});
	});
}

 // use
