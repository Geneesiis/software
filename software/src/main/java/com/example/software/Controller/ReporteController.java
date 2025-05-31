package com.example.software.Controller;

import com.example.software.Model.Reporte;
import com.example.software.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/reportes")
@CrossOrigin
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteService.getReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscarReporte(@PathVariable Long id) {
        return reporteService.getReporteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reporte agregarReporte(@RequestBody Reporte reporte) {
        return reporteService.saveReporte(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizarReporte(@PathVariable Long id, @RequestBody Reporte reporte) {
        if (!reporteService.getReporteById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporte.setId(id); // Asegurarse de que el ID sea correcto
        Reporte actualizado = reporteService.updateReporte(reporte);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        if (!reporteService.getReporteById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reporteService.deleteReporte(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public long totalReportes() {
        return reporteService.totalReportes();
    }
}
