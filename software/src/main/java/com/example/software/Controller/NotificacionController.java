package com.example.software.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.software.Model.Notificacion;
import com.example.software.Service.NotificacionService;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService NotificacionService;

    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        return NotificacionService.getNotificacions();
    }

    @PostMapping
    public Notificacion agregarNotificacion(@RequestBody Notificacion notificacion) {
        return NotificacionService.saveNotificacion(notificacion);
    }

    @GetMapping("{id}")
    public Notificacion buscarNotificacion(@PathVariable int id){
        return NotificacionService.getNotificacionId(id);
    }

    @PutMapping("{id}")
    public Notificacion actualizarNotificacion(@PathVariable int id, @RequestBody Notificacion notificacion){
        // el id lo usaremos mas adelante
        return NotificacionService.updateNotificacion(notificacion);
    }

    @DeleteMapping("{id}")
    public String eliminarNotificacion(@PathVariable int id) {
        return NotificacionService.deleteNotificacion(id);
    }
}
