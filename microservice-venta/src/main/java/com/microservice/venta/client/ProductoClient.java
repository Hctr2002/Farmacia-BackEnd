package com.microservice.venta.client;

import com.microservice.venta.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-producto", path = "/api/v1/productos")
public interface ProductoClient {

    @GetMapping("/{id}")
    ProductoDTO getProductoById(@PathVariable("id") Long id);

    @PutMapping("/{id}/reducir-stock")
    ProductoDTO reducirStock(@PathVariable("id") Long id, @RequestParam("cantidad") int cantidad);
}

