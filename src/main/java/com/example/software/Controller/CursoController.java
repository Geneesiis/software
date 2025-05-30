package com.example.EduTech.Controller;

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

import com.example.EduTech.Model.Curso;
import com.example.EduTech.Service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService CursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return CursoService.getCursos();
    }

    @PostMapping
    public Curso agregarCurso(@RequestBody Curso curso) {
        return CursoService.saveCurso(curso);
    }

    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable int id){
        return CursoService.getCursoId(id);
    }

    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso){
        // el id lo usaremos mas adelante
        return CursoService.updateCurso(curso);
    }

    @DeleteMapping("{id}")
    public String eliminarCurso(@PathVariable int id) {
        return CursoService.deleteCurso(id);
    }


    @GetMapping("/total")
    public int totalCursosV2() {
        return CursoService.totalCursosV2();
    }
}
