package com.uni.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uni.biblioteca.model.Prestamo;
import com.uni.biblioteca.service.PrestamoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/agendar")
    public ResponseEntity<Prestamo> agendar(@RequestParam int libroId, @RequestParam int usuarioId) {
        return ResponseEntity.ok(prestamoService.agendarPrestamo(libroId, usuarioId));
    }

    @PutMapping("/renovar/{id}")
    public ResponseEntity<Prestamo> renovar(@PathVariable int id) {
        return ResponseEntity.ok(prestamoService.renovPrestamo(id));
    }

    @GetMapping("/historial/{usuarioId}")
    public List<Prestamo> historial(@PathVariable int usuarioId) {
        return prestamoService.historialUsuario(usuarioId);
    }
    
}
