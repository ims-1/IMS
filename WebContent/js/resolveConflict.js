document.observe("dom:loaded", function() {
	require
			.config({
				baseUrl : '/',
				paths : {
			        'jquery': "/IMS/js/jquery-3.1.1.min",
			        'bootstrap': "/IMS/js/bootstrap.min"
				},
				shim : {
					'bootstrap' : [ 'jquery' ]
				},
				map : {
					'*' : {
						'jquery' : 'jQueryNoConflict'
					},
					'jQueryNoConflict' : {
						'jquery' : 'jquery'
					}
				}
			});
	define('jQueryNoConflict', [ 'jquery' ], function($) {
		return $.noConflict();
	});
	if (Prototype.BrowserFeatures.ElementExtensions) {
		require([ 'jquery', 'bootstrap' ], function($) {
			// Fix incompatibilities between BootStrap and Prototype
			var disablePrototypeJS = function(method, pluginsToDisable) {
				var handler = function(event) {
					event.target[method] = undefined;
					setTimeout(function() {
						delete event.target[method];
					}, 0);
				};
				pluginsToDisable.each(function(plugin) {
					$(window).on(method + '.bs.' + plugin, handler);
				});
			}, pluginsToDisable = [ 'collapse', 'dropdown', 'modal', 'tooltip',
					'popover', 'tab' ];
			disablePrototypeJS('show', pluginsToDisable);
			disablePrototypeJS('hide', pluginsToDisable);
		});
	}
	require([ 'jquery', 'bootstrap' ], function($) {
		$(document).ready(function() {
			$('.bs-example-tooltips').children().each(function() {
				$(this).tooltip();
			});
			$('.bs-example-popovers').children().each(function() {
				$(this).popover();
			});
		});
	});
});