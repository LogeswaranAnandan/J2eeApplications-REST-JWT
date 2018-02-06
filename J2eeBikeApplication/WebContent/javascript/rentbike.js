document.getElementsByTagName('body')[0].onload = function() {bootstrapping()};

function bootstrapping() {
	document.getElementById('table').addEventListener('click', setHiddenFieldValue);
}

function setHiddenFieldValue(event) {
	var target = event.srcElement;
	var pattern = /\d+/;
	if(target.value == 'Rent this Bike') {
		var id = target.dataset.id;
		var duration = prompt('Enter the Duration of Rent(in hours)');
		if(duration != '' && duration != null && duration != 0 && duration.match(pattern)) {
			var advanceAmount = prompt('Enter the Advance amount to be paid');
			if(advanceAmount != '' && advanceAmount != null && advanceAmount >= 500 && advanceAmount.match(pattern)) {
				document.getElementById('rent-duration-' + id).value = duration;
				document.getElementById('advance-paid-' + id).value = advanceAmount;
			} else {
				alert('Please enter a valid advance amount');
				event.preventDefault();
			}
		} else {
			alert('Please enter a valid duration');
			event.preventDefault();
		}
	}
}