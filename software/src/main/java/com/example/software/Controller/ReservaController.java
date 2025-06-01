package com.example.software.Controller;

import com.example.software.Model.Reserva;
import com.example.software.Service.ReservaService;
import com.example.software.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/reservas")
@CrossOrigin
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.getReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable Long id) {
        return reservaService.getReservaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reserva agregarReserva(@RequestBody Reserva reserva) {
        return reservaService.saveReserva(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        if (!reservaService.getReservaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reserva.setId(id);
        Reserva actualizado = reservaService.updateReserva(reserva);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        if (!reservaService.getReservaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public long totalReservas() {
        return reservaService.totalReservas();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> listarReservasPorUsuario(@PathVariable Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId)
                .map(usuario -> {
                    List<Reserva> reservas = reservaService.getReservasByUsuario(usuario);
                    return ResponseEntity.ok(reservas);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
