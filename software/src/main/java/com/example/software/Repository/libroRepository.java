package com.example.software.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.software.Model.Libro;

@Repository
public class LibroRepository {
    private List<Libro> listaLibros = new ArrayList<>();

    public LibroRepository() {
        listaLibros.add(new Libro(1, "Base de datos", "Alan Brito", "Pearson", "Aprende a utilizar MYSQL y sus herramientas", "Informatica y Telecomunicaciones", "300"));
        listaLibros.add(new Libro(2, "Redes de Computadoras", "Juan Pérez", "McGraw-Hill", "Fundamentos de redes y su configuración", "Informatica y Telecomunicaciones", "280"));
        listaLibros.add(new Libro(3, "Seguridad Informática", "María García", "Alfaomega", "Protección contra ciberataques y criptografía", "Informatica y Telecomunicaciones", "250"));
        listaLibros.add(new Libro(4, "Programación en Python", "Carlos López", "Anaya", "Introducción a Python para principiantes", "Informatica y Telecomunicaciones", "320"));
        listaLibros.add(new Libro(5, "Desarrollo Web", "Ana Sánchez", "Santillana", "Creación de sitios web con HTML, CSS y JavaScript", "Informatica y Telecomunicaciones", "290"));
        listaLibros.add(new Libro(6, "Inteligencia Artificial", "Luis Rodríguez", "Ra-Ma", "Algoritmos básicos de IA y aprendizaje automático", "Informatica y Telecomunicaciones", "310"));
        listaLibros.add(new Libro(7, "Big Data", "Elena Martínez", "Cengage", "Manejo y análisis de grandes volúmenes de datos", "Informatica y Telecomunicaciones", "330"));
        listaLibros.add(new Libro(8, "Desarrollo de Aplicaciones Móviles", "Ricardo Torres", "Pearson", "Creación de apps para Android y iOS", "Informatica y Telecomunicaciones", "305"));
        listaLibros.add(new Libro(9, "Bases de Datos Avanzadas", "Sofía Hernández", "McGraw-Hill", "Optimización de consultas y administración avanzada de MySQL", "Informatica y Telecomunicaciones", "340"));
        listaLibros.add(new Libro(10, "Redes Sociales", "Gabriela López", "Santillana", "Estrategias de marketing digital en plataformas sociales", "Informatica y Telecomunicaciones", "260"));
    }

    public List<Libro> obtenerLibros() {
        return listaLibros;
    }

    public Libro buscarPorId(int id) {
        for (Libro libro : listaLibros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    public Libro buscarPorTitulo(String titulo) {
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public Libro guardar(Libro lib) {
        long nuevoId = 1;
        for (Libro l : listaLibros) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        Libro libro = new Libro();
        libro.setId((int) nuevoId);
        libro.setTitulo(lib.getTitulo());
        libro.setAutor(lib.getAutor());
        libro.setEditorial(lib.getEditorial());
        libro.setDescripcion(lib.getDescripcion());
        libro.setCategoria(lib.getCategoria());
        libro.setPaginas(lib.getPaginas());

        listaLibros.add(libro);

        return libro;
    }

    public Libro actualizar(Libro lib) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getId() == lib.getId()) {
                id = lib.getId();
                idPosicion = i;
            }
        }

        Libro libro1 = new Libro();
        libro1.setId(id);
        libro1.setTitulo(lib.getTitulo());
        libro1.setAutor(lib.getAutor());
        libro1.setEditorial(lib.getEditorial());
        libro1.setDescripcion(lib.getDescripcion());
        libro1.setCategoria(lib.getCategoria());
        libro1.setPaginas(lib.getPaginas());

        listaLibros.set(idPosicion, libro1);
        return libro1;
    }

    public void eliminar(int id) {
        listaLibros.removeIf(x -> x.getId() == id);
    }

    public int totalLibros() {
        return listaLibros.size();
    }
}
