package com.uni.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.biblioteca.model.Libro;
import com.uni.biblioteca.model.Prestamo;
import com.uni.biblioteca.model.Usuario;
import com.uni.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepo;

    @Autowired
    private LibroService libroService;

    @Autowired
    private UsuarioService usuarioService;

    public Prestamo agendarPrestamo(int libroId, int usuarioId){
        Libro libro = libroService.obtenerLibroPorId(libroId).orElseThrow();
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId).orElseThrow();

        if (!libro.isDisponible()){
            throw new RuntimeException("Libro no disponible");
        }

        libro.setDisponible(false);
        libroService.guardar(libro);

        Prestamo p = new Prestamo();
        p.setLibro(libro);
        p.setUsuario(usuario);
        p.setFechaInicio(LocalDate.now());
        p.setFechaFin(LocalDate.now().plusDays(7));

        return prestamoRepo.save(p);
    }

    public Prestamo renovPrestamo(int PrestamoId){
        Prestamo p = prestamoRepo.findById(PrestamoId).orElseThrow();
        Libro libro =p.getLibro();

        boolean hayReserva = prestamoRepo.exiexistsByLibroIdAndFechaFinAfter(libro.getId(), LocalDate.now());
        if (hayReserva || libro.getRenovacionesRestantes() <= 0) {
            throw new RuntimeException("No se puede renovar");
        }

        p.setFechaFin(p.getFechaFin().plusDays(7));
        p.setRenovado(true);
        libro.setRenovacionesRestantes(libro.getRenovacionesRestantes()-1);
        libroService.guardar(libro);

        return prestamoRepo.save(p);
    }

    public List<Prestamo> historialUsuario(int usuarioId){
        return prestamoRepo.findByUsuarioId(usuarioId);
    }
}
