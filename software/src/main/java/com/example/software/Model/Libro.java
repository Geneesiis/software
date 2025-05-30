package com.example.software.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Genera getters, setters, toString, equals, hashCode y un constructor con los campos requeridos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private String titulo;
    private String autor;
    private String editorial; // la duracion es un int el cual su tipo de valor es minuto, 60 de duracion es equivalente a una hora
    private String descripcion;
    private String categoria;
    private String paginas; // reemplazando "duracion" por algo más apropiado para libros
}
