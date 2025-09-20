package com.microservice.venta.client;

import com.microservice.venta.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-producto", path = "http://localhost:8003/api/v1/productos")
public interface ProductoClient {

    @GetMapping("/{id}")
    ProductoDTO getProductoById(@PathVariable("id") Long id);
}
