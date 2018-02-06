document.getElementsByTagName('body')[0].onload = function() {bootstrapping()};

function bootstrapping() {
	document.querySelector('#table').addEventListener('click', rentBike);
	console.log('inside bootstrapping');
}

function rentBike(event) {
	console.log('clicked');
	
	event.preventDefault();
}