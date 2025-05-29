package com.uni.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.biblioteca.model.Libro;
import com.uni.biblioteca.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepo;

    public List<Libro> obteneLibrosDisponibles(){
        return libroRepo.findByDisponibleTrue();
    }

    public Optional<Libro> obtenerLibroPorId(int id){
        return libroRepo.findById(id);
    }

    public Libro guardar(Libro libro){
        return libroRepo.save(libro);
    }
}
