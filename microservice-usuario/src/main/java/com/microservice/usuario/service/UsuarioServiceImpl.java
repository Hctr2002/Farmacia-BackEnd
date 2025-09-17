package com.microservice.usuario.service;

import com.microservice.usuario.dto.UsuarioDTO;
import com.microservice.usuario.model.Usuario;
import com.microservice.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private UsuarioDTO mapToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setPassword(usuario.getPassword());
        dto.setRol(usuario.getRol());
        return dto;
    }

    private Usuario mapToEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .rol(dto.getRol())
                .build();
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = repository.save(mapToEntity(usuarioDTO));
        return mapToDTO(usuario);
    }

    @Override
    public UsuarioDTO obtenerUsuario(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        return repository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setRol(usuarioDTO.getRol());
            Usuario actualizado = repository.save(usuario);
            return mapToDTO(actualizado);
        }).orElse(null);
    }

}

