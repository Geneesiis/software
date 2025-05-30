package com.example.software.Repository;

import com.example.software.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsusarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>findByEmail(String email);
    
}
