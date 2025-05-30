package com.example.EduTech.Controller;

import com.example.EduTech.Model.Curso;
import com.example.EduTech.Service.CursoService;

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
    private final List<Curso> carrito = new ArrayList<>();
    @Autowired
    private CursoService Cursoserv;

    @PostMapping("/agregar/{id}")
    public String agregarCurso(@PathVariable int id) {
        Curso curso = Cursoserv.getCursoId(id);
        if (curso!= null){
            carrito.add(curso);
            return "El Curso se agrego al carrito: " + curso.getRamo();
        }    
        return "El Curso no fue encontrado";
    }
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable int id){
        boolean eliminado = carrito.removeIf(curso -> curso.getId() == id);
        return eliminado ? "El Curso ha sido eliminado" : "El Curso no fue encontrado";
    }
    @GetMapping
    public List<Curso> verCarrito() {
        return carrito;
    }
    @DeleteMapping("/vaciar")
    public String vaciarCarritoString(){
        carrito.clear();
        return "El carrito esta vacio";
    }
    @GetMapping("/total")
    public int totalCursoCarrito() {
        return carrito.size();
    }
}
