function irARegistroCliente() {
    // Realizar la solicitud GET al controlador
    fetch('/irARegistroCliente', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.text())
        .then(data => {
            // Aquí puedes manejar la respuesta. Si la respuesta es un nuevo HTML, puedes cargarla en el DOM
            // Por ejemplo, puedes hacer un reemplazo de una parte de la página o redirigir al usuario
            window.location.href = '/irARegistroCliente'; // Si solo deseas redirigir como en el método original
        })
        .catch(error => console.error('Error:', error));
}
function irACambiarPasswordCliente(){
    fetch('/irACambiarPasswordCliente', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.text())
        .then(data => {
            window.location.href = '/irACambiarPasswordCliente';
        })
        .catch(error => console.error('Error:', error));
}

document.getElementById("loginForm").addEventListener("submit", function(event) {
    let emailInput = document.getElementById("email");
    let emailError = document.getElementById("emailError");
    let emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!emailPattern.test(emailInput.value)) {
        emailError.style.display = "block";
        event.preventDefault(); // Evita que el formulario se envíe si el correo no es válido
    } else {
        emailError.style.display = "none";
    }
});