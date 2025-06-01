package com.example.software.Controller;

import com.example.software.Model.Prestamo;
import com.example.software.Service.PrestamoService;
import com.example.software.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/prestamos")
@CrossOrigin
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoService.getPrestamos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPrestamo(@PathVariable Long id) {
        return prestamoService.getPrestamoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prestamo agregarPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.savePrestamo(prestamo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        if (!prestamoService.getPrestamoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prestamo.setId(id);
        Prestamo actualizado = prestamoService.updatePrestamo(prestamo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        if (!prestamoService.getPrestamoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prestamoService.deletePrestamo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public long totalPrestamos() {
        return prestamoService.totalPrestamos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> listarPrestamosPorUsuario(@PathVariable Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId)
                .map(usuario -> {
                    List<Prestamo> prestamos = prestamoService.getPrestamosByUsuario(usuario);
                    return ResponseEntity.ok(prestamos);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
