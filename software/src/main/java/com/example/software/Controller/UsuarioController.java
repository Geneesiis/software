package com.example.software.Controller;

import com.example.software.Model.Usuario;
import com.example.software.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService servi;

    // Registrar usuario
    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario u) {
        return servi.registrar(u);
    }

    // Login de usuario
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario u) {
        Optional<Usuario> user = servi.autenticar(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
        } else {
            response.put("result", "ERROR");
        }
        return response;
    }

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return servi.listarUsuarios();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        return servi.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (!servi.getUsuarioById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuario actualizado = servi.actualizar(usuario);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!servi.getUsuarioById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        servi.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Total de usuarios
    @GetMapping("/total")
    public long totalUsuarios() {
        return servi.totalUsuarios();
    }

    // Buscar usuario por email
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
        return servi.getPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
