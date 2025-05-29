package com.uni.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.biblioteca.model.Usuario;
import com.uni.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Usuario registrarUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(int id){
        return usuarioRepo.findById(id);
    }

    public Usuario buscarPorCorreo(String correo){
        return usuarioRepo.findByCorreo(correo);
    }
}
