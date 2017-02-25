/**
 * 
 */
function validate() { // use on your own js file
	var hasError = false;

	$$('.form-control').each(function(field) {
		$(field).removeClassName("error-required");
		$(field).removeClassName("error-integer");
		$(field).removeClassName("error-date");
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

	$$('.date')
			.each(
					function(field) {
						if ($F(field).trim() != '') {
							var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
							if (!(date_regex.test($F(field)))) {
								$(field).addClassName("error-date");
								hasError = hasError || true;
							}
						}
					});

	$$('.email').each(function(field) {
		if ($F(field).trim() != '') {
			var email_regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			if (!(email_regex.test($F(field)))) {
				$(field).addClassName("error-email");
				hasError = hasError || true;
			}
		}
	});
	
	$$('.text-only').each(function(field){
		if($F(field).trim() != ''){
			var text_only = "";
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
				clearFields($(record));
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
