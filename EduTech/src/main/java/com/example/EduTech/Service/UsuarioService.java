package com.example.EduTech.Service;

import com.example.EduTech.Model.Usuario;
import com.example.EduTech.Repository.UsusarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsusarioRepository rep;

    public Usuario registrar(Usuario u){
        return rep.save(u);
    }


    public Optional<Usuario> autenticar(String email, String password){
        return rep.findByEmail(email).filter(u->u.getPassword().equals(password));
    }
}
