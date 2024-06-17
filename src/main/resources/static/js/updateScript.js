document.getElementById('updateForm').addEventListener('submit', function(event) {
	event.preventDefault();

	const id = document.getElementById('id').value;
	const name = document.getElementById('name').value;
	const password = document.getElementById('password').value;
	const gmail = document.getElementById('gmail').value;

	fetch('/updateUser/' + id, {
		 method: 'POST',
		 headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: `name=${encodeURIComponent(name)}&password=${encodeURIComponent(password)}&gmail=${encodeURIComponent(gmail)}`
	})
});
//localhost:8080/updateUser/24?name=Logan&password=newPass&gmail=wolverine24@gmail.com