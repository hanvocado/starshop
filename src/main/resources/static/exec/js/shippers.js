
document.addEventListener('DOMContentLoaded', () => {
	const searchInput = document.getElementById('searchInput');

	searchInput.addEventListener('keyup', function() {
		const query = this.value.toLowerCase();
		console.log(query);
		const shippers = document.querySelectorAll('.shipper');

		shippers.forEach(shipper => {
			const shipperName = shipper.querySelector('.shipper-name').textContent.toLowerCase();
			console.log(shipperName);
			if (shipperName.includes(query)) {
				shipper.style.display = '';
			} else {
				shipper.style.display = 'none';
			}
		});
	});

	setTimeout(function() {
		document.getElementById('message-alert').style.display = 'none';
	}, 6000);

});