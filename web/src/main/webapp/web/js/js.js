$(document).ready(function() {

	$("#customers").click(function() {
		$("p").toggle();
	});

	$("#customers").click(function() {
		$.get("service/customers/", function(data, status) {
			$("#customers-div").html("<b>Hello world!</b><br>");
			for (var i = 0; i < data.length; i++) {
				$("#customers-div").html("<b>" + data + "</b><br>");
			}
		});
	});
});