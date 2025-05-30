package com.example.software.Service;

import  java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.software.Model.Reporte;
import com.example.software.Repository.ReporteRepository;


@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> getReporte() {
        return reporteRepository.obtenerReporte();
    }

    public Reporte saveReporte (Reporte reporte) {
        return reporteRepository.guardar(reporte);
    }

    public Reporte getReporteId(int id) {
        return reporteRepository.buscarPorId(id);
    }

    public Reporte updateReporte (Reporte reporte) {
        return reporteRepository.actualizar(reporte);
    }

    public String deleteReporte(int id) {
        reporteRepository.eliminar(id);
        return "producto eliminado";
    }

    // LA ACCIÓN LA HACE EL SERVICE
    public int totalReportes() {
        return reporteRepository.obtenerReporte().size();
    }

    // LA ACCIÓN LA HACE EL MODELO
    public int totalReportesV2() {
        return reporteRepository.totalReportes();
    }
}
