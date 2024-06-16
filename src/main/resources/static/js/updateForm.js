function redirectToUpdate(event) {
	event.preventDefault();

	var updateId = document.getElementById("updateId").value.trim();

	if (!updateId) {
		 alert("Please enter a valid User ID.");
		 return;
	}

	var updateUrl = "/update/" + encodeURIComponent(updateId);

	// Create a temporary form to submit
	var form = document.createElement("form");
	form.method = "post";
	form.action = updateUrl;

	var nameInput = document.createElement("input");
	nameInput.type = "hidden";
	nameInput.name = "name";
	nameInput.value = document.getElementById("name").value;
	form.appendChild(nameInput);

	var passwordInput = document.createElement("input");
	passwordInput.type = "hidden";
	passwordInput.name = "password";
	passwordInput.value = document.getElementById("password").value;
	form.appendChild(passwordInput);

	document.body.appendChild(form);
	form.submit();
}

// function redirectToUpdate(event) {
// 	event.preventDefault();

// 	var updateId = document.getElementById("updateId").value.trim();

// 	if (!updateId) {
// 		 alert("Please enter a valid User ID.");
// 		 return;
// 	}

// 	var form = event.target;
// 	var updateUrl = "/update/" + encodeURIComponent(updateId);

// 	form.setAttribute("th:action", "@{" + updateUrl + "}");
// 	form.submit();
// }