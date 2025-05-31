const API_TRABAJADORES = "http://localhost:8080/api/v2/trabajadores";

// REGISTRAR nuevo trabajador
function registrar() {
    fetch(`${API_TRABAJADORES}/registrar`, {
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
        alert("Usuario se creó con ID: " + data.id);
        listarTrabajadores();
        limpiarFormularioTrabajador();
    })
    .catch(error => {
        console.error("Error al registrar trabajador:", error);
        alert("Error al registrar trabajador");
    });
}

// LISTAR trabajadores
function listarTrabajadores() {
    fetch(API_TRABAJADORES)
        .then(res => res.json())
        .then(trabajadores => {
            const tbody = document.querySelector("#tablaTrabajadores tbody");
            tbody.innerHTML = "";
            trabajadores.forEach(t => {
                const fila = `
                    <tr>
                        <td>${t.id}</td>
                        <td>${t.nombre}</td>
                        <td>${t.email}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarTrabajador(${t.id})">🗑️ Eliminar</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarTrabajador(${t.id})">✏️ Editar</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}

// ELIMINAR trabajador
function eliminarTrabajador(id) {
    fetch(`${API_TRABAJADORES}/${id}`, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            alert("Trabajador eliminado correctamente");
            listarTrabajadores();
        } else {
            alert("No se pudo eliminar el trabajador");
        }
    });
}

// Buscar trabajador para edición
let trabajadorEnEdicionId = null;

function buscarTrabajador(id) {
    fetch(`${API_TRABAJADORES}/${id}`)
        .then(res => res.json())
        .then(trabajador => {
            document.getElementById("nombre").value = trabajador.nombre;
            document.getElementById("email").value = trabajador.email;
            document.getElementById("password").value = trabajador.password;

            trabajadorEnEdicionId = trabajador.id;

            const boton = document.getElementById("botonTrabajador");
            if (boton) {
                boton.textContent = "Actualizar Trabajador";
                boton.onclick = function () {
                    actualizarTrabajador(trabajador.id);
                };
            }
        });
}

// Actualizar trabajador
function actualizarTrabajador(id) {
    const trabajadorActualizado = {
        id: id,
        nombre: document.getElementById("nombre").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    fetch(`${API_TRABAJADORES}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(trabajadorActualizado)
    })
    .then(res => res.json())
    .then(data => {
        alert("Trabajador actualizado correctamente");
        listarTrabajadores();
        limpiarFormularioTrabajador();
    });
}

// LIMPIAR formulario
function limpiarFormularioTrabajador() {
    document.getElementById("nombre").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";

    const boton = document.getElementById("botonTrabajador");
    boton.textContent = "Registrar Trabajador";
    boton.setAttribute("onclick", "registrar()");

    trabajadorEnEdicionId = null;
}

// Llamar lista al cargar
document.addEventListener("DOMContentLoaded", listarTrabajadores);
