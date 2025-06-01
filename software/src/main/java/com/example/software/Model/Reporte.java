package com.example.software.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cambiamos el campo usuarioReporta String por una referencia a Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id") // esta columna será la FK en la tabla reporte
    private Usuario usuario;

    private String titulo;
    private String descripcion;
}
