const API_URL_RESERVAS = "http://localhost:8080/api/v2/reservas";
const API_URL_USUARIOS = "http://localhost:8080/api/v2/usuarios";
const API_URL_LIBROS = "http://localhost:8080/api/v1/libros";

function listarReservas() {
    fetch(API_URL_RESERVAS)
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#tablaReservas tbody");
            tbody.innerHTML = "";
            data.forEach(reserva => {
                const fila = `
                    <tr>
                        <td>${reserva.id}</td>
                        <td>${reserva.usuario ? reserva.usuario.nombre : ''}</td>
                        <td>${reserva.libro ? reserva.libro.titulo : ''}</td>
                        <td>${reserva.fechaReserva}</td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        })
        .catch(err => console.error('Error al listar reservas:', err));
}

function agregarReserva() {
    const usuarioId = document.getElementById("usuarioId").value;
    const libroId = document.getElementById("libroId").value;
    const fechaReserva = document.getElementById("fechaReserva").value;

    if (!usuarioId || !libroId || !fechaReserva) {
        alert("Por favor, complete todos los campos.");
        return;
    }

    const nuevo = {
        usuario: { id: parseInt(usuarioId) },
        libro: { id: parseInt(libroId) },
        fechaReserva
    };

    fetch(API_URL_RESERVAS, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevo)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al agregar reserva");
        return res.json();
    })
    .then(() => {
        alert("Reserva agregada exitosamente");
        listarReservas();
        limpiarFormularioReserva();
    })
    .catch(err => alert("Error al agregar reserva: " + err.message));
}

function limpiarFormularioReserva() {
    document.getElementById("usuarioId").value = "";
    document.getElementById("libroId").value = "";
    document.getElementById("fechaReserva").value = "";
}

function cargarUsuarios() {
    fetch(API_URL_USUARIOS)
        .then(res => res.json())
        .then(usuarios => {
            const select = document.getElementById('usuarioId');
            select.innerHTML = '<option value="">Seleccione un usuario</option>';
            usuarios.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.id;
                option.textContent = usuario.nombre;
                select.appendChild(option);
            });
        })
        .catch(err => console.error('Error cargando usuarios:', err));
}

function cargarLibros() {
    fetch(API_URL_LIBROS)
        .then(res => res.json())
        .then(libros => {
            const select = document.getElementById('libroId');
            select.innerHTML = '<option value="">Seleccione un libro</option>';
            libros.forEach(libro => {
                const option = document.createElement('option');
                option.value = libro.id;
                option.textContent = libro.titulo;
                select.appendChild(option);
            });
        })
        .catch(err => console.error('Error cargando libros:', err));
}

document.addEventListener("DOMContentLoaded", () => {
    listarReservas();
    cargarUsuarios();
    cargarLibros();
    limpiarFormularioReserva();

    document.getElementById("botonFormularioReserva").onclick = agregarReserva;
});
