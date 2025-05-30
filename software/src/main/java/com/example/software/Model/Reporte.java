package com.example.software.Model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor
public class Reporte {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String usuarioReporta;
    private String titulo;
    private String descripcion;
    
    public static Optional<Reporte> map(Object o){
        throw new UnsupportedOperationException("Unimplemented method 'map");
    }

}
