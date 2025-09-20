package com.microservice.venta.service;

import java.util.List;
import com.microservice.venta.dto.VentaDTO;

public interface VentaService {
    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO obtenerVenta(Long id);
    List<VentaDTO> listarVentas();
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);
    void eliminarVenta(Long id);
}
