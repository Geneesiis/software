package com.example.software.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.software.Model.Usuario;
import com.example.software.Service.UsuarioService;


@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private UsuarioService servi;
    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario  u) {
        
        return servi.registrar(u);
    }

    @PostMapping("/login")
    public Map<String, String> login (@RequestBody Usuario u) {
        Optional<Usuario> user = servi.autenticar(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
        }else{
            response.put("result", "ERROR");
        }
        return response;
    }

        @GetMapping
    public List<Usuario> listarUsuarios() {
        return servi.listarUsuarios();
    }

    
    
}
