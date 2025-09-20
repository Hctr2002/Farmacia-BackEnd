package com.microservice.venta.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.venta.dto.VentaDTO;
import com.microservice.venta.model.Venta;
import com.microservice.venta.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repository;

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

    private Venta mapToEntity(VentaDTO dto) {
        return Venta.builder()
                .id(dto.getId())
                .usuarioId(dto.getUsuarioId())
                .productoId(dto.getProductoId())
                .cantidad(dto.getCantidad())
                .total(dto.getTotal())
                .fecha(dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now())
                .build();   
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        Venta venta = repository.save(mapToEntity(ventaDTO));
        return mapToDTO(venta);
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
