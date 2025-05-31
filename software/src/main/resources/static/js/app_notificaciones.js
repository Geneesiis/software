// Este archivo contiene el código JavaScript para la gestión de notificaciones en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las notificaciones.
const API_URL = "http://localhost:8080/api/v1/notificacions"; // URL de la API para acceder a las notificaciones

function listarNotificacions() {
    fetch(API_URL)
        .then(response => response.json())
        .then(notificacions => {
            const tbody = document.querySelector("#tablaNotificacions tbody");
            tbody.innerHTML = "";
            notificacions.forEach(notificacion => {
                const fila = `
                    <tr>
                        <td>${notificacion.id}</td>
                        <td>${notificacion.destinatario}</td>
                        <td>${notificacion.mensaje}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarNotificacion(${notificacion.id})">🗑️ Eliminar</button> 
                            <button class="btn btn-warning btn-sm" onclick="buscarNotificacion(${notificacion.id})">✏️ Editar</button> 
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}

let Notificacions = [];

function agregarNotificacions() {
    const destinatario = document.getElementById("destinatario").value;
    const mensaje = document.getElementById("mensaje").value;
    
    const nuevaNotificacion = {
        destinatario,
        mensaje
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevaNotificacion)
    })
    .then(response => response.json())
    .then(data => {
        alert("Notificación agregada exitosamente");
        listarNotificacions();
        limpiarFormulario();
    });
}

function eliminarNotificacion(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Notificación eliminada exitosamente");
                listarNotificacions();
            }
        });
}

let NotificacionEnEdicionId = null;

function buscarNotificacion(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(notificacion => {
            document.getElementById("destinatario").value = notificacion.destinatario;
            document.getElementById("mensaje").value = notificacion.mensaje;

            NotificacionEnEdicionId = notificacion.id;

            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Notificación";
                boton.onclick = function() {
                    actualizarNotificacion(notificacion.id);
                };
            }
        });
}

function actualizarNotificacion(id) {
    const destinatario = document.getElementById("destinatario").value;
    const mensaje = document.getElementById("mensaje").value;

    const notificacionActualizada = {
        id: id,
        destinatario: destinatario,
        mensaje: mensaje
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(notificacionActualizada)
    })
    .then(response => response.json())
    .then(data => {
        alert("Notificación actualizada exitosamente");
        listarNotificacions();
        limpiarFormulario();
    });
}

function limpiarFormulario() {
    document.getElementById("destinatario").value = "";
    document.getElementById("mensaje").value = "";

    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Notificación";
    boton.setAttribute("onclick", "agregarNotificacions()");

    NotificacionEnEdicionId = null;
}

// Función para cargar notificaciones
function cargarNotificaciones() {
  fetch(API_URL_NOTIF)
    .then(res => res.json())
    .then(notificaciones => {
      const lista = document.getElementById("listaNotificaciones");
      const badge = document.getElementById("badgeNotificaciones");

      // Si no hay notificaciones
      if (!notificaciones || notificaciones.length === 0) {
        lista.innerHTML = '<li class="text-center text-muted">🔕 Sin notificaciones por ahora</li>';
        badge.style.display = "none";
        return;
      }

      // Mostrar badge con cantidad
      badge.textContent = notificaciones.length;
      badge.style.display = "inline";

      // Generar lista dinámica
      lista.innerHTML = "";
      notificaciones.forEach(n => {
        const li = document.createElement("li");
        li.classList.add("dropdown-item");
        li.textContent = `${n.mensaje} (Para: ${n.destinatario})`;
        lista.appendChild(li);
      });
    })
    .catch(error => {
      console.error("Error cargando notificaciones:", error);
    });
}

// Llama esta función cuando cargue la página
document.addEventListener("DOMContentLoaded", cargarNotificaciones);

listarNotificacions();
