package com.uni.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.biblioteca.model.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{
    List<Prestamo> findByUsuarioId(int usuarioId);
    boolean exiexistsByLibroIdAndFechaFinAfter(int libroId, java.time.LocalDate fecha);
}
