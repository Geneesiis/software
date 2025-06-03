API_URL = "http://localhost:8080/api/v2/usuarios/login";

function login() {
    fetch(API_URL, {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify( {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })
    .then(response => response.json())
    .then(data=> {
        if (data.result === "OK" ) {
            sessionStorage.setItem("nombreUsuario", data.nombre);
            window.location.href = "/E_index.html";
        } else {
            alert ("Acceso denegado");
        }
    });
}