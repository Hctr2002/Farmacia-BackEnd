package com.microservice.usuario.service;

import com.microservice.usuario.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuario(Long id);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);
    void eliminarUsuario(Long id);
}

