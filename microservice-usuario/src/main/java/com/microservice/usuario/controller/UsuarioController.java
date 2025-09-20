package com.microservice.usuario.controller;

import com.microservice.usuario.dto.UsuarioDTO;
import com.microservice.usuario.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return service.crearUsuario(usuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        UsuarioDTO usuario = service.obtenerUsuario(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(usuario);
    }


    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return service.listarUsuarios();
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        service.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return service.actualizarUsuario(id, usuarioDTO);
    }

}

