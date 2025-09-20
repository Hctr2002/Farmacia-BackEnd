package com.microservice.venta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.venta.dto.VentaDTO;
import com.microservice.venta.service.VentaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping
    public VentaDTO crearVenta(@RequestBody VentaDTO ventaDTO) {
        return service.crearVenta(ventaDTO);
    }

    @GetMapping("/{id}")
    public VentaDTO obtenerVenta(@PathVariable Long id) {
        return service.obtenerVenta(id);
    }

    @GetMapping
    public List<VentaDTO> listarVentas() {
        return service.listarVentas();
    }

    @PutMapping("/{id}")
    public VentaDTO actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        return service.actualizarVenta(id, ventaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        service.eliminarVenta(id);
    }
    

}
