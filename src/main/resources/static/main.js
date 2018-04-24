let cancelButton = document.getElementById("cancel");
let openDeleteModal = document.getElementById("openDeleteModal");
let deleteModal = document.getElementById("deleteModal");
let modalClose = document.getElementsByClassName("close")[0];

if(cancelButton != null) {
    cancelButton.onclick = (e) => {
        e.preventDefault();
        window.history.back();
    };
}
if(openDeleteModal != null) {
    openDeleteModal.onclick = () => {
        deleteModal.style.display = "block";
    };
}
if(modalClose != null) {
    modalClose.onclick = function () {
        deleteModal.style.display = "none";
    };
}
window.onclick = function(event) {
    if (event.target === deleteModal) {
        deleteModal.style.display = "none";
    }
};