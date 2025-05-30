package com.example.EduTech.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EduTech.Model.Curso;
import com.example.EduTech.Repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository CursoRepository;

    public List<Curso> getCursos() {
        return CursoRepository.obtenerCursos();
    }

    public Curso saveCurso(Curso curso) {
        return CursoRepository.guardar(curso);
    }

    public Curso getCursoId(int id) {
        return CursoRepository.buscarPorId(id);
    }

    public Curso updateCurso(Curso curso) {
        return CursoRepository.actualizar(curso);
    }

    public String deleteCurso(int id) {
        CursoRepository.eliminar(id);
        return "producto eliminado";
    }

    // LA ACCIÓN LA HACE EL SERVICE
    public int totalCursos() {
        return CursoRepository.obtenerCursos().size();
    }

    // LA ACCIÓN LA HACE EL MODELO
    public int totalCursosV2() {
        return CursoRepository.totalCursos();
    }
}
