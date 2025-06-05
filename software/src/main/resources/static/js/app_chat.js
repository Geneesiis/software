function toggleChat() {
    const chat = document.getElementById("chatContenedor");
    chat.style.display = chat.style.display === "none" ? "block" : "none";
}

function enviarMensaje() {
    const input = document.getElementById("chatInput");
    const mensaje = input.value.trim();
    if (mensaje === "") return;

    agregarMensaje("Tú", mensaje);
    input.value = "";

    setTimeout(() => {
        const respuesta = obtenerRespuestaAutomatica(mensaje);
        agregarMensaje("Soporte", respuesta);
    }, 800);
}

function agregarMensaje(remitente, texto) {
    const contenedor = document.getElementById("chatMessages");
    const mensajeElemento = document.createElement("div");
    mensajeElemento.innerHTML = `<strong>${remitente}:</strong> ${texto}`;
    mensajeElemento.style.marginBottom = "8px";
    contenedor.appendChild(mensajeElemento);
    contenedor.scrollTop = contenedor.scrollHeight;
}

function obtenerRespuestaAutomatica(mensaje) {
    const msg = mensaje.toLowerCase();
    if (msg.includes("hola")) return "¡Hola! ¿En qué puedo ayudarte?";
    if (msg.includes("libro")) return "Puedes consultar libros disponibles en la sección de biblioteca.";
    if (msg.includes("reserva")) return "Para hacer una reserva, ve al módulo de reservas.";
    if (msg.includes("gracias")) return "¡De nada! Estoy para ayudarte.";
    return "Lo siento, no entendí eso. ¿Puedes reformularlo?";
}

document.getElementById("chatInput").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault(); // Evita que se haga un salto de línea
        enviarMensaje();
    }
});
