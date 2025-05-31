package com.example.software.Controller;

import com.example.software.Model.Notificacion;
import com.example.software.Service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@CrossOrigin
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.getNotificacions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtener(@PathVariable Long id) {
        return notificacionService.getNotificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Notificacion guardar(@RequestBody Notificacion notificacion) {
        return notificacionService.saveNotificacion(notificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        if (!notificacionService.getNotificacionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notificacion.setId(id); // Asegura que se actualiza la notificación con el ID correcto
        Notificacion actualizado = notificacionService.updateNotificacion(notificacion);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!notificacionService.getNotificacionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notificacionService.deleteNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}
