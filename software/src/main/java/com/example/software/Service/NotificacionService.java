package com.example.software.Service;

import com.example.software.Model.Notificacion;
import com.example.software.Repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> getNotificacions() {
        return notificacionRepository.findAll();
    }

    public Notificacion saveNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Optional<Notificacion> getNotificacionById(Long id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion updateNotificacion(Notificacion notificacion) {
        // Asegura que existe antes de actualizar
        if (notificacionRepository.existsById(notificacion.getId())) {
            return notificacionRepository.save(notificacion);
        }
        return null; // o lanza una excepción personalizada
    }

    public String deleteNotificacion(Long id) {
        if (notificacionRepository.existsById(id)) {
            notificacionRepository.deleteById(id);
            return "Notificación eliminada";
        } else {
            return "Notificación no encontrada";
        }
    }

    public long totalNotificacions() {
        return notificacionRepository.count();
    }

    public Optional<Notificacion> getByDestinatario(String destinatario) {
        return notificacionRepository.findByDestinatario(destinatario);
    }
}
