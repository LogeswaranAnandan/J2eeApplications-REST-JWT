document.getElementsByTagName('body')[0].onload = function() {bootstrapping()};

function bootstrapping() {
	document.getElementsByName('submit-button')[0].addEventListener('click', validateFields);
}

function validateFields(event) {
	validatePhoneNumber(event);
	validateLicenseNumber(event);
}

function validateLicenseNumber(event) {
	var licenseNumber = document.getElementById('licensenumber').value;
	console.log(licenseNumber);
	var pattern = /^[A-Z]{2}\d{2}\s{1}\d{11}/;
	if(!licenseNumber.match(pattern)) {
    	document.getElementById('licensenumber').classList.add('wrong-input');
    	event.preventDefault();
	}
}


function validatePhoneNumber(event) {
	var phoneNumber = document.getElementById('phonenumber').value;
	console.log(phoneNumber);
	var pattern = /^(\+?91)?[987]{1}[0-9]{9}$/;
    if(!phoneNumber.match(pattern)) {
    	document.getElementById('phonenumber').classList.add('wrong-input');
    	event.preventDefault();
	}
}