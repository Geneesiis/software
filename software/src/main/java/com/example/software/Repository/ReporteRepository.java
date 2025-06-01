package com.example.software.Repository;

import com.example.software.Model.Reporte;
import com.example.software.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByUsuario(Usuario usuario);

    // Este método busca por la FK usuario.id
    List<Reporte> findByUsuarioId(Long usuarioId);
}

