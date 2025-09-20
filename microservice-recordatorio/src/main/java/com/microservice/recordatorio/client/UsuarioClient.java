package com.microservice.recordatorio.client;

import com.microservice.recordatorio.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-usuario", path = "/api/v1/usuarios")
public interface UsuarioClient {

    @GetMapping("/{id}")
    UsuarioDTO getUsuarioById(@PathVariable("id") Long id);
}
