package com.example.EduTech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Genera getters, setters, toString, equals, hashCode y un constructor con los campos requeridos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor

public class Curso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private int id;
    private String ramo;
    private String profesor;
    private String horario; //la duracion es un int el cual su tipo de valor es minuto, 60 de duracion es equivalente a una hora
    private String descripcion;
    private String categoria;
    private String duracion;

}
