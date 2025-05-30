package com.example.software.Controller;

import com.example.software.Model.Libro;
import com.example.software.Service.LibroService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carrito")
@CrossOrigin
public class CarritoController {

    private final List<Libro> carrito = new ArrayList<>();

    @Autowired
    private LibroService libroService;

    @PostMapping("/agregar/{id}")
    public String agregarLibro(@PathVariable Long id) {
        Optional<Libro> libroOpt = libroService.buscarPorId(id);
        if (libroOpt.isPresent()) {
            carrito.add(libroOpt.get());
            return "El libro se agregó al carrito: " + libroOpt.get().getTitulo();
        }    
        return "El libro no fue encontrado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id){
        boolean eliminado = carrito.removeIf(libro -> libro.getId().equals(id));
        return eliminado ? "El libro ha sido eliminado" : "El libro no fue encontrado";
    }

    @GetMapping
    public List<Libro> verCarrito() {
        return carrito;
    }

    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        carrito.clear();
        return "El carrito está vacío";
    }

    @GetMapping("/total")
    public int totalLibroCarrito() {
        return carrito.size();
    }
}
