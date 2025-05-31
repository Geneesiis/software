package com.example.software.Controller;

import com.example.software.Model.Trabajador;
import com.example.software.Service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v2/trabajadores")
@CrossOrigin
public class TrabajadorController {

    @Autowired
    private TrabajadorService servi;

    // Registrar trabajador
    @PostMapping("/registrar")
    public Trabajador registrar(@RequestBody Trabajador trabajador) {
        return servi.registrar(trabajador);
    }

    // Login de trabajador
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Trabajador trabajador) {
        Optional<Trabajador> user = servi.autenticar(trabajador.getEmail(), trabajador.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
        } else {
            response.put("result", "ERROR");
        }
        return response;
    }

    // Obtener todos los trabajadores
    @GetMapping
    public List<Trabajador> listarTrabajadores() {
        return servi.getTodos();
    }

    // Obtener trabajador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> buscarTrabajador(@PathVariable Long id) {
        return servi.getPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar trabajador
    @PutMapping("/{id}")
    public ResponseEntity<Trabajador> actualizarTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador) {
        if (!servi.getPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        trabajador.setId(id); // Asegura que el ID sea el correcto
        Trabajador actualizado = servi.actualizar(trabajador);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar trabajador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Long id) {
        if (!servi.getPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        servi.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Total de trabajadores
    @GetMapping("/total")
    public long totalTrabajadores() {
        return servi.totalTrabajadores();
    }

    // Buscar trabajador por email
    @GetMapping("/buscar")
    public ResponseEntity<Trabajador> buscarPorEmail(@RequestParam String email) {
        return servi.getPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
