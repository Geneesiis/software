package com.example.software.Repository;

import com.example.software.Model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
    
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    Optional<Trabajador>findByEmail(String email);
    
}
