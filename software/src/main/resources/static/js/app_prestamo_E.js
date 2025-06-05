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

    if (!usuarioId || !libroId || !fechaPrestamo || !fechaDevolucion) {
        alert("Por favor, complete todos los campos.");
        return;
    }

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
    .then(res => {
        if (!res.ok) throw new Error("Error al agregar préstamo");
        return res.json();
    })
    .then(() => {
        alert("Préstamo agregado exitosamente");
        listarPrestamos();
        limpiarFormularioPrestamo();
        mostrarGraficoPrestamosPorMes();
    })
    .catch(err => alert("Error al agregar préstamo: " + err.message));
}

// Eliminamos funciones buscarPrestamo y actualizarPrestamo

function eliminarPrestamo(id) {
    if (confirm("¿Estás seguro de eliminar este préstamo?")) {
        fetch(`${API_URL_PRESTAMOS}/${id}`, {
            method: "DELETE"
        })
        .then(res => {
            if (!res.ok) throw new Error("Error al eliminar préstamo");
            alert("Préstamo eliminado");
            listarPrestamos();
        })
        .catch(err => alert("Error al eliminar préstamo: " + err.message));
    }
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
    listarPrestamos();
    cargarUsuarios();
    cargarLibros();
    mostrarGraficoPrestamosPorMes(); // Opcional
});

// Función para exportar CSV igual que antes
function exportarPrestamosCSV() {
    const filas = document.querySelectorAll("#tablaPrestamos tbody tr");
    if (filas.length === 0) {
        alert("No hay datos para exportar.");
        return;
    }

    let csv = "ID,Usuario,Libro,Fecha Préstamo,Fecha Devolución\n";

    filas.forEach(fila => {
        const columnas = fila.querySelectorAll("td");
        const datos = [
            columnas[0]?.textContent.trim(),
            columnas[1]?.textContent.trim(),
            columnas[2]?.textContent.trim(),
            columnas[3]?.textContent.trim(),
            columnas[4]?.textContent.trim()
        ];
        csv += datos.join(",") + "\n";
    });

    const blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
    const url = URL.createObjectURL(blob);
    const enlace = document.createElement("a");
    enlace.href = url;
    enlace.download = "prestamos.csv";
    enlace.click();
}
