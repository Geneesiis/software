package com.example.software.Model;


import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String nombre;
    private String email;
    private String password;


    public static Optional<Usuario> map(Object o){
        throw new UnsupportedOperationException("Unimplemented method 'map");
    }
    
}
