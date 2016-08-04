var config = {
	headers: 'h3, h4, h5, h6',
	minimumHeaders: 2,
	classes: { list: 'lorem ipsum',
		item: 'dolor sit amet'
	}
}
$(document).ready(function() {
	$('#toc').toc(config);
});
