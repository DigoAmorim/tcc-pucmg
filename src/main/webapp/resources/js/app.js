$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content, .bd-margin-left40').toggleClass('is-toggled');
		event.preventDefault();
		
	});
});

function getIdSelected() {
	var vars = {};
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
			function(m, key, value) {
				vars[key] = value;
			});
	document.getElementById(vars["tela"]).classList.add("is-selected");
}

getIdSelected();
