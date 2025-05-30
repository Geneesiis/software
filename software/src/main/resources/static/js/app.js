// Este archivo contiene el código JavaScript para la gestión de libros en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros.
const API_URL = "http://localhost:8080/api/v1/libros"; // URL de la API para acceder a los libros
// Función para listar los libros en la tabla
// Se utiliza la API Fetch para obtener los datos de los libros desde la DB
function listarLibros() {
    fetch(API_URL)
        .then(response => response.json())
        .then(libros => {
            const tbody = document.querySelector("#tablaLibros tbody"); //se edito el querySelector 
            tbody.innerHTML = "";
            libros.forEach(libro => {
                const fila = `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.ramo}</td>
                        <td>${libro.profesor}</td>
                        <td>${libro.horario}</td>
                        <td>${libro.descripcion}</td>
                        <td>${libro.categoria}</td>
                        <td>${libro.duracion}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarLibro(${libro.id})">🗑️ Eliminar</button> 
                            <button class="btn btn-warning btn-sm" onclick="buscarLibro(${libro.id})">✏️ Editar</button> 
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}
let Libros = []; // Variable para almacenar la lista de libros
// Función para agregar un libro
function agregarLibros() {
    const ramo = document.getElementById("ramo").value;
    const profesor = document.getElementById("profesor").value;
    const horario = document.getElementById("horario").value;
    const descripcion = document.getElementById("descripcion").value;
    const categoria = document.getElementById("categoria").value;
    const duracion = document.getElementById("duracion").value;
    
    const nuevoLibro = {
        ramo,
        profesor,
        horario,
        descripcion,
        categoria,
        duracion
    };
    // Enviar el nuevo libro al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoLibro)
    })// Enviar el nuevo libro al servidor
    .then(response => response.json())
    .then(data => {
        alert("Libro agregado exitosamente");
        listarLibros();// Actualizar la tabla de libros
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un libro
function eliminarLibro(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Libro eliminado exitosamente");
                listarLibros();
            }
        });
}
// Función para buscar un libro por su ID y cargarlo en el formulario
// Se utiliza la API Fetch para obtener los datos del libro desde el servidor
let LibroEnEdicionId = null; // Variable para almacenar el ID del libro en edición
function buscarLibro(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(libro => {
            document.getElementById("ramo").value = libro.ramo;
            document.getElementById("profesor").value = libro.profesor;
            document.getElementById("horario").value = libro.horario;
            document.getElementById("descripcion").value = libro.descripcion;
            document.getElementById("categoria").value = libro.categoria;
            document.getElementById("duracion").value = libro.duracion;

             // Guardar el ID del libro en edición
             LibroEnEdicionId = libro.id;
             
            // Cambiar el botón de agregar por actualizar
            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Libro";
                boton.onclick = function() {
                    actualizarLibro(libro.id);
                };
            }
        });
}
// Función para actualizar un libro
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarLibro(id) {
    const ramo = document.getElementById("ramo").value;
    const profesor = document.getElementById("profesor").value;
    const horario = document.getElementById("horario").value;
    const descripcion = document.getElementById("descripcion").value;
    const categoria = document.getElementById("categoria").value;
    const duracion = document.getElementById("duracion").value;

    const libroActualizado = {
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
        body: JSON.stringify(libroActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Libro actualizado exitosamente");
        listarLibros();
        limpiarFormulario();
    });
}
// Función para limpiar el formulario después de agregar o actualizar un libro
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
    boton.innerText = "Agregar Libro";
    boton.setAttribute("onclick", "agregarLibros()");

    // Resetear la variable global
    LibroEnEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar libros al abrir la página

listarLibros();
