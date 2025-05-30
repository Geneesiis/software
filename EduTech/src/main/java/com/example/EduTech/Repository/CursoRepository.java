package com.example.EduTech.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.EduTech.Model.Curso;

@Repository
public class CursoRepository {
    private List<Curso> listaCursos = new ArrayList<>();

    public CursoRepository() {
        listaCursos.add(new Curso(1,"Base de datos", "Alan Brito", "14:30", "Aprende a utilizar MYSQL y sus herramientas", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(2,"Redes de Computadoras", "Juan Pérez", "10:00", "Fundamentos de redes y su configuración", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(3,"Seguridad Informática", "María García", "11:30", "Protección contra ciberataques y criptografía", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(4,"Programación en Python", "Carlos López", "16:00", "Introducción a Python para principiantes", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(5,"Desarrollo Web", "Ana Sánchez", "13:00", "Creación de sitios web con HTML, CSS y JavaScript", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(6,"Inteligencia Artificial", "Luis Rodríguez", "09:00", "Algoritmos básicos de IA y aprendizaje automático", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(7,"Big Data", "Elena Martínez", "15:30", "Manejo y análisis de grandes volúmenes de datos", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(8,"Desarrollo de Aplicaciones Móviles", "Ricardo Torres", "14:00", "Creación de apps para Android y iOS", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(9,"Bases de Datos Avanzadas", "Sofía Hernández", "17:00", "Optimización de consultas y administración avanzada de MySQL", "Informatica y Telecomunicaciones", "2:00:00"));
        listaCursos.add(new Curso(10,"Redes Sociales", "Gabriela López", "12:30", "Estrategias de marketing digital en plataformas sociales", "Informatica y Telecomunicaciones", "2:00:00"));
        
    }

    public List<Curso> obtenerCursos() {
        return listaCursos;
    }

    public Curso buscarPorId(int id) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    public Curso buscarPorRamo(String ramo) {
        for (Curso curso : listaCursos) {
            if (curso.getRamo().equals(ramo)) {
                return curso;
            }
        }
        return null;
    }

    public Curso guardar(Curso cur) {
        // Generar nuevo ID secuencial
        long nuevoId = 1;
        for (Curso l : listaCursos) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        Curso curso = new Curso();
    curso.setId((int) nuevoId); // ID generado automáticamente editar aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    curso.setRamo(cur.getRamo());
    curso.setProfesor(cur.getProfesor());
    curso.setHorario(cur.getHorario());
    curso.setDescripcion(cur.getDescripcion());
    curso.setCategoria(cur.getCategoria());

    // Agregar el nuevo Curso a la lista
    listaCursos.add(curso);

    return curso;
}

public Curso actualizar(Curso cur) {
    int id = 0;
    int idPosicion = 0;

    for (int i = 0; i < listaCursos.size(); i++) {
        if (listaCursos.get(i).getId() == cur.getId()) {
            id = cur.getId();
            idPosicion = i;
        }
    }

    Curso curso1 = new Curso();
    curso1.setId(id);
    curso1.setRamo(cur.getRamo());
    curso1.setProfesor(cur.getProfesor());
    curso1.setHorario(cur.getHorario());
    curso1.setDescripcion(cur.getDescripcion());
    curso1.setCategoria(cur.getCategoria());

    listaCursos.set(idPosicion, curso1);
    return curso1;
}

public void eliminar(int id) {
    listaCursos.removeIf(x -> x.getId() == id);
}

public int totalCursos() {
    return listaCursos.size();
}

}
