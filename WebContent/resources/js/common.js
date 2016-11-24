$(function() {

	$(window).scroll(function() {
		/************************
		 * Paralax image effect *
		 ************************/
	

		/* Calculate percentComplete, which goes from 0 to 1 */
		var distanceScrolled = Math.max(0, $(window).scrollTop());


		/* Use percentComplete to determine how much we translate */

		/**********************
		 * Pinning the navbar *
		 **********************/

		var $navbar = $('nav');
		var $navbarWrapper = $('.navbar-wrapper')

		/* navbarWrapper never moves, so it's a good pinPoint */
		var pinPoint = $navbarWrapper.offset().top;

		/* add or remove the 'pinned' class depending on what side of the pin
		 * point we are.
		 */
		if (distanceScrolled >= pinPoint) {
			$navbar.addClass('pinned');
		} else {
			$navbar.removeClass('pinned')
		}
	});

	/*************
	 * Scroll to *
	 *************/

	$('a[href*="#"]').click(function(e) {
		e.preventDefault();
		var $target = $($(this).attr('href'));
		var scrollTop = $target.offset().top;
		$('html, body').animate({'scrollTop': scrollTop}, 500);
	});

});