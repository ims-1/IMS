/**
 * 
 */
function validate() {
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