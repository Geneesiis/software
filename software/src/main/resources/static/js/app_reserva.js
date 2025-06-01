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
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarReserva(${reserva.id})">🗑️</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarReserva(${reserva.id})">✏️</button>
                        </td>
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

function buscarReserva(id) {
    fetch(`${API_URL_RESERVAS}/${id}`)
        .then(res => {
            if (!res.ok) throw new Error("Reserva no encontrada");
            return res.json();
        })
        .then(r => {
            document.getElementById("usuarioId").value = r.usuario?.id || "";
            document.getElementById("libroId").value = r.libro?.id || "";
            document.getElementById("fechaReserva").value = r.fechaReserva || "";

            const boton = document.getElementById("botonFormularioReserva");
            boton.textContent = "Actualizar Reserva";
            boton.onclick = () => actualizarReserva(id);
        })
        .catch(err => alert("Error al buscar reserva: " + err.message));
}

function actualizarReserva(id) {
    const usuarioId = document.getElementById("usuarioId").value;
    const libroId = document.getElementById("libroId").value;
    const fechaReserva = document.getElementById("fechaReserva").value;

    if (!usuarioId || !libroId || !fechaReserva) {
        alert("Por favor, complete todos los campos.");
        return;
    }

    const actualizado = {
        id,
        usuario: { id: parseInt(usuarioId) },
        libro: { id: parseInt(libroId) },
        fechaReserva
    };

    fetch(`${API_URL_RESERVAS}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(actualizado)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al actualizar reserva");
        return res.json();
    })
    .then(() => {
        alert("Reserva actualizada");
        listarReservas();
        limpiarFormularioReserva();
    })
    .catch(err => alert("Error al actualizar reserva: " + err.message));
}

function eliminarReserva(id) {
    if (confirm("¿Eliminar esta reserva?")) {
        fetch(`${API_URL_RESERVAS}/${id}`, {
            method: "DELETE"
        })
        .then(res => {
            if (!res.ok) throw new Error("Error al eliminar reserva");
            alert("Reserva eliminada");
            listarReservas();
        })
        .catch(err => alert("Error al eliminar reserva: " + err.message));
    }
}

function limpiarFormularioReserva() {
    document.getElementById("usuarioId").value = "";
    document.getElementById("libroId").value = "";
    document.getElementById("fechaReserva").value = "";

    const boton = document.getElementById("botonFormularioReserva");
    boton.textContent = "Agregar Reserva";
    boton.onclick = agregarReserva;
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
});

function exportarReservasCSV() {
    fetch(API_URL_RESERVAS)
        .then(res => res.json())
        .then(data => {
            let csv = "ID,Usuario,Libro,Fecha de Reserva\n";
            data.forEach(r => {
                const fila = [
                    r.id,
                    r.usuario?.nombre || '',
                    r.libro?.titulo || '',
                    r.fechaReserva
                ].join(",");
                csv += fila + "\n";
            });

            const blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
            const url = URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = url;
            a.download = "reservas.csv";
            a.click();
            URL.revokeObjectURL(url);
        })
        .catch(err => alert("Error al exportar CSV: " + err.message));
}

