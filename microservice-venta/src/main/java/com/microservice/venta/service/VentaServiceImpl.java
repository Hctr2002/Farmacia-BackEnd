package com.microservice.venta.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.venta.client.ProductoClient;
import com.microservice.venta.client.UsuarioClient;
import com.microservice.venta.dto.VentaDTO;
import com.microservice.venta.model.Venta;
import com.microservice.venta.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ProductoClient productoClient;

    private VentaDTO mapToDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId(venta.getId());
        dto.setUsuarioId(venta.getUsuarioId());
        dto.setProductoId(venta.getProductoId());
        dto.setCantidad(venta.getCantidad());
        dto.setFecha(venta.getFecha());
        dto.setTotal(venta.getTotal());
        return dto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        try {
            usuarioClient.getUsuarioById(ventaDTO.getUsuarioId());
        } catch (feign.FeignException.NotFound e) {
            throw new RuntimeException("El usuario con id " + ventaDTO.getUsuarioId() + " no existe");
        }

        Float precioProducto;
        try {
            var producto = productoClient.getProductoById(ventaDTO.getProductoId());
            precioProducto = producto.getPrecio();
        } catch (feign.FeignException.NotFound e) {
            throw new RuntimeException("El producto con id " + ventaDTO.getProductoId() + " no existe");
        }

        Float totalCalculado = precioProducto * ventaDTO.getCantidad();

        Venta venta = Venta.builder()
                .usuarioId(ventaDTO.getUsuarioId())
                .productoId(ventaDTO.getProductoId())
                .cantidad(ventaDTO.getCantidad())
                .fecha(LocalDateTime.now())
                .total(totalCalculado)
                .build();

        Venta guardada = repository.save(venta);

        return mapToDTO(guardada);
    }


    @Override
    public VentaDTO obtenerVenta(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null); 
    }

    @Override
    public List<VentaDTO> listarVentas() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        return repository.findById(id)
                .map(existingVenta -> {
                    existingVenta.setUsuarioId(ventaDTO.getUsuarioId());
                    existingVenta.setProductoId(ventaDTO.getProductoId());
                    existingVenta.setCantidad(ventaDTO.getCantidad());
                    existingVenta.setTotal(ventaDTO.getTotal());
                    return mapToDTO(repository.save(existingVenta));
                })
                .orElse(null);
    }

    @Override
    public void eliminarVenta(Long id) {
        repository.deleteById(id);
    }

}
