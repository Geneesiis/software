package com.example.software.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.software.Model.Reporte;
import com.example.software.Service.ReporteService;


@RestController
@RequestMapping("/api/v2/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteService.getReporte();
    }
    
        @PostMapping
    public Reporte agregarReporte (@RequestBody Reporte reporte) {
        return reporteService.saveReporte(reporte);
    }

    @GetMapping("{id}")
    public Reporte buscarReporte(@PathVariable int id){
        return reporteService.getReporteId(id);
    }

    @PutMapping("{id}")
    public Reporte actualizarReporte(@PathVariable int id, @RequestBody Reporte reporte){
        // el id lo usaremos mas adelante
        return reporteService.updateReporte(reporte);
    }

    @DeleteMapping("{id}")
    public String eliminarReporte(@PathVariable int id) {
        return reporteService.deleteReporte(id);
    }


    @GetMapping("/total")
    public int totalReportesV2() {
        return reporteService.totalReportesV2();
    }
    
}
