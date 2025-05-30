// Este archivo contiene el código JavaScript para la gestión de cursos en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los cursos.
const API_URL = "http://localhost:8080/api/v1/cursos"; // URL de la API para acceder a los cursos
// Función para listar los cursos en la tabla
// Se utiliza la API Fetch para obtener los datos de los cursos desde la DB
function listarCursos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(cursos => {
            const tbody = document.querySelector("#tablaCursos tbody"); //se edito el querySelector 
            tbody.innerHTML = "";
            cursos.forEach(curso => {
                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.ramo}</td>
                        <td>${curso.profesor}</td>
                        <td>${curso.horario}</td>
                        <td>${curso.descripcion}</td>
                        <td>${curso.categoria}</td>
                        <td>${curso.duracion}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarCurso(${curso.id})">🗑️ Eliminar</button> 
                            <button class="btn btn-warning btn-sm" onclick="buscarCurso(${curso.id})">✏️ Editar</button> 
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}
let Cursos = []; // Variable para almacenar la lista de cursos
// Función para agregar un curso
function agregarCursos() {
    const ramo = document.getElementById("ramo").value;
    const profesor = document.getElementById("profesor").value;
    const horario = document.getElementById("horario").value;
    const descripcion = document.getElementById("descripcion").value;
    const categoria = document.getElementById("categoria").value;
    const duracion = document.getElementById("duracion").value;
    
    const nuevoCurso = {
        ramo,
        profesor,
        horario,
        descripcion,
        categoria,
        duracion
    };
    // Enviar el nuevo curso al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoCurso)
    })// Enviar el nuevo curso al servidor
    .then(response => response.json())
    .then(data => {
        alert("Curso agregado exitosamente");
        listarCursos();// Actualizar la tabla de cursos
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un curso
function eliminarCurso(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Curso eliminado exitosamente");
                listarCursos();
            }
        });
}
// Función para buscar un curso por su ID y cargarlo en el formulario
// Se utiliza la API Fetch para obtener los datos del curso desde el servidor
let CursoEnEdicionId = null; // Variable para almacenar el ID del curso en edición
function buscarCurso(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(curso => {
            document.getElementById("ramo").value = curso.ramo;
            document.getElementById("profesor").value = curso.profesor;
            document.getElementById("horario").value = curso.horario;
            document.getElementById("descripcion").value = curso.descripcion;
            document.getElementById("categoria").value = curso.categoria;
            document.getElementById("duracion").value = curso.duracion;

             // Guardar el ID del curso en edición
             CursoEnEdicionId = curso.id;
             
            // Cambiar el botón de agregar por actualizar
            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Curso";
                boton.onclick = function() {
                    actualizarCurso(curso.id);
                };
            }
        });
}
// Función para actualizar un curso
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarCurso(id) {
    const ramo = document.getElementById("ramo").value;
    const profesor = document.getElementById("profesor").value;
    const horario = document.getElementById("horario").value;
    const descripcion = document.getElementById("descripcion").value;
    const categoria = document.getElementById("categoria").value;
    const duracion = document.getElementById("duracion").value;

    const cursoActualizado = {
        id: id,
        ramo: ramo,
        profesor: profesor,
        horario: horario,
        descripcion: descripcion,
        categoria: categoria,
        duracion: duracion
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cursoActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Curso actualizado exitosamente");
        listarCursos();
        limpiarFormulario();
    });
}
// Función para limpiar el formulario después de agregar o actualizar un curso
// Se utiliza para restaurar el formulario a su estado inicial
function limpiarFormulario() {
    document.getElementById("ramo").value = "";
    document.getElementById("profesor").value = "";
    document.getElementById("horario").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("categoria").value = "";
    document.getElementById("duracion").value = "";


    // Restaurar botón
    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Curso";
    boton.setAttribute("onclick", "agregarCursos()");

    // Resetear la variable global
    CursoEnEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar cursos al abrir la página

listarCursos();
