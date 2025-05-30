package com.example.EduTech.Repository;

import com.example.EduTech.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsusarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>findByEmail(String email);
    
}
