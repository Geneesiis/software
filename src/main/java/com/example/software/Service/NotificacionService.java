package com.example.EduTech.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EduTech.Model.Notificacion;
import com.example.EduTech.Repository.NotificacionRepository;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository NotificacionRepository;

    public List<Notificacion> getNotificacions() {
        return NotificacionRepository.obtenerNotificacions();
    }

    public Notificacion saveNotificacion(Notificacion notificacion) {
        return NotificacionRepository.guardar(notificacion);
    }

    public Notificacion getNotificacionId(int id) {
        return NotificacionRepository.buscarPorId(id);
    }

    public Notificacion updateNotificacion(Notificacion notificacion) {
        return NotificacionRepository.actualizar(notificacion);
    }

    public String deleteNotificacion(int id) {
        NotificacionRepository.eliminar(id);
        return "producto eliminado";
    }

    // LA ACCIÓN LA HACE EL SERVICE
    public int totalNotificacions() {
        return NotificacionRepository.obtenerNotificacions().size();
    }

    // LA ACCIÓN LA HACE EL MODELO
    public int totalNotificacionsV2() {
        return NotificacionRepository.totalNotificacions();
    }
}
