package com.example.software.Service;

import com.example.software.Model.Usuario;
import com.example.software.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository rep;

    // Registrar usuario
    public Usuario registrar(Usuario u) {
        return rep.save(u);
    }

    // Autenticar usuario (login)
    public Optional<Usuario> autenticar(String email, String password) {
        return rep.findByEmail(email)
                  .filter(u -> u.getPassword().equals(password));
    }

    // Obtener usuario por ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return rep.findById(id);
    }

    // Obtener todos los usuarios
    public List<Usuario> listarUsuarios() {
        return rep.findAll();
    }

    // Actualizar usuario
    public Usuario actualizar(Usuario usuario) {
        if (rep.existsById(usuario.getId())) {
            return rep.save(usuario);
        }
        return null; // o lanzar excepción si prefieres
    }

    // Eliminar usuario por ID
    public String eliminar(Long id) {
        if (rep.existsById(id)) {
            rep.deleteById(id);
            return "Usuario eliminado";
        } else {
            return "Usuario no encontrado";
        }
    }

    // Total de usuarios
    public long totalUsuarios() {
        return rep.count();
    }

    // Buscar por email
    public Optional<Usuario> getPorEmail(String email) {
        return rep.findByEmail(email);
    }
}
