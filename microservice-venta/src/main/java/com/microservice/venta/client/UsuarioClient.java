package com.microservice.venta.client;

import com.microservice.venta.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", url = "http://localhost:8002/api/v1/usuarios")
public interface UsuarioClient {

    @GetMapping("/{id}")
    UsuarioDTO getUsuarioById(@PathVariable("id") Long id);
}
