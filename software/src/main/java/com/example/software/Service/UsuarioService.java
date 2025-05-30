package com.example.software.Service;

import com.example.software.Model.Usuario;
import com.example.software.Repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository rep;

    public Usuario registrar(Usuario u){
        return rep.save(u);
    }


    public Optional<Usuario> autenticar(String email, String password){
        return rep.findByEmail(email).filter(u->u.getPassword().equals(password));
    }
}
