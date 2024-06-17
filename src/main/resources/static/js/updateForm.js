document.getElementById('myForm').addEventListener('submit', function(event) {
	event.preventDefault(); // Предотвращение отправки формы по умолчанию

	// Получение данных из формы
	const formData = new FormData(event.target);

	// Преобразование данных формы в объект
	const data = {};
	formData.forEach((value, key) => {
		 data[key] = value;
	});

	// Отправка POST-запроса с использованием fetch
	fetch('http://localhost:8080/update', {
		 method: 'POST',
		 headers: {
			  'Content-Type': 'application/json' // Тип содержимого JSON
		 },
		 body: JSON.stringify(data) // Преобразование данных в JSON-строку
	})
	.then(response => {
		 if (!response.ok) {
			  throw new Error('Network response was not ok ' + response.statusText);
		 }
		 return response.json(); // Преобразование ответа в JSON
	})
	.then(data => {
		 console.log('Success:', data); // Обработка данных ответа
	})
	.catch(error => {
		 console.error('Error:', error); // Обработка ошибки
	});
});