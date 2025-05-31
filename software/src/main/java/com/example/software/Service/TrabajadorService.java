package com.example.software.Service;

import com.example.software.Model.Trabajador;
import com.example.software.Repository.TrabajadorRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository repo;

    // Registrar (crear)
    public Trabajador registrar(Trabajador trabajador) {
        return repo.save(trabajador);
    }

    // Autenticar (login)
    public Optional<Trabajador> autenticar(String email, String password) {
        return repo.findByEmail(email)
                   .filter(u -> u.getPassword().equals(password));
    }

    // Obtener todos los trabajadores
    public List<Trabajador> getTodos() {
        return repo.findAll();
    }

    // Obtener uno por ID
    public Optional<Trabajador> getPorId(Long id) {
        return repo.findById(id);
    }

    // Actualizar trabajador
    public Trabajador actualizar(Trabajador trabajador) {
        if (repo.existsById(trabajador.getId())) {
            return repo.save(trabajador);
        }
        return null; // o lanza una excepción si prefieres
    }

    // Eliminar por ID
    public String eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Trabajador eliminado";
        } else {
            return "Trabajador no encontrado";
        }
    }

    // Total de trabajadores
    public long totalTrabajadores() {
        return repo.count();
    }

    // Buscar por email
    public Optional<Trabajador> getPorEmail(String email) {
        return repo.findByEmail(email);
    }
}
