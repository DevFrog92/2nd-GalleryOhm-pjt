// Acc
$(document).on("click", ".about .menu div", function() {
	var numberIndex = $(this).index();

	if (!$(this).is("active")) {
		$(".about .menu div").removeClass("active");
		$(".about ul li").removeClass("active");

		$(this).addClass("active");
		$(".about ul").find("li:eq(" + numberIndex + ")").addClass("active");

		var listItemHeight = $(".about ul")
			.find("li:eq(" + numberIndex + ")")
			.innerHeight();
		$(".about ul").height(listItemHeight + "px");
	}
});

