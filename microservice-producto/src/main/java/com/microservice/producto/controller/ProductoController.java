package com.microservice.producto.controller;

import com.microservice.producto.dto.ProductoDTO;
import com.microservice.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @PostMapping
    public ProductoDTO crearProducto(@RequestBody ProductoDTO productoDTO) {
        return service.crearProducto(productoDTO);
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerProducto(@PathVariable Long id) {
        return service.obtenerProducto(id);
    }

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return service.listarProductos();
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return service.actualizarProducto(id, productoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
    }
}
