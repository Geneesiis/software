package com.uni.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.biblioteca.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{
    List<Libro> findByDisponibleTrue();
}
