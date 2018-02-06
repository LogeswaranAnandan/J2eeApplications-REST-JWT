document.getElementsByName('body')[0].onload = function() {bootstrapping()};

function bootstrapping() {
	document.getElementsByName('quantity')[0].addEventListener('click', displayAddForm);
	document.getElementsByName('display-add-form')[0].addEventListener('click', displayQuantityForm);
}

function displayQunatityForm(event) {
	var textNode = document.createTextNode('Enter the number of bikes : ');
	var input = document.createElement('input');
	var container = document.getElementById('container');
	input.type = 'number';
	input.name = 'quantity';
	container.appendChild(textNode);
	container.appendChild(input);
}

function displayAddForm(event) {
	var container = document.getElementById('container');
	var textNode = document.createTextNode('Enter the Registration Number : ');
	var input = document.createElement('input');
	var submitButton = document.createElement('input');
	input.type = 'text';
	input.name = 'registration-number';
	submitButton.type = 'submit';
	submitButton.name = 'admin-functionality';
	submitButton.value = 'Add Bike';
	container.appendChild(textNode);
	container.appendChild(input);
	container.appendChild(submitButton);
	document.getElementsByName('display-add-form')[0].removeEventListener('click', displayAddForm);
	
	
	/*
	var textNode = document.createTextNode('Enter the number of bikes : ');
	var input = document.createElement('input');
	input.type = "number";
	input.name = "quantity";
	console.log('input = ' + input);
	console.log('container = ' + container);
	container.appendChild(textNode);
	container.appendChild(input);
	*/
}