const API_URL = "http://localhost:8080/api/v1/libros";

// Función para listar los libros en la tabla
function listarLibros() {
    fetch(API_URL)
        .then(response => response.json())
        .then(libros => {
            const tbody = document.querySelector("#tablaLibros tbody");
            tbody.innerHTML = "";
            libros.forEach(libro => {
                const fila = `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>${libro.editorial}</td>
                        <td>${libro.descripcion}</td>
                        <td>${libro.categoria}</td>
                        <td>${libro.paginas}</td>
                        <td>${libro.stock}</td>
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

// Función para agregar un libro
function agregarLibros() {
    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const editorial = document.getElementById("editorial").value;
    const descripcion = document.getElementById("descripcion").value;
    const categoria = document.getElementById("categoria").value;
    const paginas = document.getElementById("paginas").value;
    const stock = document.getElementById("stock").value;

    const nuevoLibro = {
        titulo,
        autor,
        editorial,
        descripcion,
        categoria,
        paginas,
        stock
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoLibro)
    })
    .then(response => response.json())
    .then(data => {
        alert("Libro agregado exitosamente");
        listarLibros();
        limpiarFormulario();
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
let LibroEnEdicionId = null;
function buscarLibro(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(libro => {
            document.getElementById("titulo").value = libro.titulo;
            document.getElementById("autor").value = libro.autor;
            document.getElementById("editorial").value = libro.editorial;
            document.getElementById("descripcion").value = libro.descripcion;
            document.getElementById("categoria").value = libro.categoria;
            document.getElementById("paginas").value = libro.paginas;
            document.getElementById("stock").value = libro.stock;

            LibroEnEdicionId = libro.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Libro";
                boton.onclick = function () {
                    actualizarLibro(libro.id);
                };
            }
        });
}

// Función para actualizar un libro
function actualizarLibro(id) {
    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const editorial = document.getElementById("editorial").value;
    const descripcion = document.getElementById("descripcion").value;
    const categoria = document.getElementById("categoria").value;
    const paginas = document.getElementById("paginas").value;
    const stock = document.getElementById("stock").value;

    const libroActualizado = {
        id,
        titulo,
        autor,
        editorial,
        descripcion,
        categoria,
        paginas,
        stock
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

// Función para limpiar el formulario
function limpiarFormulario() {
    document.getElementById("titulo").value = "";
    document.getElementById("autor").value = "";
    document.getElementById("editorial").value = "";
    document.getElementById("descripcion").value = "";
    document.getElementById("categoria").value = "";
    document.getElementById("paginas").value = "";
    document.getElementById("stock").value = "";

    const boton = document.getElementById("botonFormulario");
    if (boton) {
        boton.innerText = "Agregar Libro";
        boton.setAttribute("onclick", "agregarLibros()");
    }

    LibroEnEdicionId = null;
}

// Al cargar la página, asegurarse que el botón está en modo agregar
window.onload = function () {
    limpiarFormulario();
};


// Cargar libros al iniciar
listarLibros();
