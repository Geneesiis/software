package com.example.software.Controller;

import com.example.software.Model.Libro;
import com.example.software.Service.LibroService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {
    private final List<Libro> carrito = new ArrayList<>();

    @Autowired
    private LibroService libroServ;

    @PostMapping("/agregar/{id}")
    public String agregarLibro(@PathVariable int id) {
        Libro libro = libroServ.getLibroId(id);
        if (libro != null){
            carrito.add(libro);
            return "El libro se agregó al carrito: " + libro.getTitulo();
        }    
        return "El libro no fue encontrado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable int id){
        boolean eliminado = carrito.removeIf(libro -> libro.getId() == id);
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
