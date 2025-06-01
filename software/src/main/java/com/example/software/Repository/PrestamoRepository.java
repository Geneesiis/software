package com.example.software.Repository;

import com.example.software.Model.Prestamo;
import com.example.software.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuario(Usuario usuario);
    List<Prestamo> findByUsuarioId(Long usuarioId);
}
