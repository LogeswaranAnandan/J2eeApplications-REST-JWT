document.getElementsByTagName('body')[0].onload = function() {bootstrapping()};

function bootstrapping() {
	document.getElementById('user-name-container').addEventListener('click', toggleClass);
	document.getElementsByTagName('body')[0].addEventListener('click', removeClass);
	document.getElementById('menu-icon').addEventListener('click', displayMenu);
}

function toggleClass() {
	document.getElementById('logout-container').classList.toggle('display-none');
	document.getElementById('logout-container').classList.toggle('display-block');
}

function removeClass() {
	if((event.srcElement.id != 'user-name-container') && (event.srcElement.className != 'user-name')
		&& (event.srcElement.id != 'menu-icon') && (event.srcElement.className != 'fa fa-bars')) {
		document.getElementById('logout-container').classList.remove('display-block');
		document.getElementById('logout-container').classList.remove('display-none');
		document.getElementById('logout-container').classList.add('display-none');
	}
}

function displayMenu(event) {
	document.getElementsByTagName('nav')[0].classList.toggle('display-menu');
	document.getElementById('logout-container').classList.toggle('display-none');
}