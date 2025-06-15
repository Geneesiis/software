document.addEventListener("DOMContentLoaded", function () {
  const id = sessionStorage.getItem("idUsuario");
  const nombre = sessionStorage.getItem("nombreUsuario");

  // Rellenar input oculto con el ID del usuario
  const usuarioIdInput = document.getElementById("usuarioId");
  if (usuarioIdInput) {
    usuarioIdInput.value = id || "";
  }

  // Opcional: Mostrar el nombre del usuario en algún lugar, si quieres
  const nombreUsuarioTexto = document.getElementById("nombreUsuarioTexto");
  if (nombreUsuarioTexto) {
    nombreUsuarioTexto.textContent = nombre || "";
  }
});
