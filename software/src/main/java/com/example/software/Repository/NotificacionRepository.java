package com.example.software.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.software.Model.Notificacion;

@Repository
public class NotificacionRepository {
    private List<Notificacion> listaNotificacions = new ArrayList<>();

    public NotificacionRepository() {
        listaNotificacions.add(new Notificacion(1,"Alan Brito", "Aprende a utilizar MYSQL y sus herramientas"));
        listaNotificacions.add(new Notificacion(2,"Juan Pérez", "Fundamentos de redes y su configuración"));
        listaNotificacions.add(new Notificacion(3,"María García", "Protección contra ciberataques y criptografía"));
        listaNotificacions.add(new Notificacion(4,"Carlos López", "Introducción a Python para principiantes"));
        listaNotificacions.add(new Notificacion(5,"Ana Sánchez", "Creación de sitios web con HTML, CSS y JavaScript"));
        listaNotificacions.add(new Notificacion(6,"Luis Rodríguez", "Algoritmos básicos de IA y aprendizaje automático"));
        listaNotificacions.add(new Notificacion(7,"Elena Martínez", "Manejo y análisis de grandes volúmenes de datos"));
        listaNotificacions.add(new Notificacion(8,"Ricardo Torres", "Creación de apps para Android y iOS"));
        listaNotificacions.add(new Notificacion(9,"Sofía Hernández", "Optimización de consultas y administración avanzada de MySQL"));
        listaNotificacions.add(new Notificacion(10,"Gabriela López", "Estrategias de marketing digital en plataformas sociales"));
    }

    public List<Notificacion> obtenerNotificacions() {
        return listaNotificacions;
    }

    public Notificacion buscarPorId(int id) {
        for (Notificacion notificacion : listaNotificacions) {
            if (notificacion.getId() == id) {
                return notificacion;
            }
        }
        return null;
    }

    public Notificacion buscarPorDestinatario(String destinatario) {
        for (Notificacion notificacion : listaNotificacions) {
            if (notificacion.getDestinatario().equals(destinatario)) {
                return notificacion;
            }
        }
        return null;
    }

    public Notificacion guardar(Notificacion notifica) {
        long nuevoId = 1;
        for (Notificacion l : listaNotificacions) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        Notificacion notificacion = new Notificacion();
        notificacion.setId((int) nuevoId);
        notificacion.setDestinatario(notificacion.getDestinatario());
        notificacion.setMensaje(notificacion.getMensaje());

        listaNotificacions.add(notificacion);

        return notificacion;
    }

    public Notificacion actualizar(Notificacion notificacion) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaNotificacions.size(); i++) {
            if (listaNotificacions.get(i).getId() == notificacion.getId()) {
                id = notificacion.getId();
                idPosicion = i;
            }
        }

        Notificacion notificacion1 = new Notificacion();
        notificacion1.setId(id);
        notificacion1.setDestinatario(notificacion.getDestinatario());
        notificacion1.setMensaje(notificacion.getMensaje());

        listaNotificacions.set(idPosicion, notificacion1);
        return notificacion1;
    }

    public void eliminar(int id) {
        listaNotificacions.removeIf(x -> x.getId() == id);
    }

    public int totalNotificacions() {
        return listaNotificacions.size();
    }
}
