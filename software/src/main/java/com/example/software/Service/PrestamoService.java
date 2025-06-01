package com.example.software.Service;

import com.example.software.Model.Prestamo;
import com.example.software.Model.Usuario;
import com.example.software.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Prestamo> getPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo savePrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Optional<Prestamo> getPrestamoById(Long id) {
        return prestamoRepository.findById(id);
    }

    public Prestamo updatePrestamo(Prestamo prestamo) {
        if (prestamoRepository.existsById(prestamo.getId())) {
            return prestamoRepository.save(prestamo);
        }
        return null;
    }

    public String deletePrestamo(Long id) {
        if (prestamoRepository.existsById(id)) {
            prestamoRepository.deleteById(id);
            return "Préstamo eliminado";
        } else {
            return "Préstamo no encontrado";
        }
    }

    public long totalPrestamos() {
        return prestamoRepository.count();
    }

    public List<Prestamo> getPrestamosByUsuario(Usuario usuario) {
        return prestamoRepository.findByUsuario(usuario);
    }

    public List<Prestamo> getPrestamosByUsuarioId(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }
}
