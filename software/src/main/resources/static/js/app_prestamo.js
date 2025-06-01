const API_URL_PRESTAMOS = "http://localhost:8080/api/v2/prestamos";
const API_URL_USUARIOS = "http://localhost:8080/api/v2/usuarios";
const API_URL_LIBROS = "http://localhost:8080/api/v1/libros";

function listarPrestamos() {
    fetch(API_URL_PRESTAMOS)
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#tablaPrestamos tbody");
            tbody.innerHTML = "";
            data.forEach(prestamo => {
                const fila = `
                    <tr>
                        <td>${prestamo.id}</td>
                        <td>${prestamo.usuario ? prestamo.usuario.nombre : ''}</td>
                        <td>${prestamo.libro ? prestamo.libro.titulo : ''}</td>
                        <td>${prestamo.fechaPrestamo}</td>
                        <td>${prestamo.fechaDevolucion}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarPrestamo(${prestamo.id})">🗑️</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarPrestamo(${prestamo.id})">✏️</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}

function agregarPrestamo() {
    const usuarioId = document.getElementById("usuarioId").value;
    const libroId = document.getElementById("libroId").value;
    const fechaPrestamo = document.getElementById("fechaPrestamo").value;
    const fechaDevolucion = document.getElementById("fechaDevolucion").value;

    const nuevo = {
        usuario: { id: parseInt(usuarioId) },
        libro: { id: parseInt(libroId) },
        fechaPrestamo,
        fechaDevolucion
    };

    fetch(API_URL_PRESTAMOS, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevo)
    })
    .then(res => res.json())
    .then(() => {
        alert("Préstamo agregado exitosamente");
        listarPrestamos();
        limpiarFormularioPrestamo();
    });
}

function buscarPrestamo(id) {
    fetch(`${API_URL_PRESTAMOS}/${id}`)
        .then(res => res.json())
        .then(p => {
            document.getElementById("usuarioId").value = p.usuario?.id;
            document.getElementById("libroId").value = p.libro?.id;
            document.getElementById("fechaPrestamo").value = p.fechaPrestamo;
            document.getElementById("fechaDevolucion").value = p.fechaDevolucion;

            const boton = document.getElementById("botonFormularioPrestamo");
            boton.textContent = "Actualizar Préstamo";
            boton.onclick = () => actualizarPrestamo(id);
        });
}

function actualizarPrestamo(id) {
    const usuarioId = document.getElementById("usuarioId").value;
    const libroId = document.getElementById("libroId").value;
    const fechaPrestamo = document.getElementById("fechaPrestamo").value;
    const fechaDevolucion = document.getElementById("fechaDevolucion").value;

    const actualizado = {
        id,
        usuario: { id: parseInt(usuarioId) },
        libro: { id: parseInt(libroId) },
        fechaPrestamo,
        fechaDevolucion
    };

    fetch(`${API_URL_PRESTAMOS}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(actualizado)
    })
    .then(res => res.json())
    .then(() => {
        alert("Préstamo actualizado");
        listarPrestamos();
        limpiarFormularioPrestamo();
    });
}

function eliminarPrestamo(id) {
    if (confirm("¿Estás seguro de eliminar este préstamo?")) {
        fetch(`${API_URL_PRESTAMOS}/${id}`, {
            method: "DELETE"
        }).then(() => {
            alert("Préstamo eliminado");
            listarPrestamos();
        });
    }
}

function limpiarFormularioPrestamo() {
    document.getElementById("usuarioId").value = "";
    document.getElementById("libroId").value = "";
    document.getElementById("fechaPrestamo").value = "";
    document.getElementById("fechaDevolucion").value = "";

    const boton = document.getElementById("botonFormularioPrestamo");
    boton.textContent = "Agregar Préstamo";
    boton.onclick = agregarPrestamo;
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
    listarPrestamos();
    cargarUsuarios();
    cargarLibros();
});
