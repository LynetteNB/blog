let cancelButton = document.getElementById("cancel");

cancelButton.addEventListener("click", (e) => {
    e.preventDefault();
    window.history.back();
});