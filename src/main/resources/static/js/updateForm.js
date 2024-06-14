function redirectToUpdate(event) {
	event.preventDefault();

	var updateId = document.getElementById("updateId").value.trim();

	if (!updateId) {
		 alert("Please enter a valid User ID.");
		 return;
	}

	var updateUrl = "/update/" + encodeURIComponent(updateId);

	window.location.href = updateUrl;
}