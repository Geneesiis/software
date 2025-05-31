package com.example.software.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.software.Model.Reporte;
import com.example.software.Repository.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> getReportes() {
        return reporteRepository.findAll();
    }

    public Reporte saveReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Optional<Reporte> getReporteById(Long id) {
        return reporteRepository.findById(id);
    }

    public Reporte updateReporte(Reporte reporte) {
        if (reporteRepository.existsById(reporte.getId())) {
            return reporteRepository.save(reporte);
        }
        return null; // O lanza una excepción personalizada
    }

    public String deleteReporte(Long id) {
        if (reporteRepository.existsById(id)) {
            reporteRepository.deleteById(id);
            return "Reporte eliminado";
        } else {
            return "Reporte no encontrado";
        }
    }

    public long totalReportes() {
        return reporteRepository.count();
    }

    public Optional<Reporte> getByUsuarioReporta(String usuarioReporta) {
        return reporteRepository.findByUsuarioReporta(usuarioReporta);
    }
}
