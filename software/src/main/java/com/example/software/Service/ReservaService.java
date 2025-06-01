package com.example.software.Service;

import com.example.software.Model.Reserva;
import com.example.software.Model.Usuario;
import com.example.software.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }

    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva updateReserva(Reserva reserva) {
        if (reservaRepository.existsById(reserva.getId())) {
            return reservaRepository.save(reserva);
        }
        return null;
    }

    public String deleteReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return "Reserva eliminada";
        } else {
            return "Reserva no encontrada";
        }
    }

    public long totalReservas() {
        return reservaRepository.count();
    }

    public List<Reserva> getReservasByUsuario(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario);
    }

    public List<Reserva> getReservasByUsuarioId(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }
}
