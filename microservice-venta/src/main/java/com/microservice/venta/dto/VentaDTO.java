package com.microservice.venta.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VentaDTO {

    private Long id;
    private Long usuarioId;
    private Long productoId;
    private Integer cantidad;
    private LocalDateTime fecha;
    private Float total;

}
