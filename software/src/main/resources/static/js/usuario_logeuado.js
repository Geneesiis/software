document.addEventListener("DOMContentLoaded", function () {
  const id = sessionStorage.getItem("idUsuario");
  const nombre = sessionStorage.getItem("nombreUsuario");

  // Rellenar input oculto con el ID
  document.getElementById("usuarioId").value = id;

  // Mostrar nombre del usuario logueado en el texto informativo
  document.getElementById("nombreUsuarioTexto").textContent = nombre;
});