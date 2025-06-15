const API_URL = "http://localhost:8080/api/v2/reportes";



function listarReportes() {
    fetch(API_URL)
        .then(response => response.json())
        .then(reportes => {
            const tbody = document.querySelector("#tablaReportes tbody");
            tbody.innerHTML = "";
            reportes.forEach(reporte => {
                const fila = `
                    <tr>
                        <td>${reporte.id}</td>
                        <td>${reporte.usuario ? reporte.usuario.nombre : ''}</td>
                        <td>${reporte.titulo}</td>
                        <td>${reporte.descripcion}</td>
                        <td> 
                            <button class="btn btn-warning btn-sm" onclick="buscarReporte(${reporte.id})">✏️ Editar</button> 
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}

function agregarReportes() {
    const usuarioId = document.getElementById("usuarioId").value; // cambiar input para capturar id
    const titulo = document.getElementById("titulo").value;
    const descripcion = document.getElementById("descripcion").value;

    const nuevoReporte = {
        usuario: { id: parseInt(usuarioId) }, 
        titulo,
        descripcion
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoReporte)
    })
    .then(response => response.json())
    .then(data => {
        alert("Reporte agregado exitosamente");
        listarReportes();
        limpiarFormulario();
    });
}

function buscarReporte(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(reporte => {
            document.getElementById("usuarioId").value = reporte.usuario ? reporte.usuario.id : "";
            document.getElementById("titulo").value = reporte.titulo;
            document.getElementById("descripcion").value = reporte.descripcion;

            ReporteEnEdicionId = reporte.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar reporte";
                boton.onclick = function() {
                    actualizarReporte(reporte.id);
                };
            }
        });
}

function actualizarReporte(id) {
    const usuarioId = document.getElementById("usuarioId").value;
    const titulo = document.getElementById("titulo").value;
    const descripcion = document.getElementById("descripcion").value;

    const reporteActualizado = {
        id: id,
        usuario: { id: parseInt(usuarioId) },
        titulo,
        descripcion
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(reporteActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Reporte actualizado exitosamente");
        listarReportes();
        limpiarFormulario();
    });
}

function limpiarFormulario() {
    document.getElementById("usuarioId").value = "";
    document.getElementById("titulo").value = "";
    document.getElementById("descripcion").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Reporte";
    boton.setAttribute("onclick", "agregarReportes()");

    ReporteEnEdicionId = null;
}

function cargarUsuarios() {
    fetch('http://localhost:8080/api/v2/usuarios') // Cambia por tu URL real
        .then(res => res.json())
        .then(usuarios => {
            const select = document.getElementById('usuarioId');
            select.innerHTML = '<option value="">Seleccione un usuario</option>'; // opción por defecto

            usuarios.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.id;
                option.textContent = usuario.nombre;
                select.appendChild(option);
            });
        })
        .catch(err => console.error('Error cargando usuarios:', err));
}

// Llama la función al cargar el script o la página
document.addEventListener('DOMContentLoaded', cargarUsuarios);

document.addEventListener('DOMContentLoaded', function() {
    listarReportes();
});

