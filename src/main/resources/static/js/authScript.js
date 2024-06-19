document.getElementById('authForm').addEventListener('submit', function(event) {
	event.preventDefault();

	const name = document.getElementById('name').value;
	const password = document.getElementById('password').value;
	const gmail = document.getElementById('gmail').value;


	fetch('/auth/api/v1/authorization', {
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
	window.location.href = '/auth/api/v1/userSuccessfullyPage';
});