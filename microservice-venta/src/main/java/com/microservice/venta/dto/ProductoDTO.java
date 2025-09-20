package com.microservice.venta.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer stock;

}
