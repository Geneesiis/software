API_URL="http://localhost:8080/api/v2/reportes";


function listarReportes() {
    fetch(API_URL)
        .then(response => response.json())
        .then(reportes => {
            const tbody = document.querySelector("#tablaReportes tbody"); //se edito el querySelector 
            tbody.innerHTML = "";
            reportes.forEach(reporte => {
                const fila = `
                    <tr>
                        <td>${reporte.id}</td>
                        <td>${reporte.usuarioReporta}</td>
                        <td>${reporte.titulo}</td>
                        <td>${reporte.descripcion}</td>

                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarReporte(${reporte.id})">🗑️ Eliminar</button> 
                            <button class="btn btn-warning btn-sm" onclick="buscarReporte(${reporte.id})">✏️ Editar</button> 
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}
let Reportes = []; // Variable para almacenar la lista de cursos
// Función para agregar un curso
function agregarReportes() {
    const usuarioReporta = document.getElementById("usuarioReporta").value;
    const titulo = document.getElementById("titulo").value;
    const descripcion = document.getElementById("descripcion").value;
    
    const nuevoReporte = {
        usuarioReporta,
        titulo,
        descripcion
    };
    // Enviar el nuevo curso al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoReporte)
    })// Enviar el nuevo curso al servidor
    .then(response => response.json())
    .then(data => {
        alert("Reporte agregado exitosamente");
        listarReportes();// Actualizar la tabla de cursos
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un curso
function eliminarReporte(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Reporte eliminado exitosamente");
                listarReportes();
            }
        });
}
// Función para buscar un curso por su ID y cargarlo en el formulario
// Se utiliza la API Fetch para obtener los datos del curso desde el servidor
let ReporteEnEdicionId = null; // Variable para almacenar el ID del curso en edición
function buscarReporte(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(reporte => {
            document.getElementById("usuarioReporta").value = reporte.usuarioReporta;
            document.getElementById("titulo").value = reporte.titulo;
            document.getElementById("descripcion").value = reporte.descripcion;

             // Guardar el ID del curso en edición
             ReporteEnEdicionId = reporte.id;
             
            // Cambiar el botón de agregar por actualizar
            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar reporte";
                boton.onclick = function() {
                    actualizarReporte(reporte.id);
                };
            }
        });
}
// Función para actualizar un curso
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarReporte(id) {
    const usuarioReporta = document.getElementById("usuarioReporta").value;
    const titulo = document.getElementById("titulo").value;
    const descripcion = document.getElementById("descripcion").value;

    const reporteActualizado = {
        id: id,
        usuarioReporta: usuarioReporta,
        titulo: titulo,
        descripcion: descripcion

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
// Función para limpiar el formulario después de agregar o actualizar un curso
// Se utiliza para restaurar el formulario a su estado inicial
function limpiarFormulario() {
    document.getElementById("usuarioReporta").value = "";
    document.getElementById("titulo").value = "";
    document.getElementById("descripcion").value = "";


    // Restaurar botón
    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Reporte";
    boton.setAttribute("onclick", "agregarReportes()");

    // Resetear la variable global
    ReporteEnEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar cursos al abrir la página

listarReportes();