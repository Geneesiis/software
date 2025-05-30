package com.example.EduTech.Repository;
import  java.util.List;

import org.springframework.stereotype.Repository;

import  java.util.ArrayList;

import com.example.EduTech.Model.Reporte;

@Repository
public class ReporteRepository {
    private List<Reporte> listaReportes = new ArrayList<>();
    //agregar reporte de incidencia
    public ReporteRepository() {
        listaReportes.add(new Reporte(1,"x", "esta mojado", "me moje"));
        
    }

    public List<Reporte> obtenerReporte() {
        return listaReportes;
    }

    public Reporte buscarPorId(int id) {
        for (Reporte reporte : listaReportes) {
            if (reporte.getId() == id) {
                return reporte;
            }
        }
        return null;
    }

    public Reporte buscarPorRamo(String usuarioReporta) {
        for (Reporte reporte : listaReportes) {
            if (reporte.getUsuarioReporta().equals(usuarioReporta)) {
                return reporte;
            }
        }
        return null;
    }

    public Reporte guardar(Reporte reporte) {
        // Generar nuevo ID secuencial
        long nuevoId = 1;
        for (Reporte l : listaReportes) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        Reporte reporte1 = new Reporte();
    reporte1.setId((int) nuevoId); // ID generado automáticamente editar aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    reporte1.setUsuarioReporta(reporte.getUsuarioReporta());
    reporte1.setTitulo(reporte.getTitulo());
    reporte1.setDescripcion(reporte.getDescripcion());


    // Agregar el nuevo Reporte a la lista
    listaReportes.add(reporte1);

    return reporte1;
}

public Reporte actualizar(Reporte cur) {
    int id = 0;
    int idPosicion = 0;

    for (int i = 0; i < listaReportes.size(); i++) {
        if (listaReportes.get(i).getId() == cur.getId()) {
            id = cur.getId();
            idPosicion = i;
        }
    }

    Reporte Reporte1 = new Reporte();
    Reporte1.setId(id);
    Reporte1.setUsuarioReporta(cur.getUsuarioReporta());
    Reporte1.setTitulo(cur.getTitulo());
    Reporte1.setDescripcion(cur.getDescripcion());
 

    listaReportes.set(idPosicion, Reporte1);
    return Reporte1;
}

public void eliminar(int id) {

    listaReportes.removeIf(x -> x.getId() == id);
}

public int totalReportes() {
    return listaReportes.size();
}

}
