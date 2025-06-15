const API_URL = "http://localhost:8080/api/v2/usuarios/login";

function login() {
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.result === "OK") {
            // ✅ Guardar el nombre y el ID del usuario en sessionStorage
            sessionStorage.setItem("nombreUsuario", data.nombre);
            sessionStorage.setItem("idUsuario", data.id); // ← asegúrate de que tu backend incluya esto

            // Redirigir al home
            window.location.href = "/E_index.html";
        } else {
            alert("Acceso denegado");
        }
    })
    .catch(error => {
        console.error("Error en el login:", error);
        alert("Ocurrió un error al intentar iniciar sesión.");
    });
}
