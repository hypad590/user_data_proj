document.getElementById('authForm').addEventListener('submit', function(event) {
	event.preventDefault();

	const name = document.getElementById('name').value;
	const password = document.getElementById('password').value;

	fetch('http://localhost:8080/auth/api/v1/authorization', {
		 method: 'POST',
		 headers: {
			'Content-Type': 'application/json'
		 },
		 body: JSON.stringify({
			name: name,
			password: password,
			gmail: gmail
		})
	})
	.then(response => response.json())
	.then(data => console.log(data))
	.catch(error => console.error('Error:', error));
});