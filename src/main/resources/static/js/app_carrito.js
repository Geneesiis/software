const carrito = (() => {
    const API = "/api/v1/carrito";

    async function listarCarrito() {
        try {
            const response = await fetch(API);
            const curso = await response.json();

            const tbody =  document.querySelector("#tablaCarrito tbody");
            const totalItems = document.getElementById("totalCarrito");
            tbody.innerHTML = "";
            totalItems.textContent = curso.length

            curso.forEach(curso => {
                const fila = `
                    <tr>
                        <td>${curso.id}</td>
                        <td>${curso.ramo}</td>
                        <td>${curso.profesor}</td>
                        <td>
                            <button class="btn btn-sm btn-danger" onclick ="carrito.eliminarCurso(${curso.id}")>Eliminar</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        } catch(err) {
            console.error("Error al cargar el carrito", err);
        }
    }

    async function agregarCurso(id) {
        try{
            await fetch(`${API}/agregar/${id}`, {method: "POST"});
            alert("El curso a sido agregar");
            listarCarrito();
        } catch(err){
            console.error("Error al agregar el curso al carrito", err);
        }
    }
    async function eliminarCurso(id) {
        try{
            await fetch(`${API}/eliminar/${id}`, {method: "POST"});
        } catch(err){
            console.error("Error al agregar el curso al carrito", err);
        }
    }

    //Método para vaciar el carrito
    async function vaciarCarrito() {
        if (confirm("¿Estas seguro de vaciar el carrito?")){
            await fetch(`${API}/vaciar`, {method: "DELETE"});
            alert("Carrito vaciado");
            listarCarrito();
        }
    }

    //Función para confirmar la compra
    async function confirmarCompra() {
        const total = document.getElementById("totalCarrito").textContent;
        if (parseInt(total) === 0){
            alert("El carrito está vacio");
            return;
        }
        if (confirm(`"¿Desea confirmar la compra por ${total}"`)){
            await fetch(`${API}/vaciar`, {method: "DELETE"});
            alert("Compra realizada exitosamente");
            listarCarrito();
        }
    }
    return { listarCarrito, agregarCurso, eliminarCurso, vaciarCarrito, confirmarCompra};
}) ();