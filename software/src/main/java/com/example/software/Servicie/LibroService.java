package com.example.software.Servicie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.software.Model.Libro;
import com.example.software.Repository.libroRepository;

@Service
public class LibroService {
    @Autowired
    private libroRepository libroRepository;

    public List<Libro> getLibros() {
        return libroRepository.obtenerLibros();
    }

    public Libro saveLibro(Libro libro) {
        return libroRepository.guardar(libro);
    }

    public Libro getLibroId(int id) {
        return libroRepository.buscarPorId(id);
    }

    public Libro updateLibro(Libro libro) {
        return libroRepository.actualizar(libro);
    }

    public String deleteLibro(int id) {
        libroRepository.eliminar(id);
        return "libro eliminado";
    }

    // LA ACCIÓN LA HACE EL SERVICE
    public int totalLibros() {
        return libroRepository.obtenerLibros().size();
    }

    // LA ACCIÓN LA HACE EL MODELO
    public int totalLibrosV2() {
        return libroRepository.totalLibros();
    }
}
