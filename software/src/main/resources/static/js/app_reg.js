function registrar() {
    fetch("http://localhost:8080/api/v2/usuarios/registrar", {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            nombre: document.getElementById("nombre").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })
    .then(res => res.json())  // <-- aquí estaba el error: antes era `res.json` sin paréntesis
    .then(data => {
        alert("Usuario se creo con en Id: " + data.id);
        window.location.href = "/E_login.html";
    });
}

const API_USUARIOS = "http://localhost:8080/api/v2/usuarios";

// REGISTRAR alumno (por parte del administrador)
function registrarAlumno() {
    fetch(`${API_USUARIOS}/registrar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nombre: document.getElementById("nombre").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    })
    .then(res => res.json())
    .then(data => {
        alert("Alumno creado con ID: " + data.id);
        listarAlumnos();
        limpiarFormularioAlumno();
    })
    .catch(error => {
        console.error("Error al registrar alumno:", error);
        alert("Error al registrar alumno");
    });
}

// LISTAR alumnos
function listarAlumnos() {
    fetch(API_USUARIOS)
        .then(res => res.json())
        .then(alumnos => {
            const tbody = document.querySelector("#tablaAlumnos tbody");
            if (!tbody) return;
            tbody.innerHTML = "";
            alumnos.forEach(a => {
                const fila = `
                    <tr>
                        <td>${a.id}</td>
                        <td>${a.nombre}</td>
                        <td>${a.email}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarAlumno(${a.id})">🗑️ Eliminar</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarAlumno(${a.id})">✏️ Editar</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}

// ELIMINAR alumno
function eliminarAlumno(id) {
    fetch(`${API_USUARIOS}/${id}`, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            alert("Alumno eliminado correctamente");
            listarAlumnos();
        } else {
            alert("No se pudo eliminar el alumno");
        }
    });
}

// Buscar alumno para edición
let alumnoEnEdicionId = null;

function buscarAlumno(id) {
    fetch(`${API_USUARIOS}/${id}`)
        .then(res => res.json())
        .then(alumno => {
            document.getElementById("nombre").value = alumno.nombre;
            document.getElementById("email").value = alumno.email;
            document.getElementById("password").value = "";

            alumnoEnEdicionId = alumno.id;

            const boton = document.getElementById("botonAlumno");
            if (boton) {
                boton.textContent = "Actualizar Alumno";
                boton.onclick = function () {
                    actualizarAlumno(alumno.id);
                };
            }
        });
}

// Actualizar alumno
function actualizarAlumno(id) {
    const alumnoActualizado = {
        id: id,
        nombre: document.getElementById("nombre").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    fetch(`${API_USUARIOS}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(alumnoActualizado)
    })
    .then(res => res.json())
    .then(data => {
        alert("Alumno actualizado correctamente");
        listarAlumnos();
        limpiarFormularioAlumno();
    });
}

// LIMPIAR formulario
function limpiarFormularioAlumno() {
    document.getElementById("nombre").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";

    const boton = document.getElementById("botonAlumno");
    boton.textContent = "Registrar Alumno";
    boton.setAttribute("onclick", "registrarAlumno()");

    alumnoEnEdicionId = null;
}

// Al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    limpiarFormularioAlumno();
    listarAlumnos();
});
